package vn.pipi.restaurant_manager_client.activity;

import vn.pipi.restaurant_manager_client.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ListFragment extends Fragment {

	private OnItemSelectedListener listener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_list, container, false);
		Button btnGroundFloor = (Button) view.findViewById(R.id.GFloor);
		Button btnFirstFloor = (Button) view.findViewById(R.id.FirstFloor);
		
		btnGroundFloor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				displayTables(1);
			}
		});
		btnFirstFloor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				displayTables(2);
			}
		});
		
		return view;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnItemSelectedListener) {
			listener = (OnItemSelectedListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implement MyListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}
	
	private void displayTables(int floorID){
		listener.onFloorSelected(floorID);
	}	
	
	public interface OnItemSelectedListener {
		public void onFloorSelected(int floorID);
	}
}
