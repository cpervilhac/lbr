package org.LibrePlan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFeuillesDeTemps extends BandeauMenu {

	// WEBELEMENTS
	// Boutons et icônes
	@FindBy(xpath = "//td[text()='Nouvelle feuille de temps']")
	private WebElement bouton_cliquable_nouvelle_feuille_de_temps;

	// METHODES
	// Méthodes de clics sur boutons
	public PageFormulaireDeCreation clicNouvelleFeuilleTemps(WebDriver driver) {
		bouton_cliquable_nouvelle_feuille_de_temps.click();
		return PageFactory.initElements(driver, PageFormulaireDeCreation.class);
	}
}
