package weatherAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherAPICalls {
	@Test(enabled = true, description = "getting weather informtion of specidfic city")
	public void gettingWeatherInfo() {
		RestAssured.given().when()
				.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=981d0095475c811603828a5cfd5f9f2b")
				.then().log().body().statusCode(200);
	}

	@Test(enabled = true)
	public void gettingWeatherInfo2() {
	      Response res=RestAssured.given().when()
				.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=981d0095475c811603828a5cfd5f9f2b");
		System.out.println(res.prettyPrint());
		System.out.println(res.getTime());
		System.out.println(res.getStatusCode());
		System.out.println(res.getContentType());
	}
	@Test(enabled = true)
	public void gettingWeatherInfo3() {
		Response res = RestAssured.given()
				.queryParam("q","Pune")
				.queryParam("appid","981d0095475c811603828a5cfd5f9f2b")
				.when()
				.get("http://api.openweathermap.org/data/2.5/weather");
	}
	@Test(enabled = true)
	public void gettingWeatherInfo4() {
		Map<String,String> param = new HashMap<String,String>();
		param.put("q", "Pune");
		param.put("appid", "981d0095475c811603828a5cfd5f9f2b");
		Response res = RestAssured.given()
				.queryParams(param)
				.when()
				.get("http://api.openweathermap.org/data/2.5/weather");
	}
}
