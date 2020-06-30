/**
 * 
 */
package com.tkp.poc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tkp.poc.common.constant.EntityConstants;

/**
 * @author Tapan
 *
 */
@MappedSuperclass
public abstract class AuditableEntity {
	
	@Column(name = EntityConstants.CREATED_BY)
	private String createdBy;
	
	@Column(name = EntityConstants.CREATED_TS)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTs;
	
	@Column(name = EntityConstants.UPDATED_BY)
	private String updatedBy;
	
	@Column(name = EntityConstants.UPDATED_TS)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTs;

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdTs
	 */
	public Date getCreatedTs() {
		return createdTs;
	}

	/**
	 * @param createdTs the createdTs to set
	 */
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedTs
	 */
	public Date getUpdatedTs() {
		return updatedTs;
	}

	/**
	 * @param updatedTs the updatedTs to set
	 */
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	} 
	
	
}
