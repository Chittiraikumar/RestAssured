package com.petstore.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.petstore.Requests.UploadImage;
import com.petstore.Util.ReadExcel;
import com.petstore.base.TestBase;

import io.restassured.http.Method;

public class TS002_PostRequestWithoutPayload extends TestBase {
	UploadImage image = new UploadImage();

	@Test(dataProvider = "exceldata")
	public void WithoutFormData(String id, String file, String ImagePath, String additionalMetadata, String Addition) {
		String testCaseName = "TC007 Verify the ResponseBody sending Request WithoutFormData";
		report(testCaseName, testCategory, testDesc);

		reponse = httpRequest.header("accept", "application/json").request(Method.POST, "/v2/pet/" + id + "/uploadImage");
		reportStep("Request sent successfully", "pass");
		String body = reponse.getBody().asString();
		System.out.println(body);

		try {

			int StatusCode = reponse.getStatusCode();
			Assert.assertEquals(StatusCode, 415);
			reportStep("Status Code Matched Testcase Passed !" + "ResponseCode is :: " + StatusCode, "pass");

		} catch (AssertionError | Exception e) {
			reportStep("Status Code not matched as per expected" + e.getMessage(), "fail");

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

		testName = "Regression";
		testDesc = "Upload Image";
		testCategory = "Functional";

	}

}
