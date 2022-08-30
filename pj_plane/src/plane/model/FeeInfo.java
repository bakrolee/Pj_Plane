package plane.model;

import java.util.Date;

public class FeeInfo {
	private int planeId;
	private int fee;
	private String searchDay;
	private String searchTime;
	
	public FeeInfo() {}
	
	public FeeInfo(int fee, String searchDay, String searchTime) {
		super();
		this.fee = fee;
		this.searchDay = searchDay;
		this.searchTime = searchTime;
	}

	public FeeInfo(int planeId, int fee) {
		super();
		this.planeId = planeId;
		this.fee = fee;
	}

	public FeeInfo(int planeId, int fee, String searchDay, String searchTime) {
		super();
		this.planeId = planeId;
		this.fee = fee;
		this.searchDay = searchDay;
		this.searchTime = searchTime;
	}

	public int getPlaneId() {
		return planeId;
	}

	public void setPlaneId(int planeId) {
		this.planeId = planeId;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}
}
