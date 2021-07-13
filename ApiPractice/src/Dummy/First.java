package Dummy;



import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class First {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//given - all input details
		//when - submit API 
		//Then - validate the response

		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")		
		.body(payload.AddPlace())
		.when().post("/maps/api/place/add/json").then().                        
		/*log().all().*/assertThat().statusCode(200).body("scope", equalTo("APP"))  //validating 
		
		
		
		.extract().response().asString();      //catch the response
		
		System.out.println(response);
		
		JsonPath j=new JsonPath(response);
		
	   String place_id=j.getString("place_id");     //catching place_id to pass to next api
	   
	   //Now update api will be called using captured place_id
	   
	   
	   String newAddress="700 summer walk, CA";
	   
	   
	   given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\n"
				+ "\"place_id\":\""+place_id+"\",\n"
				+ "\"address\":\""+newAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));	
	   
	   
	   //Now confirming whether the address has been updated or not by calling get api
	   
	   String getPlaceResponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
	   .when().get("maps/api/place/get/json")
	   .then().log().all().statusCode(200).extract().response().asString();//.body("address", equalTo(newAddressString));
	   
	   //JsonPath jsonPath=new JsonPath(getPlaceResponse);
	   
	   JsonPath jsonPath= ReusableMethods.rawToJson(getPlaceResponse);
	   
	   String actualAddress = jsonPath.getString("address");
	   
	   Assert.assertEquals(actualAddress, newAddress);
		
		
		 
	}

}
