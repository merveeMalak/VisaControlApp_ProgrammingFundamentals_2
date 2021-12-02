package applicant;

import java.util.ArrayList;

public class Applicant implements Comparable {
    int applicantId; // holds applicant id as int
    private ApplicantInfo applicantInfo; //holds ApplicantInfo object
    private Photo photo; //holds Photo object
    private Passport passport; //holds Passport object
    private FinancialStatus financialStatus; //holds FinancalStatus object
    private ArrayList<Document> documents; //holds Documents object as ArrayList

    //default constructor
    public Applicant(){
        this(0, new ApplicantInfo(), new Photo(), new Passport(), new FinancialStatus(), new ArrayList<Document>());
    }

    //full argument constructor
    public Applicant(int applicantId, ApplicantInfo applicantInfo, Photo photo, Passport passport, FinancialStatus financialStatus, ArrayList<Document> document){
        this.applicantId = applicantId;
        this.applicantInfo = new ApplicantInfo(applicantInfo);
        this.photo = new Photo(photo);
        this.passport = new Passport(passport);
        this.financialStatus = new FinancialStatus(financialStatus);
        this.documents = new ArrayList<Document>(document);

    }

    //copy constructor
    public Applicant(Applicant applicant){
        this.applicantId = applicant.applicantId;
        this.applicantInfo = new ApplicantInfo(applicant.applicantInfo);
        this.photo = new Photo(applicant.photo);
        this.passport = new Passport(applicant.passport);
        this.financialStatus = new FinancialStatus(applicant.financialStatus);
        this.documents = new ArrayList<Document>(applicant.documents);

    }

    //starts getter methods
    public int getApplicantId() {
        return applicantId;
    }

    public ApplicantInfo getApplicantInfo() {
        return applicantInfo;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Passport getPassport() {
        return passport;
    }

    public FinancialStatus getFinancialStatus() {
        return financialStatus;
    }

    public ArrayList<Document> getDocuments() {
        return new ArrayList<Document>(documents);
    }

    //ends getter methods


    //compare two applicant objects
    @Override
    public int compareTo(Object other) {
        Applicant otherApplicant = (Applicant) other;
        return (this.applicantId - otherApplicant.applicantId);
    }


    public boolean equals(Object otherObject){
        if (otherObject == null){
            return false;
        }else if(getClass() != otherObject.getClass()){
            return false;
        }else{
            Applicant otherApplicant = (Applicant) otherObject;
            return (this.applicantId == otherApplicant.applicantId) &&
                    (this.applicantInfo.equals(otherApplicant.applicantInfo)) &&
                    (this.passport.equals(otherApplicant.passport)) &&
                    (this.photo.equals(otherApplicant.photo)) &&
                    (this.documents.equals(otherApplicant.documents)) &&
                    (this.financialStatus.equals(otherApplicant.financialStatus));

        }
    }
    public String toString(){
        String documentsToString = "";
        for (int i = 0; i<documents.size(); i++){
            documentsToString = documentsToString +  documents.get(i).toString() + "\n";
        }
        return this.applicantInfo.toString() + "\n" + this.passport.toString() + "\n" +
                this.photo.toString() + "\n" + this.financialStatus.toString() + "\n" +
                "Documents: " + documentsToString;
    }

    public Applicant clone(){
        return new Applicant(this);
    }
    
}
