package com.pts.model;

/**
 * Created by taronpetrosyan on 8/15/16.
 */
public class Contact {
    private Integer id;
    private Integer projectId;
    private String contact;
    private String email;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact(Integer id, Integer projectId, String contact, String email) {
        this.id = id;
        this.projectId = projectId;
        this.contact = contact;
        this.email = email;
    }

    public Contact(Integer id, Integer projectId, String contact, String email, String phone) {
        this.id = id;
        this.projectId = projectId;
        this.contact = contact;
        this.email = email;
        this.phone = phone;
    }
}
