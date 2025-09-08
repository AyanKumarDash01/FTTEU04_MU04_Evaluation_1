package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormTest extends BaseTest {

    @Test
    public void fillPracticeForm() {
        driver.get(baseFormUrl);
        WebElement firstName = wait.until(d -> d.findElement(By.id("firstName")));
        firstName.sendKeys("Ayan");
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Dash");
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("ayan.dash@example.com");
        WebElement genderMale = driver.findElement(By.cssSelector("label[for='gender-radio-1']"));
        genderMale.click();
        WebElement mobile = driver.findElement(By.id("userNumber"));
        mobile.sendKeys("1234567890");
        WebElement dob = driver.findElement(By.id("dateOfBirthInput"));
        dob.click();
        WebElement monthSelect = wait.until(d -> d.findElement(By.cssSelector(".react-datepicker__month-select")));
        monthSelect.click();
        monthSelect.findElement(By.xpath("//option[text()='May']")).click();
        WebElement yearSelect = driver.findElement(By.cssSelector(".react-datepicker__year-select"));
        yearSelect.click();
        yearSelect.findElement(By.xpath("//option[text()='1990']")).click();
        WebElement day = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='15']"));
        day.click();
        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        subjects.sendKeys("Maths");
        subjects.sendKeys("\n");
        WebElement hobbiesSports = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
        hobbiesSports.click();
        WebElement pictureUpload = driver.findElement(By.id("uploadPicture"));
        pictureUpload.sendKeys(System.getProperty("user.dir") + "/src/test/resources/sample.jpg");
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("1234 Elm Street");
        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys("NCR");
        state.sendKeys("\n");
        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys("Delhi");
        city.sendKeys("\n");
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();
        WebElement modal = wait.until(d -> d.findElement(By.id("example-modal-sizes-title-lg")));
        Assert.assertEquals(modal.getText(), "Thanks for submitting the form");
        WebElement close = driver.findElement(By.id("closeLargeModal"));
        close.click();
    }
}
