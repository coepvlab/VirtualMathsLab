package in.ac.coep.serviceImpl;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.ac.coep.dao.TopicDao;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.Cities;
import in.ac.coep.entity.ContributorInfo;
import in.ac.coep.entity.ParentInfo;
import in.ac.coep.entity.Roles;
import in.ac.coep.entity.StudentInfo;
import in.ac.coep.entity.TeacherInfo;
import in.ac.coep.entity.Topic;
import in.ac.coep.entity.User;
import in.ac.coep.service.MediaService;
import in.ac.coep.service.QuestionGroupService;
import in.ac.coep.service.UtilityService;
import in.ac.coep.vo.AnswerTypeVO;
import in.ac.coep.vo.AnswersVO;
import in.ac.coep.vo.MediaTypeVO;
import in.ac.coep.vo.QuesGroupMediaLinksVO;
import in.ac.coep.vo.QuestionGroupVO;
import in.ac.coep.vo.QuestionVO;
import in.ac.coep.vo.SubjectVO;
import in.ac.coep.vo.TopicVO;

/**
 * @author Vaibhav
 *
 */

@Service
public class UtilityServiceImpl implements UtilityService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private QuestionGroupService questionGroupService;
	
//	@Autowired
//	private UtilityServiceDao utilityServiceDao;
	
	@Override
	public JSONObject getUserListForExcelDownload() throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONArray dataArr = new JSONArray();
		String email = null;

		List<User> userList = userDao.fetchUsers();

		if (userList.size() != 0) {
			for (User user : userList) {
				if (email != user.getEmailId()) {
					JSONObject json = new JSONObject();

					ContributorInfo conInfo = userDao.getContributorInfoByUserId(user.getUserId());
					TeacherInfo teachInfo = userDao.getTeacherInfoByUserId(user.getUserId());
					StudentInfo studInfo = userDao.getStudentInfoByUserId(user.getUserId());
					ParentInfo pi = userDao.getParentInfoByUserId(user.getUserId());
					Cities city = userDao.getCityNameByCityId(user.getCityId());

					Set<Roles> roles = user.getRoles();

					JSONObject roleJson = new JSONObject();
					int roleCnt = 0;

					Iterator<Roles> it = roles.iterator();
					while (it.hasNext()) {
						roleJson.put(it.next().getRoleName(), roleCnt++);
					}

					json.put("UserId", user.getUserId());
					json.put("emailId", user.getEmailId());
					json.put("name", user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
					json.put("gender", user.getGender());
					json.put("phoneNo", user.getPhoneNumber());
					json.put("whtsapp", user.getWhatsAppNo());
					json.put("dob", user.getDateOfBirth());
					json.put("pincode", user.getPincode());
					json.put("city", city.getName());

					json.put("roles", roleJson);

					if (roleJson.has("TEACHER")) {
						
							JSONObject teachJson = new JSONObject();
							
							if(teachInfo != null) {
								teachJson.put("schoolName", teachInfo.getSchoolName());
								teachJson.put("schoolType", teachInfo.getSchoolType());
								teachJson.put("medium", teachInfo.getMedium().getMedium());
								teachJson.put("exp", teachInfo.getExperience());
								teachJson.put("schoolAdd", teachInfo.getSchoolAdd());
								teachJson.put("teachGroup", teachInfo.getTeachingGroup());
							}
							json.put("teacher", teachJson);
						
					
					}

					if (roleJson.has("CONTRIBUTOR")) {
						JSONObject contJson = new JSONObject();
						if(conInfo != null) {
							contJson.put("schoolName", conInfo.getSchoolName());
							contJson.put("schoolType", conInfo.getSchoolType());
							contJson.put("medium", conInfo.getMedium().getMedium());
							contJson.put("exp", conInfo.getExperience());
							contJson.put("schoolAdd", conInfo.getSchoolAdd());
							contJson.put("canContribInLatex", conInfo.getCanContributeInLatex());
						}
						json.put("contributor", contJson);
					}

					if (roleJson.has("PARENT")) {
						JSONObject parJson = new JSONObject();
						if(conInfo != null) {
							parJson.put("qualif", pi.getQualification());
							parJson.put("occup", pi.getOccupation());
							parJson.put("desig", pi.getDesignation());
							parJson.put("servLeng", pi.getServiceLength());
							parJson.put("company", pi.getCompanyName());
						}
						json.put("parent", parJson);
					}

					if (roleJson.has("STUDENT")) {

						JSONObject studJson = new JSONObject();
						if(studInfo != null) {
								
								studJson.put("parEmailId", studInfo.getParEmailId());
								studJson.put("schoolName", studInfo.getSchoolName());
								studJson.put("schoolAdd", studInfo.getSchoolAdd());
								studJson.put("schoolType", studInfo.getSchoolType());
								studJson.put("standard", studInfo.getStandard());
								studJson.put("medium", studInfo.getMedium().getMedium());
		
								studJson.put("faname", studInfo.getF_name());
								studJson.put("fqualif", studInfo.getF_qualification());
								studJson.put("foccup", studInfo.getF_occupation());
								studJson.put("fdesig", studInfo.getF_designation());
								studJson.put("fcompany", studInfo.getF_company());
								studJson.put("fservLeng", studInfo.getF_serviceLength());
								studJson.put("fcity", studInfo.getF_city());
								studJson.put("fage", studInfo.getF_age());
		
								studJson.put("maname", studInfo.getM_name());
								studJson.put("mqualif", studInfo.getM_qualification());
								studJson.put("moccup", studInfo.getM_occupation());
								studJson.put("mdesig", studInfo.getM_designation());
								studJson.put("mcompany", studInfo.getM_company());
								studJson.put("mservLeng", studInfo.getM_serviceLength());
								studJson.put("mcity", studInfo.getM_city());
								studJson.put("mage", studInfo.getM_age());
						}
						json.put("student", studJson);
					}

					dataArr.put(json);
					email = user.getEmailId();
				}
			}

			data.put("users", dataArr);
			data.put("done", true);
			data.put("msg", "User fetch successfully");
		} else {
			data.put("done", false);
			data.put("msg", "User not found");
		}
		return data;
	}

	@Override
	public JSONObject uploadQuesByExcel(MultipartFile file, User user, int taskFlag) throws Exception {
		// TODO Upload excel sheet function start
		
		String errorMsg = "";
		JSONObject dataReturn = new JSONObject();
		QuestionGroupVO questionGroup;
		QuestionVO question;
		boolean EOF = false;
		
		Set<QuestionGroupVO> questionGroupVOs = new HashSet<>();

		boolean correctFormat = true;
		
		try {
			XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = wb.getSheetAt(1);

			int rowCount = sheet.getPhysicalNumberOfRows();

			XSSFRow row0 = sheet.getRow(0);
			int cols0 = row0.getPhysicalNumberOfCells();

			cols0 = 19;
			
			for (int j = 0; j < cols0; j++) {
				XSSFCell cell = row0.getCell(j);

				if (cell.getCellType() == CELL_TYPE_BLANK) {
					errorMsg = "Blank cell at column " + (j + 1) + " Row 0.";
					correctFormat = false;
					break;
				} else if (cell.getCellType() != CELL_TYPE_STRING) {
					errorMsg = "Sheet format changed at column " + (j + 1) + " Row 0.";
					correctFormat = false;
					break;
				} else {

					switch (j) {
						case 0:
							if (!cell.getStringCellValue().equalsIgnoreCase("Sr. No")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 1:
							if (!cell.getStringCellValue().equalsIgnoreCase("Question Type")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 2:
							if (!cell.getStringCellValue().equalsIgnoreCase("Answer Type")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 3:
							if (!cell.getStringCellValue().equalsIgnoreCase("Topic Number")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 4:
							if (!cell.getStringCellValue().equalsIgnoreCase("Question (Text Only)")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 5:
							if (!cell.getStringCellValue().equalsIgnoreCase("Correct Answer 1")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 6:
							if (!cell.getStringCellValue().equalsIgnoreCase("Correct Answer 2")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 7:
							if (!cell.getStringCellValue().equalsIgnoreCase("Correct Answer 3")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 8:
							if (!cell.getStringCellValue().equalsIgnoreCase("Correct Answer 4")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 9:
							if (!cell.getStringCellValue().equalsIgnoreCase("Wrong Answer 1")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 10:
							if (!cell.getStringCellValue().equalsIgnoreCase("Wrong Answer 2")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 11:
							if (!cell.getStringCellValue().equalsIgnoreCase("Wrong Answer 3")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 12:
							if (!cell.getStringCellValue().equalsIgnoreCase("Time in seconds")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 13:
							if (!cell.getStringCellValue().equalsIgnoreCase("Difficulty Level")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 14:
							if (!cell.getStringCellValue().equalsIgnoreCase("Question (Image/ Audio/ Video)")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 15:
							if (!cell.getStringCellValue().equalsIgnoreCase("Contributor's Registered mailId")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 16:
							if (!cell.getStringCellValue().equalsIgnoreCase("Solution (Text only)")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
							
						case 17:
							if (!cell.getStringCellValue().equalsIgnoreCase("Solution (Image/ Audio/ Video)")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;	
//						case 18:	
//							if (!cell.getStringCellValue().equalsIgnoreCase("QGFlag")) {
//								correctFormat = false;
//								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
//							}
//							break;	
						case 18:	
							if (!cell.getStringCellValue().equalsIgnoreCase("Variation Number")) {
								correctFormat = false;
								errorMsg = "Sheet format error : Column "+cell.getStringCellValue()+" changed at column " + (j + 1) + "Row 0.";
							}
							break;
						default:
							correctFormat = false;
							errorMsg = "Sheet format "+cell.getStringCellValue()+" changed ... Number of columns exceeds....";
							break;
					}// switch ends
					if (!correctFormat)
						break;
				}
			}
			
			System.out.println(correctFormat);

			if (correctFormat) {
				
				String newLine = "\n";
				errorMsg = "Row and column count starts from 1 " + newLine;
				boolean correctData = true;
				
				for (int i = 1; i < rowCount; i++) {
				System.out.println("group : " + i);
				
					Set<AnswersVO> answersSet = new HashSet<>();

					Set<QuestionVO> questionVOs = new HashSet<>();

					questionGroup = new QuestionGroupVO();
					question = new QuestionVO();
					MediaTypeVO mediaType = new MediaTypeVO();

					XSSFRow row = sheet.getRow(i);
					int cols = row.getPhysicalNumberOfCells();
					cols = 20;
					int ansC = 0;
					int ansW = 0;
					
					for (int j = 0; j < cols; j++) {						

						XSSFCell cell0 = row.getCell(j);
						
						switch (j) {
						case 0://Sr. No
							if(cell0.getCellType() == CELL_TYPE_STRING) {
								if(cell0.getStringCellValue().equals("****")) {
									EOF = true;
									break;
								}else {
									correctData = false;
								}
							}
							
							if(cell0.getCellType() == CELL_TYPE_NUMERIC) {
								correctData = true;
								break;	
							}
							
						case 1: // question type case mandatory field
							if (cell0 != null) {
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Question type is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
								} else if (cell0.getCellType() != CELL_TYPE_STRING) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

								} else {

									if (cell0.getStringCellValue().trim().equalsIgnoreCase("Image")) {
										correctData = true;
										questionGroup.setType(cell0.getStringCellValue());
										mediaType.setMediaTypeId(4);

									} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Video")) {
										correctData = true;
										questionGroup.setType(cell0.getStringCellValue());
										mediaType.setMediaTypeId(3);

									} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Audio")) {
										correctData = true;
										questionGroup.setType(cell0.getStringCellValue());
										mediaType.setMediaTypeId(2);

									} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Text")) {
										correctData = true;
										questionGroup.setType(cell0.getStringCellValue());
										mediaType.setMediaTypeId(1);

									}
//									else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Group")) {
//										correctData = true;
//										questionGroup.setType(cell0.getStringCellValue());
//
//									} 
									else {
										errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
										correctData = false;
									}
								}
							} else {
								errorMsg += "Question type is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
										+ newLine;
								correctData = false;
							}

							break;
						
						case 2: //Answer type case
							if (cell0 != null) {
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Answer type is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

								} else if (cell0.getCellType() != CELL_TYPE_NUMERIC) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

//								} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Singular Correct Answer")) {
								} else if (cell0.getNumericCellValue() == 1) {
									correctData = true;
									AnswerTypeVO answerType = new AnswerTypeVO();
									answerType.setAnswerTypeId(1);
									question.setAnswerType(answerType);

//								} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Fill In The Blanks")) {
								} else if (cell0.getNumericCellValue() == 2) {
									correctData = true;
									AnswerTypeVO answerType = new AnswerTypeVO();
									answerType.setAnswerTypeId(2);
									question.setAnswerType(answerType);

//								} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Match the Pairs")) {
								} else if (cell0.getNumericCellValue() == 3) {
									correctData = true;
									AnswerTypeVO answerType = new AnswerTypeVO();
									answerType.setAnswerTypeId(3);
									question.setAnswerType(answerType);

//								} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("True or False")) {
								} else if (cell0.getNumericCellValue() == 4) {
									correctData = true;
									AnswerTypeVO answerType = new AnswerTypeVO();
									answerType.setAnswerTypeId(4);
									question.setAnswerType(answerType);

//								} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Multiple Correct Answers")) {
								} else if (cell0.getNumericCellValue() == 5) {
									correctData = true;
									AnswerTypeVO answerType = new AnswerTypeVO();
									answerType.setAnswerTypeId(5);
									question.setAnswerType(answerType);

//								} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Image - Singular Correct Answer")) {
								} else if (cell0.getNumericCellValue() == 6) {
									correctData = true;
									AnswerTypeVO answerType = new AnswerTypeVO();
									answerType.setAnswerTypeId(6);
									question.setAnswerType(answerType);

//								} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Image - Match the Pairs")) {
								} else if (cell0.getNumericCellValue() == 7) {
									correctData = true;
									AnswerTypeVO answerType = new AnswerTypeVO();
									answerType.setAnswerTypeId(7);
									question.setAnswerType(answerType);

//								} else if (cell0.getStringCellValue().trim().equalsIgnoreCase("Image - Multiple Correct Answers")) {
								} else if (cell0.getNumericCellValue() == 8) {
									correctData = true;
									AnswerTypeVO answerType = new AnswerTypeVO();
									answerType.setAnswerTypeId(8);
									question.setAnswerType(answerType);

								} else {
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
								}
							} else {
								errorMsg += "Answer type is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
										+ newLine;
								correctData = false;
							}

							break;
						
						case 3: //Topic Number or Main category 
							if (cell0 != null) {
								
								cell0.setCellType(Cell.CELL_TYPE_STRING);
								
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Topic number is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
									
								}else if(cell0.getCellType() == CELL_TYPE_STRING) {
									String topics1[] = cell0.getStringCellValue().replaceAll(" ", "").split(",");
									
									String[] topics = new HashSet<String>(Arrays.asList(topics1)).toArray(new String[0]);
									
									TopicVO[] topicVOArray = new TopicVO[topics.length];

									for(int a=0; a < topics.length; a++) {
										if(topics[a].trim().length() == 1) {
											topics[a] = "0".concat(topics[a]);
										}
										
										if(topics[a].trim().length() % 2 == 0) {
											Topic topic = topicDao.getTopicByTopicNo(topics[a].trim());
											TopicVO topicvo = new TopicVO();
											if(topic != null) {
												topicvo.setTopicId(topic.getTopicId());
												topicVOArray[a] = topicvo;
											}
										}else {
											errorMsg += "Wrong topic number format at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
											correctData = false;
										}
									}
									
									questionGroup.setTopic(topicVOArray);
									
								} 
							} else {
								errorMsg += "Topic number is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
										+ newLine;
								correctData = false;
							}
							break;
							
						case 4://Question(Text Only)
							
							if (cell0 != null) {

								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Question text is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
								} else if (cell0.getCellType() != CELL_TYPE_STRING) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

								} else if(cell0.getStringCellValue().trim().isEmpty()){
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
									
								}else{
									question.setContent(cell0.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
								}
							}else {
								errorMsg += "Question text is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
										+ newLine;
								correctData = false;
							}

							break;
							
						case 5: // Correct answer 1
							if (cell0 != null) {
								AnswersVO answers = new AnswersVO();
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Correct answer 1 is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
									
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {
									ansC++;
									XSSFCell cell3 = row.getCell(2);
//									if (cell3.getStringCellValue().trim()
//											.equalsIgnoreCase("Image - Singular Correct Answer")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Match the Pairs")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Multiple Correct Answers")) {
									if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {

										String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
										if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
												|| ext.equalsIgnoreCase("jpg")) {
											String mediaUrl = uploadMedia(cell0, file, taskFlag);

											if (mediaUrl != null) {
												answers.setMedia("IMAGE");
												answers.setContent(mediaUrl);
												answers.setRightAnswer(true);
												answersSet.add(answers);
												
												if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
													boolean checkForDuplicates = checkForDuplicate(answersSet);
													if (checkForDuplicates) {
														errorMsg += "Duplicate data found in answers <br>";
														errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
														correctData = false;
													}else {
														correctData = true;
													}
												}else {
													correctData = true;
												}
											} else {
												errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
														+ ".\n No such file available at '" + cell0.getStringCellValue()
														+ "' path";
												correctData = false;
											}
										}else {
											correctData = false;
											errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (j + 1); 
										}
									} else {
										correctData = true;
										answers.setMedia("TEXT");
										answers.setContent(cell0.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
										answers.setRightAnswer(true);
										answersSet.add(answers);
										if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
											boolean checkForDuplicates = checkForDuplicate(answersSet);
											if (checkForDuplicates) {
												errorMsg += "Duplicate data found in answers <br>";
												errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
												correctData = false;
											}
										}
									}

								} else if (cell0.getCellType() == CELL_TYPE_NUMERIC) {
									ansC++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getNumericCellValue() + "");
									answers.setRightAnswer(true);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
											errorMsg += "Duplicate data found in answers <br>";
											errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " +  newLine;
											correctData = false;
										}
									}
								} else if (cell0.getCellType() == CELL_TYPE_BOOLEAN) {
									ansC++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getBooleanCellValue() + "");
									answers.setRightAnswer(true);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
											errorMsg += "Duplicate data found in answers <br>";
											errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
											correctData = false;
										}
									}
								} else {
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
								}
							} else {
								errorMsg += "Correct answer 1 is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
										+ newLine;
								correctData = false;
							}

							break;
							
							
						case 6: // Correct Answer 2
							if (cell0 != null) {
								AnswersVO answers = new AnswersVO();
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									correctData = true;
								
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {
									ansC++;
									XSSFCell cell3 = row.getCell(2);
//									if (cell3.getStringCellValue().trim()
//											.equalsIgnoreCase("Image - Singular Correct Answer")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Match the Pairs")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Multiple Correct Answers")) {
									if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
										
										String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
										if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
												|| ext.equalsIgnoreCase("jpg")) {
											String mediaUrl = uploadMedia(cell0, file, taskFlag);

											if (mediaUrl != null) {
												answers.setMedia("IMAGE");
												answers.setContent(mediaUrl);
												answers.setRightAnswer(true);
												answersSet.add(answers);
												if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
													boolean checkForDuplicates = checkForDuplicate(answersSet);
													if (checkForDuplicates) {
														errorMsg += "Duplicate data found in answers <br>";
														errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
														correctData = false;
													}else {
														correctData = true;
													}
												}else {
													correctData = true;
												}
											} else {
												errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
														+ ".\n No such file available at '" + cell0.getStringCellValue()
														+ "' path";
												correctData = false;
											}
										}else {
											correctData = false;
											errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (j + 1); 
										}
									} else {
										correctData = true;
										answers.setMedia("TEXT");
										answers.setContent(cell0.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
										answers.setRightAnswer(true);
										answersSet.add(answers);
										if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
											boolean checkForDuplicates = checkForDuplicate(answersSet);
											if (checkForDuplicates) {
												errorMsg += "Duplicate data found in answers <br>";
												errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers "  + newLine;
												correctData = false;
											}
										}
									}

								} else if (cell0.getCellType() == CELL_TYPE_NUMERIC) {
									ansC++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getNumericCellValue() + "");
									answers.setRightAnswer(true);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
											errorMsg += "Duplicate data found in answers <br>";
											errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
											correctData = false;
										}
									}
								} else {
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
								}
							} else {
								correctData = true;
							}
							break;
							
						case 7: // Correct Answer 3
							if (cell0 != null) {
								AnswersVO answers = new AnswersVO();
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									correctData = true;
								
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {
									ansC++;
									XSSFCell cell3 = row.getCell(2);
//									if (cell3.getStringCellValue().trim()
//											.equalsIgnoreCase("Image - Singular Correct Answer")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Match the Pairs")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Multiple Correct Answers")) {
									if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {

										String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
										if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
												|| ext.equalsIgnoreCase("jpg")) {
											String mediaUrl = uploadMedia(cell0, file, taskFlag);

											if (mediaUrl != null) {
												answers.setMedia("IMAGE");
												answers.setContent(mediaUrl);
												answers.setRightAnswer(true);
												answersSet.add(answers);
												correctData = true;
											} else {
												errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
														+ ".\n No such file available at '" + cell0.getStringCellValue()
														+ "' path";
												correctData = false;
											}
										}else {
										correctData = false;
										errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (j + 1); 
									}
									} else {
										correctData = true;
										answers.setMedia("TEXT");
										answers.setContent(cell0.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
										answers.setRightAnswer(true);
										answersSet.add(answers);
										if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
											boolean checkForDuplicates = checkForDuplicate(answersSet);
											if (checkForDuplicates) {
												errorMsg += "Duplicate data found in answers <br>";
												errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers "  + newLine;
												correctData = false;
											}
										}
									}

								} else if (cell0.getCellType() == CELL_TYPE_NUMERIC) {
									ansC++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getNumericCellValue() + "");
									answers.setRightAnswer(true);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
											errorMsg += "Duplicate data found in answers <br>";
											errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers "  + newLine;
											correctData = false;
										}
									}
								} else {
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
								}
							} else {
								correctData = true;
							}
							break;
							
						case 8: // Correct Answer 4
							if (cell0 != null) {
								AnswersVO answers = new AnswersVO();
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									correctData = true;
								
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {
									ansC++;
									XSSFCell cell3 = row.getCell(2);
//									if (cell3.getStringCellValue().trim()
//											.equalsIgnoreCase("Image - Singular Correct Answer")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Match the Pairs")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Multiple Correct Answers")) {
									if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
										
										String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
										if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
												|| ext.equalsIgnoreCase("jpg")) {
											String mediaUrl = uploadMedia(cell0, file, taskFlag);

											if (mediaUrl != null) {
												answers.setMedia("IMAGE");
												answers.setContent(mediaUrl);
												answers.setRightAnswer(true);
												answersSet.add(answers);
												if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
													boolean checkForDuplicates = checkForDuplicate(answersSet);
													if (checkForDuplicates) {
														errorMsg += "Duplicate data found in answers <br>";
														errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
														correctData = false;
													}else {
														correctData = true;
													}
												}else {
													correctData = true;
												}
											} else {
												errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
														+ ".\n No such file available at '" + cell0.getStringCellValue()
														+ "' path";
												correctData = false;
											}
										}else {
										correctData = false;
										errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (j + 1); 
									 }
									} else {
										correctData = true;
										answers.setMedia("TEXT");
										answers.setContent(cell0.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
										answers.setRightAnswer(true);
										answersSet.add(answers);
										if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
											boolean checkForDuplicates = checkForDuplicate(answersSet);
											if (checkForDuplicates) {
												errorMsg += "Duplicate data found in answers <br>";
												errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
												correctData = false;
											}
										}
									}

								} else if (cell0.getCellType() == CELL_TYPE_NUMERIC) {
									ansC++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getNumericCellValue() + "");
									answers.setRightAnswer(true);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
											errorMsg += "Duplicate data found in answers <br>";
											errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
											correctData = false;
										}
									}
								} else {
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
								}
							} else {
								correctData = true;
							}
							break;
							
						case 9: // Wrong Answer 1 mandatory field
							
							if (cell0 != null) {
								AnswersVO answers = new AnswersVO();
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									
									correctData = false;
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {
									ansW++;
									XSSFCell cell3 = row.getCell(2);
//									if (cell3.getStringCellValue().trim()
//											.equalsIgnoreCase("Image - Singular Correct Answer")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Match the Pairs")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Multiple Correct Answers")) {
									if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
										
										String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
										if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
												|| ext.equalsIgnoreCase("jpg")) {
											String mediaUrl = uploadMedia(cell0, file, taskFlag);

											if (mediaUrl != null) {
												answers.setMedia("IMAGE");
												answers.setContent(mediaUrl);
												answers.setRightAnswer(false);
												answersSet.add(answers);
												if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
													boolean checkForDuplicates = checkForDuplicate(answersSet);
													if (checkForDuplicates) {
														errorMsg += "Duplicate data found in answers <br>";
														errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
														correctData = false;
													}else {
														correctData = true;
													}
												}else {
													correctData = true;
												}
											} else {
												errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
														+ ".\n No such file available at '" + cell0.getStringCellValue()
														+ "' path";
												correctData = false;
											}
										}else {
											correctData = false;
											errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (j + 1); 
										}
									} else {
										correctData = true;
										answers.setMedia("TEXT");
										answers.setContent(cell0.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
										answers.setRightAnswer(false);
										answersSet.add(answers);
										if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
											boolean checkForDuplicates = checkForDuplicate(answersSet);
											if (checkForDuplicates) {
												errorMsg += "Duplicate data found in answers <br>";
												errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
												correctData = false;
											}
										}
									}
								} else if (cell0.getCellType() == CELL_TYPE_NUMERIC) {
									ansW++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getNumericCellValue() + "");
									answers.setRightAnswer(false);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
											errorMsg += "Duplicate data found in answers <br>";
											errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
											correctData = false;
										}
									}
								} else if (cell0.getCellType() == CELL_TYPE_BOOLEAN) {
									ansW++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getBooleanCellValue() + "");
									answers.setRightAnswer(false);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
											errorMsg += "Duplicate data found in answers <br>";
											errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
											correctData = false;
										}
									}

								} else {
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
								}
							} else {
								
								XSSFCell cell3 = row.getCell(2);
								if (cell3.getStringCellValue().trim()
										.equalsIgnoreCase("Multiple Correct Answers")
										|| cell3.getStringCellValue().trim()
												.equalsIgnoreCase("Image - Multiple Correct Answers")) {
									correctData = true;
								}else {
									errorMsg += "Wrong answer 1 is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
								}
							}
							break;
							
						case 10: // Wrong Answer 2
							
							if (cell0 != null) {
								AnswersVO answers = new AnswersVO();
								if (cell0.getCellType() == CELL_TYPE_BLANK) {

									correctData = true;
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {
									ansW++;
									XSSFCell cell3 = row.getCell(2);
//									if (cell3.getStringCellValue().trim()
//											.equalsIgnoreCase("Image - Singular Correct Answer")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Match the Pairs")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Multiple Correct Answers")) {
									if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
										
										String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
										if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
												|| ext.equalsIgnoreCase("jpg")) {
											String mediaUrl = uploadMedia(cell0, file, taskFlag);

											if (mediaUrl != null) {
												answers.setMedia("IMAGE");
												answers.setContent(mediaUrl);
												answers.setRightAnswer(false);
												answersSet.add(answers);
												if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
													boolean checkForDuplicates = checkForDuplicate(answersSet);
													if (checkForDuplicates) {
														errorMsg += "Duplicate data found in answers <br>";
														errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
														correctData = false;
													}else {
														correctData = true;
													}
												}else {
													correctData = true;
												}
												
											} else {
												errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
														+ ".\n No such file available at '" + cell0.getStringCellValue()
														+ "' path";
												correctData = false;
											}
										}else {
											correctData = false;
											errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (j + 1); 
										}
									} else {
										correctData = true;
										answers.setMedia("TEXT");
										answers.setContent(cell0.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
										answers.setRightAnswer(false);
										answersSet.add(answers);
										if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
											boolean checkForDuplicates = checkForDuplicate(answersSet);
											if (checkForDuplicates) {
												errorMsg += "Duplicate data found in answers <br>";
												errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " +  newLine;
												correctData = false;
											}
										}
									}

								} else if (cell0.getCellType() == CELL_TYPE_NUMERIC) {
									ansW++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getNumericCellValue() + "");
									answers.setRightAnswer(false);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
											errorMsg += "Duplicate data found in answers <br>";
											errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
											correctData = false;
										}
									}
								} else {
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
								}
							} else {
								correctData = true;
							}
							break;
							
						case 11: // Wrong Answer 3
							
							if (cell0 != null) {
								AnswersVO answers = new AnswersVO();
								if (cell0.getCellType() == CELL_TYPE_BLANK) {

									correctData = true;
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {
									ansW++;
									XSSFCell cell3 = row.getCell(2);
//									if (cell3.getStringCellValue().trim()
//											.equalsIgnoreCase("Image - Singular Correct Answer")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Match the Pairs")
//											|| cell3.getStringCellValue().trim()
//													.equalsIgnoreCase("Image - Multiple Correct Answers")) {
									if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
										
										String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
										if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
												|| ext.equalsIgnoreCase("jpg")) {
											String mediaUrl = uploadMedia(cell0, file, taskFlag);

											if (mediaUrl != null) {
												answers.setMedia("IMAGE");
												answers.setContent(mediaUrl);
												answers.setRightAnswer(false);
												answersSet.add(answers);
												if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
													boolean checkForDuplicates = checkForDuplicate(answersSet);
													if (checkForDuplicates) {
														errorMsg += "Duplicate data found in answers <br>";
														errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " +  newLine;
														correctData = false;
													}else {
														correctData = true;
													}
												}else {
													correctData = true;
												}
											} else {
												errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
														+ ".\n No such file available at '" + cell0.getStringCellValue()
														+ "' path";
												correctData = false;
											}
										}else {
											correctData = false;
											errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (j + 1); 
										}
									} else {
										correctData = true;
										answers.setMedia("TEXT");
										answers.setContent(cell0.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
										answers.setRightAnswer(false);
										answersSet.add(answers);
										if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
											boolean checkForDuplicates = checkForDuplicate(answersSet);
											if (checkForDuplicates) {
												errorMsg += "Duplicate data found in answers <br>";
												errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
												correctData = false;
											}
										}
									}

								} else if (cell0.getCellType() == CELL_TYPE_NUMERIC) {
									ansW++;
									correctData = true;
									answers.setMedia("TEXT");
									answers.setContent(cell0.getNumericCellValue() + "");
									answers.setRightAnswer(false);
									answersSet.add(answers);
									if (question.getAnswerType().getAnswerTypeId() == 4 || answersSet.size() == 4) {
										boolean checkForDuplicates = checkForDuplicate(answersSet);
										if (checkForDuplicates) {
													errorMsg += "Duplicate data found in answers <br>";
													errorMsg += "Wrong data at Row " + (i + 1) + ", check for duplicates in the answers " + newLine;
													correctData = false;
										}
									}
								} else {
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
								}
							} else {
								correctData = true;
							}
							break;
							
						case 12: // Time in seconds
							if (cell0 != null) {
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Time(in seconds) is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

								} else {

									question.setTime((int) cell0.getNumericCellValue());
									correctData = true;
								}
							}else {
								errorMsg += "Time is Mandatory at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
								correctData = false;
							}
							break;
							
						case 13: //Difficulty Level
							if (cell0 != null) {
								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Difficulty level is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
								} else if (cell0.getCellType() == CELL_TYPE_STRING) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

								} else {
									questionGroup.setLevel(((int) cell0.getNumericCellValue()));
									correctData = true;
								}
							}else {
								errorMsg += "Difficulty level is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
										+ newLine;
								correctData = false;
							}
							break;
							
						case 14: // Question Reference (Text/ Image/ Audio/ Video)
							
							if (cell0 != null) {

								XSSFCell cell2 = row.getCell(1);

								if (cell2.getStringCellValue().trim().equalsIgnoreCase("Text")) {
									
									QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
									qgml.setQuesUsage("question");
									if(cell0.getStringCellValue() != "") {
										qgml.setMediaURLText(cell0.getStringCellValue());
									}else {
										qgml.setMediaURLText(" ");
									}
									questionGroup.setQuesGroupMediaLinks(qgml);
									correctData = true;
								} else {
									if (cell0.getCellType() != CELL_TYPE_BLANK) {

										if (cell0.getCellType() != CELL_TYPE_STRING) {
											correctData = false;
										} else {
											String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
											
											if(cell2.getStringCellValue().trim().equalsIgnoreCase("Image")) {
												if(ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("jpg")) {
													String mediaUrl = uploadMedia(cell0, file, taskFlag);

													if(mediaUrl != null) {
														QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
														qgml.setQuesUsage("question");
														qgml.setMediaURLText(mediaUrl);
														questionGroup.setQuesGroupMediaLinks(qgml);
														
														correctData = true;
													}else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) 
																+ ".\n No such file available at '" + cell0.getStringCellValue() + "' path";
														correctData = false;
													}
												}else {
												correctData = false;
												errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (j + 1); 
												}	
											}
											else if(cell2.getStringCellValue().trim().equalsIgnoreCase("Audio")) {
												if(ext.equalsIgnoreCase("mp3") || ext.equalsIgnoreCase("wav")) {
													String mediaUrl = uploadMedia(cell0, file, taskFlag);

													if(mediaUrl != null) {
														QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
														qgml.setQuesUsage("question");
														qgml.setMediaURLText(mediaUrl);
														questionGroup.setQuesGroupMediaLinks(qgml);
														
														correctData = true;
													}else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) 
																+ ".\n No such file available at '" + cell0.getStringCellValue() + "' path";
														correctData = false;
													}
												}else {
												correctData = false;
												errorMsg+= "supported file formats - '.mp3' ,'.wav' at Row " + (i + 1) + " and Column " + (j + 1); 
												}	
												
											}else if(cell2.getStringCellValue().trim().equalsIgnoreCase("Video")) {
												if(ext.equalsIgnoreCase("mp4") || ext.equalsIgnoreCase("mpeg")) {
													String mediaUrl = uploadMedia(cell0, file, taskFlag);

													if(mediaUrl != null) {
														QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
														qgml.setQuesUsage("question");
														qgml.setMediaURLText(mediaUrl);
														questionGroup.setQuesGroupMediaLinks(qgml);
														
														correctData = true;
													}else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) 
																+ ".\n No such file available at '" + cell0.getStringCellValue() + "' path";
														correctData = false;
													}
												}else {
												correctData = false;
												errorMsg+= "supported file formats - '.mpeg' ,'.mp4 ' at Row " + (i + 1) + " and Column " + (j + 1); 
												}	
											}
										}
									} else {
										
										errorMsg += "Question reference is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
												+ newLine;
										correctData = false;
									}
								}
							}else{
								QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
								qgml.setQuesUsage("question");
								qgml.setMediaURLText(" ");
								questionGroup.setQuesGroupMediaLinks(qgml);
								correctData = true;
							}
							break;
							
						case 15: // Contributor's Registered Email Id
							
							if (cell0 != null) {

								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Contributor's registered email id is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
								} else if (cell0.getCellType() != CELL_TYPE_STRING) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

								} else if(cell0.getStringCellValue().trim().isEmpty()){
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
									
								}else{
									User contribUser = userDao.getUserByEmailId(cell0.getStringCellValue());
									if(contribUser != null) {
										questionGroup.setCreatedBy(String.valueOf(contribUser.getUserId()));
									}
//									else {
//										errorMsg += "User not found at Row " + (i + 1) + " and Column " + (j + 1)
//												+ newLine;
//										correctData = false;
//									}
								}
							}else {
								errorMsg += "Contributor's registered email id is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
										+ newLine;
								correctData = false;
							}

							break;
							
						case 16: // Solution (Text only)

							if (cell0 != null) {

								if (cell0.getCellType() == CELL_TYPE_BLANK) {
									errorMsg += "Solution text is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
											+ newLine;
									correctData = false;
								} else if (cell0.getCellType() != CELL_TYPE_STRING) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

								} else if(cell0.getStringCellValue().trim().isEmpty()){
									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;
									
								}else{
									question.setSolType("TEXT");
									question.setSolText(cell0.getStringCellValue());
								}
							}else {
								errorMsg += "Solution text is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
										+ newLine;
								correctData = false;
							}

							break;
							
						case 17: // Solution (Image/ Audio/ Video)

							if (cell0 != null) {
								if (cell0.getCellType() != CELL_TYPE_BLANK) {
	
									if (cell0.getCellType() != CELL_TYPE_STRING) {
										correctData = false;
									} else {
										String ext = FilenameUtils.getExtension(cell0.getStringCellValue().trim());
										
										if(ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("jpg") || 
												ext.equalsIgnoreCase("mp3") || ext.equalsIgnoreCase("wav") || 
												ext.equalsIgnoreCase("mp4") || ext.equalsIgnoreCase("mpeg")) {

											String mediaUrl = uploadMedia(cell0, file, taskFlag);

											if (mediaUrl != null) {
												question.setSolType("IMAGE");
												question.setSolMedia(mediaUrl);
												correctData = true;
											} else {
												errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
														+ ".\n No such file available at '" + cell0.getStringCellValue()
														+ "' path";
												correctData = false;
											}
										}else {
											correctData = false;
											errorMsg+= "Wrong data at Row " + (i + 1) + " and Column " + (j + 1)
													+ ".\n 1) If solution is in image format, supported file formats - '.png' , '.jpeg ' , '.jpg'"
													+ ".\n 2) If solution is in audio format, supported file formats - '.mp3' , '.wav'"
													+ ".\n 3) If solution is in video format, supported file formats - '.mp4' , '.mpeg'";
										}
									}
								} else {
									correctData = true;
								}
							}else {
								correctData = true;
							}
						
							XSSFCell cellAnsType = row.getCell(2);
