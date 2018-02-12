package com.cj.pc;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
	
public class Enter {
	 WebDriver driver;
	public Enter() {
		
		boolean isExist = false;
		//팝업닫기
		isExist = existElement(driver, By.xpath(".//*[@id='popup_spot']/div/div/div/div[2]/div[2]/button"), "닫기");
		if (isExist) {
			driver.findElement(By.xpath(".//*[@id='popup_spot']/div/div/div/div[2]/div[2]/button")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		System.out.println("팝업닫기");
	
		System.out.println("하하");
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
