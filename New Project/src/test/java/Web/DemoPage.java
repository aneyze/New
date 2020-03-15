package Web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class DemoPage {

    private WebDriver driver;
    public DemoPage(WebDriver driver){
        this.driver = driver;
    }

    //Element locator
    private By demo = By.cssSelector("a[href='https://try.discourse.org']");
    private String janela;
    private By scroll1 = By.cssSelector("[data-topic-id='264']:first-of-type");
    private By scroll2 = By.cssSelector("[data-topic-id='26']:first-of-type");
    private By topicosFechados = By.cssSelector("[class*='closed ember'] .title");
    private By qtdCategorias = By.cssSelector(".link-bottom-line span span");
    private By qtdsSemCategoria = By.cssSelector("[class*='uncategorized']:not(#ember45) .link-top-line");
    private By views = By.cssSelector(".num.views .number");
    private By descViews = By.cssSelector(".link-top-line");
    private List<WebElement> lista1;
    private List<WebElement> lista2;
    private List<WebElement> lista3;
    private List<WebElement> lista4;
    private List<WebElement> lista5;



    public DemoPage primeiroScroll(){
        JavascriptExecutor exe = (JavascriptExecutor)driver;
        WebElement ele = driver.findElement(scroll1);
        exe.executeScript("arguments[0].scrollIntoView(true);", ele );
        WebDriverWait wait = new WebDriverWait (driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-topic-id='26']:first-of-type")));
        return this;
    }


    public DemoPage segundoScroll(){
        JavascriptExecutor exe = (JavascriptExecutor)driver;
        WebElement ele1 = driver.findElement(scroll2);
        exe.executeScript("arguments[0].scrollIntoView(true);", ele1 );
        WebDriverWait wait = new WebDriverWait (driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-topic-id='264']:first-of-type")));

        return this;
    }

    public List<WebElement> imprimirTopicosBloqueados(){
        lista1 = driver.findElements(topicosFechados);
        for(int i =0;i<lista1.size();i++) {
            String et = lista1.get(i).getText();
        }
        return lista1;
    }

    public String[] imprimirQuantidadeItemPorCategoria(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        lista2 = driver.findElements(qtdCategorias);
        String[] s2 = new String[lista2.size()];
        for(int i=0;i<lista2.size();i++)
            s2[i] = lista2.get(i).getText();
        return s2;
    }

    public String[] imprimirQuantidadeItemSemCategoria(){
        lista3 = driver.findElements(qtdsSemCategoria);
        String[] et3 = new String[lista3.size()];
        for(int i =0;i<lista3.size();i++) {
            et3[i] = lista3.get(i).getText();
        }
        return et3;
    }

    public String imprimirQuantidadeNumeroViews(){
        lista4 = driver.findElements(views);
        lista5 = driver.findElements(descViews);
        int[] i1 = new int[lista4.size()];
        int max = 0;
        int l4=0;
        int largest = 0;
        String s6 = "None";
        String et5[] = new String[lista5.size()];
        char[] ch = new char[4];
        for(int i = 0;i<lista4.size();i++) {
            String et4 = lista4.get(i).getText();
            et5[i] = lista5.get(i).getText();

            if(!et4.contains(".")){
                l4 = parseInt(et4.trim());
                i1[i] = l4;
            }
            else{
                String number = et4.substring(0,3).trim();
                float num1 = Float.parseFloat(number.trim());
                float num = num1*1000;
                i1[i] = Math.round(num);
            }
            if (i1[i] > max)
                max = i1[i];
            if (i1[i] > i1[largest])
                largest = i;

            s6=et5[largest];
        }

        return s6;

    }

}
