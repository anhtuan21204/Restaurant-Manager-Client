package vn.pipi.restaurant_manager_client.activity;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.adapter.TableAdapter;
import vn.pipi.restaurant_manager_client.dao.FloorDAO;
import vn.pipi.restaurant_manager_client.dao.TableDAO;
import vn.pipi.restaurant_manager_client.dto.FloorDTO;
import vn.pipi.restaurant_manager_client.dto.TableDTO;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity{
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listFloor);
		final ArrayList<FloorDTO> floors = FloorDAO.getListOfFloor(this);
		ArrayList<String> nameOfFloor = new ArrayList<String>();
		for(int i=0; i<floors.size(); i++){
			nameOfFloor.add(floors.get(i).getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameOfFloor);
		listView.setAdapter(adapter);
		selectFloor(1);
		listView.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				selectFloor(floors.get(position).getId());
			}
        });
		
	}
	public void selectFloor(int floorID){
		final ArrayList<TableDTO> tables = TableDAO.getTablesFromFloorID(context, floorID);
		final ArrayList<String> nameOfTable = new ArrayList<String>();
		for(int i = 0; i<tables.size(); i++){
			nameOfTable.add(tables.get(i).getName());
		}
		GridView gvTable = (GridView) findViewById(R.id.gridTable);
		TableAdapter gvAdapter = new TableAdapter(this, nameOfTable);
		gvTable.setAdapter(gvAdapter);
		//gvTable.getChildAt(1).setBackground;
		gvTable.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				final Dialog dialog = new Dialog(context);
		        dialog.setContentView(R.layout.dialog_order);
		        dialog.setTitle("Order tại bàn:"+nameOfTable.get(position));
		        
		        Button btnAddOrder = (Button) dialog.findViewById(R.id.btnNewOrder);
		        Button btnViewOrder = (Button) dialog.findViewById(R.id.btnOrdered);
		        Button btnCheckBill = (Button) dialog.findViewById(R.id.btnCheckBill);
		        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancelOrder);
		        final int pos = position;
		        btnAddOrder.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(context, MenuActivity.class);
						intent.putExtra("Table", nameOfTable.get(pos));
						startActivity(intent);
					}
				});
		        btnViewOrder.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
					}
				});
		        btnCheckBill.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
					}
				});
		        btnCancel.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
		        
				dialog.show();
				
				
			}
		});
	}
}