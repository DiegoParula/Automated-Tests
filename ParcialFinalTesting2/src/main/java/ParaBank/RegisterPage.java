package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{
    private By btnRegister = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");
    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zip = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By userName = By.id("customer.username");
    private By password = By.id("customer.password");
    private By repassword = By.id("repeatedPassword");
    private By btnSubmitRegister = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");
    private By messageSuccess = By.xpath("//*[@id=\"rightPanel\"]/p");
    //Your account was created successfully. You are now logged in.
    private By messageUserNameError = By.id("customer.username.errors");

    private By errorPass = By.id("repeatedPassword.errors");

    private By messageFirstName = By.id("customer.firstName.errors");
    private By messageLastName = By.id("customer.lastName.errors");
    private By messageAddress = By.id("customer.address.street.errors");
    private By messageCity = By.id("customer.address.city.errors");
    private By messageState = By.id("customer.address.state.errors");
    private By messageZip = By.id("customer.address.zipCode.errors");
    private By messageSsn = By.id("customer.ssn.errors");

    private By messagePassword = By.id("customer.password.errors");
   ;


    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void clickRegister() throws InterruptedException {
        this.click(btnRegister);
    }

    public void insertName(String name) throws InterruptedException {
        this.sendText(name, firstName);
    }

    public void insertLastName(String lastname) throws InterruptedException {
        this.sendText(lastname, lastName);
    }

    public void insertAddress(String street) throws InterruptedException {
        this.sendText(street, address);
    }

    public void insertCity(String iCity) throws InterruptedException {
        this.sendText(iCity, city);
    }

    public void insertState(String iState) throws InterruptedException {
        this.sendText(iState, state);
    }

    public void insertZip(String iZip) throws InterruptedException {
        this.sendText(iZip, zip);
    }

    public void insertPhone(String iPhone) throws InterruptedException {
        this.sendText(iPhone, phone);
    }
    public void insertSsn(String iSsn) throws InterruptedException {
        this.sendText(iSsn, ssn);
    }
    public void insertUsername(String username) throws InterruptedException {
        this.sendText(username, userName);
    }
    public void insertPassword(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    public void insertRePassword(String pass) throws InterruptedException {
        this.sendText(pass, repassword);
    }

    public void clickSubmitRegister() throws InterruptedException {
        this.click(btnSubmitRegister);
    }

    public String getMessageSuccess() throws InterruptedException {
        String res = this.getText(messageSuccess);
        return res;
    }

    public String getMessageErrorPass() throws InterruptedException {
        String res = this.getText(errorPass);
        System.out.println(res);
        return res;
    }

    public String getMessageUserNameError() throws InterruptedException {
        String res = this.getText(messageUserNameError);
        System.out.println(res);
        return res;
    }

    public String getMessageFirstNameError()throws InterruptedException {
        String res = this.getText(messageFirstName);
        System.out.println(res);
        return res;
    }

    public String getMessageLasteNameError()throws InterruptedException {
        String res = this.getText(messageLastName);
        System.out.println(res);
        return res;
    }

    public String getMessageAdressError()throws InterruptedException {
        String res = this.getText(messageAddress);
        System.out.println(res);
        return res;
    }

    public String getMessageCityError()throws InterruptedException {
        String res = this.getText(messageCity);
        System.out.println(res);
        return res;
    }

    public String getMessageStateError()throws InterruptedException {
        String res = this.getText(messageState);
        System.out.println(res);
        return res;
    }

    public String getMessageZipError()throws InterruptedException {
        String res = this.getText(messageZip);
        System.out.println(res);
        return res;
    }

    public String getMessageSsnError()throws InterruptedException {
        String res = this.getText(messageSsn);
        System.out.println(res);
        return res;
    }

    public String getMessagePasswordError()throws InterruptedException {
        String res = this.getText(messagePassword);
        System.out.println(res);
        return res;
    }
}
