package in.ac.coep.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Prashant
 *
 */

@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "answers")
public class Answers implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answersId")
	private long answersId;
	
	@Column(length = 2000)
	private String content;
	
	@Column
	private String media; 

	private boolean isRightAnswer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionId")
	private Question question;
	
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

	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the isRightAnswer
	 */
	public boolean isRightAnswer() {
		return isRightAnswer;
	}

	/**
	 * @param isRightAnswer
	 *            the isRightAnswer to set
	 */
	public void setRightAnswer(boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
	
	

}
