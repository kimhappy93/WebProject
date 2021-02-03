package vo;

public class MovieReviewBean {

	private int r_num;
	private int m_id;
	private int m_star;
	private String m_review;
	private String r_date;
	private String id;
	
	public MovieReviewBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieReviewBean(int r_num, int m_id, int m_star, String m_review, String r_date, String id) {
		super();
		this.r_num = r_num;
		this.m_id = m_id;
		this.m_star = m_star;
		this.m_review = m_review;
		this.r_date = r_date;
		this.id = id;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public int getM_star() {
		return m_star;
	}

	public void setM_star(int m_star) {
		this.m_star = m_star;
	}

	public String getM_review() {
		return m_review;
	}

	public void setM_review(String m_review) {
		this.m_review = m_review;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
