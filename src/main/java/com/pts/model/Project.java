package com.pts.model;

/**
 * Created by taronpetrosyan on 8/15/16.
 */
public class Project {
    private Integer id;
    private String title;
    private ProjectStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Project(Integer id, String title, ProjectStatus status) {

        this.id = id;
        this.status = status;
        this.title = title;
    }
}
