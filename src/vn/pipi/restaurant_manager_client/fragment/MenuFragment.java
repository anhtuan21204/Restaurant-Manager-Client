package vn.pipi.restaurant_manager_client.fragment;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.dao.KindOfFoodDAO;
import vn.pipi.restaurant_manager_client.dto.KindOfFoodDTO;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MenuFragment extends Fragment{
	String table;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listview_menu, container, false);		
		ListView listView = (ListView) view.findViewById(R.id.listMenu);		
		
		final ArrayList<KindOfFoodDTO> kinds = KindOfFoodDAO.getAllKindOfFood(getActivity());
		ArrayList<String> nameOfKind = new ArrayList<String>();
		for(int i=0; i<kinds.size(); i++){
			nameOfKind.add(kinds.get(i).getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameOfKind);
		listView.setAdapter(adapter);
		
		Bundle extras = getActivity().getIntent().getExtras();
		if(extras == null)
			return view;
		table = extras.getString("name");
		Log.d("Name table", table);
		
		listView.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				selectKind(kinds.get(position).getId());
			}
        });
		
		return view;
	}
	
	private void selectKind(int kindId){
		FoodFragment fragment = (FoodFragment) getFragmentManager().findFragmentById(R.layout.listview_food);
		if(fragment!=null && fragment.isInLayout()){
			fragment.displayFood(kindId);
		}
	}
}
