package in.ac.coep.service;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import in.ac.coep.entity.User;

public interface UtilityService {

	public JSONObject getUserListForExcelDownload()throws Exception;

	public JSONObject uploadQuesByExcel(MultipartFile file, User user, int flag) throws Exception;

	public JSONObject uploadJavaFile(MultipartFile file, User user, int flag) throws Exception;

	public JSONObject getDataForDemoCall()throws Exception;

	public JSONObject updateTopicStatusByTID(long tid, boolean flag)throws Exception;

}
