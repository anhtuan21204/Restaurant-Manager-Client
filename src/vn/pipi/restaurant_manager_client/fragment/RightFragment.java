package vn.pipi.restaurant_manager_client.fragment;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.activity.MenuActivity;
import vn.pipi.restaurant_manager_client.adapter.GridViewAdapter;
import vn.pipi.restaurant_manager_client.dao.TableDAO;
import vn.pipi.restaurant_manager_client.dto.TableDTO;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

public class RightFragment extends Fragment{
	GridView gvTable;
	GridViewAdapter gvAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_item, container, false);			
		return view;
	}
	
	public void displayTables(int floorID){
		ArrayList<TableDTO> tables = TableDAO.getTablesFromFloorID(getView().getContext(), floorID);
		//Log.d("Gridview", gvTable.toString());
		final ArrayList<String> nameOfTable = new ArrayList<String>();
		for(int i = 0; i<tables.size(); i++){
			nameOfTable.add(tables.get(i).getName());
		}
		
		gvTable = (GridView) getView().findViewById(R.id.gridTable);
		
		gvAdapter = new GridViewAdapter(getActivity(), nameOfTable);
		gvTable.setAdapter(gvAdapter);
		gvTable.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				final Dialog dialog = createDialog(nameOfTable.get(position));
				dialog.show();
				
				Button btnAddOrder = (Button) getView().findViewById(R.id.btnNewOrder);
		        Button btnViewOrder = (Button) getView().findViewById(R.id.btnOrdered);
		        Button btnCheckBill = (Button) getView().findViewById(R.id.btnCheckBill);
		        Button btnCancel = (Button) getView().findViewById(R.id.btnCancelOrder);
		        
		        btnAddOrder.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getActivity(), MenuActivity.class);
						intent.putExtra("Name", "102");
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
			}
		});
	}
	
	private Dialog createDialog(final String name){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_order);
        dialog.setTitle("Order tại bàn:"+name);
        
        
        return dialog;
    }
}
