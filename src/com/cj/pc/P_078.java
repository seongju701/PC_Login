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
 * @author 조성주 Date : 2017-10-27
 * Subject : CJ Mall 운영 
 * Name : TC_78
 * Scenario : 카테고리 > 대분류 선택 > 상품상세
 * Assertion : 상품 이미지 확인
 *
 */


public class P_078 {
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
	public void p_075() throws Exception {
		driver.get(P_URL);
		//상품상세 접근
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div[3]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"gnb\"]/ul/li[1]/a")).click();
		Thread.sleep(3000);
	
		driver.findElement(By.xpath("//*[@id=\"dpCateIdG00011\"]")).click();
		Thread.sleep(3000);
		// 스크롤 내리기
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"cont_listing0\"]/div[5]/ul/li[4]/a/span[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"cont_listing0\"]/div[5]/ul/li[1]/a/span[1]")).click();
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"_topPrdImg\"]/img")).getTagName());
		// text check
        if ("img".equals(driver.findElement(By.xpath("//*[@id=\"_topPrdImg\"]/img")).getTagName())) {
           System.out.println("TC_078 PASS");
           assertTrue(true);
           return;
        } else {
           System.out.println("TC_078 Fail");
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
