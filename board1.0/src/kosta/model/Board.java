package kosta.model;

import java.io.Serializable;
import java.util.List;

public class Board implements Serializable {
	private int seq;
	private String title;
	private String writer;
	private String contents;
	private String regdate;
	private int hitcount;
	private String fname;
	
	public Board() {}

	public Board(int seq, String title, String writer, String contents, String regdate, int hitcount, String f_name) {
		super();
		this.seq = seq;
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.regdate = regdate;
		this.hitcount = hitcount;
		this.fname = f_name;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getHitcount() {
		return hitcount;
	}

	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String f_name) {
		this.fname = f_name;
	}
	
	
}
