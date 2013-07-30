package vn.pipi.restaurant_manager_client.fragment;

import java.util.ArrayList;

import vn.pipi.restaurant_manager_client.R;
import vn.pipi.restaurant_manager_client.dao.FoodDAO;
import vn.pipi.restaurant_manager_client.dto.FoodDTO;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FoodFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listview_food, container, false);			
		return view;
	}
	
	public void displayFood(int kindId){
		final ArrayList<FoodDTO> foods = FoodDAO.geFoodFromKind(getActivity(), kindId);
		ArrayList<String> nameOfFood = new ArrayList<String>();
		for(int i = 0; i<foods.size(); i++){
			nameOfFood.add(foods.get(i).getName());
		}
	}
}
