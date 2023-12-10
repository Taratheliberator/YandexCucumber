package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.appmanager.ApplicationManager;
import org.example.pageobjects.YandexPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MarketStepDefs {

    private ApplicationManager app;
    private YandexPage yandexPage;
    private String target;



    @After
    public void tearDown() {
        app.stop();
    }

    @Given("Пользователь находится на главной странице Яндекс Маркета")
    public void пользователь_находится_на_главной_странице() throws IOException {
        app = new ApplicationManager();
        app.init();
        yandexPage = app.yandex();

    }

    @When("Пользователь переходит в Каталог")
    public void пользователь_переходит_в_Каталог() {
        yandexPage.goToMarket();
    }

    @When("Пользователь переходит в раздел ноутбуков и компьютеров")
    public void пользователь_переходит_в_раздел_ноутбуков_и_компьютеров() {
        yandexPage.goToComputers().goToNotebooks();
    }

    @When("Пользователь открывает фильтры")
    public void пользователь_открывает_фильтры() {
        yandexPage.openFilter();
    }
    @When("Пользователь устанавливает фильтр производителя {string}")
    public void пользователь_устанавливает_фильтр_производителя(String vendors) {
        Arrays.asList(vendors.split(", ")).forEach(yandexPage::setVendorName);
    }

    @When("Пользователь устанавливает ценовой диапазон от {int} до {int}")
    public void пользователь_устанавливает_ценовой_диапазон(int priceFrom, int priceTo) {
        yandexPage.setDownRange(priceFrom).setUpRange(priceTo);
    }

    @Then("Отображается список ноутбуков, соответствующих заданным критериям")
    public void отображается_список_ноутбуков() {
        yandexPage.showResults();


    }
    @Then("Пользователь загружает первую страницу с ноутбуками и проверяет на отображение более 12 ноутбуков")
    public void пользователь_загружает_первую_страницу_с_ноутбуками() {
        yandexPage.loadFirstPageNotebooks();
    }
    @Then("Пользователь загружает все страницы с ноутбуками и выполняет проверку на соответствие условиям фильтра")
    public void пользователь_загружает_все_страницы_с_ноутбуками_и_выполняет_проверку() {
        List<WebElement> firstPageNotebooks = yandexPage.getList();
        System.out.println("\nКоличество загруженных ноутбуков на первой странице: " + firstPageNotebooks.size());
       target = firstPageNotebooks.get(0).getText();
        System.out.println("\nПервый элемент:\n" + target);
        yandexPage.loadAllNotebooks();
        List<WebElement> allNotebooks = yandexPage.getList();
        System.out.println("\nКоличество загруженных ноутбуков на всех страницах: " + allNotebooks.size());

        for (WebElement notebook : allNotebooks) {
            String notebookInfo = notebook.getText();
           assertTrue("\nНоутбук не удовлетворяет условиям фильтра: \n" + notebookInfo, yandexPage.isLaptopValid(notebookInfo));
        }


    }
    @Then("Пользователь проверяет соответствие первого ноутбука условиям фильтра")
    public void пользователь_проверяет_соответствие_первого_ноутбука_условиям_фильтра() {
        yandexPage.validateFirstNotebookModel(target);
    }
}
