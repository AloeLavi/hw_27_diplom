package ru.moysklad.browser.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SalesOrderList {
    public SalesOrderList openList(){
        open("/app/#customerorder");
        return this;
    }
    public SalesOrderList openNew(){
        $(byText("Заказ")).click();
          $("[data-react-widget='customerOrderEditBanner']").shouldHave(text("Заказ покупателя"));
        return this;

    }

    public SalesOrderList checkDocumentExistanceByNumber(String documentNumber)
    {
        $("#DocumentTableCustomerOrder").$(".tutorial-document-table-body").shouldHave(text(documentNumber));
        return this;

    }

    public SalesOrderList DeleteAllDocuments(){
        $("#DocumentTableCustomerOrder").$("thead").$("tr", 1).$("th",0).click();
        $(".pump-title-panel").$(byText("Изменить")).click();
        $(byText("Удалить")).click();
        return this;

    }
}
