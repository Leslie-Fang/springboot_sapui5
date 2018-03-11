package org.intel.dcg.leslie.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextsUtil implements ApplicationContextAware{
    final Logger logger = LoggerFactory.getLogger(SpringContextsUtil.class);

    private static ApplicationContext applicationContext;

    public SpringContextsUtil(){
        logger.debug("Loading SpringContextsUtil");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("Inject ApplicationContext: {} into SpringContextsUtil", applicationContext);
        SpringContextsUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

}
