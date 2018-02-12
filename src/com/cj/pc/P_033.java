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
 * Name : TC_33
 * Scenario :임의의 상품 > 장바구니 버튼 선택
 * Assertion : 장바구니 Text 체크
 *
 */
public class P_033 {
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
	public void p_033() throws Exception {
		driver.get(P_URL);

		// 로그인
		driver.findElement(By.xpath("//*[@id='header']/div[1]/div[5]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys("homecart1");
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys("stasta1!");
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 성공");
		Thread.sleep(3000);
		// 임의상품진입
		driver.findElement(By.id("srh_keyword")).clear();
		driver.findElement(By.id("srh_keyword")).sendKeys(PRODUCT);
		driver.findElement(By.cssSelector("button._search")).click();
		Thread.sleep(3000);
		System.out.println("임의상품 진입 성공");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='lst_cate_result']/li[1]/a/span[1]")).click();
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"_RECOMMEND\"]/div[1]/h4"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		Thread.sleep(3000);
		driver.findElement(By.linkText("장바구니")).click();
		System.out.println("장바구니 버튼 클릭");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div[5]/ul/li[3]/a/strong")).click();
		System.out.println("장바구니 이동");
		//장바구니 이동
		boolean isExist = false;
		isExist = existElement(driver, By.xpath("//*[@id=\"_itemBuyButton\"]/span"), "구매하기");
		
		System.out.println(isExist);
		if (isExist) {
			driver.findElement(By.xpath("//*[@id=\"_allDeleteBtn\"]/span")).click();
			driver.switchTo().alert().accept();
			System.out.println("TC_33 PASS");
			assertTrue(true);
		} else {
			System.out.println("TC_33 FAIL");
			assertTrue(false);
		}
		System.out.println("팝업닫기");
		Thread.sleep(3000);
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
	 public boolean existElement1(WebDriver wd, By by, String meaning) {
			WebDriverWait wait = new WebDriverWait(wd, 2);
			// wait.ignoring(NoSuchElementException.class);

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
