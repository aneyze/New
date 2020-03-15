import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static java.lang.Integer.parseInt;



public class Web {



    public static void main(String[] args) /*throws InterruptedException*/ {

        //Propriedades do chrome
        String projectPath = System.getProperty("user.dir");   // return project folder path
        String chromePath = projectPath + "\\libs\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);

        //Iniciar o driver para interagir com o chrome
        WebDriver driver = new ChromeDriver();


        //Enviar a url para abrir o browser
        driver.get("https://www.discourse.org/");

        //Mazimiza o browser
        driver.manage().window().maximize();

        //clicar na opção Demo
        WebElement demo = driver.findElement(By.cssSelector("a[href='https://try.discourse.org']"));
        demo.click();

        //obtendo a janela mais recente aberta
        String janela = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()){
            janela = winHandle;
        }
        driver.switchTo().window(janela);

        //Primeiro scroll até "Can I upload attachments?"
        WebElement scroll1 = driver.findElement(By.cssSelector("[data-topic-id='123']:nth-child(1)"));
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);",scroll1);
        //scroll1.isSelected();

        //Espera pelo elemento "Charlie The Unicorn 4"
        WebDriverWait wait = new WebDriverWait (driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-topic-id='15']:nth-child(1)")));

        //Segundo scroll até o fim da página
        WebElement scroll2 = driver.findElement(By.cssSelector("[data-topic-id='63']:nth-child(1)"));
        JavascriptExecutor je1 = (JavascriptExecutor) driver;
        je1.executeScript("arguments[0].scrollIntoView(true);",scroll2);
        //System.out.println(z2.getText());

        //Imprimindo tópicos com cadeados
        List<WebElement> list1 = driver.findElements(By.cssSelector("[class*='closed ember'] .title"));
        for(int i =0;i<list1.size();i++) {
            String et = list1.get(i).getText();
            System.out.println("Tópico fechado: " + et);
        }
        System.out.println("Total de tópicos fechados: " + list1.size() + "\n");

        //Retornando a quantidade de itens de cada categoria
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<WebElement> list2 = driver.findElements(By.cssSelector(".link-bottom-line span span"));
        String[] s2 = new String[list2.size()];
        for(int i=0;i<list2.size();i++)
            s2[i] = list2.get(i).getText();
        ArrayList <String> l1 = new ArrayList<>(Arrays.asList(s2));
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        for (String k : l1) {
            Integer j = map1.get(k);
            map1.put(k, (j == null) ? 1 : j + 1);
        }
        for (Map.Entry<String, Integer> i : map1.entrySet())
            System.out.println("Categoria " + i.getKey() + " " + "aparece" + ": " + i.getValue() + " vezes");


        //Retornando a quantidade de itens que não possuem categoria
        List<WebElement> list3 = driver.findElements(By.cssSelector("[class*='uncategorized']:not(#ember45) .link-top-line"));
        String[] et3 = new String[list3.size()];
        System.out.println("\n");
        for(int i =0;i<list3.size();i++) {
            et3[i] = list3.get(i).getText();
            System.out.println("Descrição dos elementos sem categoria: " + et3[i]);
        }
        System.out.println("\nTotal de elementos que não possuem categoria: " + list3.size());


        //Imprimindo o tópico que contém o maior número de views
        List<WebElement> list4 = driver.findElements(By.cssSelector(".num.views .number"));
        List<WebElement> list5 = driver.findElements(By.cssSelector(".link-top-line"));
        int[] i1 = new int[list4.size()];
        int max = 0;
        int l4=0;
        int largest = 0;
        String s6 = "None";
        String et5[] = new String[list5.size()];
        char[] ch = new char[4];
           for(int i = 0;i<list4.size();i++) {
               String et4 = list4.get(i).getText();
               et5[i] = list5.get(i).getText();
               if (!et4.contains(".")) {
                   l4 = parseInt(et4.trim());
                   i1[i] = l4;
               } else {
                   String number = et4.substring(0, 3).trim();
                   float num1 = Float.parseFloat(number.trim());
                   float num = num1 * 1000;
                   i1[i] = Math.round(num);
               }
            if (i1[i] > max)
                max = i1[i];

            if (i1[i] > i1[largest])
                largest = i;

            s6=et5[largest];
           }

        System.out.println("\nMaior número de views encontrado: " + max);
        System.out.println("Descrição do item com mais views: " + s6);
        //Fechar o browser
        driver.quit();
    }

}


