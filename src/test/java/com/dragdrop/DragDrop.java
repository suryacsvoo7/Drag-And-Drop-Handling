package com.dragdrop;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import mini.driversetup;
import mini.utils;


public class DragDrop {
	public static WebDriver driver;
	
	public void createDriver()
	{
		driver = driversetup.getWebDriver();
		driver.get("https://demoqa.com/droppable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public int dragdropfunc() throws InterruptedException, IOException
	{
		WebElement drag=driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement drop=driver.findElement(By.xpath("//*[@id='droppable']"));
		Actions act=new Actions(driver);
		act.dragAndDrop(drag, drop).build().perform();
		String text=driver.findElement(By.xpath("//*[@id=\"droppable\"]/p")).getText();
		
		int c=0;
		if(text.equals("Dropped!"))
			c=1;
		
		System.out.println(text);
		Thread.sleep(5000);
		driver.navigate().to("https://demoqa.com/date-picker");
		return c;
	}
	
	public void dateandtime()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd,yyyy h:mm a");
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime nextDayTime = currentDateTime.plusDays(1).withHour(6).withMinute(0).withSecond(0);
		WebElement wel = driver.findElement(By.id("dateAndTimePickerInput"));
		wel.sendKeys(Keys.CONTROL+ "a");
		wel.sendKeys(Keys.DELETE);
		String s= nextDayTime.format(formatter);
		wel.sendKeys(s);
		driver.findElement(By.xpath("//*[@id=\"datePickerContainer\"]/div[2]/div[1]")).click();
		wel.click();
	}
	
	public void excelwrite(int c) throws InterruptedException, IOException
	{
		//driver.findElement(By.xpath("//*[@id='datePickerContainer']/div[2]/div[1]")).click();
		//driver.findElement(By.xpath("//*[@id=\'dateAndTimePickerInput\']")).click();
		Thread.sleep(5000);
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File src = ts.getScreenshotAs(OutputType.FILE);
			
		File trg = new File("C:\\Users\\2304079\\eclipse-workspace\\CIS\\Screenshot\\sc.png");;
		FileUtils.copyFile(src, trg);
		String path ="C:\\Users\\2304079\\eclipse-workspace\\CIS\\src\\test\\resources\\worksheet.xlsx";
		utils.setCellData(path, "Sheet1", 0, 0, "Before Drag");
		utils.setCellData(path, "Sheet1", 0, 1, "After Drag");
		utils.setCellData(path, "Sheet1", 0, 2, "Validate Drop(T/F)");
		utils.setCellData(path, "Sheet1", 1, 0, "Drop here");
		if(c==1)
		{
			utils.setCellData(path, "Sheet1", 1, 1, "Dropped");
			utils.setCellData(path, "Sheet1", 1, 2, "T");
		}
		else
		{
			utils.setCellData(path, "Sheet1", 1, 1, "Drop here");
			utils.setCellData(path, "Sheet1", 1, 2, "F");
		}
		Thread.sleep(3000);
		
	}
	
	public void close()
	{
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		DragDrop obj = new DragDrop();
		obj.createDriver();
		int c=obj.dragdropfunc();
		obj.dateandtime();
		obj.excelwrite(c);
		System.out.println("done");
		obj.close();
		System.out.println("done");
		
	}
	
}





