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
 * Name : TC_65
 * Scenario : 로그인 > 네이버 아이디 로그인 > 얼럿확인 클릭
 * Assertion : 간편회원가입 Text 체크
 *
 */

public class P_065 {
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
	public void p_065() throws Exception {
		driver.get(P_URL);
		//로그인 버튼 클릭		
		driver.findElement(By.xpath(".//*[@id=\"header\"]/div[1]/div[5]/ul/li[1]/a/span")).click();
		System.out.println("로그인 버튼 클릭");
		Thread.sleep(5000);
		// 네이버 로그인 버튼 클릭
		driver.findElement(By.xpath(".//*[@id=\"naver\"]")).click();
		System.out.println("네이버 로그인");
		Thread.sleep(5000);
		// 네이버 로그인 아이디 입력
		driver.findElement(By.xpath("//*[@id=\"id\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys("cjlogintest");
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).sendKeys("whtjdwn1");
		driver.findElement(By.xpath("//*[@id=\"frmNIDLogin\"]/fieldset/input")).click();
		System.out.println("네이버 로그인");
		Thread.sleep(10000);
		// 동의하기
		
		driver.switchTo().alert().accept();
		Thread.sleep(3000);	
		// text check
		if ("간편 회원가입".equals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2")).getText())) {
			System.out.println("TC_065 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_065 FAIL");
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
