package plane.model;

public class SearchKeyWord {
	private String date;
	private String depLoc;
	private String arrLoc;
	
	public SearchKeyWord() {}
	
	public SearchKeyWord(String date, String depLoc, String arrLoc) {
		super();
		this.date = date;
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
}
