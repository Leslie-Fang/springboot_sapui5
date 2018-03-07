package org.intel.dcg.leslie.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;


@Configuration
public class CxfServletRegister {
    @Bean
    public ServletRegistrationBean getODataServletRegistrationBean() {
        ServletRegistrationBean odataServletRegistrationBean = new ServletRegistrationBean(new CXFNonSpringJaxrsServlet(), "/MyODataSample.svc/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("javax.ws.rs.Application", "org.apache.olingo.odata2.core.rest.app.ODataApplication");
        initParameters.put("org.apache.olingo.odata2.service.factory", "org.intel.dcg.leslie.utils.JPAServiceFactory");
        odataServletRegistrationBean.setInitParameters(initParameters);
        return odataServletRegistrationBean;
    }
}