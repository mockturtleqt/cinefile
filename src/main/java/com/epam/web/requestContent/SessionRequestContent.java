package com.epam.web.requestContent;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, String[]> requestParameters = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    public void extractValues(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
        extractRequestAttributes(request);
        extractSessionAttributes(request);
    }

    public void insertAttributes(HttpServletRequest request) {

    }

    private void extractRequestAttributes(HttpServletRequest request) {
        Enumeration attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String)attributeNames.nextElement();
            Object value = request.getAttribute(name);
            requestAttributes.put(name, value);
        }
    }

    private void extractSessionAttributes(HttpServletRequest request) {
        Enumeration attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String)attributeNames.nextElement();
            Object value = request.getSession().getAttribute(name);
            sessionAttributes.put(name, value);
        }
    }
}
