package vo;

import java.sql.Date;

public class ReplyBean {
	private int m_reply_num;
	private int m_reply_ref_num;
	private String m_content;
	private String id;
	private Date m_reply_date;
	
	
	public int getM_reply_num() {
		return m_reply_num;
	}
	public void setM_reply_num(int m_reply_num) {
		this.m_reply_num = m_reply_num;
	}
	public int getM_reply_ref_num() {
		return m_reply_ref_num;
	}
	public void setM_reply_ref_num(int m_reply_ref_num) {
		this.m_reply_ref_num = m_reply_ref_num;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getM_reply_date() {
		return m_reply_date;
	}
	public void setM_reply_date(Date m_reply_date) {
		this.m_reply_date = m_reply_date;
	}
	
}
