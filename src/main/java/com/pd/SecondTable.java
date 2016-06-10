package com.pd;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SecondTable")
public class SecondTable {

	private long id;
	private String content;
	private Timestamp ts;
	private String recordId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOMEID", precision = 5, scale = 0)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "CONTENT", nullable = false, length = 50000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "UPDATETS")
	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	@Column(name = "RecordID", nullable = false, length = 100)
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	
	
}
