package fsoft.fsu11.bu11.tcs.commons.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class TcsLocalEntityManagerFactoryBean extends LocalEntityManagerFactoryBean {

    private static final Logger log =
            LoggerFactory.getLogger(TcsLocalEntityManagerFactoryBean.class);

    @Resource(name = "tcsStructureConfig")
    protected TcsStructureConfig config;

    @Override
    public Map<String, Object> getJpaPropertyMap() {

        Map<String, Object> jpaPropertyMap = super.getJpaPropertyMap();

        if (log.isDebugEnabled()) {
            log.debug("initial JPA properties " + jpaPropertyMap);
        }

        Properties hibernateProperties = config.getHibernateProperties();

        CollectionUtils.mergePropertiesIntoMap(hibernateProperties, jpaPropertyMap);

        if (log.isDebugEnabled()) {
            log.debug("JPA properties " + jpaPropertyMap);
        }

        return jpaPropertyMap;
    }
}