//							if(cellAnsType.getStringCellValue().trim().equalsIgnoreCase("Singular Correct Answer") || 
//									cellAnsType.getStringCellValue().trim().equalsIgnoreCase("Image - Singular Correct Answer") || 
//									cellAnsType.getStringCellValue().trim().equalsIgnoreCase("Fill In The Blanks") || 
//									cellAnsType.getStringCellValue().trim().equalsIgnoreCase("Match the Pairs") || 
//									cellAnsType.getStringCellValue().trim().equalsIgnoreCase("Image - Match the Pairs")) {
							if(cellAnsType.getNumericCellValue() == 1 || cellAnsType.getNumericCellValue() == 6 || 
									cellAnsType.getNumericCellValue() == 2 || cellAnsType.getNumericCellValue() == 3 || 
									cellAnsType.getNumericCellValue() == 7) {
								
								if(ansC != 1) {
									correctData = false;
									errorMsg += "Wrong data at Row " + (i + 1) +" Check correct answers. Correct option must be 1";
								}else if(ansW != 3) {
									correctData = false;
									errorMsg += "Wrong data at Row " + (i + 1) +" Check wrong answers. Wrong options must be 3";
								}
							}
							
//							if(cellAnsType.getStringCellValue().trim().equalsIgnoreCase("True or False")) {
							if(cellAnsType.getNumericCellValue() == 4) {
								if(ansC != 1) {
									correctData = false;
									errorMsg += "Wrong data at Row " + (i + 1) + " Check correct answers. Correct option must be 1";
								}else if(ansW != 1) {
									correctData = false;
									errorMsg += "Wrong data at Row " + (i + 1) + " Check wrong answers. Wrong option must be 1";
								}
							}
							
