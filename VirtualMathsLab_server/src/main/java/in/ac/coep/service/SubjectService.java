/**
 * 
 */
package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.entity.User;
import in.ac.coep.vo.SubjectVO;

/**
 * @author Prashant
 *
 */
public interface SubjectService {

	JSONObject addSubject(SubjectVO subjectVO, User user) throws Exception;

	JSONObject getAllSubjects() throws Exception;

}
