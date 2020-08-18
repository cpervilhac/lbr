package org.LibrePlan;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAdministrationDesCriteresCRI01 {
	static Logger logger = LoggerFactory.getLogger(TestAdministrationDesCriteresCRI01.class);
	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	// JEUX DE DONNEES
	String jdd_utilisateur = "admin";
	String jdd_motdepasse = "admin";
	String jdd_checkboxvaleurs = "valeurs";
	String jdd_checkboxhierarchie = "hierarchie";
	String jdd_checkboxactive = "active";
	String jdd_nomcritereannule = "Critère - Test bouton [Annuler]";
	String jdd_descriptioncritereannule = "Critère - Test bouton [Annuler]";
	String jdd_nomcritereenregistre = "Critère - Test bouton [Enregistrer]";
	String jdd_descriptioncritereenregistre = "Critère - Test bouton [Enregistrer]";
	String jdd_nomcriteresauver = "Critère - Test bouton [Sauver et continuer]";
	String jdd_descriptioncriteresauver = "Critère - Test bouton [Sauver et continuer]";
	String jdd_nomcriteresauversecond = "Critère - Test bouton [Sauver et continuer] 2";

	@Before
	public void setUp() {
		driver = SocleTechnique.choisirNavigateur(logger, ENavigateur.f);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 7000);
		action = new Actions(driver);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void test() {
		driver.get("http://localhost:8090/libreplan/common/layout/login.zul");

		// Nettoyage, renseignenement champs et connexion
		SocleTechnique.seConnecter(jdd_utilisateur, jdd_motdepasse, driver);

		// Instanciation PageIndex et verification Page d'accueil
		PageIndex page_index = PageFactory.initElements(driver, PageIndex.class);
		assertTrue(page_index.isMessagePresent());
		logger.info("La page d'accueil s'affiche");

		// Appel méthode clicRessourceCritere Instanciation PageCritere
		PageCritere page_critere = page_index.clicRessourceCritere(driver);
		assertTrue(driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Nom')]")).isDisplayed()
				&& driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Code')]")).isDisplayed()
				&& driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Type')]")).isDisplayed()
				&& driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Activé')]")).isDisplayed()
				&& driver.findElement(By.xpath("//div[@class='z-column-cnt' and contains(.,'Opérations')]"))
						.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//td[@class='z-button-cm'][contains(.,'Créer')]")).isDisplayed());
		logger.info("Les éléments de la page Critère s'affichent");

		// Appel méthode clicRessourceCritere; Instanciation PageCreerCritere;
		PageCreerCritere page_creer_critere = page_critere.clicBoutonCreer(driver);

		// vérification présence onglet modifier et présence du tableau contenant le
		// formulaire de saisie
		String onglet_modifier = driver.findElement(By.xpath("//span[@class='z-tab-text' and contains(.,'Modifier')]"))
				.getText();
		assertEquals("Modifier", onglet_modifier);
		assertTrue(driver.findElement(By.xpath("//div[@class='z-tabpanels']")).isDisplayed());
		logger.info("L'onglet Modifier et le tableau s'affichent");

		// Vérification de la pésence des boutons Enregistrer, Sauver et Continuer et
		// Annuler
		assertTrue(
				driver.findElement(By.xpath("//td[@class='z-button-cm'and contains(.,'Enregistrer')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//td[@class='z-button-cm'and contains(.,'Sauver et continuer')]"))
				.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//td[@class='z-button-cm'and contains(.,'Annuler')]")).isDisplayed());
		logger.info("Les boutons Enregistrer, Sauver et Continuer et Annuler s'affichent");

		// Appel méthode Remplir Nom formulaire pas de Test 4 Annuler
		page_creer_critere.rempliNomCritere(jdd_nomcritereannule);

		// Appel méthode Remplir Déscription formulaire pas de Test 4 Annuler
		page_creer_critere.rempliDescriptionCritere(jdd_descriptioncritereannule);

		// Appel méthode coche checkbox si non coche
		page_creer_critere.verificationClickCheckbox(jdd_checkboxvaleurs);
		page_creer_critere.verificationClickCheckbox(jdd_checkboxhierarchie);
		page_creer_critere.verificationClickCheckbox(jdd_checkboxactive);
		page_creer_critere.clicBoutonAnnuler(driver);
		page_critere.clicBoutonCreer(driver);

		// Appel méthode Remplir Nom formulaire pas de Test 5 Enregistrer
		page_creer_critere.rempliNomCritere(jdd_nomcritereenregistre);

		// Appel méthode Remplir Déscription formulaire pas de Test 5 Enregistrer
		page_creer_critere.rempliDescriptionCritere(jdd_descriptioncritereenregistre);

		// Appel méthode coche checkbox si non coche
		page_creer_critere.verificationClickCheckbox(jdd_checkboxvaleurs);
		page_creer_critere.verificationClickCheckbox(jdd_checkboxhierarchie);
		page_creer_critere.verificationClickCheckbox(jdd_checkboxactive);

		// Appel methode clic bouton enregistrer
		page_creer_critere.clicBoutonEnregistrer(driver);

		// Vérification présence critère Critère - Test bouton [Enregistrer]
		assertTrue(driver.findElement(By.xpath("//span[@title='Critère - Test bouton [Enregistrer]']")).isDisplayed());

		// Appel methode bouton creer
		page_critere.clicBoutonCreer(driver);

		// Appel méthode Remplir Nom formulaire pas de Test 7 Sauver
		page_creer_critere.rempliNomCritere(jdd_nomcriteresauver);

		// Appel méthode Remplir Déscription formulaire pas de Test 7 Sauver
		page_creer_critere.rempliDescriptionCritere(jdd_descriptioncriteresauver);

		// Appel méthode coche checkbox si non coche
		page_creer_critere.verificationClickCheckbox(jdd_checkboxvaleurs);
		page_creer_critere.verificationClickCheckbox(jdd_checkboxhierarchie);
		page_creer_critere.verificationClickCheckbox(jdd_checkboxactive);
		page_creer_critere.clicBoutonSauverContinuer(driver);

		// Vérification page message "Type de critère "Critère - Test bouton [Sauver et
		// continuer]" enregistré" apparent
		assertEquals("Type de critère \"Critère - Test bouton [Sauver et continuer]\" enregistré", driver.findElement(By
				.xpath("//div[@class='message_INFO' and contains(.,'Type de critère \"Critère - Test bouton [Sauver et continuer]\" enregistré')]"))
				.getText());

		// Vérifcation titre du titre de la page modifiée
		assertEquals("Modifier Type de critère: Critère - Test bouton [Sauver et continuer]",
				driver.findElement(By.xpath("//td[@class='z-caption-l']")).getText());
		page_creer_critere.clicBoutonAnnuler(driver);

		// Verification présence critère Critère - Test bouton [Sauver et continuer]
		assertTrue(driver.findElement(By.xpath("//span[@title='Critère - Test bouton [Sauver et continuer]']"))
				.isDisplayed());

		// Appel méthode clic bouton éditer sans sauvegarde
		page_critere.clicBoutonEditer(driver);

		// Vérification page message "Type de critère "Critère - Test bouton [Sauver et
		// continuer]" enregistré" apparent
		assertEquals("Type de critère \"Critère - Test bouton [Sauver et continuer]\" enregistré", driver.findElement(By
				.xpath("//div[@class='message_INFO' and contains(.,'Type de critère \"Critère - Test bouton [Sauver et continuer]\" enregistré')]"))
				.getText());

		// Appel méthode Remplir Nom formulaire pas de Test 10 modificiation puis annule
		page_creer_critere.rempliNomCritere(jdd_nomcriteresauversecond);
		page_creer_critere.clicBoutonAnnuler(driver);

		// Verification présence critère Critère - Test bouton [Sauver et continuer]
		assertTrue(driver.findElement(By.xpath("//span[@title='Critère - Test bouton [Sauver et continuer]']"))
				.isDisplayed());
		page_critere.clicNomCritere(driver);

		// Vérifcation titre du titre de la page pas de Test 11
		assertEquals("Modifier Type de critère: Critère - Test bouton [Sauver et continuer]",
				driver.findElement(By.xpath("//td[@class='z-caption-l']")).getText());

		// Appel méthode Remplir Nom formulaire pas de Test 12 et 13 modificiation puis
		// sauvegarde continuer
		page_creer_critere.rempliNomCritere(jdd_nomcriteresauversecond);

		// Appel methode clic bouton sauver et contineur
		page_creer_critere.clicBoutonSauverContinuer(driver);

		// Vérification page message "Type de critère "Critère - Test bouton [Sauver et
		// continuer] 2" enregistré" apparent
		assertEquals("Type de critère \"Critère - Test bouton [Sauver et continuer] 2\" enregistré",
				driver.findElement(By.xpath(
						"//div[@class='message_INFO' and contains(.,'Type de critère \"Critère - Test bouton [Sauver et continuer] 2\" enregistré')]"))
						.getText());

		// Verification titre page modifiée et apparition message Type de critère
		// "Critère - Test bouton [Sauver et continuer] 2" enregistré
		assertEquals("Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2",
				driver.findElement(By.xpath("//td[@class='z-caption-l']")).getText());

		// Appel méthode clic bouton Annuler
		page_creer_critere.clicBoutonAnnuler(driver);

		// Verification présence Critère - Test bouton [Sauver et continuer] 2 Pas 14
		assertEquals("Critère - Test bouton [Sauver et continuer] 2",
				driver.findElement(By.xpath("//span[@title='Critère - Test bouton [Sauver et continuer]']")).getText());

		// Appel methoe clic bouton supprimer
		page_critere.clicBoutonSupprimer(driver);

		// Verification affichage texte popup
		assertEquals("Supprimer Type de critère \"Critère - Test bouton [Sauver et continuer] 2\". Êtes-vous sûr ?",
				driver.findElement(By.xpath(
						"//div[@class='z-window-modal-cl']//div[@class='z-window-modal-cnt']//tbody//span[@class='z-label']"))
						.getText());

		// Verification précense bouton Ok message popup
		assertEquals("OK",
				driver.findElement(By.xpath(
						"//div[@class='z-window-modal-cl']//tbody//td[@class='z-button-cm' and contains(.,'OK')]"))
						.getText());

		// Verification précense bouton Annuler message popup
		assertEquals("Annuler",
				driver.findElement(By.xpath(
						"//div[@class='z-window-modal-cl']//tbody//td[@class='z-button-cm' and contains(.,'Annuler')]"))
						.getText());
		page_critere.clicAnnulerBoutonPopUp(driver);

		// Vérification page message "Type de critère "Critère - Test bouton [Sauver et
		// continuer] 2" enregistré" toujours présent dans le tableau Pas 16
		assertEquals("Type de critère \"Critère - Test bouton [Sauver et continuer] 2\" enregistré",
				driver.findElement(By.xpath(
						"//div[@class='message_INFO' and contains(.,'Type de critère \"Critère - Test bouton [Sauver et continuer] 2\" enregistré')]"))
						.getText());

		// Appel méthode clic bouton supprimer Pas 17
		page_critere.clicBoutonSupprimer(driver);

		// Verification affichage texte popup
		assertEquals("Supprimer Type de critère \"Critère - Test bouton [Sauver et continuer] 2\". Êtes-vous sûr ?",
				driver.findElement(By.xpath(
						"//div[@class='z-window-modal-cl']//div[@class='z-window-modal-cnt']//tbody//span[@class='z-label']"))
						.getText());

		// Verification précense bouton Ok message popup
		assertEquals("OK",
				driver.findElement(By.xpath(
						"//div[@class='z-window-modal-cl']//tbody//td[@class='z-button-cm' and contains(.,'OK')]"))
						.getText());

		// Verification précense bouton Annuler message popup
		assertEquals("Annuler",
				driver.findElement(By.xpath(
						"//div[@class='z-window-modal-cl']//tbody//td[@class='z-button-cm' and contains(.,'Annuler')]"))
						.getText());
		page_critere.clicOkBoutonPopUp(driver);
	}
}
