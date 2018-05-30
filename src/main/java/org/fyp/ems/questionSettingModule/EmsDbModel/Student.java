package org.fyp.ems.questionSettingModule.EmsDbModel;

public class Student {

	private String student_id;
	private String password;

	
	public Student() {
		super();
	}
	public Student(String student_id, String password) {
		super();
		this.student_id = student_id;
		this.password = password;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
