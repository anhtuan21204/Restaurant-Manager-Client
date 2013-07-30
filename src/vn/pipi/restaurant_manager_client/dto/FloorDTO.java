package vn.pipi.restaurant_manager_client.dto;

public class FloorDTO {
	public FloorDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	int id;
	String name;
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
	
}
