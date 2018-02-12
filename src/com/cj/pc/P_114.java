package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;
/**
 * 
 * @author 조성주 Date : 2017-10-27
 * Subject : CJ Mall 운영 
 * Name : TC_78
 * Scenario : 카테고리 > 대분류 선택 > 상품상세
 * Assertion : 상품 이미지 확인
 *
 */


public class P_114 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String P_URL = null;
	private String ID_1 = null;
	private String PW_1 = null;
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		P_URL = sp.getProperty("P_URL");
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
	}

	@Test
	public void p_075() throws Exception {
		 driver.get("http://gw.sta.co.kr");
		    driver.findElement(By.id("UserName")).clear();
		    driver.findElement(By.id("UserName")).sendKeys("dmlim");
		    driver.findElement(By.id("Password")).clear();
		    driver.findElement(By.id("Password")).sendKeys("@wlim4474");
		    Thread.sleep(3000);
		    driver.findElement(By.cssSelector("img[alt=\"로그인\"]")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.cssSelector("li.out_time")).click();
		    Thread.sleep(3000);
		

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}
	public boolean isAlertPresent(){
	    boolean foundAlert = false;
	    WebDriverWait wait = new WebDriverWait(driver, 0 /*timeout in seconds*/);
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        foundAlert = true;
	    } catch (TimeoutException eTO) {
	        foundAlert = false;
	    }
	    return foundAlert;
	}
	
}
