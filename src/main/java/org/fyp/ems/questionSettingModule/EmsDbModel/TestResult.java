package org.fyp.ems.questionSettingModule.EmsDbModel;

public class TestResult {
	
	private int testResultId;
	private   int testId;
	private String studentId;
	private double totalMarks;
	private double marksObtained;
	private int unattempted;
	private int corrected;
	  
   public TestResult() {
		super();
	}
   public TestResult(int testResultId, int testId, String studentId, double totalMarks, double marksObtained,
			int unattempted, int corrected) {
		super();
		this.testResultId = testResultId;
		this.testId = testId;
		this.studentId = studentId;
		this.totalMarks = totalMarks;
		this.marksObtained = marksObtained;
		this.unattempted = unattempted;
		this.corrected = corrected;
	}
   public int getUnattempted() {
	return unattempted;
}
public void setUnattempted(int unattempted) {
	this.unattempted = unattempted;
}
public int getCorrected() {
	return corrected;
}
public void setCorrected(int corrected) {
	this.corrected = corrected;
}
public int getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public double getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}
	public double getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(double marksObtained) {
		this.marksObtained = marksObtained;
	}

}
