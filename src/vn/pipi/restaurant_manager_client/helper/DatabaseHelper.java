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
    	
    	String createDB = "CREATE TABLE `res_employee` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `username` TEXT, `fullname` TEXT);"
    					+ "CREATE TABLE `res_floor` (`_id` integer primary key AUTOINCREMENT, `name` text NOT NULL);"
    					+ "CREATE TABLE `res_table` (`_id` integer primary key AUTOINCREMENT, `floor_id` text NOT NULL, `` integer not null);"
    					+ "CREATE TABLE `res_kind_of_food` (`_id` integer primary key AUTOINCREMENT, `name` text NOT NULL);"
						+ "CREATE TABLE `res_food` (`_id` integer primary key AUTOINCREMENT, `name` text NOT NULL, `kind_id` integer not null, `image` text, `price` integer not null);";
        String insertFloor = "INSERT INTO `res_floor` (`id`, `name`) VALUES(1, 'Ground Floor'),(2, '1st Floor');";
    	String insertTable = "INSERT INTO `res_table` (`id`, `name`, `floor_id`) VALUES(1, '101', 1),(2, '102', 1),(3, '201', 2),(4, '103', 1),(5, '104', 1),(6, '202', 2),(7, '105', 1),(8, '106', 1);";
    	
    	
    	
    	public OpenHelper(Context context) {
            super(context, RestaurantManagerClientConstant.DBName, null, RestaurantManagerClientConstant.DATABASE_VERSION);
        }
 
        @Override
        public void onCreate(SQLiteDatabase arg0) {
            arg0.execSQL(createDB);
            arg0.execSQL(insertFloor);
            arg0.execSQL(insertTable);
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }
}
