package ru.moysklad.browser.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.moysklad.browser.pages.HeaderPage;
import ru.moysklad.browser.pages.SalesOrderList;
import ru.moysklad.browser.pages.SalesOrderPage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

public class SalesOrderTests extends BrowserTestBase {

    SalesOrderPage salesOrderPage = new SalesOrderPage();
    SalesOrderList salesOrderList = new SalesOrderList();
    HeaderPage header = new HeaderPage();

    @Test
    @DisplayName("Создание заказа покупателя с заполненными обязательными полями")
    void createSalesOrderWithRequiredFields(){
        step("Открыть реестр Заказов покупателя", () -> {
            salesOrderList.openList();
        });
        step("Нажать +Заказ", () -> {
        salesOrderList.openNew();
        });

        step("Заполнить покупателя", () -> {
            salesOrderPage.setCounterparty("ООО \"Покупатель\"");
        });

        step("Cохранить документ", () -> {
            sleep(4000);
            salesOrderPage.saveDocument();
            sleep(4000);
            salesOrderPage.checkDialogMiddleCenter("Заказ создан");
        });
        step("Закрыть документ", () -> {
        salesOrderPage.closeDocument();
            sleep(2000);
        });
        step("В реестре проверить наличие Заказа покупателя 00001", () -> {
        salesOrderList.checkDocumentExistanceByNumber("00001");
        });
    }

    @DisplayName("Попытка создания заказа покупателя без обязательных полей")
    @Test
    void createSalesOrderWithoutRequiredFields() {
        step("Открыть реестр Заказов покупателя", () -> {
            salesOrderList.openList();
        });
        step("Нажать +Заказ", () -> {
            salesOrderList.openNew();
        });
        step("Не заполняя поля, сохранить документ", () -> {
        salesOrderPage.saveDocument();
     $(byText("Поле должно быть заполнено")).should(exist);
        });
        step("Заполнить покупателя", () -> {
            salesOrderPage.setCounterparty("ООО \"Покупатель\"");
        });
        step("Cохранить документ", () -> {
            sleep(4000);
            salesOrderPage.saveDocument();
            sleep(4000);

            salesOrderPage.checkDialogMiddleCenter("Заказ создан");
        });
        step("В реестре проверить наличие Заказа поставщику 00001", () -> {
            salesOrderPage.closeDocument();
        salesOrderList.checkDocumentExistanceByNumber("00001");
        });
    }

    @DisplayName("Копирование заказа покупателя")
        @Test
        void copySalesOrder(){
        step("Открыть реестр Заказов покупателя", () -> {
            salesOrderList.openList();
        });
        step("Нажать +Заказ", () -> {
            salesOrderList.openNew();
        });
        step("Заполнить покупателя", () -> {
            salesOrderPage.setCounterparty("ООО \"Покупатель\"");
        });
        step("Cохранить документ", () -> {
            sleep(4000);
            salesOrderPage.saveDocument();
            sleep(4000);

            salesOrderPage.checkDialogMiddleCenter("Заказ создан");
        });
        step("Нажать Изменить -> Копировать", () -> {
            sleep(4000);
            salesOrderPage.copyDocument();
            sleep(4000);
            salesOrderPage.checkDialogMiddleCenter("Заказ скопирован");
        });
        step("В реестре проверить наличие Заказов покупателя 00001, 00002", () -> {
            salesOrderList.openList();
            salesOrderList.checkDocumentExistanceByNumber("00001");
            salesOrderList.checkDocumentExistanceByNumber("00002");
        });
        }

    @AfterEach
    void cleanData(){
        salesOrderList.openList();
        salesOrderList.DeleteAllDocuments();
        header.exit();
    }
}
