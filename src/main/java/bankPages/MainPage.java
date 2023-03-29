package bankPages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver driver;

    @FindBy(xpath = "//button[contains(text(),'Bank Manager Login')]")
    private WebElement bankManagerLoginButton;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]")
    private WebElement addCustomerButtonFirst;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/input[1]")
    private WebElement firstNameField;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/input[1]")
    private WebElement secondNameField;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[3]/input[1]")
    private WebElement postCodeField;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/button[1]")
    private WebElement addCustomerButtonSecond;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[1]/button[3]")
    private WebElement customersButton;

    @FindBy(xpath = "//td[contains(text(),'TestFirstName')]")
    private WebElement firstNameContainer;

    @FindBy(xpath = "//td[contains(text(),'TestSecondName')]")
    private WebElement secondNameContainer;

    @FindBy(xpath = "//td[contains(text(),'TestPostCode')]")
    private WebElement postCodeContainer;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[1]/button[2]")
    private WebElement openAccountButton;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/select[1]//option[last()]")
    private WebElement customerChooseForm;

    @FindBy(xpath = "//option[contains(text(),'Dollar')]")
    private WebElement currencyDollar;

    @FindBy(xpath = "//option[contains(text(),'Pound')]")
    private WebElement currencyPound;

    @FindBy(xpath = "//option[contains(text(),'Rupee')]")
    private WebElement currencyRupee;

    @FindBy(xpath = "//button[contains(text(),'Process')]")
    private WebElement processButton;

    @FindBy(xpath = "//tbody/tr[last()]/td[4]")
    private WebElement accountNumberContainer;

    @FindBy(xpath = "//thead/tr[1]/td[1]/a[1]")
    private WebElement firstNameButton;

    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    private WebElement firstNameFirstCell;

    @FindBy(xpath = "//tbody/tr[last()]/td[1]")
    private WebElement firstNameLastCell;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/input[1]")
    private WebElement searchCustomerField;

    @FindBy(xpath = "//button[contains(text(),'Home')]")
    private WebElement homeButton;

    @FindBy(xpath = "//button[contains(text(),'Customer Login')]")
    private WebElement customerLoginButton;

    @FindBy(xpath = "//option[last()]")
    private WebElement clientFieldButton;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/strong[1]")
    private WebElement welcomeString;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/strong[1]")
    private WebElement accountNumber;

    @Step("Нажатие кнопки bankManagerLogin")
    public MainPage bankManagerLoginButtonClick() {
        bankManagerLoginButton.click();
        return this;
    }

    @Step("Нажатие кнопки addCustomer (открытие формы)")
    public MainPage addCustomerButtonFirstClick() {
        addCustomerButtonFirst.click();
        return this;
    }

    @Step("Ввод имени в строку")
    public MainPage inputStrInFirstNameField(String str) {
        firstNameField.sendKeys(str);
        return this;
    }

    @Step("Ввод фамилии в строку")
    public MainPage inputStrInSecondNameField(String str) {
        secondNameField.sendKeys(str);
        return this;
    }

    @Step("Ввод почтового индекса в строку")
    public MainPage inputStrInPostCodeField(String str) {
        postCodeField.sendKeys(str);
        return this;
    }

    @Step("Нажатие кнопки addCustomer (добавление клиента в систему)")
    public MainPage addCustomerButtonSecondClick() {
        addCustomerButtonSecond.click();
        return this;
    }

    @Step("Нажатие кнопки customers")
    public MainPage customersButtonClick() {
        customersButton.click();
        return this;
    }

    @Step("Получение имени клиета")
    public String firstNameContainerGetText() {
        return firstNameContainer.getText();
    }

    @Step("Получение фамилии клиета")
    public String secondNameContainerGetText() {
        return secondNameContainer.getText();
    }

    @Step("Получение почтового индекса клиета")
    public String postCodeContainerGetText() {
        return postCodeContainer.getText();
    }

    @Step("Нажатие кнопки openAccount")
    public MainPage openAccountButtonClick() {
        openAccountButton.click();
        return this;
    }

    @Step("Выбор клиента из выпадающего списка")
    public MainPage customerChooseFormClick() {
        customerChooseForm.click();
        return this;
    }

    @Step("Выбор валюты (Dollar)")
    public MainPage currencyDollarClick() {
        currencyDollar.click();
        return this;
    }

    @Step("Выбор валюты (Pound)")
    public MainPage currencyPoundClick() {
        currencyPound.click();
        return this;
    }

    @Step("Выбор валюты (Rupee)")
    public MainPage currencyRupeeClick() {
        currencyRupee.click();
        return this;
    }

    @Step("Нажатие кнопки process (открытие валютного счёта)")
    public MainPage processButtonClick() {
        processButton.click();
        return this;
    }

    @Step("Получение всех номеров валютных счетов пользователя")
    public String accountNumberContainerGetText() {
        return accountNumberContainer.getText();
    }

    public String findNextAccountNumbers(int numberOfAddedAccountNumbers) {
        String currentAccountNumber = accountNumberContainer.getText();
        String[] subStr = currentAccountNumber.split(" ");
        int currentAccountNumberInt = Integer.parseInt(subStr[subStr.length - 1]);
        String nextAccountNumberStr = Integer.toString(++currentAccountNumberInt);
        for (int i = 0; i < numberOfAddedAccountNumbers - 1; i++) {
            nextAccountNumberStr += " ";
            nextAccountNumberStr += Integer.toString(++currentAccountNumberInt);
        }
        return nextAccountNumberStr;
    }

    @Step("Нажатие по полю firstName")
    public MainPage firstNameButtonClick() {
        firstNameButton.click();
        return this;
    }

    @Step("Получение имени клиента из первой ячейки таблицы")
    public String firstNameFirstCellGetText() {
        return firstNameFirstCell.getText();
    }

    @Step("Получение имени клиента из последней ячейки таблицы")
    public String firstNameLastCellGetText() {
        return firstNameLastCell.getText();
    }

    @Step("Ввод строки в поле поиска")
    public MainPage inputStrInSearchCustomerField(String str) {
        searchCustomerField.sendKeys(str);
        return this;
    }

    @Step("Нажатие кнопки home")
    public MainPage homeButtonClick() {
        homeButton.click();
        return this;
    }

    @Step("Нажатие кнопки customerLogin")
    public MainPage customerLoginButtonClick() {
        customerLoginButton.click();
        return this;
    }

    @Step("Выбор клиента из списка")
    public MainPage clientFieldButtonClick() {
        clientFieldButton.click();
        return this;
    }

    @Step("Нажатие кнопки логин")
    public MainPage loginButtonClick() {
        loginButton.click();
        return this;
    }

    @Step("Получение приветственной строки")
    public String welcomeStringGetText() {
        return welcomeString.getText();
    }

    public String accountNumberGetText() {
        return accountNumber.getText();
    }

    public MainPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
