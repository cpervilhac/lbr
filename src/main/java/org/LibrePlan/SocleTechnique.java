package org.LibrePlan;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocleTechnique {

	static Logger logger = LoggerFactory.getLogger(SocleTechnique.class);
	WebDriver driver;

	// Méthode pour choisir le navigateur
	public static WebDriver choisirNavigateur(Logger logger, ENavigateur navigateur) {
		switch (navigateur) {
		case f:
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			return new FirefoxDriver();
		case c:
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
			return new ChromeDriver();
		case e:
			System.setProperty("webdriver.edge.driver", "src/main/resources/driver/msedgedriver.exe");
			return new EdgeDriver();
		case ie:
			System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
			return new InternetExplorerDriver();
		default:
			logger.warn("le navigateur choisi n'existe pas");
			return null;
		}
	}

	// Méthode pour effacer et remplir un champ
	public static void renseignerChamps(WebElement we, String s) {
		we.clear();
		we.sendKeys(s);
	}

	// Méthode pour vérifier la présence d'un WebElement via xpath
	public static boolean isWebElementPresent(WebElement we) {
		boolean resultat = we.isDisplayed();
		return resultat;
	}

	// Méthode pour se connecter via un champ Utilisateur et un champ Mot de passe
	public static void seConnecter(String utilisateur, String mdp, WebDriver driver) {
		WebElement champ_utilisateur = driver.findElement(By.xpath("//input[@name='j_username']"));
		WebElement champ_motdepasse = driver.findElement(By.xpath("//input[@name='j_password']"));
		WebElement bouton_connexion = driver.findElement(By.xpath("//input[@name='button']"));
		champ_utilisateur.clear();
		champ_utilisateur.sendKeys(utilisateur);
		champ_motdepasse.clear();
		champ_motdepasse.sendKeys(mdp);
		bouton_connexion.click();
	}

}
