package org.agriopredict.dto.response;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AgronomistResponse {
//	private long agronomistId;
//	private String agronomistName;
//	private String mobileNo;
//	private String address;
//	private LocalDate createdDate;
	private String message;
	private String responseCode;

	// Add a new field for a list of agronomist details
	private List<AgronomistDetails> agronomists;

//	public long getAgronomistId() {
//		return agronomistId;
//	}
//
//	public void setAgronomistId(long agronomistId) {
//		this.agronomistId = agronomistId;
//	}
//
//	public String getAgronomistName() {
//		return agronomistName;
//	}
//
//	public void setAgronomistName(String agronomistName) {
//		this.agronomistName = agronomistName;
//	}
//
//	public String getMobileNo() {
//		return mobileNo;
//	}
//
//	public void setMobileNo(String mobileNo) {
//		this.mobileNo = mobileNo;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public LocalDate getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(LocalDate createdDate) {
//		this.createdDate = createdDate;
//	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	// Getters and setters for the agronomists list
	public List<AgronomistDetails> getAgronomists() {
		return agronomists;
	}

	public void setAgronomists(List<AgronomistDetails> agronomists) {
		this.agronomists = agronomists;
	}

	// Other existing fields and methods...

}
