package ru.stqa.pft.gartender;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class FirstGarTest {
    WebDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        String browser = BrowserType.FIREFOX;
        if (browser == BrowserType.FIREFOX){
            wd = new FirefoxDriver();
        }else if (browser == BrowserType.CHROME){
            wd = new ChromeDriver();
        }else if (browser == BrowserType.IE){
            wd = new InternetExplorerDriver();
        }
        System.setProperty("webdriver.firefox.marionette", "./geckodriver.exe");

        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void FirstGarTest() throws ParseException {
        wd.get("https://geesupport.gartender.ru/login");
        wd.findElement(By.id("user_login")).click();
        wd.findElement(By.id("user_login")).sendKeys("strahov");
        wd.findElement(By.id("user_password")).click();
        wd.findElement(By.id("user_password")).sendKeys("3ef92200");
        wd.findElement(By.name("commit")).click();
        System.out.println(methodDate());
        System.out.println(dateFromWeb());
        System.out.println(theCurentDate());
        datesDifference();
        int value0=0;
        int value1=1;

        assertThat(datesDifference(),anyOf(is(value0),is(value1)));

    }

    private char[] methodDate() {
        String str = wd.findElement(By.xpath("//div[contains( text(),'опубликовано')]")).getText();
        int start = 13;
        int end = 23;
        char [] dst = new char [end - start];
        str.getChars(start, end, dst, 0);
        return dst;
    }

    private Date dateFromWeb() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date = format.parse(String.valueOf(methodDate()));
        return date;
    }

    private static Date theCurentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return calendar.getTime();
    }

    private int datesDifference() throws ParseException {
        long milliseconds = dateFromWeb().getTime() - theCurentDate().getTime();
        int days = (int)(milliseconds/(24*60*60*1000));
        return days;
    }





    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
