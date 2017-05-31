package com.lanzdev.domain.entity;

import com.lanzdev.domain.Identified;

import java.io.Serializable;
import java.sql.Date;

/**
 * Representation of course entity from db.
 * Contains all information about course entity.
 */
public class Course implements Identified<Integer>, Serializable {

    private static final long serialVersionUID = 1512426005384981976L;

    private Integer id;
    private String name;
    private Integer subjectId;
    private String subjectName;
    private Integer teacherId;
    private String teacherName;
    private Date startDate;
    private Date expirationDate;
    private Integer subscribedBy;

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

    public String getSubjectName( ) {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getTeacherId( ) {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName( ) {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public Integer getSubscribedBy( ) {
        return subscribedBy;
    }

    public void setSubscribedBy(Integer subscribedBy) {
        this.subscribedBy = subscribedBy;
    }

    @Override
    public String toString( ) {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                ", subscribedBy=" + subscribedBy +
                '}';
    }
}
