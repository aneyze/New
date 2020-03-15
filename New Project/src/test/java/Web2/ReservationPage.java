package Web2;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ReservationPage {

    private WebDriver driver;

    public ReservationPage(WebDriver driver){
        this.driver = driver;
    }

    //Element locators
    private By distanciaClick = By.cssSelector("#mf-select-sortby");
    private By distanciaSelecao = By.cssSelector("#mf-select-sortby [value='3']");
    private By somenteDistancia = By.cssSelector("#mf-select-sortby");
    private By nomeHotel = By.cssSelector("h3 span[title='Natal Palace']");
    private By tipoAcomodacao = By.cssSelector("[id='3166102'] .accommodation-type");
    private By siteOferta = By.cssSelector("[id='3166102'] [data-qa='recommended-price']+span");
    private By valorQuarto = By.cssSelector("[id='3166102'] [class*='accommodation-list__deal'] span");
    private By localizacao = By.cssSelector("[id='3166102'] p.details-paragraph");
    private By todasComodidades = By.cssSelector(".expand-amenities button");
    private By comodidadesQuarto = By.cssSelector(".all-amenities__group.mb-gutter:nth-child(2) li");
    private List<WebElement> lista;

    public ReservationPage ordenarApenasPorDistancia(){
        driver.findElement(distanciaClick).click();
        driver.findElement(distanciaSelecao).click();
        driver.findElement(somenteDistancia).click();
        return this;
    }

    public String imprimirNomeHotel(){
        WebDriverWait wait = new WebDriverWait (driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3 span[title='Natal Palace']")));
        return driver.findElement(nomeHotel).getText();
    }

    public String imprimirQuantidadeEstrelas() {
        return driver.findElement(tipoAcomodacao).getText();
    }

    public String imprimirSiteOferta(){
        return driver.findElement(siteOferta).getText();
    }

    public String imprimirValorQuarto(){
        return driver.findElement(valorQuarto).getText();
    }

    public ReservationPage clicarLocalizacao() {
        driver.findElement(localizacao).click();
        return this;
    }

    public ReservationPage clicarVIsualizarTodasComodidades (){
        WebDriverWait wait = new WebDriverWait (driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".expand-amenities button")));
        driver.findElement(todasComodidades).click();
        return this;
    }

    public List <WebElement> imprimirComodidadesQuarto(){
        lista = driver.findElements(comodidadesQuarto);
        return lista;

    }
}
