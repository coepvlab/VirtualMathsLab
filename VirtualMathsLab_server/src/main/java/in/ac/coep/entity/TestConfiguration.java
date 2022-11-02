package in.ac.coep.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "testConfiguration")
public class TestConfiguration implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "testConfigId")
	private long testConfigId;

	@Column
	private int noOfQuestionGroup;

	@Column
	private int topicTimeLimit;

	@OneToOne
	@ForeignKey(name = "testId")
	private Test test;

	@OneToOne
	@ForeignKey(name = "TC_qgComplexityLevelId")
	private QGComplexityLevel complexityLevel;
	
	@ManyToOne
	@ForeignKey(name = "TC_stdId")
	private Standard standard;
	
	@ManyToOne
	@ForeignKey(name = "TC_topicId")
	private Topic topic;

	/**
	 * @return the testConfigId
	 */
	public long getTestConfigId() {
		return testConfigId;
	}

	/**
	 * @param testConfigId the testConfigId to set
	 */
	public void setTestConfigId(long testConfigId) {
		this.testConfigId = testConfigId;
	}

	/**
	 * @return the noOfQuestionGroup
	 */
	public int getNoOfQuestionGroup() {
		return noOfQuestionGroup;
	}

	/**
	 * @param noOfQuestionGroup the noOfQuestionGroup to set
	 */
	public void setNoOfQuestionGroup(int noOfQuestionGroup) {
		this.noOfQuestionGroup = noOfQuestionGroup;
	}

	/**
	 * @return the topicTimeLimit
	 */
	public int getTopicTimeLimit() {
		return topicTimeLimit;
	}

	/**
	 * @param topicTimeLimit the topicTimeLimit to set
	 */
	public void setTopicTimeLimit(int topicTimeLimit) {
		this.topicTimeLimit = topicTimeLimit;
	}

	/**
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(Test test) {
		this.test = test;
	}

	/**
	 * @return the complexityLevel
	 */
	public QGComplexityLevel getComplexityLevel() {
		return complexityLevel;
	}

	/**
	 * @param complexityLevel the complexityLevel to set
	 */
	public void setComplexityLevel(QGComplexityLevel complexityLevel) {
		this.complexityLevel = complexityLevel;
	}

	/**
	 * @return the standard
	 */
	public Standard getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
	
}
