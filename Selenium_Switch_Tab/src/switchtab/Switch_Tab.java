package switchtab;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Switch_Tab {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.letskodeit.com/practice");
        
        WebElement openTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='opentab']")));
        openTab.click();
        waitForTheUser();
        
        // Get the main tab handle
        String mainTabHandle = driver.getWindowHandle();
        
        // Get all tab handles
        Set<String> allTabHandles = driver.getWindowHandles();
        
        String openedTabHandle = null;
        
        // Switch to the new tab
        for (String handle : allTabHandles) {
        	if (!handle.equals(mainTabHandle)) {
        		driver.switchTo().window(handle);
        		openedTabHandle = handle; // Save the handle of the opened tab
        		System.out.println("Switched to new tab: " + driver.getTitle());
        		break;
        	}
        }
        waitForTheUser();
        
        // Scroll
        JavascriptExecutor js_scrolldownone = (JavascriptExecutor) driver;
        js_scrolldownone.executeScript("window.scrollBy(0,1000)");
        waitForTheUser();
        JavascriptExecutor js_scrolldowntwo = (JavascriptExecutor) driver;
        js_scrolldowntwo.executeScript("window.scrollBy(0,1000)");
        waitForTheUser();
        JavascriptExecutor js_scrollupone = (JavascriptExecutor) driver;
  	    js_scrollupone.executeScript("window.scrollBy(0,-1000)");
  	    waitForTheUser();
  	    JavascriptExecutor js_scrolluptwo = (JavascriptExecutor) driver;
	    js_scrolluptwo.executeScript("window.scrollBy(0,-1000)");
	    waitForTheUser();
        
        // Switch back to the main tab
        driver.switchTo().window(mainTabHandle);
        System.out.println("Switched back to main tab: " + driver.getTitle());
        waitForTheUser();
        
        // Switch back to the opened tab
        if (openedTabHandle != null) {
        	driver.switchTo().window(openedTabHandle);
        	System.out.println("Switched to previously opened tab: " + driver.getTitle());
        }
        waitForTheUser();
        
        // Close the opened tab
        driver.close();
        System.out.println("Closed the opened tab.");
        
        // Switch back to the main tab to continue further actions
        driver.switchTo().window(mainTabHandle);
        System.out.println("Switched back to main tab: " + driver.getTitle());
        waitForTheUser();
        
        driver.quit();
	}
	
	public static void waitForTheUser() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
