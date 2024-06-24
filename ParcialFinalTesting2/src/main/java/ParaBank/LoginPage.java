package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private By searchBoxMail = By.id("email");
    private By searchBoxPass = By.id("password");
    private By loginButtom = By.xpath("//button[normalize-space()='Ingresar']");
    private By nameLogin = By.className("txt-nombre");
    private  By mailObligatorio = By.className("small-feedback");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /** Completa el campo de email.
     * @param mail el email del usuario.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void completarMail(String mail) throws InterruptedException {
        this.sendText(mail, searchBoxMail);
    }

    /** Completa la contraseña del operador.
     * @param pass contraseña del operador.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void completarContraseña(String pass) throws InterruptedException {
        this.sendText(pass, searchBoxPass);
    }

    /** Hace click en el botón de Ingresar.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void clickIngresar() throws InterruptedException {
        this.click(loginButtom);
    }

    /** Obtiene el nombre del usuario logueado.
     * @return El texto del usuario.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public String nombreLogueado() throws InterruptedException {
        System.out.println("El nombre del usuario es: " + this.getText(nameLogin));
        return this.getText(nameLogin);
    }

    /** Obtiene mensaje de Email obligatorio.
     * @return El mensaje de obligatorio.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public String validaMailObligatorio() throws InterruptedException {
        System.out.println("Se valida mensaje de Mail Obligatorio: " + this.getText(mailObligatorio));
        return this.getText(mailObligatorio);
    }

    /** Obtiene mensaje de Contraseña obligatoria.
     * @return El mensaje de obligatorio.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public String validaPassObligatorio() throws InterruptedException {
        System.out.println("Se valida mensaje de Contraseña Obligatoria: " + this.getText(mailObligatorio));
        return this.getText(mailObligatorio);
    }

    /** Obtiene mensaje de Email invalido.
     * @return El mensaje de invalido.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public String validaMensajeInvalido() throws InterruptedException {
        System.out.println("Se valida el mensaje de que el Mail es Invalido: " + this.getText(mailObligatorio));
        return this.getText(mailObligatorio);
    }
}