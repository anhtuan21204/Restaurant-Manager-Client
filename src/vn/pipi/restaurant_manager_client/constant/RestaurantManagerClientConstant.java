package vn.pipi.restaurant_manager_client.constant;

public class RestaurantManagerClientConstant {
	// Database
	public static final String DBName = "restaurant_manager";
	public static final String DBPATH = "/data/data/vn.pipi.restaurant_manager_client/databases/";
	public static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_EMPLOYEE = "res_employee";
	public static final String TABLE_FLOOR = "res_floor";
	public static final String TABLE_TABLE = "res_table";
	
	public static final String COL_ID = "_id";
	
	// URL Server
	public static final String URL_LOGIN = "http://10.0.2.2/restaurant_manager/login.php";
	
	
	// tag sent to server
	public static final String TAG_LOGIN = "login";
	
	//
	public static final String KEY_SUCCESS = "success";
}
