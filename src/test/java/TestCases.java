import config.AssertsConfig;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TestCases extends MainTest {

    private final AssertsConfig config = ConfigFactory.create(AssertsConfig.class, System.getenv());

    @Test()
    @Description("Создание аккаунта")
    public void createAnAccountTest() {

        mainPage.bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("FirstName")
                .inputStrInSecondNameField("SecondName")
                .inputStrInPostCodeField("PostCode")
                .addCustomerButtonSecondClick();
        driver.switchTo().alert().accept();
        mainPage.homeButtonClick()
                .bankManagerLoginButtonClick()
                .customersButtonClick();

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(mainPage.firstNameContainerGetText(), "FirstName", config.errorMessage());
        softAssertion.assertEquals(mainPage.secondNameContainerGetText(), "SecondName", config.errorMessage());
        softAssertion.assertEquals(mainPage.postCodeContainerGetText(), "PostCode", config.errorMessage());
        softAssertion.assertAll();

        mainPage.deleteButtonClick();
    }

    @Test
    @Description("Добавление номеров счёта клиенту")
    public void addAccountNumbersTest() {

        mainPage.bankManagerLoginButtonClick()
                .openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick()
                .customerChooseFormClick()
                .currencyPoundClick()
                .processButtonClick()
                .customerChooseFormClick()
                .currencyRupeeClick()
                .processButtonClick()
                .customersButtonClick();

        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers, config.errorMessage());
    }

    @Test
    @Description("Вход в аккаунт клиента")
    public void enterToAccountTest() {

        mainPage.customerLoginButtonClick()
                .clientFieldButtonClick()
                .loginButtonClick();

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(mainPage.welcomeStringGetText(), "Welcome TestFirstName TestSecondName !!", config.errorMessage());
    }

    @Test
    @Description("Сортировка по имени")
    public void sortByNameTest() {

        mainPage.bankManagerLoginButtonClick()
                .customersButtonClick();

        ArrayList<String> expectedSort = mainPage.parseCustomers();
        expectedSort.sort(Collections.reverseOrder());
        mainPage.firstNameButtonClick();

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(mainPage.parseCustomers(), expectedSort, "Array sorted incorrectly");

        mainPage.firstNameButtonClick();
        Collections.sort(expectedSort);

        softAssertion.assertEquals(mainPage.parseCustomers(), expectedSort, "Array sorted incorrectly");
        softAssertion.assertAll();
    }

    @Test
    @Description("Поиск клиента по имени")
    public void clientSearchByFirstNameTest() {

        mainPage.bankManagerLoginButtonClick()
                .customersButtonClick()
                .inputStrInSearchCustomerField("Harry");

        Assert.assertEquals(mainPage.firstNameContainerGetText(), "Harry", config.errorMessage());
    }

    @Test
    @Description("Поиск клиента по фамилии")
    public void clientSearchBySecondNameTest() {

        mainPage.bankManagerLoginButtonClick()
                .customersButtonClick()
                .inputStrInSearchCustomerField("Potter");

        Assert.assertEquals(mainPage.secondNameContainerGetText(), "Potter", config.errorMessage());
    }

    @Test
    @Description("Поиск клиента по почтовому индексу")
    public void clientSearchByPostCodeTest() {

        mainPage.bankManagerLoginButtonClick()
                .customersButtonClick()
                .inputStrInSearchCustomerField("E725JB");

        Assert.assertEquals(mainPage.postCodeContainerGetText(), "E725JB", config.errorMessage());
    }

    @Test
    @Description("Добавление одинаковых пользователей")
    public void addingIdenticalUsers() {

        mainPage.homeButtonClick()
                .bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("TestFirstName")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        Assert.assertEquals(driver.switchTo().alert().getText(), "Please check the details. Customer may be duplicate.", config.errorMessage());

        driver.switchTo().alert().accept();
    }
}
