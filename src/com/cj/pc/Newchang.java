package com.cj.pc;

import java.util.Iterator;
import java.util.Set;
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
 * @author 조성주
 * Date : 2017-06-12
 * Subject : CJ Mall 운영 
 * Name : TC_1
 * Scenario : 로그인 버튼 선택 > ID 미입력 / PW 미입력 > 로그인 버튼 선택
 * Assertion : '아이디를 입력해주세요. 알럿 Text 체크
 *
 */  
public class Newchang { 
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
	public void p_001() throws Exception {
		driver.get(P_URL);
		
		driver.findElement(By.xpath(".//*[@id='header']/div[1]/div[5]/ul/li[1]/a/span")).click();
		System.out.println("로그인 버튼 클릭");
		
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).sendKeys("jsjjoo88");
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).sendKeys("whtjdwn1!");
		driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"go_myzone\"]/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"aside\"]/div/ul[1]/li[1]/ul/li[4]/a")).click();
		
		
		  String MainWindow=driver.getWindowHandle();		
		     
	        Set<String>s1 = driver.getWindowHandles();
	        Iterator<String> i1 = s1.iterator();
	        while(i1.hasNext())
	        {
	        	String ChildWindow=i1.next();
	        	if(!MainWindow.equalsIgnoreCase(ChildWindow))
	        	{
	        		driver.switchTo().window(ChildWindow);
	        		driver.findElement(By.xpath("//*[@id=\"layerWrap\"]/div/div[1]/map/area[2]")).click();
	        		Thread.sleep(3000);
	        	    driver.close();
	        	    Thread.sleep(5000);
	        	}
	        	
	        }
	        Thread.sleep(3000);
	        driver.switchTo().window(MainWindow);	
	        driver.findElement(By.xpath("//*[@id=\"go_myzone\"]/span")).click();
		
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

			return false;
		}
		return true;
	}
	 
}
