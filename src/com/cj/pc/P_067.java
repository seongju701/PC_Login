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
 * Name : TC_67
 * Scenario : 로그인 > 네이버 아이디 로그인
 * Assertion : 로그아웃 텍스트 확인
 *
 */

public class P_067 {
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
	public void p_067() throws Exception {
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
		driver.findElement(By.xpath("//*[@id=\"naver\"]")).click();
		System.out.println("네이버 로그인 페이지 이동");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"id\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys("cjlogintest");
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).sendKeys("whtjdwn1");
		driver.findElement(By.xpath("//*[@id=\"frmNIDLogin\"]/fieldset/input")).click();
		Thread.sleep(5000);
		
		// text check
		System.out.println(driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div[5]/ul/li[1]/a")).getText());
        if (" 로그아웃".equals(driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div[5]/ul/li[1]/a")).getText())) {
           System.out.println("TC_67 PASS");
           assertTrue(true);
           return;
        } else {
           System.out.println("TC_67 FAIL");
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
