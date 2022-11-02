///**
// * 
// */
//package in.ac.coep.entity;
//
//import java.io.Serializable;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.ForeignKey;
//import org.springframework.stereotype.Component;
//
///**
// * @author Prashant
// *
// */
//
//@SuppressWarnings("serial")
//@Component
//@Entity
//@Table(name = "problemStatement")
//public class ProblemStatement implements Serializable {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "probStatementId")
//	private long psId;
//
//	@Column(columnDefinition = "TEXT", length = 100000)
//	private String statement;
//	
//	@Column
//	private String varNo; // variation number
//	
//	
//	@OneToOne
//	@ForeignKey(name = "QG_qgComplexityLevelId")
//	private QGComplexityLevel complexityLevel;
//	
//	
//	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//	@JoinTable(name = "ProbleStatementMapping", joinColumns = { @JoinColumn(name = "probStatementId") }, inverseJoinColumns = {
//			@JoinColumn(name = "topicId") })
//	private Set<Topic> topicSet;
//	
//	@Column
//	private long creationTime;
//	
//	@Column
//	private long updatedTime;
//
//}
