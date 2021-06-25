package bitclass;

//import java.util.Date;

public class BitClass {
	// 강좌 데이터를 넣어서 개설 및 관리

	private int cno; // 강좌 번호
	private int mno; // 강사 번호
	private String title; // 강좌명
	private String cloc; // 강좌 지역
	private String startDate; // 강좌 시작일
	private String endDate; // 강좌 종료일
	private int fee; // 수강료
	private int discount; // 할인율
	private float rate; // 평점
	private int numPeople; // 최대 수강 인원
	private int enroll; // 현재 수강인원

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCloc() {
		return cloc;
	}

	public void setCloc(String cloc) {
		this.cloc = cloc;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int number) {
		this.numPeople = number;
	}

	public int getEnroll() {
		return enroll;
	}

	public void setEnroll(int enroll) {
		this.enroll = enroll;
	}

	public BitClass(int cno, int mno, String title, String cloc, String startDate, String endDate, int fee,
			int discount, float rate, int numPeople, int enroll) {
		super();
		this.cno = cno;
		this.mno = mno;
		this.title = title;
		this.cloc = cloc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fee = fee;
		this.discount = discount;
		this.rate = rate;
		this.numPeople = numPeople;
		this.enroll = enroll;
	}

	public BitClass(int mno, String title, String cloc, String startDate, String endDate, int fee, int numPeople) {
		this.mno = mno;
		this.title = title;
		this.cloc = cloc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fee = fee;
		this.numPeople = numPeople;
	}

	public BitClass(int cno, String title, int discount) {
		this.cno = cno;
		this.title = title;
		this.discount = discount;
	}
	
	public int discountFee() {
		return (int)(Math.round(getFee() * (1 - getDiscount() * 0.01)));
	}

	@Override
	public String toString() {
		return getTitle() + "\t " + getCloc() + "\t " + discountFee() + "\t " + getStartDate()
				+ "\t " + getEndDate() + "\t" + getEnroll() + "/" + getNumPeople();
	}
}
