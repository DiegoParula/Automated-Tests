package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccount extends BasePage{

    private By btnNewAccount = By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a");
    private By typeAccount = By.xpath("//*[@id=\"type\"]");
    private By optionSavings = By.xpath("//*[@id=\"type\"]/option[2]");
    private By fromAccountId = By.id("fromAccountId");
    private By toAccountId = By.xpath("//*[@id=\"fromAccountId\"]/option");
    private By btnOpenNewAccount = By.xpath("//*[@id=\"openAccountForm\"]/form/div/input");
    private By messageSuccess = By.xpath("//*[@id=\"openAccountResult\"]/p[1]");
    //Congratulations, your account is now open.

    protected NewAccount(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openNewAccount() throws InterruptedException {
        this.click(btnNewAccount);
    }

    public void typeAccount() throws InterruptedException {
        this.click(typeAccount);
    }

    public void selectOptionSavings() throws InterruptedException {
        this.click(optionSavings);
    }

    public void account() throws InterruptedException {
        this.click(fromAccountId);
    }

    public void selectAccount() throws InterruptedException {
        this.click(toAccountId);
    }

    public void openAccountForm() throws InterruptedException {
        this.click(btnOpenNewAccount);
    }

    public String getMessageSuccess() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(messageSuccess));
        String res = this.getText(messageSuccess);
        System.out.println(res);
        return res;


    }
}
