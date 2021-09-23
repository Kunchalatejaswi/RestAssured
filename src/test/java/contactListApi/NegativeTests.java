package contactListApi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class NegativeTests {
	String ss;

	@Test(enabled = true)
	public void f() {

		System.out.println("getting specific contact");
		RestAssured.given().when().get("http://3.13.86.142:3000/contacts/501").then().log().body().statusCode(404);
	}

	@Test(enabled = true, description = "adding contact with missing email")
	public void f1() {
		System.out.println("adding contact");
		JSONObject data = new JSONObject();
		JSONObject loc = new JSONObject();
		JSONObject emp = new JSONObject();
		loc.put("city", "Hyderbad");
		loc.put("country", "India");
		emp.put("jobTitle", "Test Engineer");
		emp.put("company", "LTI");
		data.put("firstName", "srinivas");
		data.put("lastName", "reddy");
		// data.put("email", "srinivasreddy832@gmail.com");

		ss = RestAssured.given().header("Content-Type", "application/json").body(data.toJSONString()).when()
				.post("http://3.13.86.142:3000/contacts").then().log().body().statusCode(400).extract().path("err");
		Assert.assertEquals("Contacts validation failed: email: Email is required", ss);
	}

	@Test(enabled = true, description = "adding contact with outoutrange firstname")
	public void f2() {
		System.out.println("adding contact");
		JSONObject data = new JSONObject();
		JSONObject loc = new JSONObject();
		JSONObject emp = new JSONObject();
		loc.put("city", "Hyderbad");
		loc.put("country", "India");
		emp.put("jobTitle", "Test Engineer");
		emp.put("company", "LTI");
		data.put("firstName", "srinivassrinivassrinivassrinivassrinivassrinivassrinivas");
		data.put("lastName", "reddy");
		data.put("email", "srinivasreddy832@gmail.com");

		ss = RestAssured.given().header("Content-Type", "application/json").body(data.toJSONString()).when()
				.post("http://3.13.86.142:3000/contacts").then().log().body().statusCode(400).extract().path("err");
		Assert.assertTrue(ss.contains("is longer than the maximum allowed length (20)"));
	}

	@Test(enabled = true, description = "adding contact with invalid firstname")
	public void f4() {
		System.out.println("adding contact");
		JSONObject data = new JSONObject();
		JSONObject loc = new JSONObject();
		JSONObject emp = new JSONObject();
		loc.put("city", "Hyderbad");
		loc.put("country", "India");
		emp.put("jobTitle", "Test Engineer");
		emp.put("company", "LTI");
		data.put("firstName", "srinivas123");
		data.put("lastName", "reddy123");
		data.put("email", "srinivasreddy832@gmail.com");

		ss = RestAssured.given().header("Content-Type", "application/json").body(data.toJSONString()).when()
				.post("http://3.13.86.142:3000/contacts").then().log().body().statusCode(400).extract().path("err");
		Assert.assertTrue(ss.contains("Contacts validation failed: firstName: Validator failed for path"));
	}

	@Test(enabled = true, description = "parameter not in propor format")
	public void f5() {
		System.out.println("adding contact");
		JSONObject data = new JSONObject();
		JSONObject loc = new JSONObject();
		JSONObject emp = new JSONObject();
		loc.put("city", "Hyderbad");
		loc.put("country", "India");
		emp.put("jobTitle", "Test Engineer");
		emp.put("company", "LTI");
		data.put("firstName", "srinivas");
		data.put("lastName", "reddy");
		data.put("email", "srinivasreddy832gmail.com");

		ss = RestAssured.given().header("Content-Type", "application/json").body(data.toJSONString()).when()
				.post("http://3.13.86.142:3000/contacts").then().log().body().statusCode(400).extract().path("err");
		Assert.assertEquals(
				"Contacts validation failed: email: Validator failed for path `email` with value `srinivasreddy832gmail.com`",
				ss);
	}
}