import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ReadMe {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Driver\\chromedriver.exe");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2); // 1 accept, 2 deny
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://app.hubspot.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("emilhummat@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Siena123@");
        driver.findElement(By.id("loginBtn")).click();
        driver.findElement(By.id("nav-primary-sales-branch")).click();
        driver.findElement(By.id("nav-secondary-deals")).click();
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("button[data-onboarding='new-object-button']")).click();
        driver.findElement(By.id("UIFormControl-19")).sendKeys("asd");
        String mainWindowHandle = driver.getWindowHandle();
        driver.findElement(By.xpath("(//span[text()='Sales Pipeline'])[2]")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
            }
        }
        String actualURL = driver.getCurrentUrl();
        String excpectedURL = "https://app.hubspot.com/pricing/8080043/sales?upgradeSource=deals-create-deal-general-create-deal-multiple-pipelines-pql-feature-lock&term=annual&edition=starter";
        Assert.assertEquals(excpectedURL, actualURL);
        driver.switchTo().window(mainWindowHandle);
        driver.findElement(By.xpath("//span[text()='Appointment scheduled']")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("li[class*='selectable']"));
        elements.get(new Random().nextInt(elements.size())).click();
        driver.findElement(By.xpath("(//span[@class='private-dropdown__button-label uiDropdown__buttonLabel'])[11]")).click();
        List<WebElement> elements1 = driver.findElements(By.cssSelector("button[tabindex='-1']"));
        elements1.get(numGen(1, elements1.size())).click();
        driver.findElement(By.xpath("//span[text()='Create']")).click();
        driver.findElement(By.cssSelector("span[data-selenium-test='highlight-editor-icon']")).click();
        WebElement element = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
        element.clear();
        element.sendKeys("OHIO");
        driver.findElement(By.xpath("//button[@data-button-use='tertiary']")).click();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span[tabindex='0']>span"),"OHIO"));
        String text = driver.findElement(By.cssSelector("span[tabindex='0']>span")).getText();
        Assert.assertEquals("OHIO",text);
        driver.findElement(By.id("uiabstractdropdown-button-9")).click();
        driver.findElement(By.xpath("//i18n-string[text()='Delete']")).click();
        driver.findElement(By.xpath("//i18n-string[text()='Delete deal']")).click();

        wait.until(ExpectedConditions.invisibilityOf( driver.findElement(By.xpath("//i18n-string[text()='Delete deal']"))));
        Assert.assertNotEquals("OHIO",driver.getTitle());

    }

    public static int numGen(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


}
    /*
        Manual Create your account from the website

        Navigate to https://app.hubspot.com/login
        Enter the user name and password click on login button

        Click on Sales
        Click on Deals

        Click on Create Deal (Note: After clicking on the Deals in the automation not able to see the table just refresh the browser)
        Enter the Deal name

        Click on sales Pipeline (Verify second page URL is -->https://app.hubspot.com/pricing/7766931/sales?upgradeSource=deals-create-deal-general-create-deal-multiple-pipelines-pql-feature-lock&term=annual&edition=starter )
        Choose random from the Deal Stage dropdown
        Choose random from the Deal Type dropdown
        Click on Create button

        Click on the pen icon next to dollar amount on the left top
        Change the name
        And click on the save
        Verify name is changed

        Click on actions button
        Click on Delete
        Click on Delete deal

        Verify deal is not displayed any more

        NOTE: Do not use Thread.sleep()

        NOTE: You dont have to use TestNG

        Register to this page
        http://opencart.abstracta.us/index.php?route=common/home



     */


