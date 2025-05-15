//package com.example.medical;
//
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.io.FileHandler;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class RoleFlowUITest {
//
//	private WebDriver driver;
//
//	@BeforeAll
//	void setup() {
//		driver = new ChromeDriver();
//	}
//
//	@AfterEach
//	void captureScreenshot(TestInfo testInfo) {
//		if (driver != null) {
//			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
//			String filename = "screenshot_" + testInfo.getDisplayName().replace("()", "") + "_" + timestamp + ".png";
//			try {
//				FileHandler.copy(screenshot, new File("target/screenshots/" + filename));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@AfterAll
//	void tearDown() {
//		if (driver != null) {
//			driver.quit();
//		}
//	}
//
//	@Test
//	void adminFlowTest() {
//		loginAs("alice", "password", "ADMIN");
//		driver.get("http://localhost:8080/records");
//
//		WebElement nameInput = driver.findElement(By.name("name"));
//		nameInput.clear();
//		nameInput.sendKeys("Test Admin");
//		WebElement ageInput = driver.findElement(By.name("age"));
//		ageInput.clear();
//		ageInput.sendKeys("99");
//		WebElement historyInput = driver.findElement(By.name("medicalHistory"));
//		historyInput.clear();
//		historyInput.sendKeys("Admin test case");
//		driver.findElement(By.cssSelector("input[type='submit']")).click();
//
//		driver.get("http://localhost:8080/audit-log");
//		driver.get("http://localhost:8080/logout");
//	}
//
//	@Test
//	void doctorFlowTest() {
//		loginAs("bob", "password", "DOCTOR");
//		driver.get("http://localhost:8080/records");
//
//		WebElement nameInput = driver.findElement(By.name("name"));
//		nameInput.clear();
//		nameInput.sendKeys("Test Doctor");
//		WebElement ageInput = driver.findElement(By.name("age"));
//		ageInput.clear();
//		ageInput.sendKeys("45");
//		WebElement historyInput = driver.findElement(By.name("medicalHistory"));
//		historyInput.clear();
//		historyInput.sendKeys("Doctor test entry");
//		driver.findElement(By.cssSelector("input[type='submit']")).click();
//
//		driver.get("http://localhost:8080/logout");
//	}
//
//	@Test
//	void nurseFlowTest() {
//		loginAs("claire", "password", "NURSE");
//		driver.get("http://localhost:8080/records");
//		driver.get("http://localhost:8080/logout");
//	}
//
//	private void loginAs(String username, String password, String role) {
//		driver.get("http://localhost:8080/login");
//		driver.findElement(By.name("username")).sendKeys(username);
//		driver.findElement(By.name("password")).sendKeys(password);
//		WebElement roleSelect = driver.findElement(By.name("role"));
//		roleSelect.findElement(By.xpath("//option[. = '" + role + "']")).click();
//		driver.findElement(By.cssSelector("button[type='submit']")).click();
//	}
//}
