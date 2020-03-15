import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Web2 {
    public static void main(String[] args) throws InterruptedException {

        //Propriedades do chrome
        String projectPath = System.getProperty("user.dir");   // return project folder path
        String chromePath = projectPath + "\\libs\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);

        //Iniciar o driver para interagir com o chrome
        WebDriver driver = new ChromeDriver();


        //Enviar a url para abrir o browser
        driver.get("https://www.trivago.com.br");

        //Mazimiza o browser
        driver.manage().window().maximize();

        //Digitar o valor "Natal" no campo busca
        WebElement busca = driver.findElement(By.cssSelector(".dealform__query-wrapper input"));

        busca.sendKeys("Natal");
        WebDriverWait wait1 = new WebDriverWait (driver, 10);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ssg-suggestion__info")));

        //Selecionar a opção "Quarto Individual"
        WebElement tipoQuarto = driver.findElement(By.cssSelector(".dealform-button--guests"));
        tipoQuarto.click();
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".roomtype-btn")));

        WebElement quartoIndividual = driver.findElement(By.cssSelector("div .roomtype-btn div span:nth-child(2)"));
        quartoIndividual.click();

        //Clicar no botão "Pesquisar"
        WebElement pesquisar = driver.findElement((By.cssSelector(".btn.btn--primary")));
        pesquisar.click();

        //Selecionar a opção "Ordenar apenas por Distância"
        WebElement distanciaClick = driver.findElement((By.cssSelector("#mf-select-sortby")));
        distanciaClick.click();
        WebElement distanciaSelecao= driver.findElement(By.cssSelector("#mf-select-sortby [value='3']"));
        distanciaSelecao.click();
        WebElement somenteDistancia = driver.findElement(By.cssSelector("#mf-select-sortby"));
        somenteDistancia.click();


        //No segundo item da lista de hotéis

            //Imprimir o nome do hotel
            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3 span[title='Natal Palace']")));

            WebElement nomeHotel = driver.findElement(By.cssSelector("h3 span[title='Natal Palace']"));
            String s1 = nomeHotel.getText();
            //System.out.println("Nome do Hotel: " + s1);

            //Imprimir textualmente a quantidade de estrelas que ele possui (por exemplo 5 estrelas)
            WebElement tipoAcomodacao = driver.findElement(By.cssSelector("[id='3166102'] .accommodation-type"));
            String s2 = tipoAcomodacao.getText();
            //System.out.println("O hotel não possui estrelas. Tipo de acomodação: " + s2);

            //Imprimir o nome do site da oferta
            WebElement siteOferta = driver.findElement(By.cssSelector("[id='3166102'] [data-qa='recommended-price']+span"));
            String s3 = siteOferta.getText();
            //System.out.println("Nome do site da oferta: "+s3);

            //Imprimir o valor do quarto
            WebElement valorQuarto = driver.findElement(By.cssSelector("[id='3166102'] [class*='accommodation-list__deal'] span"));
            String s4 = valorQuarto.getText();
            //System.out.println("Valor do quarto: "+s4);

            //Clicar na localização (uma área com mais informações será mostrada)
            WebElement localizacao = driver.findElement(By.cssSelector("[id='3166102'] p.details-paragraph"));
            localizacao.click();;

            //Clicar no link "Visualizar todas as comodidades"
            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".expand-amenities button")));
            WebElement todasComodidades = driver.findElement(By.cssSelector(".expand-amenities button"));
            todasComodidades.click();

            //Imprimir as comodidades do quarto
            WebElement w14 = driver.findElement(By.cssSelector(".all-amenities__group.mb-gutter:nth-child(2) h5"));
            String s5 = w14.getText();

            List<WebElement> comodidadesQuarto = driver.findElements(By.cssSelector(".all-amenities__group.mb-gutter:nth-child(2) li"));
            String[] s6 = new String[comodidadesQuarto.size()];
            for(int i=0;i<comodidadesQuarto.size();i++) {
                s6[i] = comodidadesQuarto.get(i).getText();
                //System.out.println("Lista de comodidades do quarto: " + s6[i]);
            }

            System.out.println("\nInformações do hotal desejado");
            System.out.println("\n----------------------------------------------------");
            System.out.println("\nNome do Hotel: " + s1);
            System.out.println("\nEstrelas: " + "O hotel não possui estrelas." + "Tipo de acomodação: " + s2);
            System.out.println("\nOferta da Empresa: " + s3);
            System.out.println("\nPreço do Quarto: " + s4 + "\n");
            for(int i=0; i<comodidadesQuarto.size(); i++)
                System.out.println("Comodidades do Quarto: " + s6[i]);


        //Fechar o browser
        driver.quit();

    }
}
