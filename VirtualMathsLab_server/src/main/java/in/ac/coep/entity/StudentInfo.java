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
@Table(name = "studentInfo")
public class StudentInfo  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentId")
	private long studentId;
	
	private String schoolName;
	
	private String schoolAdd;
	
	private String schoolType;
	
	private int standard;
	
	private String parEmailId;
	
	@OneToOne
	@JoinColumn(name = "mediumId")
	private Medium medium;
	
	private String F_name;
	
	private String M_name;
	
	private int F_age;
	
	private int M_age;
	
	private String F_qualification;
	
	private String M_qualification;
	
	private String F_occupation;
	
	private String M_occupation;
	
	private String F_company;
	
	private String M_company;
	
	private String F_designation;
	
	private String M_designation;
	
	private String F_serviceLength;
	
	private String M_serviceLength;
	
	private int F_city;
	
	private int M_city;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User userId;

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
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

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public String getParEmailId() {
		return parEmailId;
	}

	public void setParEmailId(String parEmailId) {
		this.parEmailId = parEmailId;
	}

	public Medium getMedium() {
		return medium;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	public String getF_name() {
		return F_name;
	}

	public void setF_name(String f_name) {
		F_name = f_name;
	}

	public String getM_name() {
		return M_name;
	}

	public void setM_name(String m_name) {
		M_name = m_name;
	}

	public int getF_age() {
		return F_age;
	}

	public void setF_age(int f_age) {
		F_age = f_age;
	}

	public int getM_age() {
		return M_age;
	}

	public void setM_age(int m_age) {
		M_age = m_age;
	}

	public String getF_qualification() {
		return F_qualification;
	}

	public void setF_qualification(String f_qualification) {
		F_qualification = f_qualification;
	}

	public String getM_qualification() {
		return M_qualification;
	}

	public void setM_qualification(String m_qualification) {
		M_qualification = m_qualification;
	}

	public String getF_occupation() {
		return F_occupation;
	}

	public void setF_occupation(String f_occupation) {
		F_occupation = f_occupation;
	}

	public String getM_occupation() {
		return M_occupation;
	}

	public void setM_occupation(String m_occupation) {
		M_occupation = m_occupation;
	}

	public String getF_company() {
		return F_company;
	}

	public void setF_company(String f_company) {
		F_company = f_company;
	}

	public String getM_company() {
		return M_company;
	}

	public void setM_company(String m_company) {
		M_company = m_company;
	}

	public String getF_designation() {
		return F_designation;
	}

	public void setF_designation(String f_designation) {
		F_designation = f_designation;
	}

	public String getM_designation() {
		return M_designation;
	}

	public void setM_designation(String m_designation) {
		M_designation = m_designation;
	}

	public String getF_serviceLength() {
		return F_serviceLength;
	}

	public void setF_serviceLength(String f_serviceLength) {
		F_serviceLength = f_serviceLength;
	}

	public String getM_serviceLength() {
		return M_serviceLength;
	}

	public void setM_serviceLength(String m_serviceLength) {
		M_serviceLength = m_serviceLength;
	}

	public int getF_city() {
		return F_city;
	}

	public void setF_city(int f_city) {
		F_city = f_city;
	}

	public int getM_city() {
		return M_city;
	}

	public void setM_city(int m_city) {
		M_city = m_city;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
}
