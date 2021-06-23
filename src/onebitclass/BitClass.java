package onebitclass;

import java.util.Date;

public class BitClass { 	
				// 강좌 데이터를 넣어서 개설 및 관리

	 private int cno; // 강좌 번호
	 private int mno; // 강사 번호
	 private String title; // 강좌명
	 private String cloc; // 강좌 지역
	 private String startdate; // 강좌 시작일
	 private String enddate; // 강좌 종료일
	 private int fee; // 수강료
	 private int discount; // 할인율
	 private float rate; // 평점
	 private int numpeople; // 최대 수강 인원
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
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getNumpeople() {
		return numpeople;
	}
	public void setNumpeople(int number) {
		this.numpeople = number;
	}

	public BitClass(int cno, int mno, String title, String cloc, String startdate, String enddate, int fee,
			int discount, float rate, int numpeople, int enroll) {
		super();
		this.cno = cno;
		this.mno = mno;
		this.title = title;
		this.cloc = cloc;
		this.startdate = startdate;
		this.enddate = enddate;
		this.fee = fee;
		this.discount = discount;
		this.rate = rate;
		this.numpeople = numpeople;
		this.enroll = enroll;
	}
	public int getEnroll() {
		return enroll;
	}
	public void setEnroll(int enroll) {
		this.enroll = enroll;
	}
	
	public BitClass(int mno, String title, String cloc, String startdate, 
						String enddate, int fee, int numpeople) {
		this.mno = mno;
		this.title = title;
		this.cloc = cloc;
		this.startdate = startdate;
		this.enddate = enddate;
		this.fee = fee;
		this.numpeople = numpeople;
	}
	@Override
	public String toString() {
		return "BitClass [cno=" + cno + ", mno=" + mno + ", title=" + title + ", cloc=" + cloc + ", startdate="
				+ startdate + ", enddate=" + enddate + ", fee=" + fee + ", discount=" + discount + ", rate=" + rate
				+ ", numpeople=" + numpeople + ", enroll=" + enroll + "]";
	}
	
	


}
