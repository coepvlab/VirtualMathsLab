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
@Table(name = "answertype")
public class AnswerType implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answerTypeId")
	private int answerTypeId;

	private String name;

	/**
	 * @return the answerTypeId
	 */
	public int getAnswerTypeId() {
		return answerTypeId;
	}

	/**
	 * @param answerTypeId
	 *            the answerTypeId to set
	 */
	public void setAnswerTypeId(int answerTypeId) {
		this.answerTypeId = answerTypeId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
