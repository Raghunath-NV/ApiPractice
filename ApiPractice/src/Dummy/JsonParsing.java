package Dummy;


import files.payload;
import io.restassured.path.json.JsonPath;

public class JsonParsing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath jsonPath=new JsonPath(payload.mimicAPIResponse());
		
		
		//1.Print number of courses
		
		int count = jsonPath.getInt("courses.size()");    //size gives count of course elements in array
        System.out.println("Number of courses is "+count);
        System.out.println("-----------------------");
        
        //2.Print purchase Amount
        
        System.out.println("Purchase Amount is "+jsonPath.getInt("dashboard.purchaseAmount"));    //purchaseamount is inside dashboard
        System.out.println("-----------------------");
        
        //3. Print Title of the first course
        
        System.out.println("Title of first course is "+jsonPath.getString("courses.title[0]"));
        System.out.println("-----------------------");
        
        //4.Print All course titles and their respective Prices
        
        for(int i=0;i<count;i++)
        {
        	System.out.println("Course name is "+jsonPath.getString("courses.title["+i+"]")+" and price is "+jsonPath.getInt("courses.price["+i+"]"));
        }
        System.out.println("-----------------------");
        
        //5.Print no of copies sold by RPA Course
        for(int i=0;i<count;i++)
        {
        	if(jsonPath.getString("courses.title["+i+"]").equals("RPA"))
        		
        		System.out.println("Course name is "+jsonPath.getString("courses.title["+i+"]")+" and copies sold is "+jsonPath.getInt("courses.copies["+i+"]"));
        }
        System.out.println("-----------------------");
        
        //6. Verify if Sum of all Course prices matches with Purchase Amount
        
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        int sum=0;
        for(int i=0;i<count;i++)
        {
        	sum = sum + (jsonPath.getInt("courses.price["+i+"]")* jsonPath.getInt("courses.copies["+i+"]"));
        }
        
        System.out.println("Is total purchaseAmount same ->"+(purchaseAmount==sum));
        System.out.println("-----------------------");
        
        
	}
	
	

}
