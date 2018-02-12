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
 * Name : TC_66
 * Scenario : 네로그인 > 회원가입 > 네이버 간편회원가입 > 상세정보입력
 * Assertion : 회원가입 확인 얼럿 확인
 *
 */

public class P_066 {
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
	public void p_066() throws Exception {
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
		driver.findElement(By.xpath("//*[@id=\"birthdate\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"birthdate\"]")).sendKeys("19830705");
		driver.findElement(By.xpath("//*[@id=\"cellPhone\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"cellPhone\"]")).sendKeys("01077371211");
		System.out.println("회원가입 텍스트 입력");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"registerForm\"]/fieldset/div[2]/span[1]/label/span")).click();
		
		// 스크롤 내리기
		WebElement searchBtn2 = driver.findElement(By.xpath("//*[@id=\"footer\"]/div/div[2]/ul/li[1]/a"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(searchBtn2).perform();
		
		driver.findElement(By.xpath("//*[@id=\"registerAccountSubmit\"]")).click();
		
		Thread.sleep(15000);
		
		//alert check
		 if ("CJmall 회원가입이 완료되었습니다.".equals(driver.switchTo().alert().getText())) {
		        System.out.println("TC_66 PASS");
		        assertTrue(true);
		        return;
		     } else {
		         System.out.println("TC_66 FAIL");
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
