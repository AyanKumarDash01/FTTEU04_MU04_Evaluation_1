package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertsTest extends BaseTest {

    @Test
    public void handleAlerts() {
        driver.get(baseAlertsUrl);
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
}
