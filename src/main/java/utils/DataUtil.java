package utils;

import commons.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;


public class DataUtil extends BaseTest {

	@DataProvider
	public Object[] invalidUserDataProvider() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;

		// Read JSON file
		Object obj = null;
		try {
			obj = parser.parse(new FileReader("src/test/resources/InvalidUserData.json"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		jsonObject = (JSONObject) obj;

		// Extract array data from JSONObject
		assert jsonObject != null;
		JSONArray formInfo = (JSONArray) jsonObject.get("invaliduserdata");

		// String array to store JSONArray data
		String[] dataArray = new String[formInfo.size()];

		// JSONObject to read each JSONArray object
		JSONObject formInfoData;
		String firstname, lastname, email, password;

		// Get data from JSONArray and store in String array
		for (int i = 0; i < formInfo.size(); i++) {
			formInfoData = (JSONObject) formInfo.get(i);
			firstname = (String) formInfoData.get("firstname");
			lastname = (String) formInfoData.get("lastname");
			email = (String) formInfoData.get("email");
			password = (String) formInfoData.get("password");

			dataArray[i] = firstname + "," + lastname + "," + email + "," + password;
		}
		return dataArray;
	}

}
