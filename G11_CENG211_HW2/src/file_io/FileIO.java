package file_io;

import applicant.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements Serializable {
    private final String FILE_NAME = "HW2_ApplicantsInfo.csv";  //holds file name as a constant String
    private ArrayList<Photo> photoArrayList;   //holds Photo objects as a ArrayList
    private ArrayList<Passport> passportArrayList;  //holds Passport objects as a ArrayList
    private ArrayList<Document> documentArrayList;  //holds Document objects as a ArrayList
    private ArrayList<FinancialStatus> financialStatusArrayList;  //holds FinancialStatus objects as a ArrayList
    private ArrayList<ApplicantInfo> applicantInfoArrayList;  //holds ApplicantInfo objects as a ArrayList
    private ArrayList<Integer> applicantIdArrayList;  //holds applicant ids as a ArrayList
    private ArrayList<Applicant> applicantArrayList;  //holds Applicant objects as a ArrayList
    private enum ApplicantType {A, P, S, F, D};   //holds information types as a enum

    //default constructor
    public FileIO() {
        this.photoArrayList = new ArrayList<Photo>();
        this.passportArrayList = new ArrayList<Passport>();
        this.documentArrayList = new ArrayList<Document>();
        this.financialStatusArrayList = new ArrayList<FinancialStatus>();
        this.applicantInfoArrayList = new ArrayList<ApplicantInfo>();
        this.applicantIdArrayList = new ArrayList<Integer>();
        this.applicantArrayList = new ArrayList<Applicant>();

    }

    //reads file
	private void readFile() {
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            while (scanner.hasNextLine()) {  //reads the file line by line
                String line = scanner.nextLine();
               decideApplicantType(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    //According to the information type, it decides which object will be created and that object is added to the ArrayList.
    private void decideApplicantType(String line){
        String[] info = line.split(",");
        ApplicantType applicantType = ApplicantType.valueOf(info[0]);
        switch (applicantType){
            case A:  //ApplicantInfo object
                applicantInfoArrayList.add(new ApplicantInfo(info));
                break;
            case P:  //Photo object
                photoArrayList.add(new Photo(info));
                break;
            case S: //Passport object
                passportArrayList.add(new Passport(info));
                break;
            case D:  //Document object
                documentArrayList.add(new Document(info));
                break;
            case F:  //FinancialStatus object
                financialStatusArrayList.add(new FinancialStatus(info));
                break;
            default:
                break;

        }

    }

    //creates applicant id ArrayList
    private void createApplicantIdsList(){
        ArrayList<ApplicantInfo> applicantInfos = new ArrayList<ApplicantInfo>(applicantInfoArrayList);
        ArrayList<Photo> photos = new ArrayList<Photo>(photoArrayList);
        ArrayList<Passport> passports = new ArrayList<Passport>(passportArrayList);
	    ArrayList<Document> documents = new ArrayList<Document>(documentArrayList);
	    ArrayList<FinancialStatus> financialStatuses = new ArrayList<FinancialStatus>(financialStatusArrayList);
        for (int i = 0; i < applicantInfos.size(); i++){
            int applicantId = applicantInfos.get(i).getApplicantId();
            ApplicantInfo applicantInfo = applicantInfos.get(i);
            Photo photo = new Photo();
            Passport passport = new Passport();
            FinancialStatus financialStatus = new FinancialStatus();
            ArrayList<Document> docs = new ArrayList<Document>();
            applicantIdArrayList.add(applicantId);
            for (int j = 0; j< photos.size(); j++){
                if (applicantId == photos.get(j).getApplicantId()){
                    photo = photos.get(j);
                    photos.remove(j);
                    break;
                }
            }
            for (int j = 0; j < passports.size(); j++){
                if (applicantId == passports.get(j).getApplicantId()){
                    passport = passports.get(j);
                    passports.remove(j);
                    break;
                }
            }
            for (int j = 0; j < financialStatuses.size(); j++){
                if (applicantId == financialStatuses.get(j).getApplicantId()){
                    financialStatus = financialStatuses.get(j);
                    financialStatuses.remove(j);
                    break;
                }
            }
            ArrayList<Integer> sameApplicantIdIndexes = new ArrayList<Integer>();
            for (int j = 0; j < documents.size(); j++){
                if (applicantId == documents.get(j).getApplicantId()){
                    docs.add(documents.get(j));
                    sameApplicantIdIndexes.add(j);
                }
            }
            if (sameApplicantIdIndexes.size() != 0){
                documents.removeAll(sameApplicantIdIndexes);
                sameApplicantIdIndexes.clear();
            }
            Applicant applicant = new Applicant(applicantId,applicantInfo, photo, passport, financialStatus, docs);
            applicantArrayList.add(applicant);
        }

	}

	//sort ApplicantId ArrayList and Applicant ArrayList
	private void sortApplicantIdList(){
	    applicantIdArrayList.sort((applicant1, applicant2) -> applicant1.compareTo(applicant2));
	    applicantArrayList.sort((applicant1, applicant2) -> applicant1.compareTo(applicant2));
    }


    private void readAndCreateOperations(){
	    readFile();
	    createApplicantIdsList();
	    sortApplicantIdList();
    }

    //returns applicantId ArrayList
    public ArrayList<Integer> getApplicantIdArrayList(){
	    readAndCreateOperations();
	    ArrayList<Integer> tempArrList = new ArrayList<Integer>(applicantIdArrayList);

	    return  tempArrList;
    }

    //returns Applicant ArrayList
    public ArrayList<Applicant> getApplicantArrayList(){
	    readAndCreateOperations();
	    ArrayList<Applicant> tempArrList = new ArrayList<Applicant>(applicantArrayList);
	    return tempArrList;
    }


}
