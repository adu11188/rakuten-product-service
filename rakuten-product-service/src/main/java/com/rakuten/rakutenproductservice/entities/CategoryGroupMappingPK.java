package com.rakuten.rakutenproductservice.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the category_group_mapping database table.
 * 
 */
@Embeddable
public class CategoryGroupMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="category_group_id", insertable=false, updatable=false)
	private Long categoryGroupId;

	@Column(name="category_id", insertable=false, updatable=false)
	private Long categoryId;

	@Column(name="sequence_no")
	private Integer sequenceNo;

	public CategoryGroupMappingPK() {
	}
	public Long getCategoryGroupId() {
		return this.categoryGroupId;
	}
	public void setCategoryGroupId(Long categoryGroupId) {
		this.categoryGroupId = categoryGroupId;
	}
	public Long getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSequenceNo() {
		return this.sequenceNo;
	}
	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CategoryGroupMappingPK)) {
			return false;
		}
		CategoryGroupMappingPK castOther = (CategoryGroupMappingPK)other;
		return 
			this.categoryGroupId.equals(castOther.categoryGroupId)
			&& this.categoryId.equals(castOther.categoryId)
			&& this.sequenceNo.equals(castOther.sequenceNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.categoryGroupId.hashCode();
		hash = hash * prime + this.categoryId.hashCode();
		hash = hash * prime + this.sequenceNo.hashCode();
		
		return hash;
	}
}