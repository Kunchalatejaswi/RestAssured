package gitHub;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GitHubExample {

	@Test(description = "get all repository for Authentic user")
	public void getAllRepository() {
		given()
		.auth()  //Given Authentication
		.oauth2("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")
		.when()
		      .get("https://api.github.com/user/repos")
		.then()
		    .log()
		    .body()
		    .statusCode(200);	
	}
	@Test(description = "get all repository for Authentic user")
	public void createRepository() {
		JSONObject para = new JSONObject();
		para.put("name", "RestAssuredToolCreatedMe2");
		para.put("description", "RestAssuredToolCreatedMe2");
		para.put("homepage", "http://github.com/kunchalatejaswi");
		given()
		.auth()  //Given Authentication
		.oauth2("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")
		.header("Content-Type","application/json")
		.body(para.toJSONString())
		.when()
		      .post("https://api.github.com/user/repos")
		.then()
		    .log()
		    .body()
		    .statusCode(422);	
	}
}
