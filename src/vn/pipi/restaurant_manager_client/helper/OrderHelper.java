package vn.pipi.restaurant_manager_client.helper;

import java.util.LinkedList;

import vn.pipi.restaurant_manager_client.dto.DetailOrderDTO;

public class OrderHelper {
	
	private static LinkedList<DetailOrderDTO> detailOrders = new LinkedList<DetailOrderDTO>();
	
	public static void setDetailOrder(DetailOrderDTO detailOrder){
		if(detailOrders.size() > 0){
			for(int i=0; i<detailOrders.size(); i++){
				if(detailOrders.get(i).getFoodId() == detailOrder.getFoodId()){
					int quantity = detailOrders.get(i).getQuantity() + detailOrder.getQuantity();
					int price = detailOrders.get(i).getPrice() + detailOrder.getPrice();
					detailOrders.get(i).setQuantity(quantity);
					detailOrders.get(i).setPrice(price);
					return;
				}
			}
			detailOrders.add(detailOrder);
		}else{
			detailOrders.add(detailOrder);
		}
	}
	
	public static LinkedList<DetailOrderDTO> getListOfDetailOrder(){
		return detailOrders;
	}
	
	public static void completeOrder(){
		detailOrders = new LinkedList<DetailOrderDTO>();
	}
}
