package org.LibrePlan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BandeauMenu {
	WebDriver driver;
	
	// WEBELEMENTS
	// Images
	@FindBy(xpath = "//img[contains(@src,'logo.png')]")
	WebElement img_logo;	
	
	// Liens du menu
	@FindBy(xpath = "//a[contains(@href,'worker.zul')]")
	WebElement lien_participants;	
	@FindBy(xpath = "//a[contains(@href,'advanceTypes')]")
	WebElement lien_types_davancement;
	
	// Onglets
	@FindBy (xpath = "//button[contains(.,'Calendrier')]")
	WebElement onglet_calendrier;	
	@FindBy(xpath = "//button[@type='button' and contains(.,'Ressources')]")
	WebElement onglet_ressources;
}