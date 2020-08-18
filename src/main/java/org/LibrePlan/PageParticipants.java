package org.LibrePlan;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageParticipants extends BandeauMenu {

	static Logger logger = LoggerFactory.getLogger(PageTypesDAvancement.class);
	Actions action;

	// WEBELEMENTS
	// Boutons et icônes
	@FindBy(xpath = "//table[@class='filtering-area z-hbox']/tbody/tr/td/table/tbody/tr/td[3]/span/i/i")
	private WebElement icone_rechercher;
	@FindBy(xpath = "//td[@class='z-caption-l' and contains(.,'options')]")
	private WebElement bouton_plus_doptions;
	@FindBy(xpath = "//td[@class='z-button-cm' and contains(.,'Filtre')]")
	private WebElement bouton_filtres;
	@FindBy(xpath = "//div/span/table/tbody/tr/td[text()='Créer']")
	private WebElement bouton_creer;
	@FindBy(xpath = "//label[@class and contains(.,'un nouvel utilisateur')]/preceding-sibling::input")
	private WebElement bouton_radio_nouvel_utilisateur;
	@FindBy(xpath = "//label[@class and contains(.,'Utilisateur existant')]/preceding-sibling::input")
	private WebElement bouton_radio_utilisateur_existant;
	@FindBy(xpath = "//label[@class and contains(.,'Non lié')]/preceding-sibling::input")
	private WebElement bouton_radio_non_lie;
	@FindBy(xpath = "//td[text()='Sauver et continuer']")
	private WebElement bouton_sauver_continuer;
	@FindBy(xpath = "//td[text()='Enregistrer']")
	private WebElement bouton_enregistrer;
	@FindBy(xpath = "//span[3][@class='cancel-button global-action z-button']/table/tbody/tr[2]/td[text()='Annuler']")
	private WebElement bouton_annuler;

	// Champs et menus
	@FindBy(xpath = "//table[@class='filtering-area z-hbox']/tbody/tr/td/table/tbody/tr/td[3]/span/i/input")
	private WebElement champ_rechercher;
	@FindBy(xpath = "//input[@class='z-textbox' and contains(@style,'200')]")
	private WebElement champ_details_perso;
	@FindBy(xpath = "//span[@class and contains(.,'Prénom')]//parent::div//parent::td/following-sibling::td/div/input")
	private WebElement champ_donnees_prenom;
	@FindBy(xpath = "//input[@style='width:350px;' and @disabled]")
	private WebElement champ_code;
	@FindBy(xpath = "//table/tbody[2]/tr[2]/td[2]/div/input[contains(@style,'500')]")
	private WebElement champ_prenom;
	@FindBy(xpath = "//table/tbody[2]/tr[4]/td[2]/div/input[contains(@style,'500')")
	private WebElement champ_nom;
	@FindBy(xpath = "//table/tbody[2]/tr[5]/td[2]/div/input[contains(@class,'textbox')]")
	private WebElement champ_id;
	@FindBy(xpath = "//div/select[contains(@style,'200')]")
	private WebElement menu_type;
	@FindBy(xpath = "//span[text()='Nom']/parent::div/parent::td/following-sibling::td/div/input[@style='width:500px;' and @class='z-textbox']")
	private WebElement champ_donnees_nom;
	@FindBy(xpath = "//span[@class and contains(.,'ID')]//parent::div//parent::td/following-sibling::td/div/input")
	private WebElement champ_donnees_id;
	@FindBy(xpath = "//span[@class and contains(.,'utilisateur')]//parent::div//parent::td/following-sibling::td/div/input")
	private WebElement champ_donnees_nouvel_utilisateur_nomutilisateur;
	@FindBy(xpath = "//span[@class and contains(.,'Mot de passe')]//parent::div//parent::td/following-sibling::td/div/input")
	private WebElement champ_donnees_nouvel_utilisateur_mdp;
	@FindBy(xpath = "//span[@class and contains(.,'Confirmation')]//parent::div//parent::td/following-sibling::td/div/input")
	private WebElement champ_donnees_nouvel_utilisateur_confirmationmdp;
	@FindBy(xpath = "//span[@class and contains(.,'Email')]//parent::div//parent::td/following-sibling::td/div/input")
	private WebElement champ_donnees_nouvel_utilisateur_email;
	@FindBy(xpath = "//span[text()='Jean']")
	private WebElement nouvelutilisateur_jean;
	@FindBy(xpath = "//span[text()='DU']")
	private WebElement nouvelutilisateur_du;
	@FindBy(xpath = "//span[text()='jdu']")
	private WebElement nouvelutilisateur_jdu;
	@FindBy(xpath = "//span[text()='Période active depuis']/parent::td/following-sibling::td[2][@style='height:100%']/i[1]/input")
	private WebElement champ_periode_active1;
	@FindBy(xpath = "//span[text()='Période active depuis']/parent::td/following-sibling::td[6][@style='height:100%']/i[1]/input")
	private WebElement champ_periode_active2;
	@FindBy(xpath = "//select[not(@style)]")
	private WebElement liste_deroulante_type;
	@FindBy(xpath="//span[text()='Détails personnels']/parent::td/following-sibling::td[2]/input")
	private WebElement champ_details_personnels;

	// Checkboxes
	@FindBy(xpath = "//span[@class='z-checkbox']/input[@type='checkbox']/following-sibling::label[contains(.,'le code')]/ancestor::div[contains(@style,'1272')]")
	private WebElement checkbox_generer_code;

	// Messages
	@FindBy(xpath = "//span[text()='Participant enregistré']")
	private WebElement message_participant_enregistre;

	// Tableau
	@FindBy(xpath = "//div[@class='z-column-cnt' and contains(.,'Surnom')]")
	private WebElement colonne_surnom;
	@FindBy(xpath = "//div[@class='z-column-cnt' and contains(.,'Prénom')]")
	private WebElement colonne_prenom;
	@FindBy(xpath = "//div[@class='z-column-cnt' and contains(.,'ID')]")
	private WebElement colonne_id;
	@FindBy(xpath = "//div[@class='z-column-cnt' and contains(.,'Code')]")
	private WebElement colonne_code;
	@FindBy(xpath = "//div[@class='z-column-cnt' and contains(.,'En file')]")
	private WebElement colonne_enFile;
	@FindBy(xpath = "//div[@class='z-column-cnt' and contains(.,'Opérations')]")
	private WebElement colonne_operations;

	// Titres
	@FindBy(xpath = "//*[text()='Liste des participants']")
	private WebElement titre_participants;
	@FindBy(xpath = "//td[@class='z-caption-l' and contains(.,'Créer')]")
	private WebElement titre_creer_participant;
	@FindBy(xpath = "//span[@class='z-tab-text' and contains(.,'Données personnelles')]")
	private WebElement titre_onglet_donnees_persos;
	@FindBy(xpath = "//span[text()='Données de base']")
	private WebElement titre_bloc_donnees_de_base;
	@FindBy(xpath = "//span[@id and contains(.,'Utilisateur lié')]")
	private WebElement titre_bloc_utilisateur_lie;

	// METHODES
	// Méthodes de clics sur boutons
	public void clicCreer() {
		bouton_creer.click();
	}

	public void clicBoutonRadioCreerNouvelUtilisateur() {
		bouton_radio_nouvel_utilisateur.click();
	}

	public void clicEnregistrer() {
		bouton_enregistrer.click();
	}

	public void clicPlusDoptions() {
		bouton_plus_doptions.click();
	}

	// Méthodes pour renseigner et vérifier des champs
	public void renseignerDonneesNom(String donneesnom) {
		SocleTechnique.renseignerChamps(champ_donnees_nom, donneesnom);
	}

	public void renseignerDonneesPrenom(String donneesprenom) {
		SocleTechnique.renseignerChamps(champ_donnees_prenom, donneesprenom);
	}

	public void renseignerDonneesID(String donneesid) {
		SocleTechnique.renseignerChamps(champ_donnees_id, donneesid);
	}

	public void renseignerNUNom(String nuNom) {
		SocleTechnique.renseignerChamps(champ_donnees_nouvel_utilisateur_nomutilisateur, nuNom);
	}

	public void renseignerNUMdp(String numdp) {
		SocleTechnique.renseignerChamps(champ_donnees_nouvel_utilisateur_mdp, numdp);
	}

	public void renseignerNUConfirmationMdp(String nuconfmdp) {
		SocleTechnique.renseignerChamps(champ_donnees_nouvel_utilisateur_confirmationmdp, nuconfmdp);
	}

	public void renseignerNUEmail(String nuemail) {
		SocleTechnique.renseignerChamps(champ_donnees_nouvel_utilisateur_email, nuemail);
	}

	public void listeType() {
		WebElement listeType = driver.findElement(By.xpath("//select[not(@style)]"));
		Select select = new Select(listeType);
		select.getOptions();
	}
}
