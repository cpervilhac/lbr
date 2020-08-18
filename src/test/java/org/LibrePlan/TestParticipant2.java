package org.LibrePlan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestParticipant2 {
	static Logger logger = LoggerFactory.getLogger(TestParticipant2.class);
	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	// JEUX DE DONNEES
	String jdd_nomutilisateur = "admin";
	String jdd_mdp = "admin";

	@Before
	public void setup() throws Exception {
		driver = SocleTechnique.choisirNavigateur(logger, ENavigateur.c);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 7000);
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void testCreerUtilisateurJDU() {
		// Se connecter à LibrePlan
		driver.get("http://localhost:8090/libreplan");
		SocleTechnique.seConnecter(jdd_nomutilisateur, jdd_mdp, driver);
		PageIndex page_index = PageFactory.initElements(driver, PageIndex.class);

		// Vérifier la présence de l'onglet Calendrier
		assertEquals("Calendrier ", driver.findElement(By.xpath("//button[contains(.,'Calendrier')]")).getText());

		// Afficher la page Liste des participants
		PageParticipants page_participants = page_index.clickParticipants(driver);
		assertTrue(SocleTechnique
				.isWebElementPresent(driver.findElement(By.xpath("//*[text()='Liste des participants']"))));
		logger.info("Page Liste des participants affichée");

		// Vérifier la présence des colonnes Surnom, Prénom, ID, Code, En file et
		// Opérations
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Surnom')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Prénom')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'ID')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Code')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'En file')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Opérations')]"))));
		logger.info("Les colonnes Surnom, Prénom, ID, Code, En file et Opérations sont affichées");
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//div/span/table/tbody/tr/td[text()='Créer']"))));

		// Vérifier la présence du champ de recherche "Filtré par" et de l'icône de
		// recherche
		assertTrue(SocleTechnique
				.isWebElementPresent(driver.findElement(By.xpath(
						"//table[@class='filtering-area z-hbox']/tbody/tr/td/table/tbody/tr/td[3]/span/i/input")))
				&& SocleTechnique.isWebElementPresent(driver.findElement(By
						.xpath("//table[@class='filtering-area z-hbox']/tbody/tr/td/table/tbody/tr/td[3]/span/i/i"))));
		logger.info("Le champ de recherche et l'icône de recherche sont affichés");

		// Vérifier la présence du champ Détails personnels, des boutons Plus d'options,
		// Filtre et Créer
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//input[@class='z-textbox' and contains(@style,'200')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//td[@class='z-caption-l' and contains(.,'options')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//td[@class='z-button-cm' and contains(.,'Filtre')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//div/span/table/tbody/tr/td[text()='Créer']"))));
		logger.info("Le champ Détails personnels et les boutons Plus d'options, Filtre et Créer sont affichés");

		// Clic sur le bouton Créer
		page_participants.clicCreer();

		// Vérifier le titre de la page et que l'onglet par défaut est Données
		// personnelles
		assertTrue(SocleTechnique
				.isWebElementPresent(driver.findElement(By.xpath("//td[@class='z-caption-l' and contains(.,'Créer')]")))
				&& SocleTechnique.isWebElementPresent(driver
						.findElement(By.xpath("//span[@class='z-tab-text' and contains(.,'Données personnelles')]"))));
		logger.info("le titre de la page est affiché et l'onglet par défaut est Données personnelles");

		// Vérifier la présence des champs Code, Prénom, Nom, ID et du menu déroulant
		// Type
		assertTrue(SocleTechnique
				.isWebElementPresent(driver.findElement(By.xpath("//input[@style='width:350px;' and @disabled]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//table/tbody[2]/tr[2]/td[2]/div/input[contains(@style,'500')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//table/tbody[2]/tr[4]/td[2]/div/input[contains(@style,'500')]")))
				&& SocleTechnique.isWebElementPresent(driver
						.findElement(By.xpath("//table/tbody[2]/tr[5]/td[2]/div/input[contains(@class,'textbox')]")))
				&& SocleTechnique
						.isWebElementPresent(driver.findElement(By.xpath("//div/select[contains(@style,'200')]"))));
		logger.info("Les champs Code, Prénom, Nom, ID et le menu déroulant sont affichés");
		// Vérifier la présence du bloc Utilisateur lié et des boutons radio Non lié,
		// Utilisateur existant, Créer un nouvel utilisateur
		assertTrue(SocleTechnique
				.isWebElementPresent(driver.findElement(By.xpath("//span[@id and contains(.,'Utilisateur lié')]")))
				&& SocleTechnique.isWebElementPresent(driver
						.findElement(By.xpath("//label[@class and contains(.,'Non lié')]/preceding-sibling::input")))
				&& SocleTechnique.isWebElementPresent(driver.findElement(
						By.xpath("//label[@class and contains(.,'Utilisateur existant')]/preceding-sibling::input")))
				&& SocleTechnique.isWebElementPresent(driver.findElement(
						By.xpath("//label[@class and contains(.,'un nouvel utilisateur')]/preceding-sibling::input"))));
		logger.info("Le bloc et les boutons radio sont affichés");
		// Renseigner les informations dans le formulaire

	}

}
