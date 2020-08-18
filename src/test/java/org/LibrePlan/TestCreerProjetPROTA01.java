package org.LibrePlan;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCreerProjetPROTA01 {
	WebDriver driver;
	WebDriverWait wait;
	static Logger logger = LoggerFactory.getLogger(TestCreerProjetPROTA01.class);
	
	// JEUX DE DONNEES
	String jdd_utilisateur = "admin";
	String jdd_mdp = "admin";
	String jdd_nom_projet = "PROJET_TEST1";
	String jdd_code_projet = "PRJTEST001";
	int jdd_checkbox = 0;

	@Before
	public void setup() {
		driver = SocleTechnique.choisirNavigateur(logger, ENavigateur.c);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 7000);
	}

	@After
	public void teardown() {
		 driver.quit();
	}

	@Test
	public void test() throws ParseException {
		// Se connecter à l'application
		driver.get("http://localhost:8090/libreplan");
		SocleTechnique.seConnecter(jdd_utilisateur, jdd_mdp, driver);
		PageIndex page_index = PageFactory.initElements(driver, PageIndex.class);
		logger.info("l'utilisateur est connecté");
		
		//
		PageProjet page_projet = page_index.creationNouveauProjet(driver);
		assertEquals("", driver.findElement(By.xpath("//input[contains(@class,'z-textbox')]")).getText());
		logger.info("le champ de saisie nom n'est pas renseigné");
		page_projet.saisirNomProjet(jdd_nom_projet);
		assertEquals("",driver.findElement(By.xpath("//tr[2][contains(@class,'z-row z-grid-odd')]/td[2]")).getText());
		logger.info("la liste déroulante du champ modèle n'est pas renseignée");
		assertTrue(page_projet.isDisabled());
		logger.info("le champ de saisie code est renseigné par une valeur par défaut et non modifiable");
		assertTrue(page_projet.isCheckboxSelectionne());
		logger.info("la case génerer le code est cochée par défaut");
		page_projet.deselectionnecheckbox(jdd_checkbox);
		page_projet.saisirCodeProjet(jdd_code_projet);
		page_projet.selectDateDebut(driver);
		page_projet.selectDateEcheance(driver);
		assertEquals("", driver.findElement(By.xpath("//tr[6][contains(@class,'z-row z-grid-odd')]/td[2]")).getText());
		logger.info("le champ de saisie client n'est pas renseigné");
		assertEquals("Accepter", driver.findElement(By.xpath("//td[.='Accepter']")).getText());
		logger.info("le bouton accepter est affiché");
		page_projet.accepter();
		assertEquals("Détail du projet", driver.findElement(By.xpath("//td[.='Détail du projet']")).getText());
		logger.info("Détail du projet est affiché");
		assertTrue(driver.findElement(By.xpath("//span[.='WBS (tâches)']")).isDisplayed());
		logger.info("WBS (tâches) est affiché");

	}

}
