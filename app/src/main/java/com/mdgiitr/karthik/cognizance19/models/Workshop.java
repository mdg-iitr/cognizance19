package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Workshop {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("teamLimit")
    @Expose
    private String teamLimit;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("abstract")
    @Expose
    private Object _abstract;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("problemStatement")
    @Expose
    private String problemStatement;
    @SerializedName("prize")
    @Expose
    private String prize;
    @SerializedName("rules")
    @Expose
    private String rules;
    @SerializedName("procedure")
    @Expose
    private String procedure;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = null;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("contact")
    @Expose
    private List<Contact> contact = null;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("trending")
    @Expose
    private Boolean trending;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("organizers")
    @Expose
    private String organizers;
    @SerializedName("registrationClosed")
    @Expose
    private Boolean registrationClosed;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("sponsors")
    @Expose
    private Object sponsors;
    @SerializedName("coverImage")
    @Expose
    private String coverImage;
    @SerializedName("priority")
    @Expose
    private Object priority;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("CentralId")
    @Expose
    private Object centralId;
    @SerializedName("DepartmentId")
    @Expose
    private Object departmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeamLimit() {
        return teamLimit;
    }

    public void setTeamLimit(String teamLimit) {
        this.teamLimit = teamLimit;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Object getAbstract() {
        return _abstract;
    }

    public void setAbstract(Object _abstract) {
        this._abstract = _abstract;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProblemStatement() {
        return problemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getTrending() {
        return trending;
    }

    public void setTrending(Boolean trending) {
        this.trending = trending;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getOrganizers() {
        return organizers;
    }

    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    public Boolean getRegistrationClosed() {
        return registrationClosed;
    }

    public void setRegistrationClosed(Boolean registrationClosed) {
        this.registrationClosed = registrationClosed;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Object getSponsors() {
        return sponsors;
    }

    public void setSponsors(Object sponsors) {
        this.sponsors = sponsors;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Object getPriority() {
        return priority;
    }

    public void setPriority(Object priority) {
        this.priority = priority;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getCentralId() {
        return centralId;
    }

    public void setCentralId(Object centralId) {
        this.centralId = centralId;
    }

    public Object getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Object departmentId) {
        this.departmentId = departmentId;
    }
}
