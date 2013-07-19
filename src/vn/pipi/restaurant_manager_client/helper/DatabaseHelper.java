package vn.pipi.restaurant_manager_client.helper;

import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper{
	private static Context context;
    static SQLiteDatabase db;
    private OpenHelper openHelper;
	String TAG = "DBHelper";
    
	public DatabaseHelper(Context context) {
		DatabaseHelper.context = context;
	}

	public DatabaseHelper open() throws SQLiteException{
		openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
        return this;	
	}
	
	public void close(){
		openHelper.close();
	}	
	
	public long insertData(String tableName, ContentValues cv){
		Log.d(TAG, "Inserting data");
		return db.insert(tableName, null, cv);
	}
	
	//---------------- class OpenHelper ------------------
    private static class OpenHelper extends SQLiteOpenHelper {
    	
    	String createDB = "CREATE TABLE " + RestaurantManagerClientConstant.TABLE_EMPLOYEE + "("
    			+ RestaurantManagerClientConstant.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, fullname TEXT);";

        public OpenHelper(Context context) {
            super(context, RestaurantManagerClientConstant.DBName, null, RestaurantManagerClientConstant.DATABASE_VERSION);
        }
 
        @Override
        public void onCreate(SQLiteDatabase arg0) {
            arg0.execSQL(createDB);
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }
}
