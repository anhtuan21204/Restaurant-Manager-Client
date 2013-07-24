package vn.pipi.restaurant_manager_client.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;
import vn.pipi.restaurant_manager_client.dto.TableDTO;
import vn.pipi.restaurant_manager_client.helper.DatabaseHelper;

public class TableDAO {
	
	public static ArrayList<TableDTO> getTablesFromFloorID(Context context, int floorID){
		ArrayList<TableDTO> tables = new ArrayList<TableDTO>();
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		String sql = "select * from `" + RestaurantManagerClientConstant.TABLE_TABLE + 
					"` where `floor_id` = ?";
		String[] str = {String.valueOf(floorID)};
		
		dbHelper.open();
		try{
			Cursor cursor = dbHelper.rawQuery(sql, str);
			
			int idIndex = cursor.getColumnIndex(RestaurantManagerClientConstant.COL_ID);
			int nameIndex = cursor.getColumnIndex("name");		
			
			for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
				TableDTO table = new TableDTO(cursor.getInt(idIndex), cursor.getString(nameIndex), floorID);
				tables.add(table);
			}
			cursor.close();
		}catch(Exception e){
			Log.d("Get tables:", e.toString());
		}finally{
			
			dbHelper.close();
		}
		return tables;
	}
}
