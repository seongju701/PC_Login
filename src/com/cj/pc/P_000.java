package com.cj.pc;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class P_000 { 
  private WebDriver driver;
 
   
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    	
  }

  @Test
	public void p_001() throws Exception {
		driver.get("http://kor.lottedfs.com/kr");
		
        String MainWindow=driver.getWindowHandle();		
        		
        // To handle all new opened window.				
            Set<String> s1=driver.getWindowHandles();		
        Iterator<String> i1=s1.iterator();		
        		System.out.println("1");
        while(i1.hasNext())			
        {		
            String ChildWindow=i1.next();		
            		
            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
            {    		
            	System.out.println("2");
                    // Switching to Child window
                    driver.switchTo().window(ChildWindow);		
                    driver.close();
                                           
                       			
                       
			// Closing the Child Window.
                       	
                        System.out.println("5");
            }		
        }		
        // Switching to Parent window i.e Main Window.
            driver.switchTo().window(MainWindow);	
            driver.findElement(By.xpath("//*[@id=\"mainCateInfo\"]/li[1]/a")).click();			
            System.out.println("6");
            
    }
	} 

