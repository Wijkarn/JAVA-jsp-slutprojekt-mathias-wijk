package model;

public class weatherBean {

	private String cityStr;

	private String countryStr;

	private String cloudsStr;
	
	private int temperatureInt;
	
	private String lastUpdateStr;
	
	private String lastUpdateTimeStr;

	public weatherBean(String cityStr, String countryStr) {

		this.cityStr = cityStr;
		this.countryStr = countryStr;

	}

	public String getCityStr() {
		return cityStr;
	}

	public String getCountryStr() {
		return countryStr;
	}

	public String getCloudsStr() {
		return cloudsStr;
	}

	public void setCloudsStr(String cloudsStr) {
		this.cloudsStr = cloudsStr;
	}
	
	public int getTemperatureInt() {
		return temperatureInt;
	}
	
	public void setTemperatureInt(int temperatureInt) {
		this.temperatureInt = temperatureInt;
	}
	
	public void setLastUpdateStr(String lastUpdateStr) {
		this.lastUpdateStr = lastUpdateStr;
	}
	
	public String getLastUpdateStr() {
		return lastUpdateStr;
	}
	public void setLastUpdateTimeStr(String lastUpdateTimeStr) {
		this.lastUpdateTimeStr = lastUpdateTimeStr;
	}
	
	public String getLastUpdateTimeStr() {
		return lastUpdateTimeStr;
	}
}
