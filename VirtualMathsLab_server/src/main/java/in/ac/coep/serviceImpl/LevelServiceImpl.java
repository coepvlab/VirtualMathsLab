///**
// * 
// */
//package in.ac.coep.serviceImpl;
//
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import in.ac.coep.dao.LevelFiveDao;
//import in.ac.coep.dao.LevelFourDao;
//import in.ac.coep.dao.LevelSixDao;
//import in.ac.coep.dao.LevelThreeDao;
//import in.ac.coep.dao.LevelTwoDao;
//import in.ac.coep.entity.Level2;
//import in.ac.coep.entity.Level3;
//import in.ac.coep.entity.Level4;
//import in.ac.coep.entity.Level5;
//import in.ac.coep.entity.Level6;
//import in.ac.coep.service.LevelService;
//
///**
// * @author Prahsant
// *
// */
//
//@Service
//public class LevelServiceImpl implements LevelService{
//	
//	private static final Logger LOGGER = Logger.getLogger(LevelServiceImpl.class);
//
//	@Autowired
//	private LevelSixDao levelSixDao;
//	
//	@Autowired
//	private LevelFiveDao levelFiveDao;
//
//	@Autowired
//	private LevelFourDao levelFourDao;
//
//	@Autowired
//	private LevelThreeDao levelThreeDao;
//
//	@Autowired
//	private LevelTwoDao levelTwoDao;
//
//	
//	@Override
//	public JSONObject checkAvailability(String levelNo) throws Exception {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//		
//		try {
//			
//			if(levelNo.length()  % 2 == 0 ) {
//				
//				int len = levelNo.length();
//				
//				if(len == 12) {
//					System.out.println(len);
//					data =  getLevelSixDetails(levelNo);
//							
//				}else if(len == 10) {
//					System.out.println(len);
//					data =  getLevelFiveDetails(levelNo);
//					
//				}else if(len == 8) {
//					System.out.println(len);
//					data =  getLevelForDetails(levelNo);
//					
//				}else if(len == 6) {
//					System.out.println(len);
//					data =  getLevelThreeDetails(levelNo);
//					
//				}else if(len == 4) {
//					System.out.println(len);
//					data =  getLevelTwoDetails(levelNo);
//					
//				}else if(len == 2) {
//					System.out.println(len);
//					data.put("done",false);
//					data.put("msg","Please enter higher level number.");
//				}
//				
//			}else {
//				
//				data.put("done",false);
//				data.put("msg","Incorrect level number.");
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			LOGGER.error(" error-", e);
//			data.put("done",false);
//			data.put("msg","Something went wrong level...");
//		}
//		
//		
//	
//		return data;
//	}
//
//	private JSONObject getLevelTwoDetails(String levelNo) {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//		
//		try {
//			
//			if (levelNo != null) {
//				
//				 Level2 level2 = levelTwoDao.getLevelByLevelNo(levelNo);
//				 
//				 JSONObject level = new JSONObject();
//				 
//				 if(level2 != null) {
//					
//					 level.put("L2ID", level2.getLevel2Id());
//					 level.put("L2N", level2.getLevel2Name());
//					 level.put("L2NO", level2.getLevel2No());
//					 
//					 
//					 level.put("L1ID", level2.getLevel1().getLevel1Id());
//					 level.put("L1N", level2.getLevel1().getLevel1Name());
//					 level.put("L1NO", level2.getLevel1().getLevel1No());
//					 
//					 level.put("SUBID", level2.getLevel1().getSubject().getSubjectId());
//					 
//					 data.put("data", level);
//					 data.put("done", true);
//					 
//				 }else {
//
//					 data.put("msg", "This level number is not available in system..");
//					 data.put("done", false);
//				 }
//				 
//			}else {
//				 data.put("msg", "Please enter valid level number");
//				 data.put("done", false);
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			LOGGER.error(" error-", e);
//			 data.put("msg", "Something went wrong..");
//			 data.put("done", false);
//		}
//		
//		return data;
//	
//	}
//
//	private JSONObject getLevelThreeDetails(String levelNo) {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//		
//		try {
//			
//			if (levelNo != null) {
//				
//				 Level3 level3 = levelThreeDao.getLevelByLevelNo(levelNo);
//				
//				 JSONObject level = new JSONObject();
//				 
//				 if(level3 != null) {
//					
//					 level.put("L3ID", level3.getLevel3Id());
//					 level.put("L3N", level3.getLevel3Name());
//					 level.put("L3NO", level3.getLevel3No());
//					 
//					 
//					 level.put("L2ID", level3.getLevel2().getLevel2Id());
//					 level.put("L2N", level3.getLevel2().getLevel2Name());
//					 level.put("L2NO", level3.getLevel2().getLevel2No());
//					 
//					 
//					 level.put("L1ID", level3.getLevel2().getLevel1().getLevel1Id());
//					 level.put("L1N", level3.getLevel2().getLevel1().getLevel1Name());
//					 level.put("L1NO", level3.getLevel2().getLevel1().getLevel1No());
//					 
//					 level.put("SUBID", level3.getLevel2().getLevel1().getSubject().getSubjectId());
//					 
//					 data.put("data", level);
//					 data.put("done", true);
//					 
//				 }else {
//
//					 data.put("msg", "This level number is not available in system..");
//					 data.put("done", false);
//				 }
//				 
//			}else {
//				 data.put("msg", "Please enter valid level number");
//				 data.put("done", false);
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			LOGGER.error(" error-", e);
//			 data.put("msg", "Something went wrong..");
//			 data.put("done", false);
//		}
//		
//		return data;
//	
//	}
//
//	private JSONObject getLevelForDetails(String levelNo) {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//		
//		try {
//			
//			if (levelNo != null) {
//				
//				 Level4 level4 = levelFourDao.getLevelByLevelNo(levelNo);
//				 
//				 JSONObject level = new JSONObject();
//				 
//				 if(level4 != null) {
//					
//					 level.put("L4ID", level4.getLevel4Id());
//					 level.put("L4N", level4.getLevel4Name());
//					 level.put("L4NO", level4.getLevel4No());
//					 
//					 
//					 level.put("L3ID", level4.getLevel3().getLevel3Id());
//					 level.put("L3N", level4.getLevel3().getLevel3Name());
//					 level.put("L3NO", level4.getLevel3().getLevel3No());
//					 
//					 
//					 level.put("L2ID", level4.getLevel3().getLevel2().getLevel2Id());
//					 level.put("L2N", level4.getLevel3().getLevel2().getLevel2Name());
//					 level.put("L2NO", level4.getLevel3().getLevel2().getLevel2No());
//					 
//					 
//					 level.put("L1ID", level4.getLevel3().getLevel2().getLevel1().getLevel1Id());
//					 level.put("L1N", level4.getLevel3().getLevel2().getLevel1().getLevel1Name());
//					 level.put("L1NO", level4.getLevel3().getLevel2().getLevel1().getLevel1No());
//					 
//					 level.put("SUBID", level4.getLevel3().getLevel2().getLevel1().getSubject().getSubjectId());
//					 
//					 data.put("data", level);
//					 data.put("done", true);
//					 
//				 }else {
//
//					 data.put("msg", "This level number is not available in system..");
//					 data.put("done", false);
//				 }
//				 
//				
//			}else {
//				 data.put("msg", "Please enter valid level number");
//				 data.put("done", false);
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			LOGGER.error(" error-", e);
//			 data.put("msg", "Something went wrong..");
//			 data.put("done", false);
//		}
//		
//		return data;
//	
//	}
//
//	private JSONObject getLevelFiveDetails(String levelNo) {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//		
//		try {
//			
//			if (levelNo != null) {
//				
//				 Level5 level5 = levelFiveDao.getLevelByLevelNo(levelNo);
//				 
//				 JSONObject level = new JSONObject();
//				 
//				 if(level5 != null) {
//					
//					 level.put("L5ID", level5.getLevel5Id());
//					 level.put("L5N", level5.getLevel5Name());
//					 level.put("L5NO", level5.getLevel5No());
//					 
//					 
//					 level.put("L4ID", level5.getLevel4().getLevel4Id());
//					 level.put("L4N", level5.getLevel4().getLevel4Name());
//					 level.put("L4NO", level5.getLevel4().getLevel4No());
//					 
//					 
//					 level.put("L3ID", level5.getLevel4().getLevel3().getLevel3Id());
//					 level.put("L3N", level5.getLevel4().getLevel3().getLevel3Name());
//					 level.put("L3NO", level5.getLevel4().getLevel3().getLevel3No());
//					 
//					 
//					 level.put("L2ID", level5.getLevel4().getLevel3().getLevel2().getLevel2Id());
//					 level.put("L2N", level5.getLevel4().getLevel3().getLevel2().getLevel2Name());
//					 level.put("L2NO", level5.getLevel4().getLevel3().getLevel2().getLevel2No());
//					 
//					 
//					 level.put("L1ID", level5.getLevel4().getLevel3().getLevel2().getLevel1().getLevel1Id());
//					 level.put("L1N", level5.getLevel4().getLevel3().getLevel2().getLevel1().getLevel1Name());
//					 level.put("L1NO", level5.getLevel4().getLevel3().getLevel2().getLevel1().getLevel1No());
//					 
//					 level.put("SUBID", level5.getLevel4().getLevel3().getLevel2().getLevel1().getSubject().getSubjectId());
//					 
//					 data.put("data", level);
//					 data.put("done", true);
//					 
//				 }else {
//
//					 data.put("msg", "This level number is not available in system..");
//					 data.put("done", false);
//				 }
//				 
//				
//				
//			}else {
//				 data.put("msg", "Please enter valid level number");
//				 data.put("done", false);
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			LOGGER.error(" error-", e);
//			 data.put("msg", "Something went wrong..");
//			 data.put("done", false);
//		}
//		
//		return data;
//	
//	}
//
//	private JSONObject getLevelSixDetails(String levelNo) {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//		
//		try {
//			
//			if (levelNo != null) {
//				
//				 Level6 level6 = levelSixDao.getLevelByLevelNo(levelNo);
//	
//				 JSONObject level = new JSONObject();
//				 
//				 if(level6 != null) {
//					
//					 level.put("L6ID", level6.getLevel6Id());
//					 level.put("L6N", level6.getLevel6Name());
//					 level.put("L6NO", level6.getLevel6No());
//					 
//					 
//					 level.put("L5ID", level6.getLevel5().getLevel5Id());
//					 level.put("L5N", level6.getLevel5().getLevel5Name());
//					 level.put("L5NO", level6.getLevel5().getLevel5No());
//					 
//					 
//					 level.put("L4ID", level6.getLevel5().getLevel4().getLevel4Id());
//					 level.put("L4N", level6.getLevel5().getLevel4().getLevel4Name());
//					 level.put("L4NO", level6.getLevel5().getLevel4().getLevel4No());
//					 
//					 
//					 level.put("L3ID", level6.getLevel5().getLevel4().getLevel3().getLevel3Id());
//					 level.put("L3N", level6.getLevel5().getLevel4().getLevel3().getLevel3Name());
//					 level.put("L3NO", level6.getLevel5().getLevel4().getLevel3().getLevel3No());
//					 
//					 
//					 level.put("L2ID", level6.getLevel5().getLevel4().getLevel3().getLevel2().getLevel2Id());
//					 level.put("L2N", level6.getLevel5().getLevel4().getLevel3().getLevel2().getLevel2Name());
//					 level.put("L2NO", level6.getLevel5().getLevel4().getLevel3().getLevel2().getLevel2No());
//					 
//					 
//					 level.put("L1ID", level6.getLevel5().getLevel4().getLevel3().getLevel2().getLevel1().getLevel1Id());
//					 level.put("L1N", level6.getLevel5().getLevel4().getLevel3().getLevel2().getLevel1().getLevel1Name());
//					 level.put("L1NO", level6.getLevel5().getLevel4().getLevel3().getLevel2().getLevel1().getLevel1No());
//					 
//					 level.put("SUBID", level6.getLevel5().getLevel4().getLevel3().getLevel2().getLevel1().getSubject().getSubjectId());
//					 
//					 data.put("data", level);
//					 data.put("done", true);
//					 
//				 }else {
//					 
//					 data.put("msg", "This level number is not available in system..");
//					 data.put("done", false);
//				 }
//				 
//				 
//				
//			}else {
//				 data.put("msg", "Please enter valid level number");
//				 data.put("done", false);
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			 LOGGER.error(" error-", e);
//			 data.put("msg", "Something went wrong.. level6");
//			 data.put("done", false);
//		}
//		
//		return data;
//	
//	}
//	
//	
//	
//	
//	
//
//}
