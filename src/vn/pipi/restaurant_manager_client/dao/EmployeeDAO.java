package vn.pipi.restaurant_manager_client.dao;

import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;
import vn.pipi.restaurant_manager_client.helper.DatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;

public class EmployeeDAO {
	public static void insertEmployee(Context context, String username, String fullname) throws SQLiteException{
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		ContentValues cv = new ContentValues();
		cv.put("username", username);
		cv.put("fullname", fullname);
		
		dbHelper.open();
		String query = "delete from " + RestaurantManagerClientConstant.TABLE_EMPLOYEE;
		dbHelper.execSQL(query);
		dbHelper.insertData(RestaurantManagerClientConstant.TABLE_EMPLOYEE, cv);
		dbHelper.close();
		
	}
}
