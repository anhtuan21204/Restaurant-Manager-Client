package vn.pipi.restaurant_manager_client.dto;

public class TableDTO {
	private int id;
	private String name;
	private int floorID;
	
	public TableDTO(int id, String name, int floorID) {
		this.id = id;
		this.name = name;
		this.floorID = floorID;
	}
	
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
	public int getFloorID() {
		return floorID;
	}
	public void setFloorID(int floorID) {
		this.floorID = floorID;
	}
	
}
