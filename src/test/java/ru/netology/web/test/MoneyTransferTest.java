package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardBalancePage;
import ru.netology.web.page.LoginPageV2;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.getFirstCardNumber;
import static ru.netology.web.data.DataHelper.getSecondCardNumber;
import static ru.netology.web.page.CardBalancePage.pushFirstCardId;
import static ru.netology.web.page.CardBalancePage.pushSecondCardId;

class MoneyTransferTest {

  @BeforeEach
          void set() {
    open("http://localhost:9999");
    val loginPage = new LoginPageV2();
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    val cardBalancePage = verificationPage.validVerify(verificationCode);
  }
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
      val cardBalancePage = new CardBalancePage();
      int amount = 5000;
      val transferPage = pushSecondCardId();
      transferPage.transferMoney(amount, getFirstCardNumber());
      val firstCardBalanceFinal = cardBalancePage.getFirstCardBalance();
      val secondCardBalanceFinal = cardBalancePage.getSecondCardBalance();
      assertEquals(firstCardBalanceFinal, cardBalancePage.getFirstCardBalance());
      assertEquals(secondCardBalanceFinal, cardBalancePage.getSecondCardBalance());
    }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV2() {
    val cardBalancePage = new CardBalancePage();
    int amount = 3000;
    val transferPage = pushFirstCardId();
    transferPage.transferMoney(amount, getSecondCardNumber());
    val firstCardBalanceFinal = cardBalancePage.getFirstCardBalance();
    val secondCardBalanceFinal = cardBalancePage.getSecondCardBalance();
    assertEquals(firstCardBalanceFinal, cardBalancePage.getFirstCardBalance());
    assertEquals(secondCardBalanceFinal, cardBalancePage.getSecondCardBalance());
  }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV3() {
    val cardBalancePage = new CardBalancePage();
    int amount = 30000;
    val transferPage = pushFirstCardId();
    transferPage.transferMoney(amount, getSecondCardNumber());
    val firstCardBalanceFinal = cardBalancePage.getFirstCardBalance();
    val secondCardBalanceFinal = cardBalancePage.getSecondCardBalance();
    assertEquals(firstCardBalanceFinal, cardBalancePage.getFirstCardBalance());
    assertEquals(secondCardBalanceFinal, cardBalancePage.getSecondCardBalance());
  }
}

