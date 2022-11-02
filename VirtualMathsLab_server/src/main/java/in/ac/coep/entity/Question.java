package in.ac.coep.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.stereotype.Component;

/**
 * @author Prashant
 *
 */

@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "question")
public class Question implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionId")
	private long questionId;

	@Column(columnDefinition = "TEXT", length = 100000)
	private String content;

	@Column
	private int time;
	
	
	@Column(columnDefinition = "TEXT", length = 200000)
	private String solText;
	
	@Column
	private String solMedia;
	
	@Column
	private String solType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionGroupId", nullable = false)
	private QuestionGroup questionGroup;
	/**
	 * @return the questionGroup
	 */
	public QuestionGroup getQuestionGroup() {
		return questionGroup;
	}

	/**
	 * @param questionGroup
	 *            the questionGroup to set
	 */
	public void setQuestionGroup(QuestionGroup questionGroup) {
		this.questionGroup = questionGroup;
	}
	

	@OneToOne
	@ForeignKey(name = "Q_answerTypeId")
	private AnswerType answerType;

	
 	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question",cascade = CascadeType.ALL)
	private Set<Answers> answers;
	/**
	 * @return the answers
	 */
	public Set<Answers> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Set<Answers> answers) {
		this.answers = answers;
	}

	

	/**
	 * @return the questionId
	 */
	public long getQuestionId() {
		return questionId;
	}

	
	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
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

	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the answerType
	 */
	public AnswerType getAnswerType() {
		return answerType;
	}

	/**
	 * @param answerType
	 *            the answerType to set
	 */
	public void setAnswerType(AnswerType answerType) {
		this.answerType = answerType;
	}


	public String getSolType() {
		return solType;
	}

	public void setSolType(String solType) {
		this.solType = solType;
	}

	/**
	 * @return the solText
	 */
	public String getSolText() {
		return solText;
	}

	/**
	 * @param solText the solText to set
	 */
	public void setSolText(String solText) {
		this.solText = solText;
	}

	/**
	 * @return the solMedia
	 */
	public String getSolMedia() {
		return solMedia;
	}

	/**
	 * @param solMedia the solMedia to set
	 */
	public void setSolMedia(String solMedia) {
		this.solMedia = solMedia;
	}
	
	
	

}
