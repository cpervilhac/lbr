package org.LibrePlan;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageProjet {

	WebDriver driver;
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	// WEBELEMENTS
	// Boutons et icônes
	@FindBy(xpath = ("//tr[@class='z-row z-grid-odd']//i[contains(@class,'z-datebox-btn')]"))
	private WebElement bouton_date_debut;
	@FindBy(xpath = ("//tr[@class='z-row']//i[contains(@class,'z-datebox-btn')]"))
	private WebElement bouton_date_echeance;
	@FindBy(xpath = ("//td[.='Accepter']"))
	private WebElement bouton_accepter;

	// Champs et menus
	@FindBy(xpath = ("//input[contains(@class,'z-textbox')]"))
	private WebElement champ_nom_projet;
	@FindBy(xpath = ("//td[1]/input[contains(@class,'z-textbox')]"))
	private WebElement champ_code_projet;
	@FindBy(xpath = ("//div[1]/i[contains(@class,'z-datebox')]/input[contains(@class,'z-datebox-inp')]"))
	private WebElement champ_date_debut;
	@FindBy(xpath = ("//tr[@class='z-row z-row-over']//i[contains(@class,'z-datebox')]/input[contains(@class,'z-datebox-inp')]"))
	private WebElement champ_date_echeance;
	@FindBy(xpath = ("//input[@class='z-textbox z-textbox-disd z-textbox-text-disd'][@disabled='disabled']"))
	private WebElement valeur_par_defaut;

	// Checkboxes
	@FindBy(xpath = ("//span[@class='z-checkbox']/input[@type='checkbox'][@checked='checked']"))
	private WebElement checkbox_code;

	// Messages
	@FindBy(xpath = ("//div[@class='z-errbox-right z-errbox-close']"))
	private WebElement message_erreur;
	@FindBy(xpath = ("//div[contains(@class,'z-errbox-right z-errbox-close z-errbox-close-over')]"))
	private WebElement message_close;

	// Titres
	@FindBy(xpath = ("//td[.='Détail du projet']"))
	private WebElement titre;

	// METHODES
	// Méthodes pour récupérer/gérer des informations temporelles
	public void selectDateDebut(WebDriver driver) throws ParseException {
		bouton_date_debut.click();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 5);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		String String_date_format = sdf.format(calendar.getTime()).toString();
		String nouvelle_date_debut = String_date_format;
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(nouvelle_date_debut));
		System.out.println(nouvelle_date_debut = sdf.format(c.getTime()));
		champ_date_debut.clear();
		champ_date_debut.sendKeys(nouvelle_date_debut);
	}

	public void selectDateEcheance(WebDriver driver) throws ParseException {
		bouton_date_echeance.click();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 15);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		String String_date_format = sdf.format(calendar.getTime()).toString();
		String nouvelle_date_echeance = String_date_format;
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(nouvelle_date_echeance));
		System.out.println(nouvelle_date_echeance = sdf.format(c.getTime()));
		champ_date_echeance.clear();
		champ_date_echeance.sendKeys(nouvelle_date_echeance);
	}

	// Méthodes pour renseigner et vérifier des champs
	public void saisirNomProjet(String n) {
		SocleTechnique.renseignerChamps(champ_nom_projet, n);
		champ_nom_projet.isDisplayed();
	}

	public void saisirCodeProjet(String c) {
		champ_code_projet.click();
		champ_code_projet.clear();
		champ_code_projet.sendKeys(c);
	}

	public boolean isDisabled() {
		if (valeur_par_defaut.isEnabled()) {
		}
		return !(valeur_par_defaut.isEnabled());
	}

	// Méthodes de gestion des checkboxes
	public boolean isCheckboxSelectionne() {
		return checkbox_code.isSelected();
	}

	public boolean deselectionnecheckbox(int i) {
		if (checkbox_code.isSelected()) {
			checkbox_code.click();
		}
		return !(checkbox_code.isSelected());
	}

	// Méthodes de clics sur boutons
	public boolean accepter() {
		bouton_accepter.click();
		return titre.isDisplayed();
	}

}
