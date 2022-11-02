package in.ac.coep.vo;

/**
 * @author Vaibhav
 *
 */
public class TeacherInfoVO {

	private long userId;
	
	private long teacherId;
	
	private String schoolName;
	
	private String schoolAdd;
	
	private String schoolType;
	
	private String experience;
	
	private String teachingGroup;
	
	private int mediumId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
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

	public String getTeachingGroup() {
		return teachingGroup;
	}

	public void setTeachingGroup(String teachingGroup) {
		this.teachingGroup = teachingGroup;
	}

	public int getMediumId() {
		return mediumId;
	}

	public void setMediumId(int mediumId) {
		this.mediumId = mediumId;
	}
}
