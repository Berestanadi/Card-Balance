package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.valueOf;

public class TransferPage {
    private SelenideElement sum = $("[data-test-id=amount] input");
    private SelenideElement whereFrom = $("[data-test-id=from] input");
    private SelenideElement topUp = $("[data-test-id=action-transfer]");


    public void transferMoney(int amount, DataHelper.CardsInfo from) {
        sum.setValue(valueOf(amount));
        whereFrom.setValue(String.valueOf(from));
        topUp.click();
        new CardBalancePage();
    }

    public void errorLimit() {
        $(".notification__content").should(Condition.exactText("Ошибка"));
    }

    public void invalidCard() {
        $(".notification__content").should(Condition.text("Ошибка! Произошла ошибка"));
    }
}