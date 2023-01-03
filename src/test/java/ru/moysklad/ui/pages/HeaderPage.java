package ru.moysklad.ui.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HeaderPage {
    public HeaderPage clickOnHelp(){
        $(".help-img-new").click();
        return this;
    }

    public HeaderPage exit(){
        $(".menu-username-new").click();
        $$(".gwt-MenuBar").findBy(visible).find(byText("Выход")).click();
        return this;

    }
}