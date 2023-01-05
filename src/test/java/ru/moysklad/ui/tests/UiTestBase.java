package ru.moysklad.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UiTestBase {

    @BeforeAll
    static void configure() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        Configuration.baseUrl = "https://online.moysklad.ru";


        /*
        Configuration.browserSize = "1440x1080";
     String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "100");
        String browserSize = System.getProperty("browserSize",  "1600x800");
        String remote = System.getProperty("remote",  "https://user1:1234@selenoid.autotests.cloud/wd/hub");


        Configuration.browser= browser;
        Configuration.browserVersion= browserVersion;
        Configuration.browserSize = browserSize;
        //    Configuration.holdBrowserOpen = true;
     Configuration.remote = remote; */
        Configuration.browserSize = "1600x800";
        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }

    @BeforeEach
    void logIn(){
        open("/");
        $("#lable-login").setValue("admin@vbagrova2");
        $("#lable-password").setValue("123123");
        $("#submitButton").click();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }


}