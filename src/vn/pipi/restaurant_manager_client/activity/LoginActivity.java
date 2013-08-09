package vn.pipi.restaurant_manager_client.activity;

import org.json.JSONObject;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;
import vn.pipi.restaurant_manager_client.dao.EmployeeDAO;
import vn.pipi.restaurant_manager_client.helper.DatabaseHelper;
import vn.pipi.restaurant_manager_client.helper.LoginFunction;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	Button btnLogin;
	EditText txtUsername;
	EditText txtPass;
	ProgressDialog progressDialog;
	Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//Create DB
		DatabaseHelper db = new DatabaseHelper(this);
		db.open();
		db.close();
		
		btnLogin = (Button) findViewById(R.id.btnLogin);
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		txtPass = (EditText) findViewById(R.id.txtPassword);
		final Context context = this;
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setTitle("Authentication");
		progressDialog.setMessage("Please wait ...");
		progressDialog.setIndeterminate(true);
		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				progressDialog.show();
				final String username = txtUsername.getText().toString();
				final String password = txtPass.getText().toString();
				if(username.trim().length() == 0 || password.trim().length() == 0){
					progressDialog.dismiss();
					displayToast(context, "Username or password incorrect!");    				
				}else{
					Thread t = new Thread() {  
			            public void run() {  
			            	try{
			            		JSONObject jsonResponse = LoginFunction.loginUser(username, password);
			            		//Log.d("doLogin", jsonResponse.toString());
			            		if (jsonResponse!=null && jsonResponse.getString(RestaurantManagerClientConstant.KEY_SUCCESS) != null) {									
									if(Integer.parseInt(jsonResponse.getString(RestaurantManagerClientConstant.KEY_SUCCESS)) == 1){
										JSONObject jsonUser = jsonResponse.getJSONObject("user");
					            		try{
					            			EmployeeDAO.insertEmployee(context, username, jsonUser.getString("fullname"));
					            			handler.post(new Runnable(){
					            				public void run(){
					            					//displayToast(context, "Login Successful");
					            					Intent i = new Intent(context, MainActivity.class);
					            					startActivity(i);
					            					finish();
					            				}
				            				});
					            		}catch(SQLiteException ex){
					            			//Log.d("DB", "insert employee" + ex.toString());
						            		progressDialog.dismiss();
					            		}
									}else{
										handler.post(new Runnable(){
				            				public void run(){
				            					displayToast(context, "Username or password incorrect!");
				            				}
			            				});
									}
			            		}else{
			            			handler.post(new Runnable(){
			            				public void run(){
			            					displayToast(context, "Can't connect to server!");
			            				}
		            				});
			            		}
			            		progressDialog.dismiss();
			            	} catch(Exception e){
			            		progressDialog.dismiss();
			            		Log.d("doLogin", "Error:" + e.toString());
			            	}
			            }
					};
					t.start();
				}
				
			}
		});
	}
	/***
	 * 
	 * @param context
	 * @param text
	 */
	private void displayToast(Context context, String text){
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		toast.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
