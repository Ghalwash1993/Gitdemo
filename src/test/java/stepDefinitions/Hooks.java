package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{

		StepDefination m = new StepDefination();

		if(StepDefination.place_id==null)
		{
			m.add_place_payload("susan", "arabic", "egypt");
			m.user_calls_with_http_request("AddplaceAPI", "POST");
			m.verfiy_place_id_created_maps_to_using("susan", "getPlaceApi");
		}
	}
}
