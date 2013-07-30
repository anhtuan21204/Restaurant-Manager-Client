package vn.pipi.restaurant_manager_client.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;
import vn.pipi.restaurant_manager_client.dto.KindOfFoodDTO;
import vn.pipi.restaurant_manager_client.helper.DatabaseHelper;

public class KindOfFoodDAO {
	public static ArrayList<KindOfFoodDTO> getAllKindOfFood(Context context){
		ArrayList<KindOfFoodDTO> kinds = new ArrayList<KindOfFoodDTO>();
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		String sql = "select * from " + RestaurantManagerClientConstant.TABLE_KIND_OF_FOOD;
		dbHelper.open();
		try{
			Cursor cursor = dbHelper.rawQuery(sql, null);			
			int idIndex = cursor.getColumnIndex(RestaurantManagerClientConstant.COL_ID);
			int nameIndex = cursor.getColumnIndex("name");					
			for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
				KindOfFoodDTO kind = new KindOfFoodDTO(cursor.getInt(idIndex), cursor.getString(nameIndex));
				kinds.add(kind);
			}
			cursor.close();
		}catch(Exception e){
			Log.d("Get Kind:", e.toString());
		}finally{
			dbHelper.close();
		}
		
		return kinds;
	}
}
