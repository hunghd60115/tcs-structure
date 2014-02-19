package fsoft.fsu11.bu11.tcs.commons.conf;


import org.nuiton.config.ApplicationConfig;
import org.nuiton.config.ArgumentsParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/15/14
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class TcsStructureConfig {

    protected ApplicationConfig applicationConfig = null;

    private static final Logger log = LoggerFactory.getLogger(TcsStructureConfig.class);

    public TcsStructureConfig(String configFileName) {
        applicationConfig = new ApplicationConfig();
        applicationConfig.setOption(ApplicationConfig.CONFIG_PATH, "D:\\config");
        applicationConfig.setConfigFileName(configFileName);
        applicationConfig.loadDefaultOptions(TcsStructureConfigOption.values());
        try {
            applicationConfig.parse();
        } catch (ArgumentsParserException e) {
            throw new RuntimeException(e);
        }
        if (log.isDebugEnabled()) {
            log.debug("parsed options in config file" + applicationConfig.getOptions());
        }

        if (log.isInfoEnabled()) {
            String toPrint = applicationConfig.getPrintableConfig(null, 50);
            log.info(toPrint);
        }
    }

    public TcsStructureConfig() {
    }

    public Properties getHibernateProperties() {
        return applicationConfig.getOptionStartsWith("hibernate");
    }

    public String getService1BaseUrl(){
        return applicationConfig.getOption(TcsStructureConfigOption.SERVICE1_BASE_URL.key);
    }

    public String getService1HealthCheckPath(){
        return applicationConfig.getOption(TcsStructureConfigOption.SERVICE1_HEALTH_CHECK_PATH.key);
    }

    public String getService1HealthCheckUrl(){
        return getService1BaseUrl() + getService1HealthCheckPath();
    }

    public String getService2BaseUrl(){
        return applicationConfig.getOption(TcsStructureConfigOption.SERVICE2_BASE_URL.key);
    }

    public String getService2HealthCheckPath(){
        return applicationConfig.getOption(TcsStructureConfigOption.SERVICE2_HEALTH_CHECK_PATH.key);
    }

    public String getService2HealthCheckUrl(){
        return getService2BaseUrl() + getService2HealthCheckPath();
    }
}
