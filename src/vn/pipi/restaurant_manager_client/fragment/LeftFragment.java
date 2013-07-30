package vn.pipi.restaurant_manager_client.fragment;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.dao.FloorDAO;
import vn.pipi.restaurant_manager_client.dto.FloorDTO;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LeftFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_list, container, false);		
		ListView listView = (ListView) view.findViewById(R.id.listMenu);
		final ArrayList<FloorDTO> floors = FloorDAO.getListOfFloor(getActivity());
		ArrayList<String> nameOfFloor = new ArrayList<String>();
		for(int i=0; i<floors.size(); i++){
			nameOfFloor.add(floors.get(i).getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameOfFloor);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				selectFloor(floors.get(position).getId());
			}
        });
		
		selectFloor(1);
		
		return view;
	}
	
	public void selectFloor(int floorID){
		RightFragment rf = (RightFragment) getFragmentManager().findFragmentById(R.id.fragmentTable);
		if( rf != null && rf.isInLayout()){
			rf.displayTables(floorID);
		}
	}
}
