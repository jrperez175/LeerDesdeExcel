package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.java.en.Then;

public class LoginTest {
	WebDriver driver;
	
	@Test(dataProvider = "getData") //corre test y de donde voy sacar los datos de la funcion
	public void loginTest(String email, String password) {
		
		//WebDriver driver;
		
		System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String url = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		driver.manage().window().maximize();
		driver.get(url);
		
		//Ingresa la informacion para la pagina
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys(email);
		driver.findElement(By.name("ctl00$MainContent$txtPassword")).sendKeys(password);
		driver.findElement(By.id("MainContent_btnLogin")).click();
		
		//Validacion
		String resultado = driver.findElement(By.id("conf_message")).getText();
		assertEquals("Logged in successfully", resultado);

		driver.close();
	}
	
	@DataProvider
	public String[][] getData(){
		return util.Excel.get("C:\\Users\\jrperez\\eclipse-workspace\\ProyectoLeerDesdeExcel\\UserLogin.xls");
	}
}
