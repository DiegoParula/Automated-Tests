package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private By userName = By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private By password = By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");
    private By loginButtom = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");
    private By overview = By.xpath("//*[@id=\"showOverview\"]/h1");
    private By errorMessage = By.xpath("//*[@id=\"rightPanel\"]/p");


    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }


    public void insertUsername(String user) throws InterruptedException {
        this.sendText(user, userName);
    }

    public void insertPassword(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    public void clickLogIn() throws InterruptedException {
        this.click(loginButtom);
    }

    public String getTitleAccountOverview() throws InterruptedException {
        String res = this.getText(overview);
        System.out.println(res);
        return res;
    }

    public String getErrorMessage() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(errorMessage));
        String res = this.getText(errorMessage);
        System.out.println(res);
        return res;
    }

}