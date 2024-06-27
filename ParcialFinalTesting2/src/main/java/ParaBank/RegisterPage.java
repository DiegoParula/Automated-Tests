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
}
