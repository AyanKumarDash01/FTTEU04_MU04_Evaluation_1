package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SeleniumSprintChallenge {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void formTest() {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("firstName")).sendKeys("Ayan");
        driver.findElement(By.id("lastName")).sendKeys("Dash");
        driver.findElement(By.id("userEmail")).sendKeys("ayan.dash@example.com");
        driver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");
        driver.findElement(By.id("dateOfBirthInput")).click();
        WebElement monthSelect = driver.findElement(By.cssSelector(".react-datepicker__month-select"));
        monthSelect.click();
        monthSelect.findElement(By.xpath("//option[text()='May']")).click();
        WebElement yearSelect = driver.findElement(By.cssSelector(".react-datepicker__year-select"));
        yearSelect.click();
        yearSelect.findElement(By.xpath("//option[text()='1990']")).click();
        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='15']")).click();
        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        subjects.sendKeys("Maths");
        subjects.sendKeys("\n");
        driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();
        driver.findElement(By.id("uploadPicture")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/sample.jpg");
        driver.findElement(By.id("currentAddress")).sendKeys("1234 Elm Street");
        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys("NCR");
        state.sendKeys("\n");
        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys("Delhi");
        city.sendKeys("\n");
        driver.findElement(By.id("submit")).click();
        String modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
        Assert.assertEquals(modalTitle, "Thanks for submitting the form");
        driver.findElement(By.id("closeLargeModal")).click();
    }

    @Test
    public void alertsTest() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        Alert alert1 = wait.until(d -> d.switchTo().alert());
        alert1.accept();
        driver.findElement(By.id("timerAlertButton")).click();
        Alert alert2 = wait.until(d -> d.switchTo().alert());
        alert2.accept();
        driver.findElement(By.id("confirmButton")).click();
        Alert confirm = wait.until(d -> d.switchTo().alert());
        confirm.dismiss();
        String confirmResult = driver.findElement(By.id("confirmResult")).getText();
        Assert.assertEquals(confirmResult, "You selected Cancel");
        driver.findElement(By.id("promtButton")).click();
        Alert prompt = wait.until(d -> d.switchTo().alert());
        prompt.sendKeys("TestUser");
        prompt.accept();
        String promptResult = driver.findElement(By.id("promptResult")).getText();
        Assert.assertTrue(promptResult.contains("TestUser"));
    }

    @Test
    public void framesTest() {
        driver.get("https://demoqa.com/frames");
        WebElement frame1 = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frame1);
        String heading1 = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(heading1, "This is a sample page");
        driver.switchTo().defaultContent();
        WebElement frame2 = driver.findElement(By.id("frame2"));
        driver.switchTo().frame(frame2);
        String heading2 = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(heading2, "This is a sample page");
        driver.switchTo().defaultContent();
    }
}
