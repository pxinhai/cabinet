package cabinet.domain.model;

import java.math.BigDecimal;

// Generated 2015-12-6 16:26:02 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import cabinet.domain.model.ValueObject.*;

public class CategoryProperty implements java.io.Serializable {

	private Integer cagetroyPropertyId;
	private int categoryId;
	private int tagId;
	private byte valueType;
	private String unit;
	private Date dataChangeCreateTime;
	private Date dataChangeLastTime;
	private boolean isDel;
	private int sortOrder;
	private String PropertyName;
	private Goodspropertyvalue propertyValue;
	private Tag tag;

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Goodspropertyvalue getPropertyValue() {
		return propertyValue;
	}
	
	public void setPropertyValue(Goodspropertyvalue goodspropertyvalue) {
		this.propertyValue=goodspropertyvalue;
	}

	public void setPropertyValue(String value) {
		this.propertyValue = new Goodspropertyvalue();
		switch (this.getValueTypeEnum()) {
		case SingleText:
		case MulitsTag:
		this.propertyValue.setShortStrValue(value);
			break;
		case MultisText:
			this.propertyValue.setLongStrValue(value);
			break;
		case Number:
		case SingleTag:
			this.propertyValue.setDecimalValue(new BigDecimal(value));
			break;
		}
	}

	public CategoryProperty() {
	}

	public Integer getCagetroyPropertyId() {
		return this.cagetroyPropertyId;
	}

	public void setCagetroyPropertyId(Integer cagetroyPropertyId) {
		this.cagetroyPropertyId = cagetroyPropertyId;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getTagId() {
		return this.tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public byte getValueType() {
		return this.valueType;
	}

	public void setValueType(byte valueType) {
		this.valueType = valueType;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public InputValueType getValueTypeEnum() {
		return InputValueType.valueOf(this.valueType);
	}

	public String getPropertyName() {
		return this.PropertyName;
	}

	public void setPropertyName(String propertyName) {
		this.PropertyName = propertyName;
	}
}
