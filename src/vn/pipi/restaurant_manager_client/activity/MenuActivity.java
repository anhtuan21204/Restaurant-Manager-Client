package vn.pipi.restaurant_manager_client.activity;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.adapter.FoodAdapter;
import vn.pipi.restaurant_manager_client.dao.FoodDAO;
import vn.pipi.restaurant_manager_client.dao.KindOfFoodDAO;
import vn.pipi.restaurant_manager_client.dto.DetailOrderDTO;
import vn.pipi.restaurant_manager_client.dto.FoodDTO;
import vn.pipi.restaurant_manager_client.dto.KindOfFoodDTO;
import vn.pipi.restaurant_manager_client.helper.OrderHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends Activity {
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		ListView listView = (ListView) findViewById(R.id.listMenu);		
		
		//Lấy tên bàn từ activity chính gửi qua
		Bundle extras = getIntent().getExtras();
		if(extras == null)
			return;
		final String table = extras.getString("Table");
		
		// Hiển thị category và món ăn
		final ArrayList<KindOfFoodDTO> kinds = KindOfFoodDAO.getAllKindOfFood(context);
		ArrayList<String> nameOfKind = new ArrayList<String>();
		for(int i=0; i<kinds.size(); i++){
			nameOfKind.add(kinds.get(i).getName());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, nameOfKind);
		listView.setAdapter(adapter);
		if(kinds.size() >0){
			selectKind(kinds.get(0).getId());
		}
		listView.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				selectKind(kinds.get(position).getId());
			}
        });
		
		//Tìm kiếm món ăn
		
		//Review order
		Button btnPreviewOrder = (Button) findViewById(R.id.btnPreviewOrder);
		btnPreviewOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, PreviewOrderActivity.class);
				intent.putExtra("table", table);
				startActivity(intent);
			}
		});
			
	}
	private void selectKind(int id){
		ArrayList<FoodDTO> foods = FoodDAO.geFoodFromKind(context, id);
		ListView listFood =(ListView) findViewById(R.id.listFood);
		FoodAdapter foodAdapter = new FoodAdapter(this, R.layout.food_item, foods);
		listFood.setAdapter(foodAdapter);
	}
	
	public void addFoodOnClickHandler(View v) {
		FoodDTO food = (FoodDTO) v.getTag();
		DetailOrderDTO detailOrder = new DetailOrderDTO(food.getId(), 1, food.getPrice());
		OrderHelper.setDetailOrder(detailOrder);
		Toast.makeText(context, "Đã thêm 1 phần " + food.getName(), Toast.LENGTH_SHORT).show();
	}
}
