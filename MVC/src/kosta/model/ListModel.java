package kosta.model;

import java.util.List;

public class ListModel {
	private List<Board> list;
	private int requestPage;
	private int nPages;
	private int startPage;
	private int endPage;
	
	public ListModel() {}
	
	public ListModel(List<Board> list, int requestPage, int nPages, int startPage, int endPage) {
		super();
		this.list = list;
		this.requestPage = requestPage;
		this.nPages = nPages;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public List<Board> getList() {
		return list;
	}

	public void setList(List<Board> list) {
		this.list = list;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getnPages() {
		return nPages;
	}

	public void setnPages(int nPages) {
		this.nPages = nPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
}
