import bankPages.MainPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends MainTest {

    MainPage mainPage;

    @Test(groups = "CreationOfAccount")
    @Description("Создание аккаунта")
    public void createAnAccountTest() {

        mainPage = new MainPage(driver);

        mainPage.bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("TestFirstName")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        driver.switchTo().alert().accept();

        mainPage.customersButtonClick();

        Assert.assertEquals(mainPage.firstNameContainerGetText(), "TestFirstName");
        Assert.assertEquals(mainPage.secondNameContainerGetText(), "TestSecondName");
        Assert.assertEquals(mainPage.postCodeContainerGetText(), "TestPostCode");
    }

    @Test(groups = "CreationOfAccount")
    @Description("Добавление номеров счёта клиенту")
    public void addAccountNumbersTest() {

        mainPage = new MainPage(driver);

        String trueNextAccountNumbers = mainPage.bankManagerLoginButtonClick()
                .customersButtonClick()
                .findNextAccountNumbers(3);

        mainPage.homeButtonClick()
                .bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("TestFirstName")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        driver.switchTo().alert().accept();

        mainPage.openAccountButtonClick()
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

        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers);
    }

    @Test(groups = "CreationOfAccount")
    @Description("Вход в аккаунт клиента")
    public void EnterToAccountTest() {

        mainPage = new MainPage(driver);

        String trueNextAccountNumbers = mainPage.bankManagerLoginButtonClick()
                .customersButtonClick()
                .findNextAccountNumbers(3);

        mainPage.homeButtonClick()
                .bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("TestFirstName")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        driver.switchTo().alert().accept();

        mainPage.openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();

        driver.switchTo().alert().accept();

        mainPage.homeButtonClick()
                .customerLoginButtonClick()
                .clientFieldButtonClick()
                .loginButtonClick();

        Assert.assertEquals(mainPage.welcomeStringGetText(), "Welcome TestFirstName TestSecondName !!");
        Assert.assertEquals(mainPage.accountNumberGetText(), trueNextAccountNumbers.split(" ")[0]);
    }

    @Test
    @Description("Сортировка по имени")
    public void sortByNameTest() {

        mainPage = new MainPage(driver);

        mainPage.bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("ZZZZZZ")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        driver.switchTo().alert().accept();

        mainPage.inputStrInFirstNameField("AAAAAA")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        driver.switchTo().alert().accept();

        mainPage.customersButtonClick()
                .firstNameButtonClick();

        Assert.assertEquals(mainPage.firstNameFirstCellGetText(), "ZZZZZZ");
        Assert.assertEquals(mainPage.firstNameLastCellGetText(), "AAAAAA");
        Assert.assertEquals(mainPage.secondNameContainerGetText(), "TestSecondName");
        Assert.assertEquals(mainPage.postCodeContainerGetText(), "TestPostCode");

        mainPage.firstNameButtonClick();

        Assert.assertEquals(mainPage.firstNameFirstCellGetText(), "AAAAAA");
        Assert.assertEquals(mainPage.firstNameLastCellGetText(), "ZZZZZZ");
        Assert.assertEquals(mainPage.secondNameContainerGetText(), "TestSecondName");
        Assert.assertEquals(mainPage.postCodeContainerGetText(), "TestPostCode");
    }

    @Test(groups = "clientSearch")
    @Description("Поиск клиента по имени")
    public void clientSearchByFirstNameTest() {

        mainPage = new MainPage(driver);

        String trueNextAccountNumbers = mainPage.bankManagerLoginButtonClick()
                .customersButtonClick()
                .findNextAccountNumbers(1);

        mainPage.homeButtonClick()
                .bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("TestFirstName")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        driver.switchTo().alert().accept();

        mainPage.openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();

        driver.switchTo().alert().accept();

        mainPage.customersButtonClick()
                .inputStrInSearchCustomerField("TestFirstName");

        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers);
    }

    @Test(groups = "clientSearch")
    @Description("Поиск клиента по фамилии")
    public void clientSearchBySecondNameTest() {

        mainPage = new MainPage(driver);

        String trueNextAccountNumbers = mainPage.bankManagerLoginButtonClick()
                .customersButtonClick()
                .findNextAccountNumbers(1);

        mainPage.homeButtonClick()
                .bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("TestFirstName")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        driver.switchTo().alert().accept();

        mainPage.openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();

        driver.switchTo().alert().accept();

        mainPage.customersButtonClick()
                .inputStrInSearchCustomerField("TestSecondName");

        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers);
    }

    @Test(groups = "clientSearch")
    @Description("Поиск клиента по почтовому индексу")
    public void clientSearchByPostCodeTest() {

        mainPage = new MainPage(driver);

        String trueNextAccountNumbers = mainPage.bankManagerLoginButtonClick()
                .customersButtonClick()
                .findNextAccountNumbers(1);

        mainPage.homeButtonClick()
                .bankManagerLoginButtonClick()
                .addCustomerButtonFirstClick()
                .inputStrInFirstNameField("TestFirstName")
                .inputStrInSecondNameField("TestSecondName")
                .inputStrInPostCodeField("TestPostCode")
                .addCustomerButtonSecondClick();

        driver.switchTo().alert().accept();

        mainPage.openAccountButtonClick()
                .customerChooseFormClick()
                .currencyDollarClick()
                .processButtonClick();

        driver.switchTo().alert().accept();

        mainPage.customersButtonClick()
                .inputStrInSearchCustomerField("TestPostCode");

        Assert.assertEquals(mainPage.accountNumberContainerGetText(), trueNextAccountNumbers);
    }
}
