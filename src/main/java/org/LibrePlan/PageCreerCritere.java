package org.LibrePlan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageCreerCritere extends BandeauMenu {

	static Logger logger = LoggerFactory.getLogger(PageCreerCritere.class);

	// WEBELEMENTS
	// Boutons et icônes
	@FindBy(xpath = "//td[@class='z-button-cm'and contains(.,'Annuler')]")
	private WebElement bouton_annuler;
	@FindBy(xpath = "//td[@class='z-button-cm'and contains(.,'Enregistrer')]")
	private WebElement bouton_enregistrer;
	@FindBy(xpath = "//td[@class='z-button-cm'and contains(.,'Sauver et continuer')]")
	private WebElement bouton_sauvercontinuer;
	@FindBy(xpath = "//tr[3][@class='clickable-rows z-row']/td[5][ @class='z-row-inner']/div//span[1][@title='Modifier']//td[@class='z-button-cm']")
	private WebElement bouton_editer;
	@FindBy(xpath = "//tr[3][@class='clickable-rows z-row']/td[5][ @class='z-row-inner']/div//span[1][@title='Supprimer']//td[@class='z-button-cm'] ")
	private WebElement bouton_supprimer;
	@FindBy(xpath = "//div[@class='z-window-modal-cl']//tbody//td[@class='z-button-cm' and contains(.,'OK')]")
	private WebElement bouton_okpopup;
	@FindBy(xpath = "//div[@class='z-window-modal-cl']//tbody//td[@class='z-button-cm' and contains(.,'Annuler')]")
	private WebElement bouton_annulerpopup;

	// Checkboxes
	@FindBy(xpath = "//tr[3][@class='z-row']/td[@class='z-row-inner']/div/span/input[@type='checkbox']")
	private WebElement checkbox_valeurs;
	@FindBy(xpath = "//tr[4][@class='z-row z-grid-odd']/td[@class='z-row-inner']/div/span/input[@type='checkbox']")
	private WebElement checkbox_hierarchie;
	@FindBy(xpath = "//tr[5][@class='z-row']/td[@class='z-row-inner']/div/span/input[@type='checkbox']")
	private WebElement checkbox_active;

	// Champs et menus
	@FindBy(xpath = "//div[@class='z-row-cnt z-overflow-hidden']/input[@class='z-textbox']")
	private WebElement champ_nom;
	@FindBy(xpath = "//input[@class='z-combobox-inp']")
	private WebElement liste_type_participant;
	@FindBy(xpath = "//textarea")
	private WebElement zone_texte_descprition;

	// METHODES
	// Méthodes pour renseigner et vérifier des champs
	public void rempliNomCritere(String ncritere) {
		SocleTechnique.renseignerChamps(champ_nom, ncritere);
	}

	public void rempliDescriptionCritere(String description) {
		SocleTechnique.renseignerChamps(zone_texte_descprition, description);
	}

	// Méthodes pour s'assurer qu'une checkbox est cochée
	public void verificationClickCheckbox(String c) {
		if (checkbox_valeurs != null) {
		} else {
			checkbox_valeurs.click();
		}

	}

	// Méthodes de clics sur boutons
	public void clicBoutonAnnuler(WebDriver driver) {
		bouton_annuler.click();
	}

	public void clicBoutonEnregistrer(WebDriver driver) {
		bouton_enregistrer.click();
	}

	public void clicBoutonSauverContinuer(WebDriver driver) {
		bouton_sauvercontinuer.click();
	}

}
