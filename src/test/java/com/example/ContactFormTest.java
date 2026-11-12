package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ContactFormTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        File htmlFile = new File("app/index.html");
        driver.get("file://" + htmlFile.getAbsolutePath());
    }

    @Test
    public void testEmptyFieldsValidation() {
        driver.findElement(By.id("submitBtn")).click();

        WebElement feedback = driver.findElement(By.id("feedback"));
        assertTrue(feedback.getText().contains("All fields are required"));
        assertTrue(feedback.getAttribute("class").contains("error"));
    }

    @Test
    public void testSuccessfulFormSubmission() {
        driver.findElement(By.id("name")).sendKeys("Jane Doe");
        driver.findElement(By.id("email")).sendKeys("jane@example.com");
        driver.findElement(By.id("message")).sendKeys("Hello, testing from Eclipse!");

        driver.findElement(By.id("submitBtn")).click();

        WebElement feedback = driver.findElement(By.id("feedback"));
        assertTrue(feedback.getText().contains("Thank you! Your message has been sent successfully."));
        assertTrue(feedback.getAttribute("class").contains("success"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}