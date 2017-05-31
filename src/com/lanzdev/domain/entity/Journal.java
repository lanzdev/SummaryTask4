package com.lanzdev.domain.entity;

import com.lanzdev.domain.Identified;

import java.io.Serializable;

/**
 * Representation of journal entity from db.
 * Contains all information about journal entity.
 */
public class Journal implements Identified<Integer>, Serializable {

    private static final long serialVersionUID = 2779728401129970549L;

    private Integer id;
    private Integer courseId;
    private Integer studentId;
    private Double mark;

    @Override
    public Integer getId( ) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId( ) {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId( ) {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Double getMark( ) {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    @Override
    public String toString( ) {
        return "Journal{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                ", mark=" + mark +
                '}';
    }
}
