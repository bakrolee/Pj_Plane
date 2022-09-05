package plane.model;

public class PlaneInfo {
	private Integer id;
	private String depLoc;
	private String arrLoc;
	private String date;
	private String airLine;
	private String depTime;
	private String arrTime;
	private int minfee; 
	private int maxfee; 
	private int nowfee; 
	
	public PlaneInfo() {}

	public PlaneInfo(String airLine, String depTime, String arrTime, int minfee, int maxfee, int nowfee, Integer id) {
		super();
		this.airLine = airLine;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.minfee = minfee;
		this.maxfee = maxfee;
		this.nowfee = nowfee;
		this.id = id;
	}

	public PlaneInfo(String airLine, String depTime, String arrTime) {
		super();
		this.airLine = airLine;
		this.depTime = depTime;
		this.arrTime = arrTime;
	}

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

	public int getMinfee() {
		return minfee;
	}

	public void setMinfee(int minfee) {
		this.minfee = minfee;
	}

	public int getMaxfee() {
		return maxfee;
	}

	public void setMaxfee(int maxfee) {
		this.maxfee = maxfee;
	}

	public int getNowfee() {
		return nowfee;
	}

	public void setNowfee(int nowfee) {
		this.nowfee = nowfee;
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

	@Override
	public String toString() {
		return "PlaneInfo [airLine=" + airLine + ", depTime=" + depTime + ", arrTime=" + arrTime + "]";
	}
}
