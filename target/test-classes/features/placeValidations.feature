Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is being Succesfully adding using AddPlaceAPI
     Given Add Place Payload "<name>" "<language>" "<address>"
     When user calls "AddplaceAPI" with "POST" http request
     Then the API call got success with status code 200
     And "status" in response body is "OK"
     And "scope" in response body is "APP"
     And verfiy place_Id created maps to "<name>" using "getPlaceApi"
     Examples:
         |name   |language|address           |
         |AAhouse| English|world cross center|
     #    |BBhouse|Spanish |sea cross center  |
     
     @DeletePlace
     Scenario: Verfiy if Delete Place functionality is working
     
     	Given DeletePlace Payload
     	When  user calls "deletePlaceAPI" with "POST" http request
     	Then  the API call got success with status code 200
     	And  "status" in response body is "OK"