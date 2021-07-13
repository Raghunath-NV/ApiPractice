package DynamicJson;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;


public class TestngDynamicJSON {
	
	@Test
	public void addBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		given().log().all().header("Content-Type","application/json")
		.body(payload.addBook())
		.when().post("/Library/AddBook.php")
		.then().log().all().statusCode(200);
	}

}
