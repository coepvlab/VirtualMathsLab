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
@Table(name = "parentInfo")
public class ParentInfo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "parentInfoId")
	private long parentInfoId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User userId;
	
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

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
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
