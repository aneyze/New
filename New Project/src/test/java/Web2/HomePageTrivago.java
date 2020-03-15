package Web2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageTrivago {

    private WebDriver driver;
    private String pageUrl = "https://www.trivago.com.br";

    public HomePageTrivago(WebDriver driver){
        this.driver = driver;
    }

    //Element locators
    private By busca = By.cssSelector(".dealform__query-wrapper input");
    private By tipoQuarto = By.cssSelector(".dealform-button--guests");
    private By quartoIndividual = By.cssSelector("div .roomtype-btn div span:nth-child(2)");
    private By pesquisar = By.cssSelector(".btn.btn--primary");


    //Interactions
    public HomePageTrivago accessarPagina(){
        driver.get(this.pageUrl);
        return this;
    }

    public HomePageTrivago informarTextoPesquisa(String a){
        driver.findElement(busca).sendKeys(a);
        WebDriverWait wait = new WebDriverWait (driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ssg-suggestion__info")));
        return this;

    }

    public HomePageTrivago selecionarQuartoIndividual(){
        driver.findElement(tipoQuarto).click();
        WebDriverWait wait = new WebDriverWait (driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".roomtype-btn")));
        driver.findElement(quartoIndividual).click();
        return this;
    }

    public ReservationPage clicarPesquisar(){
        driver.findElement(pesquisar).click();
        return new ReservationPage(driver);
    }


}
