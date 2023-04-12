package com.petstore.Requests;

import java.io.File;

import com.petstore.base.TestBase;

import io.restassured.http.Method;

public class UploadImage extends TestBase {

	public UploadImage header() {

		try {
			httpRequest.header("accept", "application/json");
			reportStep("Header Request sent successfully", "pass");
		} catch (Exception e) {
			reportStep("Header Request sending failed" + e.getMessage(), "fail");

		}

		return this;

	}

	

	public UploadImage fileUpload(String id, String file, String ImagePath, String additionalMetadata,
			String Addition) {
		try {
			File filepath = new File(ImagePath);
			reponse = httpRequest.given().formParam(additionalMetadata, Addition).multiPart(file, filepath)
					.request(Method.POST, "/v2/pet/" + id + "/uploadImage");

			reportStep("Image File Uploaded successfully", "pass");
		} catch (Exception e) {
			reportStep("Image file sending failed" + e.getMessage(), "fail");

		}

		return this;

	}

}
