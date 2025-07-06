package com.Inventory.Management.DTO;

public class OtpVerifyRequest {

    private String emailId;
    private String otp;

    // Constructors (optional)
    public OtpVerifyRequest() {}

    public OtpVerifyRequest(String emailId, String otp) {
        this.emailId = emailId;
        this.otp = otp;
    }

    // Getters and Setters
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
