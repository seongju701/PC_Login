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
 * Name : TC_74
 * Scenario : 미성년자 로그인
 * Assertion : 해당 브랜드는 14세 미만의 경우 가입 불가합니다. 실패사유코드(3-2) 얼럿 확인
 *
 */

public class P_074 {
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
	public void p_074() throws Exception {
		driver.get(P_URL);
		//로그인 페이지 접근
		driver.findElement(By.xpath("//*[@id=\"go_myzone\"]/span")).click();
		System.out.println("로그인페이지 접근");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).sendKeys("energypark03");
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).sendKeys("!dongsu03");
		driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]")).click();
		Thread.sleep(5000);
		//전체동의 버튼 클릭
		driver.findElement(By.xpath("//*[@id=\"join_terms_agreement\"]/fieldset/div[2]/span[1]/label")).click();
		Thread.sleep(5000);
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"footer\"]/div/div[2]/ul/li[1]/a"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"join_terms_agreement\"]/fieldset/button")).click();
		
		
		Thread.sleep(5000);
		// text check
		 if ("해당 브랜드는 14세 미만의 경우 가입 불가합니다. 실패사유코드(3-2)".equals(driver.switchTo().alert().getText())) {
		        System.out.println("TC_074 Success");
		        assertTrue(true);
		        return;
		     } else {
		         System.out.println("TC_074 Failure");
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
