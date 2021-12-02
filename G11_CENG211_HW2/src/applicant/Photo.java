package applicant;

public class Photo{
	
	private String informationType;  //holds information type as a String
	private int applicantId;  //holds applicant id as a int
	private String resolution;  //holds resolution as a String
	private String position;    //holds position as a String

	//default constructor
	public Photo() {
		this("P", 0, "", "");
	}

	//full argument constructor
	public Photo(String informationType, int applicantId, String resolution, String position){
		this.informationType = informationType;
		this.applicantId = applicantId;
		this.resolution = resolution;
		this.position = position;
	}

	//copy constructor
	public Photo(Photo photo){
		this(photo.informationType, photo.applicantId, photo.resolution, photo.position);
	}

	//takes all arguments as a string array
	public Photo(String[] photo){
		this(photo[0], Integer.parseInt(photo[1]), photo[2], photo[3]);

	}

	//starts getter methods
	public String getInformationType() {
		return informationType;
	}

	public int getApplicantId(){
		return  this.applicantId;
	}

	public String getResolution() {
		return resolution;
	}

	public String getPosition() {
		return position;
	}
	//ends getter methods
	

	public boolean equals(Object otherObject){
		if (otherObject == null){
			return false;
		}else if(getClass() != otherObject.getClass()){
			return false;
		}else{
			Photo otherPhoto = (Photo) otherObject;
			return (this.informationType.equals(otherPhoto.informationType)) &&
					(this.applicantId == otherPhoto.applicantId) &&
					(this.resolution.equals(otherPhoto.resolution)) &&
					(this.position.equals(otherPhoto.position));

		}
	}
	public String toString(){
		return "Applicant Id: " + this.applicantId + " Resolution: " + this.resolution + "  Position: " + this.position;
	}

	public Photo clone(){
		return new Photo(this);
	}

}
