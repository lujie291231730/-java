package com.zhongji.entity.stud;

import java.util.Date;

import com.seed.entity.*;
import com.seed.entity.publics.DataDeal;
import com.seed.entity.publics.SearchParams;
import com.seed.entity.publics.SelectBean;


public class StudInfo implements BaseBean{
	private int id;
	
    private String name;
	
	private String age;
	
	private String classes;
	
	private String grade;
	
	private String classesname;
	
	private Date createdate;
	
	public String getClassesname() {
		return classesname;
	}

	public void setClassesname(String classesname) {
		this.classesname = classesname;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	private String gradename;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	private SearchParams search;

	private DataDeal deal;
	
	private SelectBean<StudInfo> item;
	
	public SelectBean<StudInfo> getItem() {
		if (item == null)
			item = new SelectBean<StudInfo>();

		return item;
	}

	public void setItem(SelectBean<StudInfo> item) {
		this.item = item;
	}
	
	public SearchParams getSearch() {
		if (search == null)
			search = new SearchParams();

		return search;
	}

	public void setSearch(SearchParams search) {
		this.search = search;
	}

	public DataDeal getDeal() {
		if (deal == null)
			deal = new DataDeal();

		return deal;
	}

	public void setDeal(DataDeal deal) {
		this.deal = deal;
	}
	
	@Override
	public String OnDebug() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public StudInfo() {
		this.OnInit();
	}

	@Override
	public void OnInit() {
		this.name = "";
		this.age = "";
		this.classes = "";
		this.grade = "";
		this.createdate = new Date();
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		return false;
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] OnProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}
