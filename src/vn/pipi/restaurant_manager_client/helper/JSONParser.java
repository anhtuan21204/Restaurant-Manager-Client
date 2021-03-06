package vn.pipi.restaurant_manager_client.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	static String TAG = "JSONParser";

	public static JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
		//Cố gắng thực hiện gửi yêu cầu lên HTTP
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
		} catch (UnsupportedEncodingException e) {
			Log.d("EncodingException",e.toString());
			return null;
		} catch (ClientProtocolException e) {
			Log.d("ClientProtocolException",e.toString());
			return null;
		} catch (IOException e) {
			Log.d("IOException",e.toString());
			return null;
		} catch (Exception e) {
			return null;
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.d(TAG, "Error converting result " + e.toString());
			return null;
		}
		
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.d(TAG, "Error parsing data " + e.toString());
			return null;
		}
		return jObj;
	}
}
