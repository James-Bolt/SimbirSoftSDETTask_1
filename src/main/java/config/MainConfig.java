package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface MainConfig extends Config {
    String url();

    String driverPath();

    String driverProperty();

    String slf4JFixProperty();

    String slf4JFix();
}
