package org.LibrePlan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageIndex extends BandeauMenu {

	static Logger logger = LoggerFactory.getLogger(PageIndex.class);
	WebDriver driver;
	Actions action;

	// WEBELEMENTS
	// Boutons et icônes
	@FindBy(xpath = ("//img[@src='/libreplan/common/img/ico_add.png']"))
	private WebElement bouton_creation_projet;

	// Champs et menus
	@FindBy(xpath = "//a[@href='/libreplan/workreports/workReport.zul']")
	private WebElement menu_feuille_temps;

	// Elements de page
	@FindBy(xpath = ("//body[@class='safari safari537']"))
	private WebElement body_accueil;

	// Onglets
	@FindBy(xpath = "//button[@class='z-menu-btn' and contains(.,'Coût')]")
	private WebElement onglet_cout;
	@FindBy(xpath = "//button[contains(.,'Calendrier')]")
	private WebElement onglet_calendrier;

	// METHODES
	// Méthodes de navigation
	public PageParticipants clickParticipants(WebDriver driver) {
		action = new Actions(driver);
		action.moveToElement(onglet_ressources).build().perform();
		action.moveToElement(lien_participants).build().perform();
		lien_participants.click();
		return PageFactory.initElements(driver, PageParticipants.class);
	}

	public PageTypesDAvancement clickTypesDAvancement(WebDriver driver) {
		action = new Actions(driver);
		action.moveToElement(onglet_ressources).build().perform();
		action.moveToElement(lien_types_davancement).build().perform();
		lien_types_davancement.click();
		return PageFactory.initElements(driver, PageTypesDAvancement.class);
	}

	public PageFeuillesDeTemps clicMenuFeuillesTemps(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(onglet_cout).build().perform();
		action.moveToElement(menu_feuille_temps).build().perform();
		menu_feuille_temps.click();
		return PageFactory.initElements(driver, PageFeuillesDeTemps.class);
	}

	public PageProjet creationNouveauProjet(WebDriver driver) {
		bouton_creation_projet.click();
		return PageFactory.initElements(driver, PageProjet.class);
	}

	public PageCritere clicRessourceCritere(WebDriver driver) {
		WebElement ongletRessource = driver.findElement(By.xpath("//button[contains(.,'Ressources')]"));
		Actions a = new Actions(driver);
		a.moveToElement(ongletRessource).build().perform();
		driver.findElement(By.xpath("//a[contains(@href,'criterions.zul')]")).click();
		return PageFactory.initElements(driver, PageCritere.class);
	}

	// Méthodes de vérification
	public boolean isMessagePresent() {
		boolean resultat = SocleTechnique.isWebElementPresent(onglet_calendrier);
		return resultat;
	}

}