//							if(cellAnsType.getStringCellValue().trim().equalsIgnoreCase("Multiple Correct Answers") || 
//									cellAnsType.getStringCellValue().trim().equalsIgnoreCase("Image - Multiple Correct Answers")) {
							if(cellAnsType.getNumericCellValue() == 5 || cellAnsType.getNumericCellValue() == 8) {
								int ansCount = ansC + ansW ;
								if(ansCount != 4) {
									correctData = false;
									errorMsg += "Wrong data at Row " + (i + 1) + ". Total options must be 4";
								}
							}
							
							break;
							
							
						case 18 :
							if (cell0 != null) {
								if (cell0.getCellType() == CELL_TYPE_BLANK) {

								} else if (cell0.getCellType() != CELL_TYPE_NUMERIC) {

									errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
									correctData = false;

								} else {

									if (cell0.getNumericCellValue() > 0) {
										correctData = true;
										questionGroup.setVarNo(String.valueOf(Math.round(cell0.getNumericCellValue())));

									}else {
										errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
										correctData = false;
									}
								}
							} else {
//								errorMsg += "ion tyQuestpe is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
//										+ newLine;
//								correctData = false;
							}

							break;
//						TODO: case19 : If question group contains multiple question then this case will be in use.	
						case 19: // For group question flag
							XSSFCell cellS_FL = row.getCell(19);
							
							question.setAnswers(answersSet);
							questionVOs.add(question);
							
								if(cellS_FL != null){
									
									
								if (cellS_FL.getNumericCellValue() == 1) {
									i++;
									row = sheet.getRow(i);
									cellS_FL = row.getCell(19);
									
									while (cellS_FL.getNumericCellValue() == 2) {
										i++;
	
										Set<AnswersVO> answersSet1 = new HashSet<>();
										question = new QuestionVO();
										ansC = 0;
										ansW = 0;
										
										for (int k = 0; k < cols; k++) {						
	
											XSSFCell cellG = row.getCell(k);
											
											switch (k) {
											case 0://Sr. No
												if(cellG.getCellType() == CELL_TYPE_STRING) {
													if(cellG.getStringCellValue().equals("****")) {
														EOF = true;
														break;
													}else {
														correctData = false;
													}
												}
												
												if(cellG.getCellType() == CELL_TYPE_NUMERIC) {
													correctData = true;
													break;	
												}
												
											case 1: // question type case mandatory field
	//											if (cellG != null) {
	//												if (cellG.getCellType() == CELL_TYPE_BLANK) {
	//													errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//															+ newLine;
	//													correctData = false;
	//												} else if (cellG.getCellType() != CELL_TYPE_STRING) {
	//
	//													errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
	//													correctData = false;
	//
	//												} else {
	//
	//													if (cellG.getStringCellValue().trim().equalsIgnoreCase("Image")) {
	//														correctData = true;
	//														questionGroup.setType(cellG.getStringCellValue());
	//
	//													} else if (cellG.getStringCellValue().trim().equalsIgnoreCase("Video")) {
	//														correctData = true;
	//														questionGroup.setType(cellG.getStringCellValue());
	//
	//													} else if (cellG.getStringCellValue().trim().equalsIgnoreCase("Audio")) {
	//														correctData = true;
	//														questionGroup.setType(cellG.getStringCellValue());
	//
	//													} else if (cellG.getStringCellValue().trim().equalsIgnoreCase("Text")) {
	//														correctData = true;
	//														questionGroup.setType(cellG.getStringCellValue());
	//
	//													} else {
	//														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
	//														correctData = false;
	//													}
	//												}
	//											} else {
	//												errorMsg += "Question type is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//														+ newLine;
	//												correctData = false;
	//											}
												correctData = true;
												break;
											
											case 2: //Answer type case
												if (cellG != null) {
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
																+ newLine;
														correctData = false;
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
	
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
	
													} else if (cellG.getCellType() != CELL_TYPE_NUMERIC) {
	
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
	
													} else if (cellG.getNumericCellValue() == 1) {
														correctData = true;
														AnswerTypeVO answerType = new AnswerTypeVO();
														answerType.setAnswerTypeId(1);
														question.setAnswerType(answerType);
	
													} else if (cellG.getNumericCellValue() == 2) {
														correctData = true;
														AnswerTypeVO answerType = new AnswerTypeVO();
														answerType.setAnswerTypeId(2);
														question.setAnswerType(answerType);
	
													} else if (cellG.getNumericCellValue() == 3) {
														correctData = true;
														AnswerTypeVO answerType = new AnswerTypeVO();
														answerType.setAnswerTypeId(3);
														question.setAnswerType(answerType);
	
													} else if (cellG.getNumericCellValue() == 4) {
														correctData = true;
														AnswerTypeVO answerType = new AnswerTypeVO();
														answerType.setAnswerTypeId(4);
														question.setAnswerType(answerType);
	
													} else if (cellG.getNumericCellValue() == 5) {
														correctData = true;
														AnswerTypeVO answerType = new AnswerTypeVO();
														answerType.setAnswerTypeId(5);
														question.setAnswerType(answerType);
	
													} else if (cellG.getNumericCellValue() == 6) {
														correctData = true;
														AnswerTypeVO answerType = new AnswerTypeVO();
														answerType.setAnswerTypeId(6);
														question.setAnswerType(answerType);
	
													} else if (cellG.getNumericCellValue() == 7) {
														correctData = true;
														AnswerTypeVO answerType = new AnswerTypeVO();
														answerType.setAnswerTypeId(7);
														question.setAnswerType(answerType);
	
													} else if (cellG.getNumericCellValue() == 8) {
														correctData = true;
														AnswerTypeVO answerType = new AnswerTypeVO();
														answerType.setAnswerTypeId(8);
														question.setAnswerType(answerType);
	
													} else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
													}
												} else {
													errorMsg += "Answer type is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
															+ newLine;
													correctData = false;
												}
	
												break;
											
											case 3: //Topic Number or Main category 
	//											if (cellG != null) {
	//												
	//												cellG.setCellType(Cell.CELL_TYPE_STRING);
	//												
	//												if (cellG.getCellType() == CELL_TYPE_BLANK) {
	//													errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//															+ newLine;
	//													correctData = false;
	//													
	//												}else if(cellG.getCellType() == CELL_TYPE_STRING) {
	//													String topics1[] = cellG.getStringCellValue().replaceAll(" ", "").split(",");
	//													
	//													String[] topics = new HashSet<String>(Arrays.asList(topics1)).toArray(new String[0]);
	//													
	//													TopicVO[] topicVOArray = new TopicVO[topics.length];
	//
	//													for(int a=0; a < topics.length; a++) {
	//														if(topics[a].trim().length() == 1) {
	//															topics[a] = "0".concat(topics[a]);
	//														}
	//														
	//														if(topics[a].trim().length() % 2 == 0) {
	//															Topic topic = topicDao.getTopicByTopicNo(topics[a].trim());
	//															TopicVO topicvo = new TopicVO();
	//															if(topic != null) {
	//																topicvo.setTopicId(topic.getTopicId());
	//																topicVOArray[a] = topicvo;
	//															}
	//														}else {
	//															errorMsg += "Wrong topic number format at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
	//															correctData = false;
	//														}
	//													}
	//													
	//													questionGroup.setTopic(topicVOArray);
	//													
	//												} 
	//											} else {
	//												errorMsg += "Topic number is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//														+ newLine;
	//												correctData = false;
	//											}
												correctData = true;
												break;
												
											case 4://Question(Text Only)
												
												if (cellG != null) {
	
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
																+ newLine;
														correctData = false;
													} else if (cellG.getCellType() != CELL_TYPE_STRING) {
	
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
	
													} else if(cellG.getStringCellValue().trim().isEmpty()){
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
														
													}else{
														question.setContent(cellG.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
													}
												}else {
													errorMsg += "question text is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
															+ newLine;
													correctData = false;
												}
	
												break;
												
											case 5: // Correct answer 1
												if (cellG != null) {
													AnswersVO answers = new AnswersVO();
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
																+ newLine;
														correctData = false;
														
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
														ansC++;
														XSSFCell cell3 = row.getCell(2);
														System.out.println(cell3.getNumericCellValue());
														if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
	
															String ext = FilenameUtils.getExtension(cellG.getStringCellValue().trim());
															if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
																	|| ext.equalsIgnoreCase("jpg")) {
																String mediaUrl = uploadMedia(cellG, file, taskFlag);
	
																if (mediaUrl != null) {
																	answers.setMedia("IMAGE");
																	answers.setContent(mediaUrl);
																	answers.setRightAnswer(true);
																	answersSet1.add(answers);
																	correctData = true;
																} else {
																	errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																			+ ".\n No such file available at '" + cellG.getStringCellValue()
																			+ "' path";
																	correctData = false;
																}
															}else {
																correctData = false;
																errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (k + 1); 
															}
														} else {
															correctData = true;
															answers.setMedia("TEXT");
															answers.setContent(cellG.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
															answers.setRightAnswer(true);
															answersSet1.add(answers);
														}
	
													} else if (cellG.getCellType() == CELL_TYPE_NUMERIC) {
														ansC++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getNumericCellValue() + "");
														answers.setRightAnswer(true);
														answersSet1.add(answers);
														
													} else if (cellG.getCellType() == CELL_TYPE_BOOLEAN) {
														ansC++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getBooleanCellValue() + "");
														answers.setRightAnswer(true);
														answersSet1.add(answers);
														
													} else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
													}
												} else {
													errorMsg += "Correct answer 1 is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
															+ newLine;
													correctData = false;
												}
	
												break;
												
												
											case 6: // Correct Answer 2
												if (cellG != null) {
													AnswersVO answers = new AnswersVO();
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														correctData = true;
													
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
														ansC++;
														XSSFCell cell3 = row.getCell(2);
														if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
															
															String ext = FilenameUtils.getExtension(cellG.getStringCellValue().trim());
															if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
																	|| ext.equalsIgnoreCase("jpg")) {
																String mediaUrl = uploadMedia(cellG, file, taskFlag);
	
																if (mediaUrl != null) {
																	answers.setMedia("IMAGE");
																	answers.setContent(mediaUrl);
																	answers.setRightAnswer(true);
																	answersSet1.add(answers);
																	correctData = true;
																} else {
																	errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																			+ ".\n No such file available at '" + cellG.getStringCellValue()
																			+ "' path";
																	correctData = false;
																}
															}else {
																correctData = false;
																errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (k + 1); 
															}
														} else {
															correctData = true;
															answers.setMedia("TEXT");
															answers.setContent(cellG.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
															answers.setRightAnswer(true);
															answersSet1.add(answers);
														}
	
													} else if (cellG.getCellType() == CELL_TYPE_NUMERIC) {
														ansC++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getNumericCellValue() + "");
														answers.setRightAnswer(true);
														answersSet1.add(answers);
													} else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
													}
												} else {
													correctData = true;
												}
												break;
												
											case 7: // Correct Answer 3
												if (cellG != null) {
													AnswersVO answers = new AnswersVO();
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														correctData = true;
													
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
														ansC++;
														XSSFCell cell3 = row.getCell(2);
														if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
	
															String ext = FilenameUtils.getExtension(cellG.getStringCellValue().trim());
															if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
																	|| ext.equalsIgnoreCase("jpg")) {
																String mediaUrl = uploadMedia(cellG, file, taskFlag);
	
																if (mediaUrl != null) {
																	answers.setMedia("IMAGE");
																	answers.setContent(mediaUrl);
																	answers.setRightAnswer(true);
																	answersSet1.add(answers);
																	correctData = true;
																} else {
																	errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																			+ ".\n No such file available at '" + cellG.getStringCellValue()
																			+ "' path";
																	correctData = false;
																}
															}else {
															correctData = false;
															errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (k + 1); 
														}
														} else {
															correctData = true;
															answers.setMedia("TEXT");
															answers.setContent(cellG.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
															answers.setRightAnswer(true);
															answersSet1.add(answers);
														}
	
													} else if (cellG.getCellType() == CELL_TYPE_NUMERIC) {
														ansC++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getNumericCellValue() + "");
														answers.setRightAnswer(true);
														answersSet1.add(answers);
													} else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
													}
												} else {
													correctData = true;
												}
												break;
												
											case 8: // Correct Answer 4
												if (cellG != null) {
													AnswersVO answers = new AnswersVO();
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														correctData = true;
													
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
														ansC++;
														XSSFCell cell3 = row.getCell(2);
														if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
															
															String ext = FilenameUtils.getExtension(cellG.getStringCellValue().trim());
															if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
																	|| ext.equalsIgnoreCase("jpg")) {
																String mediaUrl = uploadMedia(cellG, file, taskFlag);
	
																if (mediaUrl != null) {
																	answers.setMedia("IMAGE");
																	answers.setContent(mediaUrl);
																	answers.setRightAnswer(true);
																	answersSet1.add(answers);
																	correctData = true;
																} else {
																	errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																			+ ".\n No such file available at '" + cellG.getStringCellValue()
																			+ "' path";
																	correctData = false;
																}
															}else {
															correctData = false;
															errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (k + 1); 
														}
														} else {
															correctData = true;
															answers.setMedia("TEXT");
															answers.setContent(cellG.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
															answers.setRightAnswer(true);
															answersSet1.add(answers);
														}
	
													} else if (cellG.getCellType() == CELL_TYPE_NUMERIC) {
														ansC++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getNumericCellValue() + "");
														answers.setRightAnswer(true);
														answersSet1.add(answers);
													} else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
													}
												} else {
													correctData = true;
												}
												break;
												
											case 9: // Wrong Answer 1 mandatory field
												
												if (cellG != null) {
													AnswersVO answers = new AnswersVO();
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														
														correctData = false;
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
														ansW++;
														XSSFCell cell3 = row.getCell(2);
														if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
															
															String ext = FilenameUtils.getExtension(cellG.getStringCellValue().trim());
															if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
																	|| ext.equalsIgnoreCase("jpg")) {
																String mediaUrl = uploadMedia(cellG, file, taskFlag);
	
																if (mediaUrl != null) {
																	answers.setMedia("IMAGE");
																	answers.setContent(mediaUrl);
																	answers.setRightAnswer(false);
																	answersSet1.add(answers);
																	correctData = true;
																} else {
																	errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																			+ ".\n No such file available at '" + cellG.getStringCellValue()
																			+ "' path";
																	correctData = false;
																}
															}else {
																correctData = false;
																errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (k + 1); 
															}
														} else {
															correctData = true;
															answers.setMedia("TEXT");
															answers.setContent(cellG.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
															answers.setRightAnswer(false);
															answersSet1.add(answers);
														}
													} else if (cellG.getCellType() == CELL_TYPE_NUMERIC) {
														ansW++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getNumericCellValue() + "");
														answers.setRightAnswer(false);
														answersSet1.add(answers);
													} else if (cellG.getCellType() == CELL_TYPE_BOOLEAN) {
														ansW++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getBooleanCellValue() + "");
														answers.setRightAnswer(false);
														answersSet1.add(answers);
	
													} else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
													}
												} else {
													
													XSSFCell cell3 = row.getCell(2);
													if (cell3.getStringCellValue().trim()
															.equalsIgnoreCase("Multiple Correct Answers")
															|| cell3.getStringCellValue().trim()
																	.equalsIgnoreCase("Image - Multiple Correct Answers")) {
														correctData = true;
													}else {
														errorMsg += "Wrong answer 1 is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
																+ newLine;
														correctData = false;
													}
												}
												break;
												
											case 10: // Wrong Answer 2
												
												if (cellG != null) {
													AnswersVO answers = new AnswersVO();
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
	
														correctData = true;
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
														ansW++;
														XSSFCell cell3 = row.getCell(2);
														if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
															
															String ext = FilenameUtils.getExtension(cellG.getStringCellValue().trim());
															if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
																	|| ext.equalsIgnoreCase("jpg")) {
																String mediaUrl = uploadMedia(cellG, file, taskFlag);
	
																if (mediaUrl != null) {
																	answers.setMedia("IMAGE");
																	answers.setContent(mediaUrl);
																	answers.setRightAnswer(false);
																	answersSet1.add(answers);
																	correctData = true;
																} else {
																	errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																			+ ".\n No such file available at '" + cellG.getStringCellValue()
																			+ "' path";
																	correctData = false;
																}
															}else {
																correctData = false;
																errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (k + 1); 
															}
														} else {
															correctData = true;
															answers.setMedia("TEXT");
															answers.setContent(cellG.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
															answers.setRightAnswer(false);
															answersSet1.add(answers);
														}
	
													} else if (cellG.getCellType() == CELL_TYPE_NUMERIC) {
														ansW++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getNumericCellValue() + "");
														answers.setRightAnswer(false);
														answersSet1.add(answers);
													} else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
													}
												} else {
													correctData = true;
												}
												break;
												
											case 11: // Wrong Answer 3
												
												if (cellG != null) {
													AnswersVO answers = new AnswersVO();
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
	
														correctData = true;
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
														ansW++;
														XSSFCell cell3 = row.getCell(2);
														if (cell3.getNumericCellValue() == 6 || cell3.getNumericCellValue() == 7 || cell3.getNumericCellValue() == 8) {
															
															String ext = FilenameUtils.getExtension(cellG.getStringCellValue().trim());
															if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")
																	|| ext.equalsIgnoreCase("jpg")) {
																String mediaUrl = uploadMedia(cellG, file, taskFlag);
	
																if (mediaUrl != null) {
																	answers.setMedia("IMAGE");
																	answers.setContent(mediaUrl);
																	answers.setRightAnswer(false);
																	answersSet1.add(answers);
																	correctData = true;
																} else {
																	errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																			+ ".\n No such file available at '" + cellG.getStringCellValue()
																			+ "' path";
																	correctData = false;
																}
															}else {
																correctData = false;
																errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (k + 1); 
															}
														} else {
															correctData = true;
															answers.setMedia("TEXT");
															answers.setContent(cellG.getStringCellValue().replaceAll("(\\r\\n|\\n)", "<br />"));
															answers.setRightAnswer(false);
															answersSet1.add(answers);
														}
	
													} else if (cellG.getCellType() == CELL_TYPE_NUMERIC) {
														ansW++;
														correctData = true;
														answers.setMedia("TEXT");
														answers.setContent(cellG.getNumericCellValue() + "");
														answers.setRightAnswer(false);
														answersSet1.add(answers);
													} else {
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
													}
												} else {
													correctData = true;
												}
												break;
												
											case 12: // Time in seconds
												if (cellG != null) {
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
																+ newLine;
														correctData = false;
													} else if (cellG.getCellType() == CELL_TYPE_STRING) {
	
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
	
													} else {
	
														question.setTime((int) cellG.getNumericCellValue());
														correctData = true;
													}
												}else {
													errorMsg += "Time is Mandatory at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
													correctData = false;
												}
												break;
												
											case 13: //Difficulty Level
	//											if (cellG != null) {
	//												if (cellG.getCellType() == CELL_TYPE_BLANK) {
	//													errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//															+ newLine;
	//													correctData = false;
	//												} else if (cellG.getCellType() == CELL_TYPE_STRING) {
	//
	//													errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
	//													correctData = false;
	//
	//												} else {
	//													questionGroup.setLevel(((int) cellG.getNumericCellValue()));
	//													correctData = true;
	//												}
	//											}else {
	//												errorMsg += "Difficulty level is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//														+ newLine;
	//												correctData = false;
	//											}
												correctData = true;
												break;
												
											case 14: // Question Reference (Text/ Image/ Audio/ Video)
												
	//											if (cellG != null) {
	//
	//												XSSFCell cell2 = row.getCell(1);
	//
	//												if (cell2.getStringCellValue().trim().equalsIgnoreCase("Text")) {
	//													
	//													QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
	//													qgml.setQuesUsage("question");
	//													if(cellG.getStringCellValue() != "") {
	//														qgml.setMediaURLText(cellG.getStringCellValue());
	//													}else {
	//														qgml.setMediaURLText(" ");
	//													}
	//													questionGroup.setQuesGroupMediaLinks(qgml);
	//													correctData = true;
	//												} else {
	//													if (cellG.getCellType() != CELL_TYPE_BLANK) {
	//
	//														if (cellG.getCellType() != CELL_TYPE_STRING) {
	//															correctData = false;
	//														} else {
	//															String ext = FilenameUtils.getExtension(cellG.getStringCellValue());
	//															
	//															if(cell2.getStringCellValue().trim().equalsIgnoreCase("Image")) {
	//																if(ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("jpg")) {
	//																	String mediaUrl = uploadMedia(cellG, file, taskFlag);
	//
	//																	if(mediaUrl != null) {
	//																		QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
	//																		qgml.setQuesUsage("question");
	//																		qgml.setMediaURLText(mediaUrl);
	//																		questionGroup.setQuesGroupMediaLinks(qgml);
	//																		
	//																		correctData = true;
	//																	}else {
	//																		errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) 
	//																				+ ".\n No such file available at '" + cellG.getStringCellValue() + "' path";
	//																		correctData = false;
	//																	}
	//																}else {
	//																correctData = false;
	//																errorMsg+= "supported file formats - '.png' ,'.jpeg ','.jpg' at Row " + (i + 1) + " and Column " + (k + 1); 
	//																}	
	//															}
	//															else if(cell2.getStringCellValue().trim().equalsIgnoreCase("Audio")) {
	//																if(ext.equalsIgnoreCase("mp3") || ext.equalsIgnoreCase("wav")) {
	//																	String mediaUrl = uploadMedia(cellG, file, taskFlag);
	//
	//																	if(mediaUrl != null) {
	//																		QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
	//																		qgml.setQuesUsage("question");
	//																		qgml.setMediaURLText(mediaUrl);
	//																		questionGroup.setQuesGroupMediaLinks(qgml);
	//																		
	//																		correctData = true;
	//																	}else {
	//																		errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) 
	//																				+ ".\n No such file available at '" + cellG.getStringCellValue() + "' path";
	//																		correctData = false;
	//																	}
	//																}else {
	//																correctData = false;
	//																errorMsg+= "supported file formats - '.mp3' ,'.wav' at Row " + (i + 1) + " and Column " + (k + 1); 
	//																}	
	//																
	//															}else if(cell2.getStringCellValue().trim().equalsIgnoreCase("Video")) {
	//																if(ext.equalsIgnoreCase("mp4") || ext.equalsIgnoreCase("mpeg")) {
	//																	String mediaUrl = uploadMedia(cellG, file, taskFlag);
	//
	//																	if(mediaUrl != null) {
	//																		QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
	//																		qgml.setQuesUsage("question");
	//																		qgml.setMediaURLText(mediaUrl);
	//																		questionGroup.setQuesGroupMediaLinks(qgml);
	//																		
	//																		correctData = true;
	//																	}else {
	//																		errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) 
	//																				+ ".\n No such file available at '" + cellG.getStringCellValue() + "' path";
	//																		correctData = false;
	//																	}
	//																}else {
	//																correctData = false;
	//																errorMsg+= "supported file formats - '.mpeg' ,'.mp4 ' at Row " + (i + 1) + " and Column " + (k + 1); 
	//																}	
	//															}
	//														}
	//													} else {
	//														
	//														errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//																+ newLine;
	//														correctData = false;
	//													}
	//												}
	//											}else{
	//												QuesGroupMediaLinksVO qgml = new QuesGroupMediaLinksVO();
	//												qgml.setQuesUsage("question");
	//												qgml.setMediaURLText(" ");
	//												questionGroup.setQuesGroupMediaLinks(qgml);
	//												correctData = true;
	//											}
												correctData = true;
												break;
												
											case 15: // Contributor's Registered Email Id
												
	//											if (cellG != null) {
	//
	//												if (cellG.getCellType() == CELL_TYPE_BLANK) {
	//													errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//															+ newLine;
	//													correctData = false;
	//												} else if (cellG.getCellType() != CELL_TYPE_STRING) {
	//
	//													errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
	//													correctData = false;
	//
	//												} else if(cellG.getStringCellValue().trim().isEmpty()){
	//													errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
	//													correctData = false;
	//													
	//												}else{
	//													User contribUser = userDao.getUserByEmailId(cellG.getStringCellValue());
	//													if(contribUser != null) {
	//														questionGroup.setCreatedBy(String.valueOf(contribUser.getUserId()));
	//													}
	////													else {
	////														errorMsg += "User not found at Row " + (i + 1) + " and Column " + (k + 1)
	////																+ newLine;
	////														correctData = false;
	////													}
	//												}
	//											}else {
	//												errorMsg += "Contributor's registered email id is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
	//														+ newLine;
	//												correctData = false;
	//											}
												correctData = true;
												break;
												
											case 16: // Solution (Text only)
	
												if (cellG != null) {
	
													if (cellG.getCellType() == CELL_TYPE_BLANK) {
														errorMsg += "Field is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
																+ newLine;
														correctData = false;
													} else if (cellG.getCellType() != CELL_TYPE_STRING) {
	
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
	
													} else if(cellG.getStringCellValue().trim().isEmpty()){
														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1) + newLine;
														correctData = false;
														
													}else{
														question.setSolType("TEXT");
														question.setSolText(cellG.getStringCellValue());
													}
												}else {
													errorMsg += "Solution text is Mandatory at Row " + (i + 1) + " and Column " + (k + 1)
															+ newLine;
													correctData = false;
												}
	
												break;
												
											case 17: // Solution (Image/ Audio/ Video)
	
												if (cellG != null) {
													if (cellG.getCellType() != CELL_TYPE_BLANK) {
						
														if (cellG.getCellType() != CELL_TYPE_STRING) {
															correctData = false;
														} else {
															String ext = FilenameUtils.getExtension(cellG.getStringCellValue().trim());
															
															if(ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("jpg") || 
																	ext.equalsIgnoreCase("mp3") || ext.equalsIgnoreCase("wav") || 
																	ext.equalsIgnoreCase("mp4") || ext.equalsIgnoreCase("mpeg")) {
	
																String mediaUrl = uploadMedia(cellG, file, taskFlag);
	
																if (mediaUrl != null) {
																	question.setSolType("IMAGE");
																	question.setSolMedia(mediaUrl);
																	correctData = true;
																} else {
																	errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																			+ ".\n No such file available at '" + cellG.getStringCellValue()
																			+ "' path";
																	correctData = false;  
																}
															}else {
																correctData = false;
																errorMsg+= "Wrong data at Row " + (i + 1) + " and Column " + (k + 1)
																		+ ".\n 1) If solution is in image format, supported file formats - '.png' , '.jpeg ' , '.jpg'"
																		+ ".\n 2) If solution is in audio format, supported file formats - '.mp3' , '.wav'"
																		+ ".\n 3) If solution is in video format, supported file formats - '.mp4' , '.mpeg'";
															}
														}
													} else {
														correctData = true;
													}
												}else {
													correctData = true;
												}
											
												XSSFCell cellAnsTypeG = row.getCell(2);
												if(cellAnsTypeG.getNumericCellValue() == 1 || cellAnsTypeG.getNumericCellValue() == 6 || 
														cellAnsTypeG.getNumericCellValue() == 2 || cellAnsTypeG.getNumericCellValue() == 3 || 
														cellAnsTypeG.getNumericCellValue() == 7) {
													
													if(ansC != 1) {
														correctData = false;
														errorMsg += "Wrong data at Row " + i +" Check correct answers. Correct option must be 1";
													}else if(ansW != 3) {
														correctData = false;
														errorMsg += "Wrong data at Row " + i +" Check wrong answers. Wrong options must be 3";
													}
												}
												
												if(cellAnsTypeG.getNumericCellValue() == 4) {
													if(ansC != 1) {
														correctData = false;
														errorMsg += "Wrong data at Row " + i + " Check correct answers. Correct option must be 1";
													}else if(ansW != 1) {
														correctData = false;
														errorMsg += "Wrong data at Row " + i + " Check wrong answers. Wrong option must be 1";
													}
												}
												
												if(cellAnsTypeG.getNumericCellValue() == 5 || cellAnsTypeG.getNumericCellValue() == 8) {
													int ansCount = ansC + ansW ;
													if(ansCount != 4) {
														correctData = false;
														errorMsg += "Wrong data at Row " + i + ". Total options must be 4";
													}
												}
												
												break;
												
