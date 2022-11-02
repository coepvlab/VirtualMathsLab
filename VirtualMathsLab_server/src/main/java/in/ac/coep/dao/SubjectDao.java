package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.Subject;

public interface SubjectDao {

	void save(Subject subject) throws Exception;

	public List<Subject> getAllSubjects() throws Exception;

	public Subject getSubjectById(long subjectId)  throws Exception;


}
