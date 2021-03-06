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
 * @author 조성주 Date : 2017-06-13 
 * Subject : CJ Mall 운영 
 * Name : TC_28
 * Scenario :임의의 상품 > 바로구매 선택
 * Assertion : 로그인 버튼 Text 체크
 *
 */

public class P_028 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String P_URL = null;
	private String PRODUCT = null;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		P_URL = sp.getProperty("P_URL");
		PRODUCT = sp.getProperty("PRODUCT");
	}

	@Test
	public void p_028() throws Exception {
		driver.get(P_URL);

		driver.findElement(By.id("srh_keyword")).clear();
		driver.findElement(By.id("srh_keyword")).sendKeys(PRODUCT);
		driver.findElement(By.cssSelector("button._search")).click();
		System.out.println("상품 진입 성공");
		driver.findElement(By.xpath("//*[@id='lst_cate_result']/li[1]/a/span[1]")).click();
		boolean isExist1 = false;
		String jjim = null;
		isExist1 = existElement(driver, By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div[2]/div[3]/div[3]/a"), "찜");
		if (isExist1) {
			jjim=  "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div[3]/div[3]/a";
			System.out.println("1");
		} else {
			jjim=  "//*[@id='content']/div[2]/div[1]/div[2]/div[2]/div[3]/a";
			System.out.println("2");
		}
		driver.findElement(By.xpath(jjim)).click();
		System.out.println("바로구매 성공");
		Thread.sleep(3000);
		// Text 체크
		
		if ("로그인".equals(driver.findElement(By.xpath("//*[@id='content']/div[1]/h2")).getText())) {
			System.out.println("TC_28 Pass");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_28 Fail");
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
