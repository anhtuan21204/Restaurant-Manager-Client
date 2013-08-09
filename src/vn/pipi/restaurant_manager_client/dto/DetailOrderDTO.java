package vn.pipi.restaurant_manager_client.dto;

public class DetailOrderDTO {
	public DetailOrderDTO(int foodId, int quantity, int price, int orderId) {
		this.foodId = foodId;
		this.quantity = quantity;
		this.price = price;
		this.orderId = orderId;
	}
	public DetailOrderDTO(int foodId, int quantity, int price) {
		this.foodId = foodId;
		this.quantity = quantity;
		this.price = price;
		this.orderId = 0;
	}
	
	int foodId;
	int quantity;
	int price;
	int orderId;
	
	
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}
