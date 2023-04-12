package com.petstore.Requests;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.base.TestBase;

import io.restassured.http.Method;

public class AddNewPet extends TestBase {

	static public String payload = "";
	static public int id;
	static public String tagId = "";
	static public int categoryId;
	static public String categoryName = "";
	static public String petName = "";
	static public String photoUrls = "";
	static public String tagname = "";
	static public String status = "";

	public AddNewPet header(String method) {

		try {
			if (method == "POST") {
				httpRequest.header("accept", "application/json");
				httpRequest.header("Content-Type", "application/json");
				reportStep("POST_Header Request sent successfully", "pass");
			} else if (method == "GET") {
				httpRequest.header("accept", "application/json");
				reportStep("GET_Header Request sent successfully", "pass");
			} else {
				httpRequest.header("Content-Type", "application/json");
				reportStep("Header Request sent successfully", "pass");
				reportStep("Header Request sent successfully", "pass");
			}

		} catch (Exception e) {
			reportStep("Header Request sending failed" + e.getMessage(), "fail");

		}

		return this;

	}

	public String payload(String method) {

		String JsonFileName = "";

		try {
			if (method == "POST") {
				JsonFileName = "TS003_body.json";
			} else {
				JsonFileName = "TS005_Update_body.json";

			}

			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\" + JsonFileName);

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);
			reportStep("Payload Request created successfully", "pass");

			payload = data.toString();

			httpRequest.body(payload);
			reportStep("Payload Request added successfully", "pass");
		} catch (Exception e) {
			reportStep("Payload Request sending failed" + e.getMessage(), "fail");

		}

		return payload;

	}

	public AddNewPet sendRequest(String method) {

		System.out.println(method);
		try {
			if (method == "POST") {
				reponse = httpRequest.request(Method.POST, "/v2/pet");
				reportStep("POST Request Initiated successfully", "pass");

			} else if (method == "PUT") {
				reponse = httpRequest.request(Method.PUT, "/v2/pet");
				reportStep("PUT Request Initiated successfully", "pass");

			} else {
				System.out.println("Please Send Request Method");
			}

		} catch (Exception e) {
			reportStep("POST Request Initiated failed" + e.getMessage(), "fail");

		}
		return this;
	}

	public AddNewPet sendRequest(String method, int id) {

		System.out.println(method);
		try {
			if (method == "GET") {
				reponse = httpRequest.request(Method.GET, "/v2/pet/" + id);
				reportStep("GET Request Initiated successfully", "pass");
				System.out.println(id);

			} else if (method == "DELETE") {
				reponse = httpRequest.request(Method.DELETE, "/v2/pet/" + id);
				reportStep("DELETE Request Initiated successfully", "pass");

			} else {
				System.out.println("Please Send Request Method");
			}

		} catch (Exception e) {
			reportStep("POST Request Initiated failed" + e.getMessage(), "fail");

		}
		return this;
	}

	public int payloadID() {

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TS003_body.json");

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);

			String dr = data.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(dr, JsonNode.class);

			JsonNode idNode = node.get("id");
			id = idNode.asInt();
			reportStep("ID created successfully (JSON Input) ", "pass");
		} catch (Exception e) {

			reportStep("ID Creation failed (JSON) " + e.getMessage(), "fail");
		}

		return id;
	}

	public int categoryID() {

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TS003_body.json");

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);

			String dr = data.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(dr, JsonNode.class);

			JsonNode idNode = node.get("category");
			JsonNode child = idNode.get("id");

			categoryId = child.asInt();
			reportStep("Category ID created successfully (JSON Input) ", "pass");
		} catch (Exception e) {
			reportStep("Category ID Creation failed (JSON) " + e.getMessage(), "fail");
		}

		return categoryId;
	}

	public String categoryName() {

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TS003_body.json");

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);

			String dr = data.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(dr, JsonNode.class);

			JsonNode idNode = node.get("category");
			JsonNode child = idNode.get("name");

			categoryName = child.asText();
			reportStep("Category Name created successfully (JSON Input) ", "pass");
		} catch (Exception e) {
			reportStep("Category Name Creation failed (JSON) " + e.getMessage(), "fail");
		}

		return categoryName;
	}

	public String petName() {

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TS003_body.json");

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);

			String dr = data.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(dr, JsonNode.class);

			JsonNode idNode = node.get("name");

			petName = idNode.asText();
			reportStep("Pet Name created successfully (JSON Input) ", "pass");
		} catch (Exception e) {
			reportStep("Pet Name Creation failed (JSON) " + e.getMessage(), "fail");
		}

		return petName;
	}

	public String photoUrls() {

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TS003_body.json");

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);

			String dr = data.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(dr, JsonNode.class);

			JsonNode idNode = node.get("photoUrls");

			photoUrls = idNode.toString();

			reportStep("photoUrls created successfully (JSON Input) ", "pass");
		} catch (Exception e) {
			reportStep("photoUrls Creation failed (JSON) " + e.getMessage(), "fail");
		}

		return photoUrls;
	}

	public String tagname() {

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TS003_body.json");

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);

			String dr = data.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(dr, JsonNode.class);

			JsonNode array = node.get("tags");
			JsonNode jsonNode = array.get(0);
			JsonNode child = jsonNode.get("name");
			// JsonNode idNode = node.get("tags.[0].name");

			tagname = child.asText();

			reportStep("tagname created successfully (JSON Input) ", "pass");
		} catch (Exception e) {
			reportStep("tagname Creation failed (JSON) " + e.getMessage(), "fail");
		}

		return tagname;
	}

	public String tagId() {

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TS003_body.json");

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);

			String dr = data.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(dr, JsonNode.class);

			JsonNode array = node.get("tags");
			JsonNode jsonNode = array.get(0);
			JsonNode child = jsonNode.get("id");
			// JsonNode idNode = node.get("tags.[0].name");

			tagId = child.asText();

			reportStep("tagId created successfully (JSON Input) ", "pass");
		} catch (Exception e) {
			reportStep("tagId Creation failed (JSON) " + e.getMessage(), "fail");
		}

		return tagId;
	}

	public String status() {

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TS003_body.json");

			FileReader fr = new FileReader(file);
			JSONTokener jsonToken = new JSONTokener(fr);
			JSONObject data = new JSONObject(jsonToken);

			String dr = data.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(dr, JsonNode.class);

			JsonNode array = node.get("status");

			status = array.asText();

			reportStep("status created successfully (JSON Input) ", "pass");
		} catch (Exception e) {
			reportStep("status Creation failed (JSON) " + e.getMessage(), "fail");
		}

		return status;
	}

}
