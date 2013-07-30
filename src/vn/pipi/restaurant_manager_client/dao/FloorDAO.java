package vn.pipi.restaurant_manager_client.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;
import vn.pipi.restaurant_manager_client.dto.FloorDTO;
import vn.pipi.restaurant_manager_client.helper.DatabaseHelper;


public class FloorDAO {
	public static ArrayList<FloorDTO> getListOfFloor(Context context){
		ArrayList<FloorDTO> floors = new ArrayList<FloorDTO>();
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		String sql = "select * from " + RestaurantManagerClientConstant.TABLE_FLOOR;
		dbHelper.open();
		try{
			Cursor cursor = dbHelper.rawQuery(sql, null);
			
			int idIndex = cursor.getColumnIndex(RestaurantManagerClientConstant.COL_ID);
			int nameIndex = cursor.getColumnIndex("name");		
			
			for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
				FloorDTO floor = new FloorDTO(cursor.getInt(idIndex), cursor.getString(nameIndex));
				floors.add(floor);
			}
			cursor.close();
		}catch(Exception e){
			Log.d("Get Floor:", e.toString());
		}finally{
			dbHelper.close();
		}
		return floors;
	}
}
