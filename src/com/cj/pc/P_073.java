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
 * Name : TC_73
 * Scenario : 마이존 > 로그인
 * Assertion : 패밀리 Text 체크
 *
 */

public class P_073 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String P_URL = null;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		P_URL = sp.getProperty("P_URL");
	}

	@Test
	public void p_068() throws Exception {
		driver.get(P_URL);
		//로그인 페이지 접근
		driver.findElement(By.xpath("//*[@id=\"go_myzone\"]/span")).click();
		System.out.println("로그인페이지 접근");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).sendKeys("jsjjoo88");
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).sendKeys("whtjdwn1!");
		driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]")).click();
		Thread.sleep(5000);
		
		Thread.sleep(3000);
		// text check
        if ("패밀리".equals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[1]/div/div[1]")).getText())) {
           System.out.println("TC_073 PASS");
           assertTrue(true);
           return;
        } else {
           System.out.println("TC_073 FAIL");
           assertTrue(false);
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

}
