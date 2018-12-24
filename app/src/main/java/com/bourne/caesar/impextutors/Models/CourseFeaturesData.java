package com.bourne.caesar.impextutors.Models;

public class CourseFeaturesData {
    private String ProgramId;
    private String programTitle;
    private String programTarget;
    private String programDescription;
    private String programfee;
    private String programTimeDuration;
    private String programFacilitatorContactNumber;
    private String programFacilitatorEmail;
    private String programPreviewVideo;
    private String programImagePreview;
    private String programImageFeatured;
    private String programnumberOfChapter;

    public CourseFeaturesData() {
    }

    public CourseFeaturesData(String ProgramId, String programTitle, String programTarget, String programDescription,
                              String programfee, String programTimeDuration, String programFacilitatorContactNumber, String programFacilitatorEmail,
                              String programPreviewVideo, String programImagePreview, String programImageFeatured, String programnumberOfChapter) {
        this.ProgramId = ProgramId;
        this.programTitle = programTitle;
        this.programTarget = programTarget;
        this.programDescription = programDescription;
        this.programfee = programfee;
        this.programTimeDuration = programTimeDuration;
        this.programFacilitatorContactNumber = programFacilitatorContactNumber;
        this.programFacilitatorEmail = programFacilitatorEmail;
        this.programPreviewVideo = programPreviewVideo;
        this.programImagePreview = programImagePreview;
        this.programImageFeatured = programImageFeatured;
        this.programnumberOfChapter = programnumberOfChapter;
    }

    public String getProgramId() {
        return ProgramId;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public String getProgramTarget() {
        return programTarget;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public String getProgramfee() {
        return programfee;
    }

    public String getProgramTimeDuration() {
        return programTimeDuration;
    }

    public String getProgramFacilitatorContactNumber() {
        return programFacilitatorContactNumber;
    }

    public String getProgramFacilitatorEmail() {
        return programFacilitatorEmail;
    }

    public String getProgramPreviewVideo() {
        return programPreviewVideo;
    }

    public String getProgramImagePreview() {
        return programImagePreview;
    }

    public String getProgramImageFeatured() {
        return programImageFeatured;
    }

    public String getProgramnumberOfChapter() {
        return programnumberOfChapter;
    }
}
