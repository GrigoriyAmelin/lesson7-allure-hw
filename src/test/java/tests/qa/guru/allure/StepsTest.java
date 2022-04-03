package tests.qa.guru.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final Integer ISSUE_NUMBER = 68;

    @Test
    public void testLambdaSteps() {

        SelenideLogger.addListener("allire", new AllureSelenide());

        step("открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").click();
            sleep(1000);
            $(".header-search-input").submit();
        });
        step("Открываем репозиторий  " + REPOSITORY, () -> {
            $(byLinkText(REPOSITORY)).click();
            sleep(1000);
        });
        step("Переходим в таб Issues", () -> {
            $(byPartialLinkText("Issues")).click();
            sleep(2000);
        });
        step("Проверяем, что Issue с номеном " + ISSUE_NUMBER + " существует", () -> {
            $(withText("#" + ISSUE_NUMBER)).should(exist);
        });
    }
}