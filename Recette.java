package sne.shopperise.selenium.basic;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Recette {
	
	WebDriver driver;
	
	public void invokeBrowser(String url){
		try {
			System.setProperty("webdriver.chrome.driver", "/Users/bambadia/Desktop/Projets/Selenium/selenium_driver/chromedriver");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies(); // Delete all cookies
			driver.manage().window().maximize(); // Maximize windows
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			driver.get(url);
			creationEnvieShopping("Envie shopping  Test 14/12/2017"); // Drag and Drop  Test
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean IsElementPresent(WebDriver driver, By locator)
    {
        try
        {
            driver.findElement(locator);
            //If element is found set the timeout back and return true
            return true;
        }
        catch (Exception NoSuchElementException)
        {
            //If element isn't found, set the timeout and return false
            return false;
        }
    }
	public void loginSuccess(WebDriver driver, By locatorLogin, By locatorPassword, By locatorSubmit){
	
		try {
			driver.findElement(locatorLogin).sendKeys("cabdia");
			driver.findElement(locatorPassword).sendKeys("7894561230");
			driver.findElement(locatorSubmit).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void creationEnvieShopping(String wishlistTitle){
		
		try {
			// Go to catalog page
			driver.navigate().to("https://test.shopperise.com/catalog");
			
		    System.out.println("Drag and Drop started :");
		    Thread.sleep(5000);

		    if(IsElementPresent(driver, By.xpath(".//*[@id='__next']/div/section/section/div[1]/section/div/div/div/div[2]/a")))
		    {
				driver.findElement(By.xpath(".//*[@id='__next']/div/section/section/div[1]/section/div/div/div/div[2]/a")).click();
				Thread.sleep(1000);
				if(!isAuthenticate()) {
						loginSuccess(driver, By.xpath("//input[@type='text']"),By.xpath("//input[@type='password']"),By.xpath("//button[@type='submit']"));
					}

		    }
		    Actions actions = new Actions(driver);
		    // Add Title to the wishlist
		    driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/section/section/div[1]/section/div/div/div/div[2]/section/form/section/div[1]/div[2]/input")).sendKeys(wishlistTitle);

		    // Select a delivery mode for the wishlist

		    WebElement mydeliveryList = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/section/section/div[1]/section/div/div/div/div[2]/section/form/section/div[2]/div[2]/select"));
		    Select dropdownDeliveryList= new Select(mydeliveryList);
		    dropdownDeliveryList.selectByIndex(1);

		    // Select En date of the wishlist
		    selectDateTimePicker();
            
            // Drag an Drop The 3 first products on the Wishlistbox
            
		    WebElement srcElement1 = driver.findElement(By.xpath("//div[@data-index = '0']"));
		    WebElement srcElement2 = driver.findElement(By.xpath("//div[@data-index = '1']"));
		    WebElement srcElement3 = driver.findElement(By.xpath("//div[@data-index = '3']"));;
		    WebElement targetElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/section/section/div[1]/section/div/div/div/div[2]/section/form/section/div[4]")); 
		    actions.dragAndDrop(srcElement1, targetElement);
		    actions.dragAndDrop(srcElement2, targetElement);
		    actions.dragAndDrop(srcElement3, targetElement); 
		    actions.build().perform();
		    System.out.println("Drag and Drom completed :");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	public boolean isAuthenticate() {
        try
        {
            driver.findElement(By.linkText("Déconnexion"));
            return true;
        }
        catch (Exception NoSuchElementException)
        {
            return false;
        }
	}
	
	public void selectDateTimePicker()
	{
		 WebElement dateBox = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/section/section/div[1]/section/div/div/div/div[2]/section/form/section/div[3]/div[2]/div/div[1]/div/input"));
		 dateBox.click();
		 dateBox.sendKeys(Keys.TAB);
	}
	public static void main(String[] args) {
		 Recette myObj = new Recette();
		 myObj.invokeBrowser("https://test.shopperise.com");
		 


	}

}

