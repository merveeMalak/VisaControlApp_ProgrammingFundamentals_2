package applicant;

public class Document {
	
	private String informationType; //holds information type as String
	private int applicantId;  //holds applicant id as int
	private String documentType;  //holds document type as String
	private int durationInMonths;  //holds duration in months as int

	//default constructor
	public Document() {
		this("D", 0, "");
	}

	//full argument constructor
	public Document(String informationType, int applicantId, String documentType, int durationInMonths){
		this.informationType = informationType;
		this.applicantId = applicantId;
		this.documentType = documentType;
		this.durationInMonths = durationInMonths;
	}

	public Document(String informationType, int applicantId, String documentType){
		this(informationType, applicantId, documentType, 0);
	}

	//copy constructor
	public Document(Document document){
		this(document.informationType, document.applicantId, document.documentType, document.durationInMonths);
	}

	//takes all arguments as a string array
	public Document(String[] info){
		if (info.length == 3){
			this.informationType = info[0];
			this.applicantId = Integer.parseInt(info[1]);
			this.documentType = info[2];
			this.durationInMonths = 0;
		}else{
			this.informationType = info[0];
			this.applicantId = Integer.parseInt(info[1]);
			this.documentType = info[2];
			this.durationInMonths = Integer.parseInt(info[3]);
		}
	}

	//starts getter methods
	public String getInformationType() {
		return informationType;
	}

	public int getApplicantId(){
		return  this.applicantId;
	}
	
	public String getDocumentType() {
		return documentType;
	}

	public int getDurationInMonths() {
		return durationInMonths;
	}
	//ends getter methods

	public boolean equals(Object otherObject){
		if (otherObject == null){
			return false;
		}else if(getClass() != otherObject.getClass()){
			return false;
		}else{
			Document otherDocument = (Document) otherObject;
			return (this.informationType.equals(otherDocument.informationType)) &&
					(this.applicantId == otherDocument.applicantId) &&
					(this.documentType.equals(otherDocument.documentType)) &&
					(this.durationInMonths == otherDocument.durationInMonths);

		}
	}
	public String toString(){
		return "Applicant Id: " + this.applicantId + " Document Type: " + this.documentType + " Duration In Months: " + this.durationInMonths;
	}

	public Document clone(){
		return new Document(this);
	}

}
