package com.bonc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: ZhaoRuiXin
 * @Date: Create in 14:17 2021/1/7
 * @Version: 1.0
 * @File: category com.bonc.util
 * @Description:
 */
public class RequestUtil extends HttpServletRequestWrapper {
    private boolean filterXSS;
    private boolean filterSQL;

    public RequestUtil(HttpServletRequest request, boolean filterXSS, boolean filterSQL) {
        super(request);
        this.filterXSS = true;
        this.filterSQL = true;
        this.filterXSS = filterXSS;
        this.filterSQL = filterSQL;
    }

    public RequestUtil(HttpServletRequest request) {
        this(request, true, true);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        } else {
            int count = values.length;
            String[] encodedValues = new String[count];

            for(int i = 0; i < count; ++i) {
                encodedValues[i] = this.filterParamString(values[i]);
            }

            return encodedValues;
        }
    }

    public Map getParameterMap() {
        Map<String, String[]> primary = super.getParameterMap();
        Map<String, String[]> result = new HashMap(primary.size());
        Iterator var3 = primary.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, String[]> entry = (Map.Entry)var3.next();
            result.put(entry.getKey(), this.filterEntryString((String[])entry.getValue()));
        }

        return result;
    }

    protected String[] filterEntryString(String[] rawValue) {
        for(int i = 0; i < rawValue.length; ++i) {
            rawValue[i] = this.filterParamString(rawValue[i]);
        }

        return rawValue;
    }

    public String getParameter(String parameter) {
        return this.filterParamString(super.getParameter(parameter));
    }

    public String getHeader(String name) {
        return this.filterParamString(super.getHeader(name));
    }

    public Cookie[] getCookies() {
        Cookie[] existingCookies = super.getCookies();
        if (existingCookies != null) {
            for(int i = 0; i < existingCookies.length; ++i) {
                Cookie cookie = existingCookies[i];
                cookie.setValue(this.filterParamString(cookie.getValue()));
            }
        }

        return existingCookies;
    }

    protected String filterParamString(String rawValue) {
        if (null == rawValue) {
            return null;
        } else {
            String tmpStr = rawValue;
            if (this.filterXSS) {
                tmpStr = stripXSS(rawValue);
            }

            if (this.filterSQL) {
                tmpStr = stripSqlInjection(tmpStr);
            }

            return tmpStr;
        }
    }

    public static String stripXSS(String value) {
        String rlt = null;
        if (null != value) {
            rlt = value.replaceAll("", "");
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", 2);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
            scriptPattern = Pattern.compile("</script>", 2);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
            scriptPattern = Pattern.compile("<script(.*?)>", 42);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", 42);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", 42);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
            scriptPattern = Pattern.compile("javascript:", 2);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
            scriptPattern = Pattern.compile("vbscript:", 2);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
            scriptPattern = Pattern.compile("onload(.*?)=", 42);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
        }

        return rlt;
    }

    public static String stripSqlInjection(String value) {
        return null == value ? null : value.replaceAll("('.+--)|(--)|(%7C)", "");
    }

    public static String stripSqlXSS(String value) {
        return stripXSS(stripSqlInjection(value));
    }
}