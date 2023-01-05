package config;

import org.aeonbits.owner.Config;
@Config.Sources({
        "classpath:properties/api.properties"

})
public interface ApiConfig extends Config {

    @Config.Key("token")
    String token();


}
