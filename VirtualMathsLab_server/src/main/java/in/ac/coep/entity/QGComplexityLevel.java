/**
 * 
 */
package in.ac.coep.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Prashant
 *
 */
@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "QGComplexityLevel")
public class QGComplexityLevel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "qgComplexityLevelId")
	private int qgComplexityLevelId;
	
	@Column
	private String name;
	
	@Column
	private String discription;

	/**
	 * @return the qgComplexityLevelId
	 */
	public int getQgComplexityLevelId() {
		return qgComplexityLevelId;
	}

	/**
	 * @param qgComplexityLevelId the qgComplexityLevelId to set
	 */
	public void setQgComplexityLevelId(int qgComplexityLevelId) {
		this.qgComplexityLevelId = qgComplexityLevelId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the discription
	 */
	public String getDiscription() {
		return discription;
	}

	/**
	 * @param discription the discription to set
	 */
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	
}
