package com.petstore.testcase;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import com.petstore.Requests.AddNewPet;
import com.petstore.base.TestBase;

import io.restassured.path.json.JsonPath;

public class TS003_POST_AddNewPet extends TestBase {

	static public JsonPath json;

	AddNewPet newPet = new AddNewPet();

	@Test
	public void addNewPet() {

		String testCaseName = "TC008 Verify the Add New Pet";
		report(testCaseName, testCategory, testDesc);

		try {
			String method = "POST";
			newPet.header(method);
			newPet.payload(method);
			newPet.sendRequest(method);

			reponse.getBody().asString();

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

	}

	@Test
	public void verifyResponseBodyID() {
		String testCaseName = "TC009 Verify the ResponseBody id ";
		report(testCaseName, testCategory, testDesc);

		try {
			String data = reponse.getBody().asString();
			json = reponse.jsonPath();
			int expectedID = newPet.payloadID();

			Assert.assertEquals(json.get("id"), expectedID);
			reportStep("ResponseBody id is matched as per expected" + "ResponseBody actual id is :: " + json.get("id"),
					"pass");

		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody id is not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@Test
	public void verifyResponseBodyCategoryId() {
		String testCaseName = "TC010 Verify the ResponseBody Category id ";
		report(testCaseName, testCategory, testDesc);

		try {

			String data = reponse.getBody().asString();
			json = reponse.jsonPath();

			int expectedID = newPet.categoryID();

			Assert.assertEquals(json.get("category.id"), expectedID);
			reportStep("ResponseBody id is matched as per expected" + "ResponseBody actual id is :: "
					+ json.get("category.id"), "pass");

		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody id is not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@Test
	public void verifyResponseBodyCategoryName() {
		String testCaseName = "TC011 Verify the ResponseBody Category Name";
		report(testCaseName, testCategory, testDesc);

		try {

			String data = reponse.getBody().asString();
			json = reponse.jsonPath();

			String name = newPet.categoryName();

			Assert.assertEquals(json.get("category.name"), name);
			reportStep("ResponseBody Category Name is matched as per expected" + "ResponseBody actual id is :: "
					+ json.get("category.name"), "pass");

		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody Category Name is not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@Test
	public void verifyResponseBodyPatName() {
		String testCaseName = "TC012 Verify the ResponseBody Pet Name";
		report(testCaseName, testCategory, testDesc);

		try {

			String data = reponse.getBody().asString();
			json = reponse.jsonPath();

			String name = newPet.petName();

			Assert.assertEquals(json.get("name"), name);
			reportStep("ResponseBody Pet Name is matched as per expected" + "ResponseBody actual id is :: "
					+ json.get("name"), "pass");

		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody Pet Name is not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@Test
	public void verifyResponseBodyphotoUrls() {
		String testCaseName = "TC013 Verify the ResponseBody photoUrls";
		report(testCaseName, testCategory, testDesc);

		try {

			String data = reponse.getBody().asString();
			json = reponse.jsonPath();

			String result1 = newPet.photoUrls();
			String name = result1.substring(2, result1.length() - 2);
			String result2 = json.get("photoUrls").toString();
			String actual = result2.substring(1, result2.length() - 1);

			Assert.assertEquals(actual, name);
			reportStep("ResponseBody photoUrls is/are matched as per expected" + "ResponseBody actual id is :: "
					+ json.get("photoUrls"), "pass");

		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody photoUrls is/are not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@Test
	public void verifyResponseBodyTagName() {
		String testCaseName = "TC014 Verify the ResponseBody tagname";
		report(testCaseName, testCategory, testDesc);

		try {

			String data = reponse.getBody().asString();
			json = reponse.jsonPath();

			String expected = newPet.tagname().toString();

			String result1 = json.get("tags.name").toString();
			String actual = result1.substring(1, result1.length() - 1);

			Assert.assertEquals(actual, expected);
			reportStep("ResponseBody tagname is/are matched as per expected" + "ResponseBody actual id is :: "
					+ json.get("tags.name"), "pass");

		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody tagname is/are not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@Test
	public void verifyResponseBodyTagId() {
		String testCaseName = "TC015 Verify the ResponseBody tagId";
		report(testCaseName, testCategory, testDesc);

		try {

			String data = reponse.getBody().asString();
			json = reponse.jsonPath();

			String expected = newPet.tagId();
			String result1 = json.get("tags.id").toString();

			String actual = result1.substring(1, result1.length() - 1);
			Assert.assertEquals(actual, expected);
			reportStep("ResponseBody tagId is/are matched as per expected" + "ResponseBody actual id is :: "
					+ json.get("tags.id"), "pass");

		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody tagId is/are not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@Test
	public void verifyResponseBodyStatus() {
		String testCaseName = "TC016 Verify the ResponseBody Status";
		report(testCaseName, testCategory, testDesc);

		try {

			String data = reponse.getBody().asString();
			json = reponse.jsonPath();

			String expected = newPet.status();

			Assert.assertEquals(json.get("status"), expected);
			reportStep("ResponseBody status is/are matched as per expected" + "ResponseBody actual id is :: "
					+ json.get("status"), "pass");

		} catch (AssertionError | Exception e) {
			reportStep("ResponseBody status is/are not matched as per expected" + e.getMessage(), "fail");
		}

	}

	@BeforeTest
	public void beforeTest() {

		
		testDesc = "Add_New_Pet";
		testCategory = "Functional";

	}
}
