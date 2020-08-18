package org.LibrePlan;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageTypesDAvancement extends BandeauMenu {

	static Logger logger = LoggerFactory.getLogger(PageTypesDAvancement.class);
	Actions action;

	// WEBELEMENTS
	// Boutons et icônes
	@FindBy(xpath = "//td[text()='Créer' and contains(@class,'button-cm')]")
	private WebElement bouton_creer;
	@FindBy(xpath = "//td[text()='Enregistrer' and contains(@class,'button-cm')]")
	private WebElement bouton_enregistrer;
	@FindBy(xpath = "//td[text()='Sauver et continuer' and contains(@class,'button-cm')]")
	private WebElement bouton_sauver;
	@FindBy(xpath = "//td[text()='Annuler' and contains(@class,'button-cm')]")
	private WebElement bouton_annuler;
	@FindBy(xpath = "//span[text()='Modifier' and contains(@class,'tab-text')]")
	private WebElement titretableau_modifier;

	// Champs et menus
	@FindBy(xpath = "//div[contains(@id,'cell')]/input[@type='text' and contains(@style,'300px')]")
	private WebElement champ_nomdesaisie;
	@FindBy(xpath = "//tr[3]/td/div/input[contains(@class,'decimalbox')]")
	private WebElement champ_valeurmax;
	@FindBy(xpath = "//tr[4]/td/div[contains(@class,'z-row-cnt z-overflow-hidden')]/input[contains(@class, 'z-decimalbox')]")
	private WebElement champ_precision;

	// Checkboxes
	@FindBy(xpath = "//div[contains(@style,'1854')]/table/tbody[2]/tr[contains(@class,'grid-odd')][1]/td[2]/div[contains(@class, 'z-row-cnt')]/span[@class='z-checkbox']/input[@type='checkbox']")
	private WebElement checkbox_actif;
	@FindBy(xpath = "//div[contains(@style,'1854')]/table/tbody[2]/tr[contains(@class,'grid-odd')][3]/td[2]/div[contains(@class, 'z-row-cnt')]/span[@class='z-checkbox']/input[@type='checkbox']")
	private WebElement checkbox_pourcentage;

	// Panel d'erreur d'exécution
	@FindBy(xpath = "//div[@class='z-panel-header ' and contains(.,'You should be in creation')]")
	private WebElement fenetre_erreurexe;
	@FindBy(xpath = "//td[@class='z-button-cm' and contains(.,'Continuer')]")
	private WebElement bouton_erreurcontinuer;

	// Tableau Types d'avancement
	@FindBy(xpath = "//tbody/tr/th/div[@class='z-column-cnt' and contains(.,'Nom')]")
	private WebElement colonne_nom;
	@FindBy(xpath = "//tbody/tr/th/div[@class='z-column-cnt' and contains(.,'Activé')]")
	private WebElement colonne_active;
	@FindBy(xpath = "//tbody/tr/th/div[@class='z-column-cnt' and contains(.,'Prédéfini')]")
	private WebElement colonne_predefini;
	@FindBy(xpath = "//tbody/tr/th/div[@class='z-column-cnt' and contains(.,'Opérations')]")
	private WebElement colonne_operations;

	// Tableau Modifier
	@FindBy(xpath = "//span[@class='z-tab-text' and contains(.,'Modifier')]")
	private WebElement tableau_modifier;

	// Titres
	@FindBy(xpath = "//div[@class='z-window-embedded-header' and contains(.,'avancement Liste')]")
	private WebElement titre_typesDAvancement;
	@FindBy(xpath = "//td[@align='left' and contains(.,'Créer Type')]")
	private WebElement titre_creerTypeDAvancement;

	// METHODES
	// Méthodes de clics sur boutons
	public void clicCreer() {
		bouton_creer.click();
		logger.info("Clic du bouton Créer effectué");
	}

	public void clicSauver() {
		bouton_sauver.click();
		logger.info("Clic du bouton Sauver effectué");
	}

	public void clicEnregistrer() {
		bouton_enregistrer.click();
		logger.info("Clic du bouton Enregistrer effectué");
	}

	public void clicAnnuler() {
		bouton_annuler.click();
		logger.info("Clic du bouton Annuler effectué");
	}

	public void clicChampNom() {
		champ_nomdesaisie.click();
	}

	public void erreurExe() {
		if (fenetre_erreurexe.isDisplayed()) {
			bouton_erreurcontinuer.click();
			logger.warn(
					"Affichage du message : You should be in creation or edition mode to use this method (code 500)");
		}
	}

	// Méthodes de gestion des checkboxes
	public void decocherCheckboxActif() {
		if (checkbox_actif.isSelected()) {
			checkbox_actif.click();
			logger.info("La checkbox est décochée");
		}
	}

	public void decocherCheckboxPourcentage() {
		if (checkbox_pourcentage.isSelected()) {
			checkbox_pourcentage.click();
			logger.info("La checkbox est décochée");
		}
	}

	public void cocherCheckboxPourcentage() {
		if (checkbox_pourcentage.isSelected()) {
			logger.info("La checkbox est cochée");
		} else
			checkbox_pourcentage.click();
		logger.info("La checkbox est décochée");
	}

}
