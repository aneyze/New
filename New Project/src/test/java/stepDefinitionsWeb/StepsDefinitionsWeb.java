package stepDefinitionsWeb;

import Web.DemoPage;
import Web.HomePage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;


public class StepsDefinitionsWeb {
    private WebDriver driver;
    HomePage home;
    DemoPage demo;

    @Before
    public void beforeScenario() {
        //Propriedades do chrome
        String projectPath = System.getProperty("user.dir");   // return project folder path
        String chromePath = projectPath + "\\libs\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        //Iniciar o driver para interagir com o chrome
        this.driver = new ChromeDriver();
        //Mazimiza o browser
        driver.manage().window().maximize();
        //Declaração de objetos
        home = new HomePage(driver);
        demo = new DemoPage(driver);
    }

    @After
    public void afterScenario() {
        //Fechar o browser
        this.driver.quit();
    }

    //Enviar a url para abrir o browser
    @Given("a web browser is opened - discourse")
    public void aWebBrowserIsOpenedDiscourse() {
        home.accessarPaginaDiscourse();
    }

    //clicar na opção Demo
    @When("the user clicks on Demo")
    public void theUserClicksOnDemo() {
        home.clicarDemo();
    }

    //obtendo a janela mais recente aberta
    @And("the user opens the right windown")
    public void theUserOpensTheRightWindown() {
        home.obterJanelaMaisRecenteAberta();
    }

    //Primeiro scroll até "Can I upload attachments?"
    @And("the user scrolls the page for the first time")
    public void theUserScrollsThePageForTheFirstTime() {
        demo.primeiroScroll();
    }

    //Segundo scroll até o fim da página
    @And("the user scrolls the page for the second time")
    public void theUserScrollsThePageForTheSecondTime() {
        demo.segundoScroll();
    }

    //Imprimindo tópicos com cadeados
    @Then("the system should display closed items")
    public void theSystemShouldDisplayClosedItems() {
        List<WebElement> expectedValue = demo.imprimirTopicosBloqueados();
        String[] s1 = new String[expectedValue.size()];
        for (int i = 0; i < expectedValue.size(); i++) {
            s1[i] = expectedValue.get(i).getText();
            System.out.println("Descrição do Tópico fechado: " + s1[i]);
        }
        System.out.println("Total de tópicos fechados: " + expectedValue.size() + "\n");
    }

    //Retornando a quantidade de itens de cada categoria
    @And("the system should display the total amount of items that has categories")
    public void theSystemShouldDisplayTheTotalAmountOfItemsThatHasCategories() {

        String[] expectedValue = demo.imprimirQuantidadeItemPorCategoria();
        ArrayList<String> l1 = new ArrayList<>(Arrays.asList(expectedValue));
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        for (String k : l1) {
            Integer j = map1.get(k);
            map1.put(k, (j == null) ? 1 : j + 1);
        }
        for (Map.Entry<String, Integer> i : map1.entrySet())
            System.out.println("Quantidade de items da categoria (" + i.getKey() + ")" + " :" + i.getValue());
        System.out.println("\n");
    }

    //Retornando a quantidade de itens que não possuem categoria
    @And("the system should display the total amount of items thtat doesn't have categories")
    public void theSystemShouldDisplayTheTotalAmountOfItemsThtatDoesnTHaveCategories() {
        String[] expectedValue = demo.imprimirQuantidadeItemSemCategoria();
        for(int i =0,j=1;i<expectedValue.length;i++,j++)
            System.out.println("Descrição do elemento " + j + " (sem categoria): " + expectedValue[i]);
        System.out.println("Quantidade de items que não possuem categoria: " + expectedValue.length + "\n");
    }

    //Imprimindo o tópico que contém o maior número de views
    @And("the system should display the highest number of views")
    public void theSystemShouldDisplayTheHighestNumberOfViews() {
        String desc = demo.imprimirQuantidadeNumeroViews();
        System.out.println("Título do tópico que contém o maior número de views: " + desc);
    }

}