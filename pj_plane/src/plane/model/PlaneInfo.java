package plane.model;

public class PlaneInfo {
	private Integer id;
	private String depLoc;
	private String arrLoc;
	private String date;
	private String airLine;
	private String depTime;
	private String arrTime;
	
	public PlaneInfo() {}

	public PlaneInfo(Integer id, String depLoc, String arrLoc, String date, String airLine, String depTime, String arrTime) {
		super();
		this.id = id;
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		this.date = date;
		this.airLine = airLine;
		this.depTime = depTime;
		this.arrTime = arrTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepLoc() {
		return depLoc;
	}

	public void setDepLoc(String depLoc) {
		this.depLoc = depLoc;
	}

	public String getArrLoc() {
		return arrLoc;
	}

	public void setArrLoc(String arrLoc) {
		this.arrLoc = arrLoc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
}
