package fsoft.fsu11.bu11.tcs.commons.utils;

import java.io.File;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import ch.qos.logback.classic.LoggerContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/19/14
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogbackConfigListener  implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(LogbackConfigListener.class);

    protected static final String CONFIG_LOCATION_PARAM = "logbackConfigLocation";
    protected static final String DEFAULT_LOCATION_PATH = "D:\\config\\logback.xml";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        String path = sce.getServletContext().getInitParameter(CONFIG_LOCATION_PARAM);
        File externalConfigFile = new File(path);
        File defaultConfigFile = new File(DEFAULT_LOCATION_PATH);
        if (externalConfigFile.exists() && externalConfigFile.isFile() && externalConfigFile.canRead()) {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(loggerContext);
            loggerContext.reset();
            try {
                // Configure logging
                configurator.doConfigure(path);

                // Install jul bridge
                SLF4JBridgeHandler.install();

                log.info("Configured Logback with config file from: " + path);
            } catch (JoranException ex) {
                throw new RuntimeException("Logback External Configuration failed, cause: " + ex.getMessage(), ex);
            }

        } if (defaultConfigFile.exists() && defaultConfigFile.isFile() && defaultConfigFile.canRead()) {
            //Default config file is in /etc/toshiba-cloud/logback-bom.xml, before load classpath one.
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(loggerContext);
            loggerContext.reset();
            try {
                // Configure logging
                configurator.doConfigure(DEFAULT_LOCATION_PATH);

                // Install jul bridge
                SLF4JBridgeHandler.install();

                log.info("Configured Logback with config file from: " + DEFAULT_LOCATION_PATH);
            } catch (JoranException ex) {
                throw new RuntimeException("Logback External Configuration failed, cause: " + ex.getMessage(), ex);
            }
        } else {
            if (log.isErrorEnabled()) {
                log.error("Unable to retrieve or read logback configuration file from path : " + path + ". Use default config.");
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Uninstall jul bridge
        SLF4JBridgeHandler.uninstall();
    }
}
