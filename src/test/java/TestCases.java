import bankPages.MainPage;
import config.AssertsConfig;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCases extends MainTest {

    private final AssertsConfig config = ConfigFactory.create(AssertsConfig.class, System.getenv());

    @Test()
    @Description("Создание аккаунта")
    public void createAnAccountTest() {

        mainPage.bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("TestFirstName")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();
        driver.switchTo().alert().accept();
        mainPage.homeButtonClick()
                .bankManagerLoginButtonClick()
                .customersButtonClick();

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(mainPage.firstNameContainerGetText(), config.expectedFirstName(), config.errorMessage());
        softAssertion.assertEquals(mainPage.secondNameContainerGetText(), config.expectedSecondName(), config.errorMessage());
        softAssertion.assertEquals(mainPage.postCodeContainerGetText(), config.expectedPostCode(), config.errorMessage());
        softAssertion.assertAll();
    }

    @Test
    @Description("Добавление номеров счёта клиенту")
    public void addAccountNumbersTest() {

        mainPage.bankManagerLoginButtonClick()
                .openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();
        driver.switchTo().alert().accept();
        mainPage.customerChooseFormClick()
                .currencyPoundClick()
                .processButtonClick();
        driver.switchTo().alert().accept();
        mainPage.customerChooseFormClick()
                .currencyRupeeClick()
                .processButtonClick();
        driver.switchTo().alert().accept();
        mainPage.customersButtonClick();

        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers, config.errorMessage());
    }

    @Test
    @Description("Вход в аккаунт клиента")
    public void enterToAccountTest() {

        mainPage.bankManagerLoginButtonClick()
                .openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();
        driver.switchTo().alert().accept();
        mainPage.homeButtonClick()
                .customerLoginButtonClick()
                .clientFieldButtonClick()
                .loginButtonClick();

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(mainPage.welcomeStringGetText(), "Welcome TestFirstName TestSecondName !!", config.errorMessage());
        softAssertion.assertEquals(mainPage.accountNumberGetText(), trueNextAccountNumbers.split(" ")[0], config.errorMessage());
        softAssertion.assertAll();
    }

    @Test
    @Description("Сортировка по имени")
    public void sortByNameTest() {

        mainPage.bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("ZZZZZZ")
                .inputStrInSecondNameField(config.expectedSecondName())
                .inputStrInPostCodeField(config.expectedPostCode())
                .addCustomerButtonSecondClick();
        driver.switchTo().alert().accept();
        mainPage.inputStrInFirstNameField("AAAAAA")
                .inputStrInSecondNameField(config.expectedSecondName())
                .inputStrInPostCodeField(config.expectedPostCode())
                .addCustomerButtonSecondClick();
        driver.switchTo().alert().accept();
        mainPage.customersButtonClick()
                .firstNameButtonClick();

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(mainPage.firstNameFirstCellGetText(), "ZZZZZZ", config.errorMessage());
        softAssertion.assertEquals(mainPage.firstNameLastCellGetText(), "AAAAAA", config.errorMessage());
        softAssertion.assertEquals(mainPage.secondNameContainerGetText(), config.expectedSecondName(), config.errorMessage());
        softAssertion.assertEquals(mainPage.postCodeContainerGetText(), config.expectedPostCode(), config.errorMessage());
        softAssertion.assertAll();

        mainPage.firstNameButtonClick();

        softAssertion.assertEquals(mainPage.firstNameFirstCellGetText(), "AAAAAA", config.errorMessage());
        softAssertion.assertEquals(mainPage.firstNameLastCellGetText(), "ZZZZZZ", config.errorMessage());
        softAssertion.assertEquals(mainPage.secondNameContainerGetText(), config.expectedSecondName(), config.errorMessage());
        softAssertion.assertEquals(mainPage.postCodeContainerGetText(), config.expectedPostCode(), config.errorMessage());
        softAssertion.assertAll();
    }

    @Test
    @Description("Поиск клиента по имени")
    public void clientSearchByFirstNameTest() {

        mainPage.bankManagerLoginButtonClick()
                .openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();
        driver.switchTo().alert().accept();
        mainPage.customersButtonClick()
                .inputStrInSearchCustomerField("TestFirstName");
        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers.split(" ")[0], config.errorMessage());
    }

    @Test
    @Description("Поиск клиента по фамилии")
    public void clientSearchBySecondNameTest() {

        mainPage.bankManagerLoginButtonClick()
                .openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();
        driver.switchTo().alert().accept();
        mainPage.customersButtonClick()
                .inputStrInSearchCustomerField("TestSecondName");

        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers.split(" ")[0], config.errorMessage());
    }

    @Test
    @Description("Поиск клиента по почтовому индексу")
    public void clientSearchByPostCodeTest() {

        mainPage.bankManagerLoginButtonClick()
                .openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();
        driver.switchTo().alert().accept();
        mainPage.customersButtonClick()
                .inputStrInSearchCustomerField("TestPostCode");

        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers.split(" ")[0], config.errorMessage());
    }
}