//											case 18 :
//												if (cell0 != null) {
//													if (cell0.getCellType() == CELL_TYPE_BLANK) {
//
//													} else if (cell0.getCellType() != CELL_TYPE_NUMERIC) {
//
//														errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
//														correctData = false;
//
//													} else {
//
//														if (cell0.getStringCellValue().trim() != null) {
//															correctData = true;
//															questionGroup.setVarNo(cell0.getStringCellValue());
//
//														}
//														else {
//															errorMsg += "Wrong data at Row " + (i + 1) + " and Column " + (j + 1) + newLine;
//															correctData = false;
//														}
//													}
//												} else {
////													errorMsg += "ion tyQuestpe is Mandatory at Row " + (i + 1) + " and Column " + (j + 1)
////															+ newLine;
////													correctData = false;
//												}
//
//												break;
												
												
											}
	
											if (!correctData) {
												break;
											}
											if (EOF) {
												break;
											}
										}
										
										question.setAnswers(answersSet1);
										questionVOs.add(question);
										
										row = sheet.getRow(i);
										cellS_FL = row.getCell(19);
										if(cellS_FL == null){
											break;
										}
										
										if (!correctData) {
											break;
										}
										if (EOF) {
											break;
										}
									}
									i--;
								}
							}
							break;
							
						
							
						} //Switch close

						if (!correctData) {
//							System.out.println("\n Excel sheet error @" + new Date() + "\n Message : " + errorMsg + "\n");
							break;
						}
						if (EOF) {
							break;
						}
					
					} //For loop closed for j switch case data columns.
					
					if (!correctData) {
						System.out.println("\n Excel sheet error @" + new Date() + "\n Message : " + errorMsg + "\n");
						break;
					}
					if (EOF)
						break;

