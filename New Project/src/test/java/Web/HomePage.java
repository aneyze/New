package Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {

    private WebDriver driver;
    private String pageUrl = "https://www.discourse.org/";

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Element locator
    private By demo = By.cssSelector("a[href='https://try.discourse.org']");
    private String janela;

    //Iteraction
    public HomePage accessarPaginaDiscourse(){
        driver.get(this.pageUrl);
        return this;
    }

    public HomePage clicarDemo(){
        driver.findElement(demo).click();
        return this;
    }

    public DemoPage obterJanelaMaisRecenteAberta(){
        for(String winHandle : driver.getWindowHandles()){
            janela = winHandle;
        }
        driver.switchTo().window(janela);
        return new DemoPage(driver);
    }

}
