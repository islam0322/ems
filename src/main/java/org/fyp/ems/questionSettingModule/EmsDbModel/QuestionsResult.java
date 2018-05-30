package org.fyp.ems.questionSettingModule.EmsDbModel;

public class QuestionsResult {

	private int qrId;
	private int testId;
	private int studentId;
	private int qId;
	private String selectedOption;
	private String correctOption;
	private double questionMarks;
	private double obtainMarks;
	
	
	public QuestionsResult() {
		super();
	}
	public QuestionsResult(int qrId, int testId, int studentId, int qId, String selectedOption, String correctOption,
			double questionMarks, double obtainMarks) {
		super();
		this.qrId = qrId;
		this.testId = testId;
		this.studentId = studentId;
		this.qId = qId;
		this.selectedOption = selectedOption;
		this.correctOption = correctOption;
		this.questionMarks = questionMarks;
		this.obtainMarks = obtainMarks;
	}
	public int getQrId() {
		return qrId;
	}
	public void setQrId(int qrId) {
		this.qrId = qrId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
	public double getQuestionMarks() {
		return questionMarks;
	}
	public void setQuestionMarks(double questionMarks) {
		this.questionMarks = questionMarks;
	}
	public double getObtainMarks() {
		return obtainMarks;
	}
	public void setObtainMarks(double obtainMarks) {
		this.obtainMarks = obtainMarks;
	}
}
