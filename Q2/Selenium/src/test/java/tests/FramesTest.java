package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesTest extends BaseTest {

    @Test
    public void switchAndVerifyFrames() {
        driver.get(baseFramesUrl);
        WebElement frame1 = wait.until(d -> d.findElement(By.id("frame1")));
        driver.switchTo().frame(frame1);
        WebElement heading1 = driver.findElement(By.id("sampleHeading"));
        Assert.assertEquals(heading1.getText(), "This is a sample page");
        driver.switchTo().defaultContent();
    }
}
