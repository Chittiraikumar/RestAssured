package com.petstore.testcase;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.petstore.Requests.UploadImage;
import com.petstore.Util.ReadExcel;
import com.petstore.base.TestBase;

import io.restassured.path.json.JsonPath;

public class TS001_POST_UploadImage extends TestBase {

	UploadImage image = new UploadImage();

	@Test(dataProvider = "exceldata")
	public void ValidateResponseMessage(String id, String file, String ImagePath, String additionalMetadata,
			String Addition) {

		String testCaseName = "TC001 Verify the Response Message";

		report(testCaseName, testCategory, testDesc);

		try {

			image.header().fileUpload(id, file, ImagePath, additionalMetadata, Addition);

			String resMessage = reponse.jsonPath().get("message");
			Assert.assertEquals(resMessage.contains("File uploaded to"), true);

		} catch (AssertionError | Exception e) {
			reportStep("Response Message not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@Test
	public void vaidateStatuscode() {

		// For Reporting purpose
		String testCaseName = "TC002 Verify the Status Code";

		report(testCaseName, testCategory, testDesc);

		try {

			int StatusCode = reponse.getStatusCode();
			Assert.assertEquals(StatusCode, 200);
			reportStep("Status Code Matched Testcase Passed !" + "ResponseCode is :: " + StatusCode, "pass");

		} catch (AssertionError | Exception e) {
			reportStep("Status Code not matched as per expected" + e.getMessage(), "fail");

		}

	}

	@Test
	public void validateStatusLine() {
		String testCaseName = "TC003 Verify the statusLine";
		report(testCaseName, testCategory, testDesc);

		try {
			String statusLine = reponse.statusLine();
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
			reportStep("StatusLine matched as per expected" + "StatusLine is :: " + statusLine, "pass");
		} catch (AssertionError | Exception e) {
			reportStep("StatusLine not matched as per expected" + e.getMessage(), "fail");
		}
	}

	@Test
	public void validateResponseBodyCode() {
		String testCaseName = "TC004 Verify the ResponseBody code";

		report(testCaseName, testCategory, testDesc);

		try {
			String bodydata = reponse.getBody().asString();
			JsonPath jsonPath = JsonPath.from(bodydata);

			String code = jsonPath.getString("code");
			Assert.assertEquals(code, "200");

			reportStep("ResponseBody code matched as per expected", "pass");
		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody code not matched as per expected" + e.getMessage(), "fail");
		}
	}

	@Test
	public void validateResponseTime() {
		String testCaseName = "TC005 Verify the ResponseTime";

		report(testCaseName, testCategory, testDesc);

		try {

			long responseTime = reponse.getTimeIn(TimeUnit.SECONDS);
			System.out.println(responseTime);
			Assert.assertTrue(responseTime < 15.88);
			reportStep("ResponseTime matched as per expected" + "ResponseTime is :: " + responseTime + "s", "pass");
		} catch (AssertionError | Exception e) {
			reportStep("ResponseTime not matched as per expected" + e.getMessage() + "ResponseTime is more than 15.88s",
					"fail");
		}
	}

	@Test
	public void validateResponseBodyType() {
		String testCaseName = "TC006 Verify the ResponseBody Type";
		report(testCaseName, testCategory, testDesc);

		try {
			String bodydata = reponse.getBody().asString();
			JsonPath jsonPath = JsonPath.from(bodydata);

			String Type = jsonPath.getString("type");
			Assert.assertEquals(Type, "unknown");

			reportStep("ResponseBody Type matched as per expected" + "ResponseBody Type is :: " + Type, "pass");
		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody Type not matched as per expected" + e.getMessage(), "fail");
		}
	}

	

	@DataProvider(name = "exceldata")
	public String[][] sendData() throws Exception {
		String fileName = "Demo";
		String sheetName = "PetShop";

		return ReadExcel.readExcel(fileName, sheetName);

	}

	@BeforeTest
	public void beforeTest() {

		
		testDesc = "Upload Image";
		testCategory = "Functional";

	}

}
