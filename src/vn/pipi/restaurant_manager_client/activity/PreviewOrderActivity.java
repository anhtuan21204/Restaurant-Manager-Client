package vn.pipi.restaurant_manager_client.activity;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.adapter.PreviewOrderAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

public class PreviewOrderActivity extends Activity{
	final Context context = this;
	ListView listPrivewOrder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_order);
		loadTabs();
		loadDataTab1();
	}
	
	private void loadTabs(){		
		final TabHost tab=(TabHost) findViewById(android.R.id.tabhost);
		tab.setup();
		TabHost.TabSpec spec;
		//Tạo tab1
		spec=tab.newTabSpec("t1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Chưa Order");
		tab.addTab(spec);
		//Tạo tab2
		spec=tab.newTabSpec("t2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Đã Order");
		tab.addTab(spec);
		//Thiết lập tab mặc định được chọn ban đầu là tab 0
		tab.setCurrentTab(0);
	}
	
	private void loadDataTab1(){
		listPrivewOrder = (ListView) findViewById(R.id.listTabPreviewOrder);
		PreviewOrderAdapter adapter = new PreviewOrderAdapter(this);
		listPrivewOrder.setAdapter(adapter);
	}
}
