package pageObjectModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage {

	@FindBy(name="q")
	public static WebElement searchTag;
}
