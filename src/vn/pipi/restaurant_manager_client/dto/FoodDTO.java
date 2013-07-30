package vn.pipi.restaurant_manager_client.dto;

public class FoodDTO {
	public FoodDTO(int id, String name, int kindId, String image, int price) {
		this.id = id;
		this.name = name;
		this.kindId = kindId;
		this.image = image;
		this.price = price;
	}
	int id;
	String name;
	int kindId;
	String image;
	int price;
	String mainElement;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMainElement() {
		return mainElement;
	}
	public void setMainElement(String mainElement) {
		this.mainElement = mainElement;
	}
	
}
