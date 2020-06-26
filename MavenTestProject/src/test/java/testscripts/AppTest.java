package testscripts;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alm.pages.JtsLogin;
import com.alm.utils.ReadProperty;
import com.alm.utils.Setupdriver;





/**
 * Unit test for simple App.
 */
public class AppTest 
{
	ReadProperty propreader = new ReadProperty("src/main/resources/Config.properties");
	
	WebDriver driver;
	String driverType = propreader.getString("browserType");
	String app_url=propreader.getString("app_url");
	
	
	@BeforeTest
	public void beforetest() {
		Setupdriver setUp = new Setupdriver();
		driver=setUp.init_webDriver(driverType, app_url);
		
	}
	
	
    /**
     * Rigorous Test :-)
//     */
    @Test
    public void login()
    {
    	System.out.println(driverType);
    	System.out.println(app_url);
    	System.out.println(propreader.getString("userField"));
    	System.out.println(propreader.getString("passField"));
    	System.out.println(propreader.getString("loginbtn"));
    	JtsLogin login = new JtsLogin(driver);
    	login.inputUserId();
    	login.inputPassword();
    	login.signIn();
    	
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
    	Thread.sleep(5000);
    	driver.quit();
    }
}
