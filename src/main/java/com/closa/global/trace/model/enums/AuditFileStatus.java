package com.closa.global.trace.model.enums;

public enum AuditFileStatus {

    /**
     * These represent the status of the audit trace
     *
     * LOCAL is that it's ONLY on the local service
     * BOTH is that it's both in Local and on the AUDIT service (duplicate)
     * AUDIT is for when it's only on the AUDIT service and no longer on the local instance
     * ARCHIVE is when is stored on the long term support. It will be required a restore operation
     */
    LOCAL, BOTH, AUDIT, ARCHIVE
}
