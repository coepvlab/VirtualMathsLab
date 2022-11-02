package in.ac.coep.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.StudentInfo;
import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.entity.User;
import in.ac.coep.service.CountryService;
import in.ac.coep.service.MediumTypeService;
import in.ac.coep.service.RegistrationService;
import in.ac.coep.service.TestConfigurationService;
import in.ac.coep.service.TestInstanceStateService;

/**
 * @author Vaibhav
 *
 */

@Controller
public class LoginController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private TestInstanceStateService testInstanceStateService;

	@Autowired
	private TestConfigurationService testConfigurationService;
	
	@Autowired
	private MediumTypeService mediumTypeService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private UserDao userDao;
	
//	@Autowired
//	private TestDao testDao;
	
	@Autowired
	private TestInstanceStateDao testInstanceStateDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Authentication authentication, HttpServletRequest request) {
		if (request != null && (request.isRequestedSessionIdValid())) {
			if (request.isUserInRole("ADMIN") || request.isUserInRole("CONTRIBUTOR") || request.isUserInRole("TEACHER") || request.isUserInRole("SUPERADMIN")|| request.isUserInRole("PARENT") || request.isUserInRole("MODERATOR")) {
				return "redirect:/home";

			} else if (request.isUserInRole("STUDENT")) {
				return "redirect:/home";
			}
		}else {
			return "redirect:/login";
		}
		return "login";
	}
	
	
	
	
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String loginAuthentication(Authentication authentication, HttpSession session, HttpServletRequest request,
			Model model) throws Exception {
		User user = (User) authentication.getPrincipal();

		model.addAttribute("FN", user.getFirstName());
		model.addAttribute("URL", user.getProfilePicUrl());
		model.addAttribute("GENDER", user.getGender());
		model.addAttribute("roles", registrationService.getAllRolesForThisUser(user.getUserId()));
		
		StudentInfo studInfo = userDao.getStudentInfoByUserId(user.getUserId());
		if (studInfo != null) {
			model.addAttribute("itoStud", studInfo.getParEmailId());
		}else {
			model.addAttribute("itoStud", "no");
		}
	
		
		if (request.getParameter(Constants.CSRF_TOKEN) != null) {
			String token = request.getParameter(Constants.CSRF_TOKEN);
			try {
//				model.addAttribute("USERDEPT", user.getStandard().getStdId());
				String result = testInstanceStateService.isURLValid(token, user.getUserId());
				if (result == "true") {

					JSONObject arr = testConfigurationService.fetchTopicNameAndTimeForPracticeTest(token);

					model.addAttribute("topicData", arr);

					return "home";
				} else if(result == Constants.linkWrongMsg || result == Constants.expiredLinkMsg || result == Constants.testCompletedMsg) {
					
					model.addAttribute("FN", user.getFirstName());
					model.addAttribute("GENDER", user.getGender());
//					model.addAttribute("LEVEL", complexityLevelService.fetchComplexityLevel());
//					model.addAttribute("ANSTYPE", answerTypeService.fetchAnswerTypes());
					model.addAttribute("role", "STUDENT");
					model.addAttribute("errMsg", result);
//					model.addAttribute("STATUS", status);
					
					
					try {
//						TestInstanceState testInstanceState =  testInstanceStateDao.getTestInstanceStateByUserIDAndCurrRound(user.getUserId(),Integer.parseInt(Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND")));
//						List<TestInstanceState> testInstanceState = testInstanceStateDao.getTestInstanceStateByUserId(user.getUserId());
//						for (TestInstanceState testInstanceState2 : testInstanceState) {
//							if(testInstanceState2.getStatus().equalsIgnoreCase("C") ){
//								if(testInstanceState2.getScoreCardLink() == null || testInstanceState2.getScoreCardLink().equals("(NULL)") || testInstanceState2.getScoreCardLink().equals(null)){
//									model.addAttribute("STUDRESULT", null);
//								}else{
////									StudentResult =  Constants.baseURLPath + "media/getResultPdf?mediaID="+testInstanceState2.getScoreCardLink() + "&userId="+testInstanceState2.getUser().getUserId()+"&testlevel="+testInstanceState2.getTestLevel();
////									StudentResulRoundNo = testInstanceState2.getTestLevel();
////									model.addAttribute("STUDRESULT", StudentResult);
//								}
//								
//							}
//							model.addAttribute("TL", testInstanceState2.getTestURL());
//							model.addAttribute("ST", testInstanceState2.getPlannedStartTime());
//							model.addAttribute("ET", testInstanceState2.getPlannedTestEndTime());
//							model.addAttribute("CURRENT_ROUND", testInstanceState2.getTestLevel());
////							model.addAttribute("USERDEPT", testInstanceState2.getDepartment().getDepartmentId());
//							model.addAttribute("RESUMECOUNT", testInstanceState2.getRescheduleNo());
////							model.addAttribute("STATUS", status);
//						}
						
					
						
					} catch (NullPointerException exception) {
						// TODO: handle exception
						model.addAttribute("TL", "link");
						
					}
					
					return "home";
					
				}else{
					model.addAttribute("FN", user.getFirstName());
					model.addAttribute("GENDER", user.getGender());
//					model.addAttribute("DEPT", departmentService.fetchAllDepartments());
//					model.addAttribute("USERDEPT", user.getDepartment().getDepartmentId());
//					model.addAttribute("LEVEL", complexityLevelService.fetchComplexityLevel());
//					model.addAttribute("ANSTYPE", answerTypeService.fetchAnswerTypes());
					model.addAttribute("role", "STUDENT");
//					model.addAttribute("STATUS", status);
					model.addAttribute("errMsg", result);
					
					return "home";
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.addAttribute("errMsg", "Something went wrong, please try again.");
				return "home";
			}
		}else {
			// user previous record check code 
			
			List<TestInstanceState> testInstanceStateList = testInstanceStateDao.getTestInstanceStateByUserIdAndTestType(user.getUserId(), Constants.practiceTestTypeId);
			
			Set<Long> topicsetComp = new HashSet<Long>(); // for cleared topic
			Set<Long> topicsetInComp = new HashSet<Long>(); // for cleared topic
			
			for (TestInstanceState testInstanceState : testInstanceStateList) {
				if (testInstanceState.getTestType().getTestTypeId() == Constants.practiceTestTypeId) {
					if (!testInstanceState.isLevelComplete()) {
//						Long topic = Long.parseLong(testInstanceState.getTest().getSelectedTopics()); 
						topicsetInComp.add(Long.parseLong(testInstanceState.getTest().getSelectedTopics())); //  for practice test there will be only topic in the array, this will be different in other test types.

					}else {
//						Long topic = Long.parseLong(testInstanceState.getTest().getSelectedTopics()); 
						topicsetComp.add(Long.parseLong(testInstanceState.getTest().getSelectedTopics()));//  for practice test there will be only topic in the array, this will be different in other test types.
					}
				}else if (testInstanceState.getTestType().getTestTypeId() == Constants.otherTestTypeId) {
					System.out.println("Other Test type");
				}
				
				
			}
			
			
			 Set<Long> result = new HashSet<Long>(topicsetInComp);
			    for (Long element : topicsetComp) {
			        // .add() returns false if element already exists
			        if (!result.add(element)) {
			            result.remove(element);
			        }
			    }
			    System.out.println("F - "+result);  ;
			    int n = result.size();
			    Long topicArr[] = new Long[n];
			    
			    topicArr = result.toArray(topicArr);

			    String tArr = "";
			    
		        for (int i = 0; i < topicArr.length; i++) {
		        	if (i == topicArr.length -1) {
		        		tArr += topicArr[i];
					}else {
						tArr += topicArr[i]+",";
					}
		        	
				}
		        
		        System.out.println(tArr);
		        
		    	model.addAttribute("TOPICARRAY", tArr);
		        
		}
		
		
		return "home";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model,Authentication authentication) throws Exception {
		return "logout";
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied(ModelMap model) {
		return "accessDenied";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String teacherRegistration(ModelMap model) {

		try {
			model.addAttribute("medium", mediumTypeService.fetchMediumTypes()); 
			model.addAttribute("country",countryService.fetchAllCountries());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "registration";
	}

	 
	 @RequestMapping(value = "/welcome", method = RequestMethod.GET)
		public String welcome(Authentication authentication, HttpServletRequest request) {

			return "index";
	}
	 
	 @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
		public String changePassword(Authentication authentication, HttpServletRequest request) {

			return "changepassword";
	} 
	 
	@RequestMapping(value = "/forgotPwdLink", method = RequestMethod.GET)
	public String getforgotPwd(ModelMap model, HttpServletRequest request)
			throws Exception {
		return "forgotPassword";

	}
}
