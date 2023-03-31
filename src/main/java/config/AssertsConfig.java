package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface AssertsConfig extends Config{
    String expectedFirstName();

    String expectedSecondName();

    String expectedPostCode();

    String errorMessage();
}
