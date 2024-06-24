package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFunds extends BasePage {

    private By btnTransfer = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");
    private By txtTransfer = By.xpath("//*[@id=\"showForm\"]/h1");
    private By idAmount = By.id("amount");
    private By fromAccount = By.id("fromAccountId");
    private By fromAccountNumber = By.xpath("//*[@id=\"fromAccountId\"]/option[1]");
    private By toAccount = By.id("toAccountId");
    private By toAccountNumber = By.xpath("//*[@id=\"toAccountId\"]/option[2]");
    private By submitTransfer = By.xpath("//*[@id=\"transferForm\"]/div[2]/input");
    private By txtTransferComplete = By.xpath("//*[@id=\"showResult\"]/h1");


    protected TransferFunds(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickTransfer() throws InterruptedException {
        this.click(btnTransfer);
    }

    public String titleTransfer() throws InterruptedException {
        String res = this.getText(txtTransfer);
        System.out.println(res);
        return res;
    }

    public void insertAmount(String amount) throws InterruptedException {
        this.sendText(amount, idAmount);
    }

    public void clickFromAccount() throws InterruptedException {
        this.click(fromAccount);
    }

    public void selectFromAccountNumber() throws InterruptedException {
        this.click(fromAccountNumber);
    }

    public void clickToAccount() throws InterruptedException {
        this.click(toAccount);
    }

    public void selectToAccountNumber() throws InterruptedException {
        this.click(toAccountNumber);
    }

    public void clickSubmitTransfer() throws InterruptedException {
        this.click(submitTransfer);
    }

    public String transferComplete() throws InterruptedException {
        String res = this.getText(txtTransferComplete);
        System.out.println(res);
        return res;
    }


}
