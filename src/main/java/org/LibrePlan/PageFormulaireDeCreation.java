package org.LibrePlan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageFormulaireDeCreation extends BandeauMenu {

	static Logger logger = LoggerFactory.getLogger(PageFormulaireDeCreation.class);
	WebDriver driver;

	// WEBELEMENTS
	// Boutons et icônes
	@FindBy(xpath = "//td[@class='z-button-cm' and contains(.,'Ajouter une ligne')]")
	private WebElement bouton_ajouter_une_ligne;
	@FindBy(xpath = "//i[@class='z-combobox' and @style='width:200px;']//i[@class='z-combobox-btn']")
	private WebElement fleche_cliquable_champ_ressource;
	@FindBy(xpath = "//div[@class='listWorkReportLines z-grid']//i[@class='z-bandbox-btn' and @style='user-select: none;']")
	private WebElement bouton_loupe_tache;
	@FindBy(xpath = "//div[@class='z-errbox-right z-errbox-close']")
	private WebElement zone_croix;
	@FindBy(xpath = "//div[@class='z-errbox-right z-errbox-close-over']")
	private WebElement croix_fermer;
	@FindBy(xpath = "//div[@class='z-errbox-right z-errbox-close']")
	private WebElement croix_fermer_popup;

	// Champs et menus
	@FindBy(xpath = "//table[@style='table-layout: fixed;']//tbody//tr//td//div//i[@class='z-datebox']//input[@size='11']")
	private WebElement champ_date;
	@FindBy(xpath = "//input[@class='z-combobox-inp' and @style='width: 178px;']")
	private WebElement champ_ressource;
	@FindBy(xpath = "//tr[@class='z-listitem z-listitem-seld']//div[@class='z-listcell-cnt z-overflow-hidden']")
	private WebElement champ_selection_tache;
	@FindBy(xpath = "//input[@class='z-textbox']")
	private WebElement champ_heure;

	// Checkboxes
	@FindBy(xpath = "//td[6][@class='z-row-inner']//input[@type='checkbox']")
	private WebElement case_a_cocher_realise;

	// METHODES
	// Méthodes de clics sur boutons
	public void ajouterUneLigne() {
		bouton_ajouter_une_ligne.click();
	}

	public void cocherCaseRealise() {
		case_a_cocher_realise.click();
	}

	// Méthodes pour récupérer/gérer des informations temporelles
	public void saisirDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		System.out.println("Aujourd'hui, nous sommes le: " + sdf.format(calendar.getTime()));
		String String_date_format = sdf.format(calendar.getTime()).toString();

		String nouvelle_date = String_date_format;
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(nouvelle_date));
		c.add(Calendar.DATE, 3); // number of days to add
		System.out.println(nouvelle_date = sdf.format(c.getTime()));

		champ_date.clear();
		champ_date.sendKeys(nouvelle_date);
	}

	public void modifierChampHeure() {
		champ_heure.clear();
		champ_heure.sendKeys("8");

	}

}
