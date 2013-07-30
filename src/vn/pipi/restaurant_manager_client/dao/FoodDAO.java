package vn.pipi.restaurant_manager_client.dao;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;
import vn.pipi.restaurant_manager_client.dto.FoodDTO;
import vn.pipi.restaurant_manager_client.helper.DatabaseHelper;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class FoodDAO {
	public static ArrayList<FoodDTO> geFoodFromKind(Context context, int kindId){
		ArrayList<FoodDTO> foods = new ArrayList<FoodDTO>();
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		String sql = "select * from " + RestaurantManagerClientConstant.TABLE_KIND_OF_FOOD +
						" where kind_id=?";
		String[] str = {String.valueOf(kindId)};
		dbHelper.open();
		try{
			Cursor cursor = dbHelper.rawQuery(sql, str);			
			int idIndex = cursor.getColumnIndex(RestaurantManagerClientConstant.COL_ID);
			int nameIndex = cursor.getColumnIndex("name");					
			int priceIndex = cursor.getColumnIndex("price");
			int imageIndex = cursor.getColumnIndex("image");			
			for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
				FoodDTO food = new FoodDTO(cursor.getInt(idIndex), cursor.getString(nameIndex), kindId, cursor.getString(imageIndex), cursor.getInt(priceIndex));
				foods.add(food);
			}
			cursor.close();
		}catch(Exception e){
			Log.d("Get Food:", e.toString());
		}finally{
			dbHelper.close();
		}
		
		return foods;
	}
}
