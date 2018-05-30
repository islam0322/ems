package org.fyp.ems.questionSettingModule.EmsDbModel;

public class StudentBatches {

	  
	private int batchId;
	private String studentId;
	  
	 
	public StudentBatches() {
		super();
	}
	public StudentBatches(int batchId, String studentId) {
		super();
		this.batchId = batchId;
		this.studentId = studentId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batch_id) {
		this.batchId = batch_id;
	}
}
