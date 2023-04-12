package com.petstore.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.petstore.Util.LoadProperties;
import com.petstore.Util.ReadExcel;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static ExtentTest logger;
	public static LoadProperties props;
	// Reports
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testName, testDesc, testAuthor, testCategory;

	public static RequestSpecification httpRequest;
	public static Response reponse;

	@BeforeClass
	public void testDetails() {
		props = new LoadProperties();

		String url = props.get("test.Baseurel");
		System.out.println(props.get("test.Baseurel"));
		RestAssured.baseURI = url;// "https://petstore.swagger.io/v2/pet"; //props.get("test.Baseurel"); //
		httpRequest = RestAssured.given();
	}

	/*
	 * @DataProvider(name = "exceldata") public String[][] sendData() throws
	 * Exception {
	 * 
	 * String fileName = "Demo"; String sheetName = "PetShop";
	 * 
	 * 
	 * return ReadExcel.readExcel(fileName, sheetName);
	 * 
	 * }
	 */
	@BeforeSuite
	public void startReport() {
		reporter = new ExtentSparkReporter("./reports/results.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);

	}

	@AfterSuite
	public void stopReport() {
		extent.flush();
	}

	public void report( String testCaseName, String testCategory, String testDesc) {

		String testExp = "<span class=\"badge-light\">Expected Result :</span>" + "<i>" + "	"
				+ "<span class=\"badge-primary\">" + "</i>";
		test = extent.createTest(testCaseName);
		test.assignAuthor(System.getProperty("user.name"));
		test.assignCategory(testCategory);
		test.assignCategory(testDesc);
		// test.assignDevice(testDesc);

		reportStep(testCaseName, "info");
	}

	public void reportStep(String msg, String status) {
		if (status.equalsIgnoreCase("pass"))
			test.pass(msg);
		else if (status.equalsIgnoreCase("fail"))
			test.fail(msg);
		else if (status.equalsIgnoreCase("info"))
			test.info(msg);
	}

}
