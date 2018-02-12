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
 * Name : TC_64
 * Scenario : 로그인 > 회원가입 > 네이버 아이디 로그인 > 동의하기 > 얼럿창 클릭
 * Assertion : 간편회원가입 페이지 이동 확인
 *
 */

public class P_064 {
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
	public void p_064() throws Exception {
		driver.get(P_URL);
		//로그인 페이지 접근
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div[5]/ul/li[1]/a")).click();
		System.out.println("로그인페이지 접근");
		Thread.sleep(3000);
		// 스크롤 내리기
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"footer\"]/div/div[2]/ul/li[1]/a"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		System.out.println("스크롤 내리기");
		Thread.sleep(3000);
		//회원가입
		driver.findElement(By.xpath("//*[@id=\"register\"]")).click();
		System.out.println("회원가입 이동");
		Thread.sleep(3000);
		//네이버 간편가입 페이지 이동
		WebElement searchBtn1 = driver.findElement(By.xpath("//*[@id=\"footer\"]/div/div[2]/ul/li[1]/a"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(searchBtn1).perform();
		System.out.println("스크롤 내리기");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"naver\"]")).click();
		System.out.println("회원가입 이동");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"id\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys("cjlogintest");
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).sendKeys("whtjdwn1");
		driver.findElement(By.xpath("//*[@id=\"frmNIDLogin\"]/fieldset/input")).click();
		Thread.sleep(3000);
		System.out.println("로그인 성공");
		
		
		// 동의하기

		boolean isExist1 = false;
		isExist1 = existElement(driver, By.xpath("//*[@id=\"confirm_terms\"]/a[2]"), "동의하기");
		if (isExist1) {
			driver.findElement(By.xpath("//*[@id=\"confirm_terms\"]/a[2]")).click();
			System.out.println("동의하기 클릭");
		} else {
			System.out.println("동의하기 없음");
		}
		Thread.sleep(5000);
		
				
		// text check
		if ("간편 회원가입".equals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2")).getText())) {
			System.out.println("TC_064 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_064 FAIL");
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
