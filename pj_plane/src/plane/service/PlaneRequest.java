package plane.service;

public class PlaneRequest {
	private String date;
	private String dep_loc;
	private String arr_loc;
	
	public PlaneRequest(String date, String dep_loc, String arr_loc) {
		super();
		this.date = date;
		this.dep_loc = dep_loc;
		this.arr_loc = arr_loc;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDep_loc() {
		return dep_loc;
	}
	public void setDep_loc(String dep_loc) {
		this.dep_loc = dep_loc;
	}
	public String getArr_loc() {
		return arr_loc;
	}
	public void setArr_loc(String arr_loc) {
		this.arr_loc = arr_loc;
	}
	
}
