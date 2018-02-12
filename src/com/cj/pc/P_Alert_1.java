package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;

/**
 * 
 * @author 조성주 Date : 2017-06-13
 * Subject : CJ Mall 운영 
 * Name : TC_58
 * Scenario :최근본상품 > 쇼핑찜가기 버튼 선택 > 알럿 확인 버튼 선택
 * Assertion : 로그인 버튼 Text 체크
 *
 */

public class P_Alert_1 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String P_URL = null;
	private String PRODUCT = null;
	private String ID_1 = null;
	private String PW_1 = null;
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		P_URL = sp.getProperty("P_URL");
		PRODUCT = sp.getProperty("PRODUCT");
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
	}

	@Test
	public void p_058() throws Exception {
		driver.get(P_URL);
		driver.findElement(By.xpath("//*[@id=\"go_myzone\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).sendKeys(ID_1);
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).sendKeys(PW_1);
		driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]")).click();
		Thread.sleep(3000);
		
		if (isAlertPresent()) {
			driver.switchTo().alert().accept();
			System.out.println("있음");
			Thread.sleep(3000);
		}
		else {
			System.out.println("없음");
			Thread.sleep(3000);
		}
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
	
	public boolean isAlertPresent() {
	    try {
	        driver.switchTo().alert();
	        return true;
	    } // try
	    catch (Exception e) {
	        return false;
	    } // catch
	}
	
}