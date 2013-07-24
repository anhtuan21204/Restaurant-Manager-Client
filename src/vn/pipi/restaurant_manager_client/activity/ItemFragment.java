package vn.pipi.restaurant_manager_client.activity;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.dao.TableDAO;
import vn.pipi.restaurant_manager_client.dto.TableDTO;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class ItemFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_item, container, false);
		return view;
	}
	
	public void displayTables(Context context, int floorID){
		ArrayList<TableDTO> tables = TableDAO.getTablesFromFloorID(getView().getContext(), floorID);
		GridView gvTable = (GridView) getView().findViewById(R.id.gvTable);
		String[] nameOfTable = new String[tables.size()];
		for(int i = 0; i<tables.size(); i++){
			nameOfTable[i] = tables.get(i).getName();
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), R.layout.simple1, nameOfTable);
		gvTable.setAdapter(adapter);
	}
	
}
