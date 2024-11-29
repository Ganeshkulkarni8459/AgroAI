package org.agriopredict.dto.response;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class AgronomistDetails {
    private long agronomistId;
    private String agronomistName;
    private String mobileNo;
    private String address;
    private LocalDate createdDate;

    // Getters and setters for each field

    public long getAgronomistId() {
        return agronomistId;
    }

    public void setAgronomistId(long agronomistId) {
        this.agronomistId = agronomistId;
    }

    public String getAgronomistName() {
        return agronomistName;
    }

    public void setAgronomistName(String agronomistName) {
        this.agronomistName = agronomistName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}