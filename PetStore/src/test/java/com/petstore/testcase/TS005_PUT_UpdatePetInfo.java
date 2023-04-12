package com.petstore.testcase;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.petstore.Requests.AddNewPet;
import com.petstore.base.TestBase;

import io.restassured.path.json.JsonPath;

public class TS005_PUT_UpdatePetInfo extends TestBase {

	static public JsonPath json;

	AddNewPet newPet = new AddNewPet();

	@Test
	public void updatePet() {
		String testCaseName = "TC018 Verify the UPDATE New Pet API ";
		report(testCaseName, testCategory, testDesc);

		try {
			String method = "PUT";

			newPet.header(method);
			newPet.payload(method);
			newPet.sendRequest(method);
			System.out.println(reponse.getBody().asString());

			int StatusCode = reponse.getStatusCode();
			Assert.assertEquals(StatusCode, 200);
			reportStep("Status Code Matched Testcase Passed !" + "ResponseCode is :: " + StatusCode, "pass");

		} catch (AssertionError | Exception e) {
			reportStep("Status Code not matched as per expected" + e.getMessage(), "fail");

		}

		try {
			String statusLine = reponse.statusLine();
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
			reportStep("StatusLine matched as per expected" + "StatusLine is :: " + statusLine, "pass");
		} catch (AssertionError | Exception e) {
			reportStep("StatusLine not matched as per expected" + e.getMessage(), "fail");
		}

		try {

			long responseTime = reponse.getTimeIn(TimeUnit.SECONDS);

			Assert.assertTrue(responseTime < 15.88);
			reportStep("ResponseTime matched as per expected" + "ResponseTime is :: " + responseTime + "s", "pass");
		} catch (AssertionError | Exception e) {
			reportStep("ResponseTime not matched as per expected" + e.getMessage() + "ResponseTime is more than 15.88s",
					"fail");
		}

		System.out.println(reponse.getBody().toString());

	}

	@BeforeTest
	public void beforeTest() {

		
		testDesc = "UPDATE_PET";
		testCategory = "Functional";

	}

}