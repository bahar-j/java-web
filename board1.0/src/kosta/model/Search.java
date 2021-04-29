package kosta.model;

import java.util.Arrays;

public class Search {
	private String[] area;
	private String searchKey;
	
	public Search() {}

	public Search(String[] area, String searchKey) {
		super();
		this.area = area;
		this.searchKey = searchKey;
	}

	public String[] getArea() {
		return area;
	}

	public void setArea(String[] area) {
		this.area = area;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	@Override
	public String toString() {
		return "Search [area=" + Arrays.toString(area) + ", searchKey=" + searchKey + "]";
	}
	
	
	
}
