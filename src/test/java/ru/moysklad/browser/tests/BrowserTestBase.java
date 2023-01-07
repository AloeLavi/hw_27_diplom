package ru.moysklad.browser.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.BrowserConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BrowserTestBase {

    @BeforeAll

    public static void configure() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        Configuration.baseUrl = "https://online.moysklad.ru";

        String browserName = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "100");
        String browserSize = System.getProperty("browserSize",  "1600x800");
        String remoteUrl = System.getProperty("remote",  "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        Configuration.browser= browserName;
        Configuration.browserVersion= browserVersion;
        Configuration.browserSize = browserSize;
        Configuration.holdBrowserOpen = false;
        if(remoteUrl!= null ){
           Configuration.remote = remoteUrl;
       }

    }



    @BeforeEach
    void logIn(){
        BrowserConfig config = ConfigFactory.create(BrowserConfig.class, System.getProperties());
        open();
        $("#lable-login").setValue(config.user());
        $("#lable-password").setValue(config.password());
        $("#submitButton").click();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }


}