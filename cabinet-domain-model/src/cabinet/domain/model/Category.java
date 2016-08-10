package cabinet.domain.model;

import java.util.Date;

public class Category implements java.io.Serializable {
	
	private int categoryId;
	private String cname="";
	private String ename="";
	private int parentId;
	private Date dataChangeCreateTime;
	private Date dataChangeLastTime;
	private boolean isDel;
	private int sortOrder;

	public Category() {
	}

	public Category(int categoryId, String cname, String ename, int parentId,
			Date dataChangeCreateTime, Date dataChangeLastTime, boolean isDel,int sortOrder)
	{
		this.categoryId = categoryId;
		this.cname = cname;
		this.ename = ename;
		this.parentId = parentId;
		this.dataChangeCreateTime = dataChangeCreateTime;
		this.dataChangeLastTime = dataChangeLastTime;
		this.isDel = isDel;
		this.sortOrder=sortOrder;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Date getDataChangeCreateTime() {
		return this.dataChangeCreateTime;
	}

	public void setDataChangeCreateTime(Date dataChangeCreateTime) {
		this.dataChangeCreateTime = dataChangeCreateTime;
	}

	public Date getDataChangeLastTime() {
		return this.dataChangeLastTime;
	}

	public void setDataChangeLastTime(Date dataChangeLastTime) {
		this.dataChangeLastTime = dataChangeLastTime;
	}

	public boolean getIsDel() {
		return this.isDel;
	}

	public void setIsDel(boolean isDel) {
		this.isDel = isDel;
	}
	
	public int getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

}
