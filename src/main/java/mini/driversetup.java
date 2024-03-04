package mini;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class driversetup {
	public static WebDriver driver;
	
	public static WebDriver getWebDriver()
	{
		WebDriver driver=new ChromeDriver();
		return driver;
	}
	
}
