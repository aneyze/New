package stepDefinitionsWeb2;

import Web2.HomePageTrivago;
import Web2.ReservationPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class StepsDefinitionsWeb2 {

    private WebDriver driver;
    HomePageTrivago home;
    ReservationPage reserva;


    @Before
    public void beforeScenario(){
        String projectPath = System.getProperty("user.dir");   // return project folder path
        String chromePath = projectPath + "\\libs\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        //Iniciar o driver para interagir com o chrome
        this.driver = new ChromeDriver();
        //Mazimiza o browser
        driver.manage().window().maximize();
        //Declaração de objetos
        home = new HomePageTrivago(driver);
        reserva = new ReservationPage(driver);
    }

    @After
    public void afterScenario(){
        //Fechar o browser
        this.driver.quit();
    }

    //Enviar a url para abrir o browser
    @Given("a web browser is opened")
    public void aWebBrowserIsOpened() {
        home.accessarPagina();
    }

    //Digitar o valor "Natal" no campo busca
    @When("the user enters {string} on Search Field")
    public void theUserEntersOnSearchField(String arg0) {
        home.informarTextoPesquisa(arg0);
    }

    //Selecionar a opção "Quarto Individual"
    @And("the user selects option Individual Room")
    public void theUserSelectsOptionIndividualRoom() {
        home.selecionarQuartoIndividual();
    }

    //Clicar no botão "Pesquisar"
    @And("the user clicks on Search")
    public void theUserClicksOnSearch() {
        home.clicarPesquisar();
    }

    //Selecionar a opção "Ordenar apenas por Distância"
    @And("the user selects option Only Distance")
    public void theUserSelectsOptionOnlyDistance() {
        reserva.ordenarApenasPorDistancia();
    }

    //Imprimir o nome do hotel
    @Then("the system should display {string} as Hotel's Name")
    public void theSystemShouldDisplayHotelSName(String arg0) {
        String expectedValue = reserva.imprimirNomeHotel();
        Assert.assertEquals(arg0, expectedValue);
        System.out.println("\nNome do Hotel (encontrado): " + expectedValue);
        System.out.println("\nNome do Hotel (esperado): " + arg0);
    }

    //Imprimir textualmente a quantidade de estrelas que ele possui (por exemplo 5 estrelas)
    @And("the system should display {string} as Accommodation Room Type because the hotel doesn't have starts")
    public void theSystemShouldDisplayAsAccommodationRoomTypeBecauseTheHotelDoesnTHaveStarts(String arg0) {
        String expectedValue = reserva.imprimirQuantidadeEstrelas();
        System.out.println("\nEstrelas: " + "O hotel não possui estrelas.");
        System.out.println("\nTipo de acomodação: " + expectedValue);
    }

    //Imprimir o nome do site da oferta
    @And("the system should display the site responsible by the offer")
    public void theSystemShouldDisplayTheSiteResponsibleByTheOffer() {
        String expectedValue = reserva.imprimirSiteOferta();
        System.out.println("\nOferta da Empresa (encontrado): " + expectedValue);
    }

    //Imprimir o valor do quarto
    @And("the system should display the price for the selected room")
    public void theSystemShouldDisplayThePriceForTheSelectedRoom() {
        String expectedValue = reserva.imprimirValorQuarto();
        reserva.clicarLocalizacao();
        reserva.clicarVIsualizarTodasComodidades();
        System.out.println("\nPreço do Quarto: " + expectedValue);
    }

    //Imprimir as comodidades do quarto
    @And("the system should display Room's comodations")
    public void theSystemShouldDisplayRoomSComodations() {
            List<WebElement> expectedValue = reserva.imprimirComodidadesQuarto();
            String[] s1 = new String[expectedValue.size()];
            int n=expectedValue.size();
            for(int i=0; i<n; i++){
                s1[i] = expectedValue.get(i).getText();
                System.out.println("Comodidades do Quarto: " + s1[i]);
            }

    }



}
