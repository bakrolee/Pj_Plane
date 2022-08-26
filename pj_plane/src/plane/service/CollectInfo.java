package plane.service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import plane.model.PlaneInfo;

public class CollectInfo {
	private WebDriver driver;
	private List<String> airlines;
	private List<String> deptimes;
	private List<String> arrtimes;
	private List<Integer> fees;

	public CollectInfo () {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
	}
	
	public int find (String depLoc, String arrLoc, String date) throws IOException {
//		System.out.println(System.currentTimeMillis());
		String searchUrl = "https://sky.interpark.com/schedules/domestic/" + depLoc + "-" + arrLoc + "-" + date;
		
		driver.get(searchUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//		System.out.println(System.currentTimeMillis());
		
		List<WebElement> airsEl = driver.findElements(By.cssSelector("#__next .airlineName > .name"));
		List<WebElement> depsEl = driver.findElements(By.cssSelector("#__next .viewBtn > div:nth-child(2) > .timeWrap > .time"));
		List<WebElement> arrsEl = driver.findElements(By.cssSelector("#__next .viewBtn > div:nth-child(4) > .timeWrap > .time"));
		List<WebElement> feesEl = driver.findElements(By.cssSelector("#__next .airlineFee > .feeWrap > .fee"));
//		System.out.println(System.currentTimeMillis());
		
		airlines = new ArrayList<>();
		deptimes = new ArrayList<>();
		arrtimes = new ArrayList<>();
		fees = new ArrayList<>();
		
		for (int i = 0; i < airsEl.size(); i++) {
			airlines.add(airsEl.get(i).getText());
			deptimes.add(depsEl.get(i).getText());
			arrtimes.add(arrsEl.get(i).getText());
			fees.add(Integer.parseInt(feesEl.get(i).getText().replaceAll("[^0-9]", "")));
		}
		
		driver.quit();
		return airsEl.size();
	}

	public List<String> getAirlines() {
		return airlines;
	}

	public void setAirlines(List<String> airlines) {
		this.airlines = airlines;
	}

	public List<String> getDeptimes() {
		return deptimes;
	}

	public void setDeptimes(List<String> deptimes) {
		this.deptimes = deptimes;
	}

	public List<String> getArrtimes() {
		return arrtimes;
	}

	public void setArrtimes(List<String> arrtimes) {
		this.arrtimes = arrtimes;
	}

	public List<Integer> getFees() {
		return fees;
	}

	public void setFees(List<Integer> fees) {
		this.fees = fees;
	}
}
