package common.config;


import org.aeonbits.owner.Config;

@Config.HotReload
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties", "classpath:properties/common.properties"
})
public interface CoreConfig extends Config {

    String env();

    @Key("rest.assured.logging.enabled")
    Boolean restAssuredLoggingEnabled();

    @Key("is.remote")
    Boolean isRemote();

    @Key("browser")
    String browser();

    @Key("default.timeout")
    Integer defaultTimeout();

    @Key("wait.accept.cookies.timeout")
    Integer waitAcceptCookiesTimeout();

    @Key("hub.url")
    String hubUrl();

    @Key("${env}.base.web.url")
    String baseWebUrl();

    @Key("${env}.base.api.url")
    String baseApiUrl();
}
