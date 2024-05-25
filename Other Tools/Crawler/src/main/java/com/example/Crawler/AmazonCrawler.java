package com.example.Crawler;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonCrawler {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // Configure ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // URL of the Amazon product page
            String furl = "https://www.amazon.com/dp/0000013714";
            driver.get(furl);

            // Allow time for manual CAPTCHA solving
            System.out.println("Please solve the CAPTCHA if it appears...");
            Thread.sleep(10000); // Wait 1 minute for manual CAPTCHA solving
            
         // Read URLs from CSV file
            String csvFilePath = "CDs_and_Vinyl_5.csv";
            List<String> productUrls = readUrlsFromCsv(csvFilePath);

            try {
                for (String url : productUrls) {
                    driver.get("https://www.amazon.com/dp/"+url);
                    
                 // Get the page source
                    String pageSource = driver.getPageSource();
                    
                 // Extract product information
                    Map<String, String> productInfo = extractProductInfo(pageSource);

                    // Save product information to a JSON file
                    saveProductInfoAsJson(productInfo, "output/info/cd/product-info-" + driver.findElement(By.id("productTitle")).getText() + ".json");

                    // Save page source to a text file
                    //savePageSource(driver.getPageSource(), "output/source/page-source-" + driver.findElement(By.id("productTitle")).getText() + ".txt");
                    System.out.println(driver.findElement(By.id("productTitle")).getText());

                    // Optionally, you can implement a small wait between requests to avoid hitting the server too hard
                    Thread.sleep(5000); // 5 seconds wait between requests
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver
            //driver.quit();
        }
    }

    private static List<String> readUrlsFromCsv(String filePath) {
    	List<String> urls = new ArrayList<>();
         try (FileReader reader = new FileReader(filePath);
              CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord())) {
             for (CSVRecord record : csvParser) {
                 urls.add(record.get(0)); // Assumes the URL is in the first column
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         return urls;
    }

    private static void savePageSource(String pageSource, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(pageSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Map<String, String> extractProductInfo(String pageSource) {
        Map<String, String> productInfo = new HashMap<>();
        Document doc = Jsoup.parse(pageSource);

        // Example extraction: Modify selectors based on the actual page structure
        Element titleElement = doc.selectFirst("#productTitle");
        if (titleElement != null) {
            productInfo.put("title", titleElement.text().trim());
        }
        
        Element itemForms = doc.selectFirst("#itemForms");
        if (itemForms != null) {
            productInfo.put("form", itemForms.text().trim());
        }
        
        Element itemCategory = doc.getElementById("bylineInfo").select("span").last();
        if (itemCategory != null) {
            productInfo.put("cat", itemCategory.text().trim());
        }

        Element imageElement = doc.selectFirst("#imgTagWrapperId img");
        if (imageElement != null) {
            String imageUrl = imageElement.attr("src");
            productInfo.put("image", imageUrl);
        }

        Element priceElement = doc.selectFirst(".a-price .a-offscreen");
        if (priceElement != null) {
            productInfo.put("price", priceElement.text().trim());
        }
        
        Elements quantityOptions = doc.select("#quantity option");
        if (quantityOptions != null && !quantityOptions.isEmpty()) {
            productInfo.put("quantity", String.valueOf(quantityOptions.size()));
        }

        Element descriptionElement = doc.selectFirst("#productDescription p");
        if (descriptionElement != null) {
            productInfo.put("description", descriptionElement.text().trim());
        }
        
        Element itemDetails = doc.selectFirst("#detailBullets_feature_div");
        if (itemDetails != null) {
            productInfo.put("details", itemDetails.text().trim());
        }

        return productInfo;
    }

    private static void saveProductInfoAsJson(Map<String, String> productInfo, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractProductId(String url) {
        // Extract product ID from the URL (assuming the format is consistent)
        String[] parts = url.split("/dp/");
        return parts.length > 1 ? parts[1].split("/")[0] : "unknown";
    }
}
