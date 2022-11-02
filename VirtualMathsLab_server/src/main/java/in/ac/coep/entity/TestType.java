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
@Table(name = "testType")
public class TestType implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "testTypeId")
	private long testTypeId;
	
	@Column
	private String name;

	/**
	 * @return the testTypeId
	 */
	public long getTestTypeId() {
		return testTypeId;
	}

	/**
	 * @param testTypeId the testTypeId to set
	 */
	public void setTestTypeId(long testTypeId) {
		this.testTypeId = testTypeId;
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
	
	
	

}
