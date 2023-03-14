package sourceCode;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjectModel.GooglePage;

public class GoogleSearchHome {
	static int count=0;
	
	public static String addFile() {
		count++;
		return "sample"+String.valueOf(count)+".png";
	}
	
	public static void screenShot() throws AWTException, IOException {
		Robot robot=new Robot();
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle=new Rectangle(screenSize);
		BufferedImage sourceImage=robot.createScreenCapture(rectangle);
		File destiantionImage=new File(addFile());
		ImageIO.write(sourceImage, "png", destiantionImage);
		
	}

	public static void main(String[] args) throws AWTException, IOException {
		// TODO Auto-generated method stub
	
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		driver.navigate().to("https://www.google.co.in");
		JavascriptExecutor executor=(JavascriptExecutor) driver;
		screenShot();
		
		PageFactory.initElements(driver, GooglePage.class);
		
		GooglePage.searchTag.sendKeys("Pro_Gamer25"+Keys.ENTER);
		screenShot();
		executor.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
		
	
		
		GooglePage.searchTag.clear();
		GooglePage.searchTag.sendKeys("Attack on Titan season"+Keys.ENTER);
		screenShot();
		executor.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
		
		
		List<WebElement>allAuggestions=driver.findElements(By.xpath("//div[@class='s75CSd OhScic AB4Wff']"));
		FileWriter fileWriter=new FileWriter("suggestions.txt");
		BufferedWriter writer=new BufferedWriter(fileWriter);
		for (WebElement webElement : allAuggestions) {
			writer.write(webElement.getText()+"\n");
		}
		
		for (WebElement webElement : allAuggestions) {
			if(webElement.getText().contains("1")) {
				webElement.click();
				screenShot();
				break;
			}
		}
		writer.close();
		driver.quit();
		
		
	}

}
