package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;

public class StepDefination extends utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions


		res=given().spec(requestSpecification())
				.body(data.addplacepayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String methoud) {
		// Write code here that turns the phrase above into concrete actions

		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec=new ResponseSpecBuilder().
				expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(methoud.equalsIgnoreCase("post"))
		{
			response=res.when().post(resourceAPI.getResource());
		}
		else if(methoud.equalsIgnoreCase("get"))
		{
			response=res.when().get(resourceAPI.getResource());
		}
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
		// Write code here that turns the phrase above into concrete actions


		assertEquals(getJsonPath(response, keyvalue),expectedvalue );
	}

	@Then("verfiy place_Id created maps to {string} using {string}")
	public void verfiy_place_id_created_maps_to_using(String expectedname,String resource) throws IOException {

		 place_id=getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource,"GET");
		String actualname=getJsonPath(response, "name");
		assertEquals(actualname, expectedname);

	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   res= given().spec(requestSpecification()).body(data.deleteplacepayload(place_id));
	
	   
	
	
	}



}

