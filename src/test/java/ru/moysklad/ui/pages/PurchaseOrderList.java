package ru.moysklad.ui.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PurchaseOrderList {
    public PurchaseOrderList openList(){
        open("/app/#purchaseorder");
        return this;
    }
    public PurchaseOrderList openNew(){
        $(byText("Заказ")).click();
        $(".operationNamePanel").shouldHave(text("Заказ поставщику"));
        return this;

    }
    public PurchaseOrderList checkDocumentExistanceByNumber(String documentNumber)
    {
        $("#DocumentTablePurchaseOrder").$(".tutorial-document-table-body").shouldHave(text(documentNumber));
        return this;

    }
    public PurchaseOrderList  DeleteAllDocuments(){
        $("#DocumentTablePurchaseOrder").$("thead").$("tr", 1).$("th",0).click();
        $(".pump-title-panel").$(byText("Изменить")).click();
        $(".popup-button-menu-bar").$$("tr").findBy(visible).click();
        return this;

    }
}