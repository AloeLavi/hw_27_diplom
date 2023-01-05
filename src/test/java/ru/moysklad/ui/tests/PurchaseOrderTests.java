package ru.moysklad.ui.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.moysklad.ui.pages.*;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class PurchaseOrderTests extends UiTestBase {
    PurchaseOrderList purchaseOrderList = new PurchaseOrderList();
    PurchaseOrderPage purchaseOrderPage = new PurchaseOrderPage();
    HeaderPage header = new HeaderPage();
    @Test
    @DisplayName("Создание заказа поставщику с заполненными обязательными полями")
    void createPurchaseOrderWithRequiredFields(){
        step("Открыть реестр Заказов поставщику", () -> {
            purchaseOrderList.openList();
        });
        step("Нажать +Заказ", () -> {
            purchaseOrderList.openNew();
        });
        step("Заполнить поставщика", () -> {
            purchaseOrderPage.setCounterparty("ООО \"Поставщик\"");
        });
        step("Cохранить документ", () -> {
            purchaseOrderPage.saveDocument()
                    .checkDialogMiddleCenter("Заказ создан");
        });
        step("Закрыть документ", () -> {
            purchaseOrderPage.closeDocument();
            sleep(2000);
        });
        step("В реестре проверить наличие Заказа поставщику 00001", () -> {
            purchaseOrderList.checkDocumentExistanceByNumber("00001");
        });

    }



    @DisplayName("Копирование заказа поставщику")
    @Test
    void copyPurchaseOrder(){
        step("Открыть реестр Заказов поставщику", () -> {
            purchaseOrderList.openList();
        });
        step("Нажать +Заказ", () -> {
            purchaseOrderList.openNew();
        });
        step("Заполнить поставщика", () -> {
            purchaseOrderPage.setCounterparty("ООО \"Поставщик\"");
        });
        step("Cохранить документ", () -> {
            purchaseOrderPage.saveDocument()
                    .checkDialogMiddleCenter("Заказ создан");
        });
        step("Нажать Изменить -> Копировать", () -> {
            purchaseOrderPage.copyDocument();
            purchaseOrderPage.checkDialogMiddleCenter("Заказ скопирован");
        });
        step("В реестре проверить наличие Заказов поставщику 00001, 00002", () -> {
            purchaseOrderPage.closeDocument();
            purchaseOrderList.checkDocumentExistanceByNumber("00001");
            purchaseOrderList.checkDocumentExistanceByNumber("00002");        });

    }

    @DisplayName("Попытка создания заказа поставщику без обязательных полей")
    @Test
    void createPurchaseOrderWithoutRequiredFields() {
        step("Открыть реестр Заказов поставщику", () -> {
            purchaseOrderList.openList();
        });
        step("Нажать +Заказ", () -> {
            purchaseOrderList.openNew();
        });

        step("Не заполняя поля, сохранить документ", () -> {
            purchaseOrderPage.saveDocument();
            $(byText("Поле должно быть заполнено")).should(exist);
        });

        step("Заполнить поставщика", () -> {
            purchaseOrderPage.setCounterparty("ООО \"Поставщик\"");
        });
        step("Cохранить документ", () -> {
            purchaseOrderPage.saveDocument()
                    .checkDialogMiddleCenter("Заказ создан");
        });
        step("В реестре проверить наличие Заказа поставщику 00001", () -> {
            purchaseOrderPage.closeDocument();
            purchaseOrderList.checkDocumentExistanceByNumber("00001");        });


    }

    @AfterEach
    void cleanAll(){
        step("Удалить все заказы поставщику", () -> {
            purchaseOrderList.openList();
            purchaseOrderList.DeleteAllDocuments();
            header.exit();
        });

    }
}
