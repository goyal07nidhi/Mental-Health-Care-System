/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Hospital;

import java.util.Date;

/**
 *
 * @author Nidhi Goyal
 */
public class TestReport {
    private final String testName;
    private final int score;
    private final String recommendation;
    private final Date testDate;
    
    public TestReport(String testName, int score, String reco) {
        this.testName = testName;
        this.score = score;
        this.recommendation = reco;
        testDate = new Date();
    }

    public String getTestName() {
        return testName;
    }

    public int getScore() {
        return score;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public Date getTestDate() {
        return testDate;
    }
}
