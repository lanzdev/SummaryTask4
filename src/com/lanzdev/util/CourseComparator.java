package com.lanzdev.util;

import com.lanzdev.domain.entity.Course;

import java.util.Comparator;

public class CourseComparator {

    public static Comparator<Course> getComparator(String compareRule) {

        switch (compareRule) {
            case "az":
                return new CompareByNameAZ();
            case "za":
                return new CompareByNameZA();
            case "duration":
                return new CompareByDuration();
            default:
                return null;
        }
    }

    private static class CompareByNameAZ implements Comparator<Course> {
        @Override
        public int compare(Course o1, Course o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    private static class CompareByNameZA implements Comparator<Course> {
        @Override
        public int compare(Course o1, Course o2) {
            return o2.getName().compareTo(o1.getName());
        }
    }

    private static class CompareByDuration implements Comparator<Course> {
        @Override
        public int compare(Course o1, Course o2) {
            long o1Duration = o1.getExpirationDate().getTime() - o1.getStartDate().getTime();
            long o2Duration = o2.getExpirationDate().getTime() - o2.getStartDate().getTime();
            return Long.compare(o1Duration, o2Duration);
        }
    }
}
