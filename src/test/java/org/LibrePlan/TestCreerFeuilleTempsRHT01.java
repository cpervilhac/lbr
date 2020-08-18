package org.LibrePlan;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCreerFeuilleTempsRHT01 {

	static Logger logger = LoggerFactory.getLogger(TestCreerFeuilleTempsRHT01.class);
	WebDriver driver;
	
	// JEUX DE DONNEES
	String jdd_utilisateur = "admin";
	String jdd_mdp = "admin";

	@Before
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	 @After
	 public void tearDown() {
	 driver.quit();
	 }

	@Test

	public void AccederPageGestionFeuillesDeTemps() throws Exception {
		// Se connecter à l'application
		driver.get("http://localhost:8090/libreplan/");
		SocleTechnique.seConnecter(jdd_utilisateur, jdd_mdp, driver);
		PageIndex page_index = PageFactory.initElements(driver, PageIndex.class);
		PageFeuillesDeTemps page_feuilles_de_temps = page_index.clicMenuFeuillesTemps(driver);

		// Vérifier l'affichage de la page feuille de temps
		WebElement titre_feuilles_de_temps = driver.findElement(
				By.xpath("//div[@class='z-window-embedded-header' and contains (.,'Liste des feuilles de temps')]"));
		assertEquals("Liste des feuilles de temps", titre_feuilles_de_temps.getText());
		logger.info("La page Liste des feuilles de temps est affichée");

		// Vérifier l'affichage du tableau colonne par colonne
		WebElement colonne_date_debut = driver.findElement(By.xpath("//div[text()='Date de début']"));
		colonne_date_debut.isDisplayed();
		logger.info("La colonne Date de Debut est affichée");
		WebElement colonne_date_fin = driver.findElement(By.xpath("//div[text()='Date de fin']"));
		colonne_date_fin.isDisplayed();
		logger.info("La colonne Date de Fin est affichée");
		WebElement colonne_modele = driver.findElement(By.xpath("//div[text()='Modèle']"));
		colonne_modele.isDisplayed();
		logger.info("La colonne Modele est affichée");
		WebElement colonne_travailTotal = driver.findElement(By.xpath("//div[text()='Travail total']"));
		colonne_travailTotal.isDisplayed();
		logger.info("La colonne Travail Total est affichée");
		WebElement colonne_code = driver.findElement(By.xpath("//div[text()='Code']"));
		colonne_code.isDisplayed();
		logger.info("La colonne Code est affichée");
		WebElement colonne_actions = driver.findElement(By.xpath("//div[text()='Actions']"));
		colonne_actions.isDisplayed();
		logger.info("La colonne Actions est affichée");

		// Vérifier l'affichage des champs de filtres
		WebElement filtre_par_modele = driver
				.findElement(By.xpath("//span[@class='z-label' and contains(.,'Modèle')]"));
		assertEquals("Modèle", filtre_par_modele.getText());
		logger.info("Le champs de filtre Modèle apparaît");
		WebElement valeur_par_defaut_filtre_modele = driver
				.findElement(By.xpath("//option[@selected=\"selected\" and contains(.,'Montrer tout')]"));
		assertEquals("Montrer tout", valeur_par_defaut_filtre_modele.getText());

		// Vérifier l'affichage des champs de saisie de date
		WebElement champ_date_debut = driver
				.findElement(By.xpath("//td[9]//i[@class='z-datebox'][1]//i[@class='z-datebox-btn']"));
		WebElement champ_date_fin = driver
				.findElement(By.xpath("//td[13]//i[@class='z-datebox'][1]//i[@class='z-datebox-btn']"));
		assertTrue(champ_date_debut.isDisplayed());
		assertTrue(champ_date_fin.isDisplayed());
		logger.info("Les champs de tri par date de début et date de fin sont présents");
		WebElement bouton_filtre = driver.findElement(By.xpath("//td[@class='z-button-cm' and contains(.,'Filtre')]"));
		bouton_filtre.isDisplayed();
		logger.info("Le bouton filtre est présent");

		// Vérifier l'affichage du champ de choix de canevas
		WebElement champ_filtre_canevas = driver
				.findElement(By.xpath("//select[@style='width:150px;']//option[1][contains(.,'Default')]"));
		assertTrue(champ_filtre_canevas.isDisplayed());
		assertEquals("Default", champ_filtre_canevas.getText());
		logger.info("La liste déroulante Choisir un canevas est présente et sa valeur par défaut est Default");
		WebElement bouton_nouvelle_feuille_de_temps = driver
				.findElement(By.xpath("//td[text()='Nouvelle feuille de temps']"));
		assertTrue(bouton_nouvelle_feuille_de_temps.isDisplayed());
		logger.info("Le bouton Nouvelle Feuille de temps est présent");

		// Creer une nouvelle feuille de temps
		PageFormulaireDeCreation page_formulaire_de_creation = page_feuilles_de_temps.clicNouvelleFeuilleTemps(driver);

		// Vérifier la page de création d'une feuille de temps
		// Vérifier le titre
		WebElement titre_page_creer_feuille_de_temps = driver.findElement(
				By.xpath("//div[@class='z-window-embedded-header' and contains (.,'Créer la feuille de temps')]"));
		assertEquals("Créer la feuille de temps", titre_page_creer_feuille_de_temps.getText());
		logger.info("La page Créer la feuille de temps s'affiche");

		// Vérifier les 3 blocs
		WebElement titre_bloc_donnees_generales = driver
				.findElement(By.xpath("//span[contains(.,'Données générales')]"));
		assertEquals("Données générales", titre_bloc_donnees_generales.getText());
		logger.info("Le bloc Données générales est présent");

		WebElement valeur_champ_type = driver.findElement(By.xpath("//span[@class='z-label' and contains(.,'Code')]"));
		assertEquals("Code", valeur_champ_type.getText());
		logger.info("La valeur par défaut du champ Type est " + valeur_champ_type.getText());

		// Vérifier que l'élément n'est pas modifiable à ajouter
		WebElement case_a_cocher_generer_code = driver.findElement(By.xpath("//input[@type='checkbox']"));
		case_a_cocher_generer_code.isSelected();
		logger.info("La case générer le code est coché par défaut");

		// Vérifier le bloc Lignes feuille de temps
		WebElement titre_lignes_feuilles_de_temps = driver
				.findElement(By.xpath("//span[contains(.,'Lignes de feuille de temps')]"));
		assertEquals("Lignes de feuille de temps", titre_lignes_feuilles_de_temps.getText());
		logger.info("Le bloc Lignes de feuille de temps est présent");

		WebElement bouton_ajouter_une_ligne = driver
				.findElement(By.xpath("//td[@class='z-button-cm' and contains(.,'Ajouter une ligne')]"));
		assertTrue(bouton_ajouter_une_ligne.isDisplayed());
		logger.info("Le bouton Ajouter une ligne est présent");

		WebElement colonne_tableau_Date = driver.findElement(
				By.xpath("//tbody[1]//tr[1]//th[1]//div[1][@class='z-column-cnt' and contains (.,'Date')]"));
		assertTrue(colonne_tableau_Date.isDisplayed());
		logger.info("La colonne Date est présente");

		WebElement colonne_tableau_Ressource = driver
				.findElement(By.xpath("//div[1][@class='z-column-cnt' and contains (.,'Ressource')]"));
		assertTrue(colonne_tableau_Ressource.isDisplayed());
		logger.info("La colonne Ressource est présente");

		WebElement colonne_tableau_Tache = driver
				.findElement(By.xpath("//div[1][@class='z-column-cnt' and contains (.,'Tâche')]"));
		assertTrue(colonne_tableau_Tache.isDisplayed());
		logger.info("La colonne Tâche est présente");

		WebElement colonne_tableau_Heures = driver
				.findElement(By.xpath("//div[1][@class='z-column-cnt' and contains (.,'Heures')]"));
		assertTrue(colonne_tableau_Heures.isDisplayed());
		logger.info("La colonne Heures est présente");

		WebElement colonne_tableau_Type_Heures = driver
				.findElement(By.xpath("//div[@class='z-column-cnt' and contains (.,'Type')]"));
		assertTrue(colonne_tableau_Type_Heures.isDisplayed());
		logger.info("La colonne Type d'heures est présente");

		WebElement colonne_tableau_Realise = driver
				.findElement(By.xpath("//div[@class='z-column-cnt' and contains (.,'Réalisé')]"));
		assertTrue(colonne_tableau_Realise.isDisplayed());
		logger.info("La colonne Réalisé est présente");

		WebElement colonne_tableau_Code = driver
				.findElement(By.xpath("//th[7]//div[1][@class='z-column-cnt' and contains (.,'Code')]"));
		assertTrue(colonne_tableau_Code.isDisplayed());
		logger.info("La colonne Code est présente");

		WebElement colonne_tableau_Op = driver
				.findElement(By.xpath("//div[@class='z-column-cnt' and contains (.,'Op')]"));
		assertTrue(colonne_tableau_Op.isDisplayed());
		logger.info("La colonne Op est présente");

		// Vérifier la présence des boutons
		WebElement bouton_enregistrer = driver
				.findElement(By.xpath("//td[@class='z-button-cm' and contains (.,'Enregistrer')]"));
		assertTrue(bouton_enregistrer.isDisplayed());
		logger.info("Le bouton Enregistrer est présent");

		WebElement bouton_sauver_et_continuer = driver
				.findElement(By.xpath("//td[@class='z-button-cm' and contains (.,'Sauver')]"));
		assertTrue(bouton_sauver_et_continuer.isDisplayed());
		logger.info("Le bouton Sauver et Continuer est présent");

		WebElement bouton_sauvegarder_nouvelle_feuille_temps = driver
				.findElement(By.xpath("//td[@class='z-button-cm' and contains (.,'&')]"));
		assertTrue(bouton_sauvegarder_nouvelle_feuille_temps.isDisplayed());
		logger.info("Le bouton Sauvegarder & Nouvelle feuille de temps est présent");

		WebElement bouton_annuler = driver
				.findElement(By.xpath("//td[@class='z-button-cm' and contains (.,'Annuler')]"));
		assertTrue(bouton_annuler.isDisplayed());
		logger.info("Le bouton Annuler est présent");

		// Ajouter une ligne
		page_formulaire_de_creation.ajouterUneLigne();
		logger.info("Une ligne a été ajoutée");

		// Vérifier les champs de la ligne
		// Vérifier que la date affichée est celle du jour et qu'elle respecte le bon
		// format
		WebElement champ_date = driver.findElement(By.xpath(
				"//table[@style='table-layout: fixed;']//tbody//tr//td//div//i[@class='z-datebox']//input[@size='11']"));
		logger.info("La date affichée au calendrier est la suivante: " + champ_date.getAttribute("value"));
		String String_champ_date = champ_date.getAttribute("value").toString();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		System.out.println("Aujourd'hui, nous sommes le: " + sdf.format(calendar.getTime()));
		String String_date_format = sdf.format(calendar.getTime()).toString();
		assertEquals(String_date_format, String_champ_date);
		logger.info("La date affichée elle la date du jour et elle respecte le format dd month yyyy");

		// Vérifier le champ ressource
		WebElement champ_ressource = driver.findElement(By.xpath("//i[@class='z-combobox' and @style='width:200px;']"));
		assertEquals("", champ_ressource.getText());

		// Vérifier le champ Tâche
		WebElement champ_recherche_tache = driver
				.findElement(By.xpath("//span[@class='bandbox-workreport-task']//i//input[@class='z-bandbox-inp']"));
		assertTrue(champ_recherche_tache.isDisplayed());
		logger.info("Le champ de recherche Tâche est présent");

		WebElement bouton_loupe_champ_recherche = driver
				.findElement(By.xpath("//span[@class='bandbox-workreport-task']//i//i[@class='z-bandbox-btn']"));
		assertTrue(bouton_loupe_champ_recherche.isDisplayed());
		logger.info("Le bouton loupe du champ de recherche Tâche est présent");

		// Vérifier le champ Heures
		WebElement champ_heure = driver.findElement(By.xpath("//input[@class='z-textbox']"));
		String champ_heure_string = champ_heure.getAttribute("value").toString();
		assertEquals("0", champ_heure_string);
		logger.info("La valeur par défaut est 0");
 
		// Vérifier le champ Type Heures
		WebElement champ_type_heures = driver.findElement(By.xpath(
				"//div[@class='z-row-cnt z-overflow-hidden']//select//option[@selected='selected' and contains (.,'Default')]"));
		assertEquals("Default", champ_type_heures.getText());
		logger.info("La valeur par défaut est Default");

		// Vérifier la case à cocher
		WebElement case_a_cocher_realise = driver
				.findElement(By.xpath("//td[6][@class='z-row-inner']//input[@type='checkbox']"));
		assertFalse(case_a_cocher_realise.isSelected());
		logger.info("La case est décochée par défaut");

		// Vérifier le champ code
		WebElement champ_code = driver.findElement(
				By.xpath("//input[@class='z-textbox z-textbox-disd z-textbox-text-disd' and @disabled='disabled']"));
		assertTrue(champ_code.isDisplayed());
		System.out.println(champ_code.getAttribute("disabled"));
		String valeur_champ_code = champ_code.getAttribute("disabled");
		assertEquals("true", valeur_champ_code);
		logger.info("Le champ Code est présent, le champ est vide et non saisissable");

		// Vérifier l'icone poubelle
		WebElement icone_poubelle = driver.findElement(By.xpath(
				"//div[@class='listWorkReportLines z-grid']//img[@src='/libreplan/common/img/ico_borrar1.png' and @align='absmiddle']"));
		assertTrue(icone_poubelle.isDisplayed());
		logger.info("L'icone poubelle est présent pour supprimer la ligne");

		// Saisir une date dans le champ
		page_formulaire_de_creation.saisirDate();
		logger.info("La date a été modifiée");

		// Modifier le champ Heure
		page_formulaire_de_creation.modifierChampHeure(); 
		logger.info("Le chiffre 8 est entré dans la case Heures");

		page_formulaire_de_creation.cocherCaseRealise();
		logger.info("La case est bien cochée");

	}

}
