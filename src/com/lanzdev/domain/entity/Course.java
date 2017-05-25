package com.lanzdev.domain.entity;

import com.lanzdev.domain.Identified;

import java.io.Serializable;
import java.sql.Date;

public class Course implements Identified<Integer>, Serializable {

    private static final long serialVersionUID = 1512426005384981976L;

    private Integer id;
    private String name;
    private Integer subjectId;
    private Integer teacherId;
    private Date startDate;
    private Date expirationDate;

    @Override
    public Integer getId( ) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName( ) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSubjectId( ) {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getTeacherId( ) {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getStartDate( ) {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate( ) {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString( ) {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
