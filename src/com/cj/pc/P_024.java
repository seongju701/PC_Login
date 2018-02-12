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
 * @author 조성주 Date : 2017-06-12 
 *  Subject : CJ Mall 운영
 *  Name : TC_23
 *	Scenario : 임의의 상품 > 쇼핑찜 선택 > 알럿 닫기 선택
 *  Assertion : 로그인 후 찜이 가능합니다. Text 체크
 *
 */

public class P_024 {
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
	public void p_024() throws Exception {
		driver.get(P_URL);

		// 로그인
		driver.findElement(By.id("srh_keyword")).clear();
		driver.findElement(By.id("srh_keyword")).sendKeys(PRODUCT);
		driver.findElement(By.cssSelector("button._search")).click();
		driver.findElement(By.cssSelector("a.link_product > span.img")).click();
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"_RECOMMEND\"]/div[1]/h4"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		Thread.sleep(3000);
		boolean isExist = false;
		String jjim = null;
		isExist = existElement(driver, By.xpath("//*[@id='content']/div[2]/div[1]/div[2]/div[2]/div[1]/a"), "찜");
		if (isExist) {
			jjim=  "//*[@id='content']/div[2]/div[1]/div[2]/div[2]/div[1]/a";
			System.out.println("1");
		} else {
			jjim=  "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div[3]/div[1]/a";
			System.out.println("2");
		}
		driver.findElement(By.xpath(jjim)).click();
		System.out.println("상품진입 성공");
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("얼럿 닫기");
		// 찜 Text 체크
		Thread.sleep(5000);
		if ("로그인".equals(driver.findElement(By.xpath("//*[@id='content']/div[1]/h2")).getText())) {
			System.out.println("TC_24 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_24 FAIL");
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
