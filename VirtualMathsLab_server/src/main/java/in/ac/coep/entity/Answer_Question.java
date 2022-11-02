package in.ac.coep.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;
import org.springframework.stereotype.Component;

/**
 * @author Prashant
 *
 */

@SuppressWarnings("serial")
@Component
@Embeddable
public class Answer_Question implements Serializable {

	@ManyToOne
	@ForeignKey(name = "questionId")
	private Question question;
	
	@Column(name = "answersId")
	private long answersId;

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the answersId
	 */
	public long getAnswersId() {
		return answersId;
	}

	/**
	 * @param answersId the answersId to set
	 */
	public void setAnswersId(long answersId) {
		this.answersId = answersId;
	}

	
	

}
