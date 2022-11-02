package in.ac.coep.serviceImpl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import in.ac.coep.service.CheckLinkCreationService;

@Service
public class CheckLinkCreationServiceImpl implements CheckLinkCreationService{
	
	
//	@Autowired
//	private UserDao userDao;
	
//	@Autowired
//	private TestInstanceStateDao testinstanceStateDao;
	
//	@Autowired
//	private TestLinkService testlinkservice;
	
	

	@Override
	public JSONObject checklinkcreation(int testLevel) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
	
//		Roles roles = new Roles();
//		
//		roles = userDao.getRolesByName(Constants.RoleConstants.ITOSTUD);
//	
//		long id = roles.getRoleId();
		
//		List<User> users = userDao.listofUserbyRoleID(id);
			
	//	List<User> users = userDao.fetchUsers();	
		
		
		
//		for(User user: users){			
			
//	    TestInstanceState testinstanceState = testinstanceStateDao.getTestInstanceStateByUserIdandTestLevel(user.getUserId(), testLevel);
			
//		if(testinstanceState != null){
//			
//			data.put("done", true);
//			data.put("msg", "link is already generated for this user");			
//			
//		}	
//		else{
//			
////			data = testlinkservice.generateLinkForRequestedMailId(user.getPhoneNumber(), user.getDepartment().getDepartmentId(), testLevel);			
//			
//			data.put("done", true);
//			data.put("msg", "Success");
//			
//		}
			
			
			
//		}
		
		
		return data;
	}
	
	
	
	
	
	
	

}
