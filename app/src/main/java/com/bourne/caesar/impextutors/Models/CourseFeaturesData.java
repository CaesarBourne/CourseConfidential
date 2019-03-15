package com.bourne.caesar.impextutors.Models;

public class CourseFeaturesData {
    private String programId;
    private String programTitle;
    private String programTarget;
    private String programDescription;
    private String programfeeNaira;
    private String programTimeDuration;
    private String programFacilitatorContactNumber;
    private String programFacilitatorEmail;
    private String programPreviewVideo;
    private String programImagePreview;
    private String programImageFeatured;
    private String programfeeDollar;

    public CourseFeaturesData() {
    }

    public CourseFeaturesData(String programId, String programTitle, String programTarget, String programDescription,
                              String programfeeNaira, String programTimeDuration, String programFacilitatorContactNumber, String programFacilitatorEmail,
                              String programPreviewVideo, String programImagePreview, String programImageFeatured, String programfeeDollar) {
        this.programId = programId;
        this.programTitle = programTitle;
        this.programTarget = programTarget;
        this.programDescription = programDescription;
        this.programfeeNaira = programfeeNaira;
        this.programTimeDuration = programTimeDuration;
        this.programFacilitatorContactNumber = programFacilitatorContactNumber;
        this.programFacilitatorEmail = programFacilitatorEmail;
        this.programPreviewVideo = programPreviewVideo;
        this.programImagePreview = programImagePreview;
        this.programImageFeatured = programImageFeatured;
        this.programfeeDollar = programfeeDollar;
    }

    public String getProgramId() {
        return programId;
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

    public String getProgramfeeNaira() {
        return programfeeNaira;
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

    public String getProgramfeeDollar() {
        return programfeeDollar;
    }
}
