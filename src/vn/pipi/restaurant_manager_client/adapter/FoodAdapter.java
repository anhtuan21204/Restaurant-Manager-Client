package vn.pipi.restaurant_manager_client.adapter;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.dto.FoodDTO;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodAdapter extends ArrayAdapter<FoodDTO>{
	private Activity activity;
	private ArrayList<FoodDTO> listFood;
	int layoutId;
	
	public FoodAdapter(Activity activity, int layoutId, ArrayList<FoodDTO> objects) {
		super(activity, layoutId, objects);
		this.activity = activity;
		this.layoutId = layoutId;
		this.listFood = objects;
	}
	
	@Override
	public int getCount() {		
		return listFood.size();
	}

	@Override
	public FoodDTO getItem(int position) {		
		return listFood.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CustomViewFood view;
		LayoutInflater inflator = activity.getLayoutInflater();
		if(convertView==null)
		{
			view = new CustomViewFood();
			convertView = inflator.inflate(layoutId, null);
			view.addFood = (Button) convertView.findViewById(R.id.btnAddFood);
			view.addFood.setTag(listFood.get(position));
			view.imageFood = (ImageView) convertView.findViewById(R.id.imageFood);
			view.nameFood = (TextView) convertView.findViewById(R.id.nameFood);
			view.priceFood = (TextView) convertView.findViewById(R.id.priceFood);
			convertView.setTag(view);
		}else{
			view =(CustomViewFood) convertView.getTag();
		}
		view.nameFood.setText(listFood.get(position).getName());
		view.priceFood.setText(String.valueOf(listFood.get(position).getPrice()));
		
		return convertView;
	}

	public static class CustomViewFood{
		
		ImageView imageFood;
		TextView nameFood;
		Button addFood;
		TextView priceFood;
		
	}
}
