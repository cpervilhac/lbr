package org.LibrePlan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageCritere extends BandeauMenu {

	static Logger logger = LoggerFactory.getLogger(PageCritere.class);

	// WEBELEMENTS
	// Boutons et icônes
	@FindBy(xpath = "//td[@class='z-button-cm'][contains(.,'Créer')]")
	private WebElement bouton_creer;
	@FindBy(xpath = "//tr[3][@class='clickable-rows z-row']/td[5][ @class='z-row-inner']/div//span[1][@title='Modifier']//td[@class='z-button-cm']")
	private WebElement bouton_editer;
	@FindBy(xpath = "//tr[3][@class='clickable-rows z-row']/td[5][ @class='z-row-inner']/div//span[1][@title='Supprimer']//td[@class='z-button-cm'] ")
	private WebElement bouton_supprimer;
	@FindBy(xpath = "//div[@class='z-window-modal-cl']//tbody//td[@class='z-button-cm' and contains(.,'OK')]")
	private WebElement bouton_okpopup;
	@FindBy(xpath = "//div[@class='z-window-modal-cl']//tbody//td[@class='z-button-cm' and contains(.,'Annuler')]")
	private WebElement bouton_annulerpopup;

	// Textes
	@FindBy(xpath = "//span[@title='Critère - Test bouton [Sauver et continuer]']")
	private WebElement nom_critere;
	@FindBy(xpath = "//div[@class='z-window-modal-cl']//div[@class='z-window-modal-cnt']//tbody//span[@class='z-label']")
	private WebElement element_textepopup;

	// METHODES
	// Méthodes de clics sur boutons
	public PageCreerCritere clicBoutonCreer(WebDriver driver) {
		bouton_creer.click();
		return PageFactory.initElements(driver, PageCreerCritere.class);
	}

	public void clicNomCritere(WebDriver driver) {
		nom_critere.click();
	}

	public void clicBoutonEditer(WebDriver driver) {
		bouton_editer.click();
	}

	public void clicBoutonSupprimer(WebDriver driver) {
		bouton_supprimer.click();
	}

	public void clicOkBoutonPopUp(WebDriver driver) {
		bouton_okpopup.click();
	}

	public void clicAnnulerBoutonPopUp(WebDriver driver) {
		bouton_annulerpopup.click();
	}

}
