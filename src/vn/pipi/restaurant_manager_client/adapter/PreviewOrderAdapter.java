package vn.pipi.restaurant_manager_client.adapter;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.dao.FoodDAO;
import vn.pipi.restaurant_manager_client.dto.DetailOrderDTO;
import vn.pipi.restaurant_manager_client.dto.FoodDTO;
import vn.pipi.restaurant_manager_client.helper.OrderHelper;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PreviewOrderAdapter extends BaseAdapter{
	private Activity activity;
	
	public PreviewOrderAdapter(Activity activity){
		super();
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return OrderHelper.getListOfDetailOrder().size();
	}

	@Override
	public DetailOrderDTO getItem(int position) {
		return OrderHelper.getListOfDetailOrder().get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();
		
		if(convertView==null)
		{
			view = new ViewHolder();
			convertView = inflator.inflate(R.layout.preview_order_adapter, null);
			
			view.textNameOfFood = (TextView) convertView.findViewById(R.id.nameOfFood);
			view.textPriceOfFood = (TextView) convertView.findViewById(R.id.priceOfFood);
			view.textPriceOrder = (TextView) convertView.findViewById(R.id.price);
			view.textQuantityOfFood = (TextView) convertView.findViewById(R.id.quantityOfFood);
			
			convertView.setTag(view);
		}
		else
		{
			view = (ViewHolder) convertView.getTag();
		}
		if(OrderHelper.getListOfDetailOrder().size()>0){
			FoodDTO food = FoodDAO.getFoodDTOFromID(activity, getItem(position).getFoodId());
			if(food!=null){
				view.textNameOfFood.setText(food.getName());
				view.textPriceOfFood.setText(String.valueOf(food.getPrice()));
				view.textQuantityOfFood.setText("x "+String.valueOf(getItem(position).getQuantity()));
				view.textPriceOrder.setText("= "+String.valueOf(getItem(position).getPrice()));
			}
		}
		return convertView;
	}
	
	static class ViewHolder{
		TextView textNameOfFood;
		TextView textPriceOfFood;
		TextView textQuantityOfFood;
		TextView textPriceOrder;
		
	}
}
