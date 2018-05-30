package org.fyp.ems.questionSettingModule.EmsDbModel;

public class TestQuestions {

	public int test_id;
	public int question_id;
	public int tq_id;
	
	
	public TestQuestions() {
		super();
	}
	
	public TestQuestions(int test_id, int question_id, int tq_id) {
		super();
		this.test_id = test_id;
		this.question_id = question_id;
		this.tq_id = tq_id;
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getTq_id() {
		return tq_id;
	}
	public void setTq_id(int tq_id) {
		this.tq_id = tq_id;
	}
	
	
	
}

