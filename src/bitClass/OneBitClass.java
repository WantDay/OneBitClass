package bitClass;

// 강좌 정보를 저장하는 기능 클래스
public class OneBitClass {
	private int cno;
	private String cloc;
	private String title;
	private String startdate;
	private String enddate;
	private int fee;
	private int discount;
	private int rate;
	private int numpeople;

	public OneBitClass(int cno, String cloc, String title, String startdate, String enddate,
						int fee, int discount, int rate, int numpeople) {
		super();
		this.cno = cno;
		this.cloc = cloc;
		this.title = title;
		this.startdate = startdate;
		this.enddate = enddate;
		this.fee = fee;
		this.discount = discount;
		this.rate = rate;
		this.numpeople = numpeople;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCloc() {
		return cloc;
	}

	public void setCloc(String cloc) {
		this.cloc = cloc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getNumpeople() {
		return numpeople;
	}

	public void setNumpeople(int numpeople) {
		this.numpeople = numpeople;
	}

	@Override
	public String toString() {
		return "OneBitClass [cno="	+ cno + ", cloc=" + cloc + ", title=" + title + ", startdate="
				+ startdate + ", enddate=" + enddate + ", fee=" + fee + ", discount=" + discount
				+ ", rate=" + rate + ", numpeople=" + numpeople + "]";
	}
}
