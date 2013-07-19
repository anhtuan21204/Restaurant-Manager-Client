package vn.pipi.restaurant_manager_client.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;

public class LoginFunction {
	public static JSONObject loginUser(String username, String password){
		//Xây dựng các giá trị
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", RestaurantManagerClientConstant.TAG_LOGIN));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = JSONParser.getJSONFromUrl(RestaurantManagerClientConstant.URL_LOGIN, params);
		//Trả về đối tượng là 1 JSONObject
		return json;
	}
}
