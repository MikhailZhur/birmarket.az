package tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WebTests extends BaseTest {

    @Test
    @DisplayName("Логотип 'birmarket' отображается на главной странице")
    @Tag("WEB")
    void logoIsDisplayedOnMainPage() {
        step("Open birmarket", () -> open("https://birmarket.az/ru"));

        step("Check", () -> {
            $("[alt='Birmarket.az']").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("При нажатии 'Регистрация вход' переход на страницу ЛК")
    @Tag("WEB")
    void byClickRegistrationButtonWillTakenAuthenticationPage() {
        open("https://birmarket.az/ru");
        $$(".i-close").get(1).click();
        $(".i-user").click();
        $(".PhoneNumber").shouldBe(visible);
    }

    @Test
    @DisplayName("Товар добавляется в таблицу")
    @Tags({@Tag("WEB"),
            @Tag("E2E")})
    void addingProductToTheTable() {

        step("Открыть браузер", () ->
                open("https://birmarket.az/ru"));

//        step("Закрыть попап 'Выбрать город'", () ->
//                $$(".i-close").get(1).click());

        step("Ввести в поисковой строке 'Холодильник бирюзовый'", () -> {
            $("[type='search']").click();
            $("[type='search']").setValue("Холодильник бирюзовый");
        });

        step("Нажать 'Искать'", () ->
                $(".search-dropdown-item-body").click());

        step("Добавить холодильник в корзину", () ->
                $$(".AddToCart").find(text("В корзину")).click());

        step("Закрыть попап", () ->
                $$(".i-close").get(2).click());
        step("Перейти в корзину", () ->
                $("[href='/ru/cart']").click());
        step("Добавить холодильник в корзину", () ->
                $(".AddToCart").click());
        step("Проверяем что корзина не пустая", () ->
                $$(".CartItemSeller-Products").shouldBe(CollectionCondition.sizeGreaterThan(0)));
    }
}
