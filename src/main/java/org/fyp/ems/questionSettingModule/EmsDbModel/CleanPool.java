package org.fyp.ems.questionSettingModule.EmsDbModel;

import java.util.Date;

public class CleanPool {

	private int id;
	private int question_id;
	private String approver_id;
	private Date approval_date;
	private String approver_comment;
	public CleanPool() {
		
	}
	public CleanPool(int question_id, String approver_id, Date approval_date, String approver_comment) {
		
		this.question_id = question_id;
		this.approver_id = approver_id;
		this.approval_date = approval_date;
		this.approver_comment = approver_comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}
	public Date getApproval_date() {
		return approval_date;
	}
	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}
	public String getApprover_comment() {
		return approver_comment;
	}
	public void setApprover_comment(String approver_comment) {
		this.approver_comment = approver_comment;
	}
	
	
}
