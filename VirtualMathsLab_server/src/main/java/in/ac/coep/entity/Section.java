///**
// * 
// */
//package in.ac.coep.entity;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.ForeignKey;
//import org.springframework.stereotype.Component;
//
///**
// * @author Prashant
// *
// */
//@SuppressWarnings("serial")
//@Component
//@Entity
//@Table(name = "sections")
//public class Section implements Serializable {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "sectionId")
//	private int sectionId;
//
//	private String name;
//
//	@ManyToOne
//	@ForeignKey(name = "S_departmentId")
//	private Department department;
//	
//	
//
//	/**
//	 * @return the department
//	 */
//	public Department getDepartment() {
//		return department;
//	}
//
//	/**
//	 * @param department the department to set
//	 */
//	public void setDepartment(Department department) {
//		this.department = department;
//	}
//
//	
//	/**
//	 * @return the sectionId
//	 */
//	public int getSectionId() {
//		return sectionId;
//	}
//
//	/**
//	 * @param sectionId the sectionId to set
//	 */
//	public void setSectionId(int sectionId) {
//		this.sectionId = sectionId;
//	}
//
//	/**
//	 * @return the name
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * @param name
//	 *            the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
//
//}