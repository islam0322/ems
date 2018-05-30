package org.fyp.ems.questionSettingModule.EmsDbModel;

import java.sql.Date;

public class TestInfo {

	private int testId;
	private int batchId;
	private double testMarks;
	private int totalQuestions;
	private int testMaxTime;
	private Date testLiveTime;
	private String testStatus;
	private String name;
	

	
	public TestInfo(int testId, int batchId, double testMarks, int totalQuestions, int testMaxTime, Date testLiveTime,
			String testStatus, String name) {
		super();
		this.testId = testId;
		this.batchId = batchId;
		this.testMarks = testMarks;
		this.totalQuestions = totalQuestions;
		this.testMaxTime = testMaxTime;
		this.testLiveTime = testLiveTime;
		this.testStatus = testStatus;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public double getTestMarks() {
		return testMarks;
	}
	public void setTestMarks(double testMarks) {
		this.testMarks = testMarks;
	}
	public Date getTestLiveTime() {
		return testLiveTime;
	}
	public void setTestLiveTime(Date testLiveTime) {
		this.testLiveTime = testLiveTime;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public TestInfo() {}
	public TestInfo(int tid,int maxt, int totalq) {
		testId = tid;
		testMaxTime=maxt;
		totalQuestions=totalq;
		
	}
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getTestMaxTime() {
		return testMaxTime;
	}
	public void setTestMaxTime(int maxTime) {
		this.testMaxTime = maxTime;
	}
	public int getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
		
	}
}
