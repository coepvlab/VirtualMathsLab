package in.ac.coep.vo;

public class ParentInfoVO {

	private long parentInfoId;

	private long userId;	
	
	private String Qualification;
	
	private String Occupation;
	
	private String Designation;
	
	private int ServiceLength;
	
	private String CompanyName;

	public long getParentInfoId() {
		return parentInfoId;
	}

	public void setParentInfoId(long parentInfoId) {
		this.parentInfoId = parentInfoId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}

	public String getOccupation() {
		return Occupation;
	}

	public void setOccupation(String occupation) {
		Occupation = occupation;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public int getServiceLength() {
		return ServiceLength;
	}

	public void setServiceLength(int serviceLength) {
		ServiceLength = serviceLength;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
}
