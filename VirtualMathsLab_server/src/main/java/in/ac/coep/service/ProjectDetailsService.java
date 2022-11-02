package in.ac.coep.service;

import org.json.JSONObject;

public interface ProjectDetailsService {
	

	public JSONObject fetchAllProjectList() throws Exception;


	public JSONObject fetchAllStudentsList() throws Exception;

}
