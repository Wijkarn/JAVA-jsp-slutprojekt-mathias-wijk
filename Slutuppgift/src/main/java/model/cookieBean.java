package model;

public class cookieBean {
	
	private String city = "";
	private String country = "";
	private boolean searchHistory;
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setSearchHistory(boolean searchHistory) {
		this.searchHistory = searchHistory;
	}
	
	public boolean getSearchHistory() {
		return searchHistory;
	}
}

