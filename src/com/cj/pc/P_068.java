package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;

/**
 * 
 * @author 조성주 Date : 2017-10-10
 * Subject : CJ Mall 운영 
 * Name : TC_68
 * Scenario : 마이존 > 네이버 아이디 로그인 > 회원탈퇴
 * Assertion : 얼럿 확인
 *
 */

public class P_068 {
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
		
		System.out.println("로그인 성공");
		Thread.sleep(5000);
		// 동의하기

		/*boolean isExist1 = false;
		isExist1 = existElement(driver, By.xpath("//*[@id=\"confirm_terms\"]/a[2]"), "동의하기");
		if (isExist1) {
			driver.findElement(By.xpath("//*[@id=\"confirm_terms\"]/a[2]")).click();
			System.out.println("동의하기 클릭");
		} else {
			System.out.println("동의하기 없음");
		}*/
		Thread.sleep(5000);
		driver.switchTo().alert().dismiss();
		WebElement searchBtn1 = driver.findElement(By.xpath("//*[@id=\"aside\"]/div/ul[2]/li[1]/a"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(searchBtn1).perform();
		System.out.println("스크롤 내리기");
		Thread.sleep(3000);
		//회원탈퇴 접근
		driver.findElement(By.xpath("//*[@id=\"aside\"]/div/ul[1]/li[4]/ul/li[4]/a")).click();
		System.out.println("회원탈퇴 접근");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"naver\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"id\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys("cjlogintest");
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).sendKeys("whtjdwn1");
		driver.findElement(By.xpath("//*[@id=\"frmNIDLogin\"]/fieldset/input")).click();
		Thread.sleep(5000);
		WebElement searchBtn2 = driver.findElement(By.xpath("//*[@id=\"footer\"]/div/div[2]/ul/li[1]/a"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(searchBtn2).perform();
		new Select(driver.findElement(By.xpath("//*[@id=\"selectWithdrawalReason\"]"))).selectByVisibleText("배송불만");
		driver.findElement(By.xpath("//*[@id=\"withdrawalReasonText\"]")).sendKeys("1111");
		driver.findElement(By.xpath("//*[@id=\"withdrawalSubmit\"]")).click();
		
	
		Thread.sleep(3000);
		// text check
		 if ("CJmall 회원탈퇴가 완료되었습니다.".equals(driver.switchTo().alert().getText())) {
		        System.out.println("TC_68 PASS");
		        assertTrue(true);
		        return;
		     } else {
		         System.out.println("TC_68 FAIL");
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
