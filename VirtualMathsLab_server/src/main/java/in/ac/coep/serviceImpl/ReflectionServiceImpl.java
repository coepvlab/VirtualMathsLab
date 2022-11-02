
package in.ac.coep.serviceImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.UserDao;
import in.ac.coep.dao.UtilityServiceDao;
import in.ac.coep.entity.MathsFileStorage;
import in.ac.coep.entity.User;
import in.ac.coep.service.ReflectionService;
/**
 * @author Prashant
 *
 */

@Service
public class ReflectionServiceImpl implements ReflectionService{

//	private static final Logger LOGGER = Logger.getLogger(ReflectionServiceImple.class);
	
	@Autowired
	private UtilityServiceDao utilityServiceDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public JSONObject fetchJavaFiles() throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		List<MathsFileStorage> mathsFileStorages =  utilityServiceDao.getAllFiles();
		
		JSONArray josnArr = new JSONArray();
		
		for (MathsFileStorage mathsFileStorage : mathsFileStorages) {
			
			JSONObject json = new JSONObject();
			
			String fileName = mathsFileStorage.getFileName();
			
			Class<?> cls = Class.forName(fileName);
			Object clsInstance = (Object) cls.newInstance();

			Method methodcall1 = cls.getDeclaredMethod("run");
			methodcall1.invoke(clsInstance);

			Field field1 = cls.getDeclaredField("questionWithOptions");
			field1.setAccessible(true);
			String quesWithOpt =  (String) field1.get(clsInstance);
			
			 
		    Field field2 = cls.getDeclaredField("solution");
		    field2.setAccessible(true);
		    String solution =  (String) field2.get(clsInstance);
			
			json.put("QUES", quesWithOpt);
			json.put("SOL", solution);
			json.put("UID", mathsFileStorage.getUser().getUserId());
			
			User user = new User();
			user = userDao.getUserById(mathsFileStorage.getUser().getUserId());
			
			json.put("UNM", user.getFirstName() + "" + user.getLastName());
			json.put("EMAIL", user.getEmailId());
			
			
			josnArr.put(json);
			
			System.out.println(mathsFileStorage.getFileName());
		}
		
		
		data.put("done", true);
		data.put("data", josnArr);
		
		return data;
	}
	
	
	
}