//					MediaTypeVO mediaType = new MediaTypeVO();
//					XSSFCell cell1 = ow.getCell(1);
//					
//					if(cell1.getStringCellValue().trim().equalsIgnoreCase("Image")){
//						mediaType.setMediaTypeId(4);
//					}else if(cell1.getStringCellValue().trim().equalsIgnoreCase("Video")){
//						mediaType.setMediaTypeId(3);
//					}else if(cell1.getStringCellValue().trim().equalsIgnoreCase("Audio")){
//						mediaType.setMediaTypeId(2);
//					}else  if(cell1.getStringCellValue().trim().equalsIgnoreCase("Text")){
//						mediaType.setMediaTypeId(1);
//					}
					
					SubjectVO sub = new SubjectVO();
					sub.setSubjectId(1);
					
					questionGroup.setSubject(sub);
					questionGroup.setMediaType(mediaType);
//					question.setAnswers(answersSet);
//					questionVOs.add(question);
					questionGroup.setQuestions(questionVOs);
					questionGroupVOs.add(questionGroup);
					
				} // for loop i closed to fetch all rows values
					
				if (correctData == true) {
					if(taskFlag == 1) {
						JSONObject jsonObject = new JSONObject();
						for(QuestionGroupVO qgVO : questionGroupVOs) {
							jsonObject = questionGroupService.addQuestionGroup(qgVO, user);
						}
						if (jsonObject.getBoolean("done") == true) {
							errorMsg = "Questions uploaded successfully!!!";
							dataReturn.put("done", true);
						}
					}else if(taskFlag == 2){
						errorMsg = "Excel file is in correct format.";
						dataReturn.put("done", true);
					}
				}	
					
				
			} // if correct form
			else {
				dataReturn.put("done", false);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
//		System.out.println("\n " + errorMsg + "\n");
		if(errorMsg.equals("Excel file is in correct format.") || errorMsg.equals("Questions uploaded successfully!!!")) {
			dataReturn.put("done", true);
		}else {
			dataReturn.put("done", false);
		}
		
		dataReturn.put("msg", errorMsg.replace("\n", "<br>"));
		return dataReturn;
	}
	
	private boolean checkForDuplicate(Set<AnswersVO> answersSet) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		String ansArr[] = new String[answersSet.size()];
		int ii = 0; 
		
		for (AnswersVO answersVO : answersSet) {
			ansArr[ii] =  answersVO.getContent();
			ii++;
		}
		
		 for(int i = 0; i < ansArr.length; i++) {  
		        for(int j = i + 1; j < ansArr.length; j++) {  
		            if(ansArr[i] == ansArr[j]) {  
		            	System.out.println(ansArr[j]);
		            flag = true;
		            }
		        }  
		    }  
		
		return flag;
	}

	public String uploadMedia(XSSFCell cell0, MultipartFile file, int taskFlag) {

		JSONObject str = null;
		String mediaUrl = null;
		try {
			
			File url = new File(cell0.getStringCellValue());
			
			FileInputStream input = new FileInputStream(url);
			if(taskFlag == 1) {
				MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "image/jpg",
						IOUtils.toByteArray(input));
				
				str = mediaService.uploadMediaFile(multipartFile);
				
				mediaUrl = str.getString("URL");
			}else {
				
//				File src = new File(cell0.getStringCellValue());
//				File dest = new File(in.ac.coep.constants.Constants.srcURLForSinglePdfResult+url.getName());
//				FileSystemUtils.copyRecursively(src, dest);
				
				mediaUrl = "trial";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mediaUrl;
	}

	@Override
	public JSONObject uploadJavaFile(MultipartFile file, User user, int flag) throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		
//		JSONObject mediaData = new JSONObject();
		
//		mediaData =  mediaService.uploadMediaFile(file);
		
		System.out.println(file.getOriginalFilename());
		
//		if (mediaData.getBoolean("done")) {
//
//			String realPathtoUploads = Constants.mathsFileUplaod;
//
//			MathsFileStorage fileStorage = new MathsFileStorage();
//
//			String filePath = realPathtoUploads + file.getOriginalFilename();
//
//			File dest = new File(filePath);
//
//			file.transferTo(dest);
//
//			fileStorage.setFileExt(FilenameUtils.getExtension(file.getOriginalFilename()));
//
//			String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().length() - 5);
////			String className = "/var/lib/tomcat8/webapps/VirtualMathsLab/WEB-INF/classes/in/ac/coep/maths/"+name; // ubuntu
//			String className = Constants.mathsFileUplaodPackage + name; // window
//			System.out.println(className);
//			fileStorage.setFileName(className);
//
//			fileStorage.setMediaURL(mediaData.getString("URL"));
//
//			Calendar cal = Calendar.getInstance();
//			fileStorage.setCreateDate(cal.getTime());
//			fileStorage.setUpdatedDate(cal.getTime());
//
//			fileStorage.setUser(user);
//
//			utilityServiceDao.save(fileStorage);
//
//			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//
////			compiler.run(null, System.out, System.err, "/var/lib/tomcat8/webapps/VirtualMathsLab/WEB-INF/classes/in/ac/coep/maths/"+ file.getOriginalFilename());
////			Runtime.getRuntime().exec("java " + "/var/lib/tomcat8/webapps/VirtualMathsLab/WEB-INF/classes/in/ac/coep/maths/" + name);
//
//			System.out.println(Constants.mathsFileUplaod + file.getOriginalFilename());
//			compiler.run(null, System.out, System.err, Constants.mathsFileUplaod + file.getOriginalFilename());
//			Runtime.getRuntime().exec("java " + Constants.mathsFileUplaod + name);
//			
//			data.put("done", true);
//			data.put("msg", "File Uploaded Successfully..");
//		}else {
//			
//			data.put("done", false);
//			data.put("msg", "Something went wrong... Please upload file once again.");
//			
//		}
		
		return data;
	}
	
}
