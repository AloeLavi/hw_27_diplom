package ru.moysklad.browser.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SalesOrderPage {


    public SalesOrderPage setCounterparty(String counterparty) {
        sleep(1000);
        $("[data-test-id='SystemFields.sourceAgent']").click();
        $(withText(counterparty)).click();
        return this;
        }
    public SalesOrderPage saveDocument(){
        $("[data-test-id='editor-toolbar-save-button']").click();
        return this;

    }
    public SalesOrderPage closeDocument(){
        $("[data-test-id='editor-toolbar-close-button']").click();
        return this;

    }

    public SalesOrderPage copyDocument(){
        $$("[type=button]").first().click();
        $(byText("Копировать")).click();
        return this;

    }

    public SalesOrderPage checkDialogMiddleCenter(String message){
        $(".dialogMiddleCenter").shouldHave(text(message));
        return this;


    }

}
