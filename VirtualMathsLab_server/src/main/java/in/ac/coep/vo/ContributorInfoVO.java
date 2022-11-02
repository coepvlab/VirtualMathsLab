package in.ac.coep.vo;

/**
 * @author Vaibhav
 *
 */
public class ContributorInfoVO {

	private long userId;
	
	private long contributorId;
	
	private String schoolName;
	
	private String schoolAdd;
	
	private String schoolType;
	
	private String experience;
	
	private String canContributeInLatex;
	
	private int mediumId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getContributorId() {
		return contributorId;
	}

	public void setContributorId(long contributorId) {
		this.contributorId = contributorId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolAdd() {
		return schoolAdd;
	}

	public void setSchoolAdd(String schoolAdd) {
		this.schoolAdd = schoolAdd;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCanContributeInLatex() {
		return canContributeInLatex;
	}

	public void setCanContributeInLatex(String canContributeInLatex) {
		this.canContributeInLatex = canContributeInLatex;
	}

	public int getMediumId() {
		return mediumId;
	}

	public void setMediumId(int mediumId) {
		this.mediumId = mediumId;
	}
}
