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
 * @author 조성주 Date : 2017-10-27
 * Subject : CJ Mall 운영 
 * Name : TC_77
 * Scenario : 로그인 > 성인용품 검색 
 * Assertion : 페이지 진입 확인
 *
 */


public class P_077 {
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
		driver.findElement(By.xpath("//*[@id=\"submitFrm\"]/fieldset/div[1]/div[2]/dl/dd")).click();
		driver.findElement(By.xpath("//*[@id=\"srh_keyword\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"srh_keyword\"]")).sendKeys("성인용품");
		driver.findElement(By.xpath("//*[@id=\"submitFrm\"]/fieldset/div[1]/div[3]/button")).click();
		Thread.sleep(3000);
		//로그인 페이지 접근
		driver.findElement(By.xpath("//*[@id=\"lst_cate_result\"]/li[1]/a/span[1]")).click();
		System.out.println("로그인페이지 접근");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).sendKeys("jsjjoo88");
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).sendKeys("whtjdwn1!");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/fieldset/div[2]/span[2]/label")).click();
		driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]")).click();
		System.out.println("로그인");
		Thread.sleep(5000);
		// 로그인 페이지 접근
		System.out.println("로그인페이지 접근");
		Thread.sleep(3000);
		
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"_topPrdImg\"]/img")).getTagName());
		// text check
        if ("img".equals(driver.findElement(By.xpath("//*[@id=\"_topPrdImg\"]/img")).getTagName())) {
           System.out.println("TC_077 PASS");
           assertTrue(true);
           return;
        } else {
           System.out.println("TC_077 Fail");
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
