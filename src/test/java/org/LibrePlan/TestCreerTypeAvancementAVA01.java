package org.LibrePlan;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.LibrePlan.BddConnexion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCreerTypeAvancementAVA01 {

	static Logger logger = LoggerFactory.getLogger(TestCreerTypeAvancementAVA01.class);
	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	// JEUX DE DONNEES
	String jdd_nomutilisateur = "admin";
	String jdd_mdp = "admin";
	String jdd_champ_valeurmax = "10,00";
	String jdd_champ_nom1 = "Type avancement - Test 1";
	String jdd_champ_nom2 = "Type avancement - Test 2";

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
	public void testAvancement() throws Exception {
		// Se connecter à LibrePlan
		driver.get("http://localhost:8090/libreplan");
		SocleTechnique.seConnecter(jdd_nomutilisateur, jdd_mdp, driver);
		PageIndex page_index = PageFactory.initElements(driver, PageIndex.class);

		// Vérifier la présence de l'onglet Calendrier
		assertEquals("Calendrier ", driver.findElement(By.xpath("//button[contains(.,'Calendrier')]")).getText());

		// Accéder à la page Types d'avancement
		PageTypesDAvancement page_type_davancement = page_index.clickTypesDAvancement(driver);

		// Vérifier la présence du titre de la page
		assertEquals("Types d'avancement Liste",
				driver.findElement(
						By.xpath("//div[@class='z-window-embedded-header' and contains(.,'avancement Liste')]"))
						.getText());
		logger.info("La page Types d'avancement List est bien affichée");

		// Vérifier la présence des colonnes Nom, Activé, Prédéfini et Opérations
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//tbody/tr/th/div[@class='z-column-cnt' and contains(.,'Nom')]")))
				&& SocleTechnique.isWebElementPresent(driver
						.findElement(By.xpath("//tbody/tr/th/div[@class='z-column-cnt' and contains(.,'Activé')]")))
				&& SocleTechnique.isWebElementPresent(driver
						.findElement(By.xpath("//tbody/tr/th/div[@class='z-column-cnt' and contains(.,'Prédéfini')]")))
				&& SocleTechnique.isWebElementPresent(driver.findElement(
						By.xpath("//tbody/tr/th/div[@class='z-column-cnt' and contains(.,'Opérations')]"))));
		logger.info("Les colonnes Nom, Activé, Prédéfini et Opérations sont affichées");

		// Vérifier la présence du bouton Créer
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//td[text()='Créer' and contains(@class,'button-cm')]"))));
		logger.info("Le bouton Créer est affiché");

		// Créer un nouvel avancement
		page_type_davancement.clicCreer();
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//td[text()='Annuler' and contains(@class,'button-cm')]"))));

		// Vérifier la présence du tableau Modifier
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//span[@class='z-tab-text' and contains(.,'Modifier')]"))));
		logger.info("Le tableau Modifier est affiché");

		// Vérifier la présence de la checkbox Actif (cochée par défaut)
		assertTrue(driver.findElement(By.xpath(
				"//div[contains(@style,'1854')]/table/tbody[2]/tr[contains(@class,'grid-odd')][1]/td[2]/div[contains(@class, 'z-row-cnt')]/span[@class='z-checkbox']/input[@type='checkbox']"))
				.isSelected());
		logger.info("La checkbox Actif est affichée et cochée");

		// Vérifier la présence du formulaire Valeur maximum
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//tr[3]/td/div/input[contains(@class,'decimalbox')]"))));
		logger.info("Le champ Valeur maximum est affiché");

		// Vérifier la présence du formulaire Précision
		assertTrue(SocleTechnique.isWebElementPresent(driver.findElement(By.xpath(
				"//tr[4]/td/div[contains(@class,'z-row-cnt z-overflow-hidden')]/input[contains(@class, 'z-decimalbox')]"))));
		logger.info("Le champ Précision est affiché");

		// Vérifier la présence de la mention User
		assertTrue(SocleTechnique
				.isWebElementPresent(driver.findElement(By.xpath("//span[@class='z-label' and contains(.,'User')]"))));
		logger.info("La mention User est présente");

		// Vérifier la présence de la checkbox Pourcentages (décochée par défaut)
		assertFalse(driver.findElement(By.xpath(
				"//div[contains(@style,'1854')]/table/tbody[2]/tr[contains(@class,'grid-odd')][3]/td[2]/div[contains(@class, 'z-row-cnt')]/span[@class='z-checkbox']/input[@type='checkbox']"))
				.isSelected());
		logger.info("La checkbox Pourcentages est affichée et décochée");

		// Vérifier la présence des boutons Enregistrer, Sauver et Annuler
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//td[text()='Enregistrer' and contains(@class,'button-cm')]")))
				&& SocleTechnique.isWebElementPresent(driver
						.findElement(By.xpath("//td[text()='Sauver et continuer' and contains(@class,'button-cm')]")))
				&& SocleTechnique.isWebElementPresent(
						driver.findElement(By.xpath("//td[text()='Annuler' and contains(@class,'button-cm')]"))));
		logger.info("Les boutons Enregistrer, Sauver et Annuler sont affichés");

		// Renseigner le nom d'unité
		SocleTechnique.renseignerChamps(
				driver.findElement(
						By.xpath("//div[contains(@id,'cell')]/input[@type='text' and contains(@style,'300px')]")),
				jdd_champ_nom1);

		// Décocher la checkbox Actif
		page_type_davancement.decocherCheckboxActif();

		// Renseigner le champ Valeur maximum
		SocleTechnique.renseignerChamps(
				driver.findElement(By.xpath("//tr[3]/td/div/input[contains(@class,'decimalbox')]")),
				jdd_champ_valeurmax);

		// Laisser la checkbox Pourcentages décochée
		page_type_davancement.decocherCheckboxPourcentage();

		// Enregistrer
		page_type_davancement.clicEnregistrer();

		// Explicit wait
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//td[text()='Créer' and contains(@class,'button-cm')]"))));

		// Vérifier la présence du titre de la page
		assertEquals("Types d'avancement Liste",
				driver.findElement(
						By.xpath("//div[@class='z-window-embedded-header' and contains(.,'avancement Liste')]"))
						.getText());
		logger.info("La page Types d'avancement List est bien affichée");

		// Vérifier l'apparition du message
		SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//span[@class='z-label' and contains(.,'Test 1\" enregistré')]")));

		// Vérifier la présence du nom de "Test 1"
		assertTrue(SocleTechnique
				.isWebElementPresent(driver.findElement(By.xpath("//span[text()='Type avancement - Test 1']"))));
		logger.info("Le titre Type avancement - Test 1 est bien affiché");

		// Vérifier si la checkbox Activé de "Test 1" est présente et décochée
		assertTrue(SocleTechnique.isWebElementPresent(driver.findElement(By.xpath(
				"//tbody/tr[6]/td[2]/div/span/input[@type='checkbox' and @disabled='disabled' and not(@checked)]"))));
		logger.info("La checkbox Activé de Test avancement - Test 1 est décochée");

		// Vérifier si la checkbox Prédéfini de "Test 1" est présente et décochée
		assertTrue(SocleTechnique.isWebElementPresent(driver.findElement(By.xpath(
				"//tbody/tr[6]/td[3]/div/span/input[@type='checkbox' and @disabled='disabled' and not(@checked)]"))));
		logger.info("La checkbox Prédéfini de Test avancement - Test 1 est décochée");

		// Vérifier si l'icône d'édition est présente pour "Test 1"
		SocleTechnique.isWebElementPresent(driver.findElement(By.xpath(
				"//tr[6]/td/div/table/tbody/tr/td/table/tbody/tr/td/span/table/tbody/tr[2]/td[2]/img[contains(@src,'editar')]")));
		logger.info("L'icône d'édition est affichée");

		// Vérifier si l'icône de suppression est présente pour "Test 1"
		assertTrue(SocleTechnique.isWebElementPresent(driver.findElement(By.xpath(
				"//span[@class='icono z-button']/table[not(class)]/tbody/tr/td/img[contains(@src,'borrar1')]"))));
		logger.info("L'icône de suppression est affichée");

		// Créer un nouvel avancement
		page_type_davancement.clicCreer();
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//td[text()='Annuler' and contains(@class,'button-cm')]"))));
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//td[@class='z-caption-l' and contains(.,'Créer Type')]"))));

		// Renseigner le nom d'unité
		SocleTechnique.renseignerChamps(
				driver.findElement(
						By.xpath("//div[contains(@id,'cell')]/input[@type='text' and contains(@style,'300px')]")),
				jdd_champ_nom2);

		// Cocher la checkbox Pourcentage
		page_type_davancement.cocherCheckboxPourcentage();

		// Vérifier que le champ Valeur maximum est 'disabled'
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//input[contains(@class,'decimalbox-disd') and @disabled]"))));
		logger.info("Le champ 'Valeur maximum' est disabled et non-modifiable");

		// Sauver
		page_type_davancement.clicSauver();

		// Vérifier l'apparition du message de confirmation
		SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//span[@class='z-label' and contains(.,'Test 2\" enregistré')]")));

		// Vérifier la présence du titre "Modifier Type d'avancement: Type avancement -
		// Test 2"
		assertTrue(SocleTechnique.isWebElementPresent(
				driver.findElement(By.xpath("//td[@class='z-caption-l' and contains(.,'Type avancement - Test 2')]"))));
		logger.info("Le titre Type avancement - Test 2 est bien affiché");

		// Annuler
		page_type_davancement.clicAnnuler();

		// Signaler l'erreur code 500
		// page_type_davancement.erreurExe();

		// Vérifier la présence du titre de la page
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//td[text()='Créer' and contains(@class,'button-cm')]"))));
		assertEquals("Types d'avancement Liste",
				driver.findElement(
						By.xpath("//div[@class='z-window-embedded-header' and contains(.,'avancement Liste')]"))
						.getText());

		// Vérifier la présence du nom de "Test 2"
		SocleTechnique.isWebElementPresent(driver.findElement(By.xpath("//span[text()='Type avancement - Test 2']")));

		// Vérifier si la checkbox Activé de "Test 2" est présente et cochée
		assertTrue(SocleTechnique.isWebElementPresent(driver.findElement(By.xpath(
				"//tr[contains(@class,'grid-odd')][3]/td/div/span/input[@type='checkbox' and @checked='checked']"))));
		logger.info("La checkbox Activé de Test avancement - Test 2 est cochée");

		// Vérifier si la checkbox Prédéfini de "Test 2" est présente et décochée
		assertTrue(SocleTechnique.isWebElementPresent(driver
				.findElement(By.xpath("//tbody/tr[6]/td[3]/div/span/input[@type='checkbox' and not(@checked)]"))));
		logger.info("La checkbox Prédéfini de Test avancement - Test 2 est décochée");

		// Vérifier si l'icône de suppression est présente pour "Test 2"
		assertTrue(SocleTechnique.isWebElementPresent(driver.findElement(By.xpath(
				"//span[@class='icono z-button']/table[not(class)]/tbody/tr/td/img[contains(@src,'borrar1')]"))));
		logger.info("L'icône de suppression est affichée");

		// Vérifier si l'icône d'édition est présente pour "Test 2"
		assertTrue(SocleTechnique.isWebElementPresent(driver.findElement(By.xpath(
				"//tr[6]/td/div/table/tbody/tr/td/table/tbody/tr/td/span/table/tbody/tr[2]/td[2]/img[contains(@src,'editar')]"))));
		logger.info("L'icône d'édition est affichée");
	}

}
