package vn.pipi.restaurant_manager_client.activity;

import vn.pipi.restaurant_manager_client.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements ListFragment.OnItemSelectedListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onFloorSelected(int floorID) {
		ItemFragment item = (ItemFragment) getFragmentManager().findFragmentById(R.id.itemFragment);
		if(item != null && item.isInLayout()){
			item.displayTables(this, floorID);
		}		
	}

}
