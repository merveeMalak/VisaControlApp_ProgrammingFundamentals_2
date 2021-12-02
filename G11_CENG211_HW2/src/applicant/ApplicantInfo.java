package applicant;

public class ApplicantInfo  {
	
	private String informationType; //holds information type as String
	private int applicantId;  //holds applicant id as int
	private String applicantName; //holds applicant name as String

	//default constructor
	public ApplicantInfo() {
		this("A", 0, "");
	}

	//full argument constructor
	public ApplicantInfo(String informationType, int applicantId, String applicantName){
		this.informationType = informationType;
		this.applicantId = applicantId;
		this.applicantName = applicantName;
	}

	//copy constructor
	public ApplicantInfo(ApplicantInfo applicantInfo){
		this(applicantInfo.informationType, applicantInfo.applicantId,applicantInfo.applicantName);
	}

	//takes all arguments as a string array
	public ApplicantInfo(String[] applicantInfo){
		this(applicantInfo[0],Integer.parseInt(applicantInfo[1]),applicantInfo[2]);
	}

	//starts getter methods
	public String getInformationType() {
		return informationType;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public String getApplicantName() {
		return applicantName;
	}
	//ends getter methods

	public boolean equals(Object otherObject){
		if (otherObject == null){
			return false;
		}else if(getClass() != otherObject.getClass()){
			return false;
		}else{
			ApplicantInfo otherApplicantInfo = (ApplicantInfo) otherObject;
			return (this.informationType.equals(otherApplicantInfo.informationType)) &&
					(this.applicantId == otherApplicantInfo.applicantId) &&
					(this.applicantName.equals(otherApplicantInfo.applicantName));

		}
	}
	public String toString(){
		return "Applicant Id: " + this.applicantId + "  Applicant Name: " + this.applicantName ;
	}

	public ApplicantInfo clone(){
		return new ApplicantInfo(this);
	}


}
