package config;

import org.aeonbits.owner.Config;



@Config.Sources({
        "classpath:properties/browser.properties"

})

public interface BrowserConfig extends Config {
    @Key("user")
    String user();

    @Key("password")
    String password();


}
