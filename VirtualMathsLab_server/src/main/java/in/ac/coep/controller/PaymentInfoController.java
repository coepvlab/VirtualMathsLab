//package in.ac.coep.controller;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import in.ac.coep.service.TestLinkService;
//import in.ac.coep.vo.UserVO;
//
//@Controller
//public class PaymentInfoController {
//
//	@Autowired
//	private TestLinkService testLinkService;
//	
//	@RequestMapping(value = "/api/user/UpdatePaymentInfo", method = RequestMethod.POST)
//	public @ResponseBody String insertUser(@RequestBody UserVO userVO) {
//
//		
//		JSONObject data = new JSONObject();
//		try {
//			
//			data = testLinkService.updatePaymentInfo(userVO);
//			return data.toString();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		
//
//	}
//}
