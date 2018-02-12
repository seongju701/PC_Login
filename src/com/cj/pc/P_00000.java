package com.cj.pc;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class P_00000 { 
  private WebDriver driver;
 
   
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    	
  }

  @Test
	public void p_001() throws Exception {
		driver.get("http://www.gsshop.com/index.gs");
		driver.findElement(By.xpath("//*[@id=\"members-link\"]/ul/li[1]/a")).click();			
		
        String MainWindow=driver.getWindowHandle();		
     
        Set<String>s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while(i1.hasNext())
        {
        	String ChildWindow=i1.next();
        	if(!MainWindow.equalsIgnoreCase(ChildWindow))
        	{
        		driver.switchTo().window(ChildWindow);
        		
        	}
        	
        }
        
        		
    }
	} 

