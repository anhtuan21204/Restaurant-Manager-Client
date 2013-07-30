package vn.pipi.restaurant_manager_client.adapter;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter{
	private ArrayList<String> listTable;
	private Activity activity;
	
	public GridViewAdapter(Activity activity,ArrayList<String> listTable) {
		super();
		this.listTable = listTable;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {		
		return listTable.size();
	}

	@Override
	public String getItem(int position) {		
		return listTable.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return 0;
	}

	public static class ViewHolder
	{
		public TextView txtViewTitle;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();
		
		if(convertView==null)
		{
			view = new ViewHolder();
			convertView = inflator.inflate(R.layout.gridview_row, null);
			
			view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);
			
			convertView.setTag(view);
		}
		else
		{
			view = (ViewHolder) convertView.getTag();
		}
		
		view.txtViewTitle.setText(listTable.get(position));
		
		return convertView;
	}

}

