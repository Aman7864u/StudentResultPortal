package com.studentresultportal.result.util;

import org.springframework.stereotype.Component;

@Component
public class GradeCalculator {

    /**
     * Calculate letter grade based on percentage marks.
     */
    public String calculateGrade(double marksObtained, double totalMarks) {
        double percentage = (marksObtained / totalMarks) * 100;

        if (percentage >= 90) return "O";       // Outstanding
        if (percentage >= 80) return "A+";
        if (percentage >= 70) return "A";
        if (percentage >= 60) return "B+";
        if (percentage >= 50) return "B";
        if (percentage >= 40) return "C";
        return "F";                              // Fail
    }

    /**
     * Calculate grade point on 10-point scale.
     */
    public double calculateGradePoint(String grade) {
        return switch (grade) {
            case "O"  -> 10.0;
            case "A+" -> 9.0;
            case "A"  -> 8.0;
            case "B+" -> 7.0;
            case "B"  -> 6.0;
            case "C"  -> 5.0;
            default   -> 0.0; // F
        };
    }

    /**
     * Calculate percentage.
     */
    public double calculatePercentage(double marksObtained, double totalMarks) {
        if (totalMarks == 0) return 0.0;
        return Math.round((marksObtained / totalMarks) * 100 * 100.0) / 100.0;
    }

    /**
     * Determine pass/fail status.
     */
    public String determineStatus(double marksObtained, double totalMarks) {
        double percentage = (marksObtained / totalMarks) * 100;
        return percentage >= 40 ? "PASS" : "FAIL";
    }

    /**
     * Calculate CGPA from a list of grade points and credits.
     */
    public double calculateCGPA(double[] gradePoints, int[] credits) {
        if (gradePoints.length == 0) return 0.0;

        double totalWeightedPoints = 0.0;
        int totalCredits = 0;

        for (int i = 0; i < gradePoints.length; i++) {
            totalWeightedPoints += gradePoints[i] * credits[i];
            totalCredits += credits[i];
        }

        if (totalCredits == 0) return 0.0;
        return Math.round((totalWeightedPoints / totalCredits) * 100.0) / 100.0;
    }
}
