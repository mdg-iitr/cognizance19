package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailsResponseModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("college")
    @Expose
    private String college;
    @SerializedName("centralRegistration")
    @Expose
    private Boolean centralRegistration;
    @SerializedName("workshopRegistration")
    @Expose
    private Boolean workshopRegistration;
    @SerializedName("expanseRegistration")
    @Expose
    private Boolean expanseRegistration;
    @SerializedName("registrationReferalCodeStatus")
    @Expose
    private Boolean registrationReferalCodeStatus;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("isCompleted")
    @Expose
    private Boolean isCompleted;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("usersReferred")
    @Expose
    private String usersReferred;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("centralPaymentStatus")
    @Expose
    private Boolean centralPaymentStatus;
    @SerializedName("workshopPaymentStatus")
    @Expose
    private Boolean workshopPaymentStatus;
    @SerializedName("CogniId")
    @Expose
    private String cogniId;
    @SerializedName("referalCode")
    @Expose
    private String referalCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Boolean getCentralRegistration() {
        return centralRegistration;
    }

    public void setCentralRegistration(Boolean centralRegistration) {
        this.centralRegistration = centralRegistration;
    }

    public Boolean getWorkshopRegistration() {
        return workshopRegistration;
    }

    public void setWorkshopRegistration(Boolean workshopRegistration) {
        this.workshopRegistration = workshopRegistration;
    }

    public Boolean getExpanseRegistration() {
        return expanseRegistration;
    }

    public void setExpanseRegistration(Boolean expanseRegistration) {
        this.expanseRegistration = expanseRegistration;
    }

    public Boolean getRegistrationReferalCodeStatus() {
        return registrationReferalCodeStatus;
    }

    public void setRegistrationReferalCodeStatus(Boolean registrationReferalCodeStatus) {
        this.registrationReferalCodeStatus = registrationReferalCodeStatus;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUsersReferred() {
        return usersReferred;
    }

    public void setUsersReferred(String usersReferred) {
        this.usersReferred = usersReferred;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getCentralPaymentStatus() {
        return centralPaymentStatus;
    }

    public void setCentralPaymentStatus(Boolean centralPaymentStatus) {
        this.centralPaymentStatus = centralPaymentStatus;
    }

    public Boolean getWorkshopPaymentStatus() {
        return workshopPaymentStatus;
    }

    public void setWorkshopPaymentStatus(Boolean workshopPaymentStatus) {
        this.workshopPaymentStatus = workshopPaymentStatus;
    }

    public String getCogniId() {
        return cogniId;
    }

    public void setCogniId(String cogniId) {
        this.cogniId = cogniId;
    }

    public String getReferalCode() {
        return referalCode;
    }

    public void setReferalCode(String referalCode) {
        this.referalCode = referalCode;
    }
}
