package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import com.cj.util.SmartProperties;

/**
 * 
 * @author 조성주 Date : 2017-06-13
 * Subject : CJ Mall 운영 
 * Name : TC_63
 * Scenario : 결제 취소
 * Assertion : 결제취소 확인
 *
 */

public class P_063 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String ID_1 = null;
	private String PW_1 = null;
	private String P_URL = null;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
		P_URL = sp.getProperty("P_URL");
	}

	@Test
	public void p_063() throws Exception {
		driver.get(P_URL);
		driver.findElement(By.xpath(".//*[@id='header']/div[1]/div[5]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		Thread.sleep(3000);
		System.out.println("로그인 성공");
		// 마이존 이동
		driver.findElement(By.xpath(".//*[@id='go_myzone']/span")).click();
		System.out.println("마이존 성공");
		Thread.sleep(3000);
		// 취소 클릭
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/a")).click();
		System.out.println("주문 전체보기 진입");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"_order_listpage\"]/div[1]/div/div/div[2]/div/button")).click();
		System.out.println("주문취소 진입");
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath("//*[@id=\"amount1\"]"))).selectByVisibleText("1");
		System.out.println("취소수량 1개 선택");
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath("//*[@id=\"claim_info_reason_0\"]/fieldset/ul/li[2]/div[2]/select"))).selectByVisibleText("상품 단순변심 취소");
		System.out.println("취소수량 1개 선택");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"detail_reason1\"]")).sendKeys("1234");
		System.out.println("취소사유 입력");
		Thread.sleep(3000);
		// 스크롤 내리기
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"aside\"]/div/ul[1]/li[4]/ul/li[4]/a"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[2]/fieldset/ul/li[1]/div[2]/span[2]/label/span")).click();
		System.out.println("입금여부 취소 클릭");
		Thread.sleep(3000);
		System.out.println("회원탈퇴 탭으로 이동하기 클릭");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[5]/div[1]/div/div[2]/button")).click();
		System.out.println("신청 완료 클릭");
		Thread.sleep(3000);
		action.moveToElement(searchBtn).perform();
		System.out.println("회원탈퇴 탭으로 이동하기 클릭2");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[5]/div[2]/div/div[2]/button")).click();
		System.out.println("신청완료 클릭");
		Thread.sleep(5000);
				
		// text check
		if ("주문취소가 완료되었습니다.".equals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[4]/div[1]/p")).getText())) {
			System.out.println("TC_063 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_063 FAIL");
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
