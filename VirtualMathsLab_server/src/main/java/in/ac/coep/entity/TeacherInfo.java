package in.ac.coep.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "teacherInfo")
public class TeacherInfo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teacherId")
	private long teacherId;
	
	private String schoolName;
	
	private String schoolAdd;
	
	private String schoolType;
	
	private String experience;
	
	private String teachingGroup;
	
	@OneToOne
	@JoinColumn(name = "mediumId")
	private Medium medium;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User userId;



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

	public Medium getMedium() {
		return medium;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeachingGroup() {
		return teachingGroup;
	}

	public void setTeachingGroup(String teachingGroup) {
		this.teachingGroup = teachingGroup;
	}
	
	
}
