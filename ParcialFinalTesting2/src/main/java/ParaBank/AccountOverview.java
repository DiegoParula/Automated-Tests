package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOverview extends BasePage{

    public By overview = By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
    public By message = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");
    public By account = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");
    public By accountDetails = By.xpath("//*[@id=\"accountDetails\"]/h1");
    public By activityPeriod = By.id("month");
    public By getActivityPeriodAll = By.xpath("//*[@id=\"ç\"]/option[1]");
    public By type = By.id("transactionType");
    public By getTypeAll = By.xpath("//*[@id=\"transactionType\"]/option[1]");
    public By submitGo = By.xpath("//*[@id=\"activityForm\"]/table/tbody/tr[3]/td[2]/input");


    protected AccountOverview(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOverview() throws InterruptedException {
        this.click(overview);
    }

    public String getMessage() throws InterruptedException {
        String res = this.getText(message);
        System.out.println(res);
        return res;

    }

    public void clickAccount () throws InterruptedException {
        this.click(account);
    }

    public String getAccountDetails() throws InterruptedException {
        String res = this.getText(accountDetails);
        System.out.println(res);
        return res;
    }

    public void clickActivityPeriod() throws InterruptedException {
        this.click(activityPeriod);
    }

    public void clickGetActivityPeriodAll() throws InterruptedException {
        this.click(getActivityPeriodAll);
    }
    public void clickType() throws InterruptedException {
        this.click(type);
    }
    public void getTypeAll() throws InterruptedException {
        this.click(getTypeAll);
    }

    public void clickSubmitGo() throws InterruptedException {
        this.click(submitGo);
    }
}
