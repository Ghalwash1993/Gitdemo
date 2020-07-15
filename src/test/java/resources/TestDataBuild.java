package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addplacepayload(String name,String language,String adress)
	{

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(adress);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");

		List<String> mylist= new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shoe");

		p.setName(name);
		p.setTypes(mylist);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	public String deleteplacepayload(String placeId)
	{
		return "{\r\n\"place_id\": \""+placeId+"\"\r\n}";
		
	}
}
