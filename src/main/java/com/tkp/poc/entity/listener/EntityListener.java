package com.tkp.poc.entity.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.tkp.poc.common.factory.ThreadContainer;
import com.tkp.poc.entity.AuditableEntity;

public class EntityListener {

	@PrePersist
	public void setPrePersistData(AuditableEntity auditableEntity) {
		String createdUser = ThreadContainer.getUserContext() != null ? ThreadContainer.getUserContext().getUserName()
				: "default_created_User";
		auditableEntity.setCreatedBy(createdUser);
		auditableEntity.setCreatedTs(new Date());
	}

	@PreUpdate
	public void setPreUpdateData(AuditableEntity auditableEntity) {
		String updatedBy = ThreadContainer.getUserContext() != null ? ThreadContainer.getUserContext().getUserName()
				: "default_updated_User";
		auditableEntity.setUpdatedBy(updatedBy);
		auditableEntity.setUpdatedTs(new Date());
	}

}
