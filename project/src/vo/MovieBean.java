package vo;

import java.sql.Date;

public class MovieBean {
	
	private int MOVIE_NUM;
	private String MOVIE_GENRE;
	private String MOVIE_SUBJECT;
	private String MOVIE_CONTENT;
	private String MOVIE_FILE;
	private String ID;
	private Date MOVIE_DATE;
	private int MOVIE_READCOUNT;
	
	public int getMOVIE_NUM() {
		return MOVIE_NUM;
	}
	public void setMOVIE_NUM(int mOVIE_NUM) {
		MOVIE_NUM = mOVIE_NUM;
	}
	public String getMOVIE_GENRE() {
		return MOVIE_GENRE;
	}
	public void setMOVIE_GENRE(String mOVIE_GENRE) {
		MOVIE_GENRE = mOVIE_GENRE;
	}
	public String getMOVIE_SUBJECT() {
		return MOVIE_SUBJECT;
	}
	public void setMOVIE_SUBJECT(String mOVIE_SUBJECT) {
		MOVIE_SUBJECT = mOVIE_SUBJECT;
	}
	public String getMOVIE_CONTENT() {
		return MOVIE_CONTENT;
	}
	public void setMOVIE_CONTENT(String mOVIE_CONTENT) {
		MOVIE_CONTENT = mOVIE_CONTENT;
	}
	public String getMOVIE_FILE() {
		return MOVIE_FILE;
	}
	public void setMOVIE_FILE(String mOVIE_FILE) {
		MOVIE_FILE = mOVIE_FILE;
	}
	public Date getMOVIE_DATE() {
		return MOVIE_DATE;
	}
	public void setMOVIE_DATE(Date mOVIE_DATE) {
		MOVIE_DATE = mOVIE_DATE;
	}
	public int getMOVIE_READCOUNT() {
		return MOVIE_READCOUNT;
	}
	public void setMOVIE_READCOUNT(int mOVIE_READCOUNT) {
		MOVIE_READCOUNT = mOVIE_READCOUNT;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

	
}