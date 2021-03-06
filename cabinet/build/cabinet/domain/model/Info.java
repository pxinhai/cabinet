package cabinet.domain.model;
// Generated 2016-1-6 15:17:50 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Info generated by hbm2java
 */
public class Info implements java.io.Serializable {

	private int articleId;
	private String title;
	private String content;
	private Date dataChangeCreateTime;
	private Date dataChangeLastTime;
	private int categoryId;
	private int SortOrder;

	public Info() {
	}

	public Info(String title, String content, Date dataChangeCreateTime, Date dataChangeLastTime, int categoryId,
			int SortOrder) {
		this.title = title;
		this.content = content;
		this.dataChangeCreateTime = dataChangeCreateTime;
		this.dataChangeLastTime = dataChangeLastTime;
		this.categoryId = categoryId;
		this.SortOrder = SortOrder;
	}

	public int getArticleId() {
		return this.articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSortOrder() {
		return this.SortOrder;
	}

	public void setSortOrder(int SortOrder) {
		this.SortOrder = SortOrder;
	}

}
