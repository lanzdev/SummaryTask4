package com.lanzdev.domain.entity;

import com.lanzdev.domain.Identified;

import java.io.Serializable;

/**
 * Representation of course-students entity from db.
 * Contains all information about course-students entity.
 */
public class CourseStudents implements Identified<Integer>, Serializable {

    private static final long serialVersionUID = 7448332893760650228L;

    private Integer id;
    private Integer courseId;
    private Integer studentId;

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

    @Override
    public String toString( ) {
        return "CourseStudents{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }
}
