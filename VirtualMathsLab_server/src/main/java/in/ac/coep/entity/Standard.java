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
 * 
 */

/**
 * @author Prashant
 *
 */

@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "standard")
public class Standard implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stdId")
	private long stdId;

	@Column
	private String name;

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
	 * @return the stdId
	 */
	public long getStdId() {
		return stdId;
	}

	/**
	 * @param stdId the stdId to set
	 */
	public void setStdId(long stdId) {
		this.stdId = stdId;
	}
	
	
	
	
}
