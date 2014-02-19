package fsoft.fsu11.bu11.tcs.commons.conf;


import org.nuiton.config.ConfigOptionDef;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/15/14
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public enum TcsStructureConfigOption implements ConfigOptionDef {
    SERVICE2_BASE_URL(
            "service2.base.url",
            "Service 2 Base Url",
            "", String.class),
    SERVICE2_HEALTH_CHECK_PATH(
            "service2.health.check.path",
            "Service 2 health check path",
            "", String.class),
    SERVICE1_BASE_URL(
            "service1.base.url",
            "Service 1 Base Url",
            "", String.class),
    SERVICE1_HEALTH_CHECK_PATH(
            "service1.health.check.path",
            "Service 1 health check path",
            "", String.class)
    ;

    protected final String key;
    protected final String description;
    protected final Class<?> type;
    protected String defaultValue;

    private TcsStructureConfigOption(String key, String description,
                                             String defaultValue, Class<?> type) {
        this.key = key;
        this.description = description;
        this.defaultValue = defaultValue;
        this.type = type;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public boolean isTransient() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public void setTransient(boolean isTransient) {
        // Nothing to do
    }

    @Override
    public void setFinal(boolean isFinal) {
        // Nothing to do
    }
}
