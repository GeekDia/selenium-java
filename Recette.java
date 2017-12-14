package sne.selenium.webdriver.shopperise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Recette {
	
	WebDriver driver;
	
	public void invokeBrowser(){
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cabdia\\Desktop\\Projets\\Selenium\\selenium_drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies(); // Delete all cookies
			driver.manage().window().maximize(); // Maximize windows
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			driver.get("https://test.shopperise.com/catalog");
			//loginSuccess();  // Call the login methods after url opened.
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
	public void loginSuccess(){
	
		driver.findElement(By.className("style__UserLink-j8a192-2")).click();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("cabdia");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("7894561230");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	public void creationEnvieShopping(String wishlistTitle){
		
		try {
            System.out.println("Drag and Drop started :");
            Thread.sleep(5000);
            
            if(IsElementPresent(driver, By.xpath(".//*[@id='__next']/div/section/section/div[1]/section/div/div/div/div[2]/a"))){
            	driver.findElement(By.xpath(".//*[@id='__next']/div/section/section/div[1]/section/div/div/div/div[2]/a")).click();
            	Thread.sleep(5000);
            	         	
            }
            Actions actions = new Actions(driver);
            // Add Title to the wishlist
            driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/section/section/div[1]/section/div/div/div/div[2]/section/form/section/div[1]/div[2]/input")).sendKeys(wishlistTitle);
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
	public static void main(String[] args) {
		 Recette myObj = new Recette();
		 myObj.invokeBrowser();


	}

}
