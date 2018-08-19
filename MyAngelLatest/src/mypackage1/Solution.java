/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage1;

/**
 *
 * @author yogesh.gandhi
 */
public class Solution {
    private int solutionno;
    private String description;
    private String problem;
    private String solution;
    private String logs;
    private String submittedby;
    private String attachmentUploadLocation;

    public String getAttachmentUploadLocation() {
        return attachmentUploadLocation;
    }

    public void setAttachmentUploadLocation(String attachmentUploadLocation) {
        this.attachmentUploadLocation = attachmentUploadLocation;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getSolutionno() {
        return solutionno;
    }

    public void setSolutionno(int solutionno) {
        this.solutionno = solutionno;
    }

    public String getSubmittedby() {
        return submittedby;
    }

    public void setSubmittedby(String submittedby) {
        this.submittedby = submittedby;
    }
    private String filename;
    
            
}
