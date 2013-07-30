package vn.pipi.restaurant_manager_client.helper;

import vn.pipi.restaurant_manager_client.constant.RestaurantManagerClientConstant;
import vn.pipi.restaurant_manager_client.dto.FloorDTO;
import vn.pipi.restaurant_manager_client.dto.FoodDTO;
import vn.pipi.restaurant_manager_client.dto.KindOfFoodDTO;
import vn.pipi.restaurant_manager_client.dto.TableDTO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper{
	private static Context context;
    private static SQLiteDatabase db;
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
	
	public void execSQL(String query){
		db.execSQL(query);
	}
	
	public Cursor rawQuery(String sql, String[] selections){
		return db.rawQuery(sql, selections);
	}
	
	//---------------- class OpenHelper ------------------
    private static class OpenHelper extends SQLiteOpenHelper {
    	
    	String createTableEmpoyee = "CREATE TABLE `res_employee` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `username` TEXT, `fullname` TEXT);";
		String createTableFloor = "CREATE TABLE `res_floor` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` text NOT NULL);";
		String createTableTable = "CREATE TABLE `res_table` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `floor_id` text NOT NULL, `name` TEXT not null);";
		String createTableKindFood = "CREATE TABLE `res_kind_of_food` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` text NOT NULL);";
		String createTableFood = "CREATE TABLE `res_food` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` text NOT NULL, `kind_id` integer not null, `image` text, `price` integer not null);";
        String createTableOrder = "CREATE TABLE `res_order` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `table_id` INTEGER NOT NULL, `employee_id` integer not null, `total` INTEGER, `TIME` integer not null);";
        String createTableDetailOrder = "CREATE TABLE `res_detail_order` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `order_id` INTEGER NOT NULL, `food_id` integer not null, `quantity` INTEGER not null, `price` integer not null);";
        
		
    	public OpenHelper(Context context) {
            super(context, RestaurantManagerClientConstant.DBName, null, RestaurantManagerClientConstant.DATABASE_VERSION);
        }
 
        @Override
        public void onCreate(SQLiteDatabase arg0) {
            arg0.execSQL(createTableEmpoyee);
            arg0.execSQL(createTableFloor);
            arg0.execSQL(createTableTable);
            arg0.execSQL(createTableFood);
            arg0.execSQL(createTableKindFood);
            arg0.execSQL(createTableOrder);
            arg0.execSQL(createTableDetailOrder);
            
            insertFloor(arg0);
            insertTable(arg0);
            insertKindOfFood(arg0);
            insertFood(arg0);
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
        
        private void insertFloor(SQLiteDatabase db){
        	FloorDTO[] floors = {new FloorDTO(1, "Ground Floor"), new FloorDTO(2, "First Floor")};        	
       		
        	ContentValues values = new ContentValues();
        	int row = 0;
        	while(row < floors.length){
        	 	values.put("_id", floors[row].getId());
	        	values.put("name", floors[row].getName());
	        	row++;
	        	db.insert("res_floor", null, values);
        	}        	
        }
        private void insertTable(SQLiteDatabase db){
        	TableDTO[] tables = {new TableDTO(1, "101", 1),new TableDTO(2, "102", 1), new TableDTO(3, "201", 2),
        						new TableDTO(4, "103", 1),new TableDTO(5, "104", 1),new TableDTO(6, "202", 2),
        						new TableDTO(7, "105", 1),new TableDTO(8, "106", 1)};

    		ContentValues values = new ContentValues();
        	for(int i=0; i<tables.length; i++){
            	values.put("_id", tables[i].getId());
	        	values.put("name", tables[i].getName());
	        	values.put("floor_id", tables[i].getFloorID());
	        	
	        	db.insert("res_table", null, values);
        	}
        }
        private void insertKindOfFood(SQLiteDatabase db){
        	KindOfFoodDTO[] kinds = {new KindOfFoodDTO(1, "Món Thái"), new KindOfFoodDTO(2, "Món Xào"),
        							new KindOfFoodDTO(3, "Món Cơm và Mì"), new KindOfFoodDTO(4, "Món Lẩu"),
        							new KindOfFoodDTO(5, "Thức uống")};

    		ContentValues values = new ContentValues();
        	for(int i=0; i<kinds.length; i++){
            	values.put("_id", kinds[i].getId());
	        	values.put("name", kinds[i].getName());
	        	
	        	db.insert("res_kind_of_food", null, values);
        	}
        }
        private void insertFood(SQLiteDatabase db){
        	FoodDTO[] foods = {new FoodDTO(1, "Bún gạo TomYum", 1, null, 50000), 
        			new FoodDTO(1, "Bún gạo TomYum", 1, null, 70000), new FoodDTO(2, "Gỏi đu đủ Thái", 1, null, 50000),
        			new FoodDTO(3, "Gỏi miến Thái", 1, null, 50000), new FoodDTO(4, "Chả tôm Thái", 1, null, 50000),
        			new FoodDTO(5, "Bò sốt tiêu đen", 2, null, 80000), new FoodDTO(6, "Cải rổ sốt dầu hào", 2, null, 60000),
        			new FoodDTO(7, "Gà chiên TomYum với xôi", 3, null, 50000), new FoodDTO(8, "Cơm chiên hải sản", 3, null, 50000),
        			new FoodDTO(9, "Lẩu Thái chua cay", 4, null, 200000), new FoodDTO(10, "Lẩu xí quách", 4, null, 200000),
        			new FoodDTO(11, "Bia Heineken", 5, null, 50000), new FoodDTO(12, "Bia Tiger", 5, null, 50000)};

    		ContentValues values = new ContentValues();
        	for(int i=0; i<foods.length; i++){
            	values.put("_id", foods[i].getId());
	        	values.put("name", foods[i].getName());
	        	values.put("kind_id", foods[i].getKindId());
	        	values.put("image", "null");
	        	values.put("price", foods[i].getPrice());
	        	
	        	db.insert("res_food", null, values);
        	}
        }
    }
}
