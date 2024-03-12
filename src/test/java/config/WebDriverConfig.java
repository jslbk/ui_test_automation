package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties",
})
public interface WebDriverConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://www.bluorbank.lv")
    String getBaseUrl();

    @Key("browserName")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String getRemoteUrl();

    @DefaultValue("false")
    Boolean isRemote();

    @Key("consentCookieValue")
    @DefaultValue("07ed8f97-cedf-ee11-ab66-00505689f2e4")
    String getBankConsentCookieValue();

}