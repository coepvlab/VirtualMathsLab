package in.ac.coep.utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonMerger {

    public static void main(String[] args) {
    	JSONArray jsonArray = new JSONArray();
    	JSONObject ab = new JSONObject();
    	JSONArray arr = new JSONArray();

    	JSONArray arr1 = new JSONArray();
    	JSONArray arr2 = new JSONArray();
    	JSONArray arr3 = new JSONArray();
    	JSONArray arr31 = new JSONArray();
    	
    	arr.put("in.ac.coep.entity.QuestionGroup@5b6cf012");
    	arr.put("in.ac.coep.entity.QuestionGroup@5b6cf013");
    	arr.put("in.ac.coep.entity.QuestionGroup@5b6cf014");
    	arr.put("in.ac.coep.entity.QuestionGroup@5b6cf015");   	
    	
    	ab.put("level1", arr);
    	
    	
    	arr1.put("in.ac.coep.entity.QuestionGroup@5b6cf016");
    	arr1.put("in.ac.coep.entity.QuestionGroup@5b6cf017");
    	arr1.put("in.ac.coep.entity.QuestionGroup@5b6cf018");
    	arr1.put("in.ac.coep.entity.QuestionGroup@5b6cf019");   	
    	
    	ab.put("level2", arr1);
    	
    	arr2.put("in.ac.coep.entity.QuestionGroup@5b6cf0110");
    	arr2.put("in.ac.coep.entity.QuestionGroup@5b6cf0113");
    	arr2.put("in.ac.coep.entity.QuestionGroup@5b6cf0114");
    	arr2.put("in.ac.coep.entity.QuestionGroup@5b6cf0115");   	
    	
    	ab.put("level3", arr2);
    	
    	arr3.put("in.ac.coep.entity.QuestionGroup@5b6cf0121");
    	arr3.put("in.ac.coep.entity.QuestionGroup@5b6cf0131");
    	arr3.put("in.ac.coep.entity.QuestionGroup@5b6cf0141");
    	arr3.put("in.ac.coep.entity.QuestionGroup@5b6cf0151");   	
    	
    	ab.put("level4", arr3);
    	
    	jsonArray.put(ab);
    	
//    	jsonArray.put("[{"level1":["in.ac.coep.entity.QuestionGroup@5b6cf012","in.ac.coep.entity.QuestionGroup@39fc8891","in.ac.coep.entity.QuestionGroup@16997df7","in.ac.coep.entity.QuestionGroup@4e640f1","in.ac.coep.entity.QuestionGroup@78eb12e3","in.ac.coep.entity.QuestionGroup@c591683","in.ac.coep.entity.QuestionGroup@4c34ce","in.ac.coep.entity.QuestionGroup@c4f44a0"]},{"level2":["in.ac.coep.entity.QuestionGroup@3c46614d","in.ac.coep.entity.QuestionGroup@11a288c6","in.ac.coep.entity.QuestionGroup@13d5c006","in.ac.coep.entity.QuestionGroup@52b02239","in.ac.coep.entity.QuestionGroup@50a9d46b","in.ac.coep.entity.QuestionGroup@16ed954d","in.ac.coep.entity.QuestionGroup@3669a2e6","in.ac.coep.entity.QuestionGroup@2e6383d3","in.ac.coep.entity.QuestionGroup@4c6357e0","in.ac.coep.entity.QuestionGroup@3f753abb"]},{"level3":["in.ac.coep.entity.QuestionGroup@4e630564","in.ac.coep.entity.QuestionGroup@432c4fdb","in.ac.coep.entity.QuestionGroup@3a960001","in.ac.coep.entity.QuestionGroup@26b27b8c"]},{"level4":["in.ac.coep.entity.QuestionGroup@6673290c","in.ac.coep.entity.QuestionGroup@191540b5","in.ac.coep.entity.QuestionGroup@505a13da","in.ac.coep.entity.QuestionGroup@674916d6","in.ac.coep.entity.QuestionGroup@659be85f","in.ac.coep.entity.QuestionGroup@2a71b96","in.ac.coep.entity.QuestionGroup@1e3e7170"]},{"level1":[]},{"level2":["in.ac.coep.entity.QuestionGroup@7a502416"]},{"level3":[]},{"level4":[]},{"level1":["in.ac.coep.entity.QuestionGroup@4c0c9a9a","in.ac.coep.entity.QuestionGroup@1ed7b7e","in.ac.coep.entity.QuestionGroup@19b3903a","in.ac.coep.entity.QuestionGroup@da2ed8c","in.ac.coep.entity.QuestionGroup@31e85bd1"]},{"level2":["in.ac.coep.entity.QuestionGroup@6e7b2443","in.ac.coep.entity.QuestionGroup@359899b4","in.ac.coep.entity.QuestionGroup@419d4c15","in.ac.coep.entity.QuestionGroup@33c4c09b","in.ac.coep.entity.QuestionGroup@615dbed7","in.ac.coep.entity.QuestionGroup@6320c27d","in.ac.coep.entity.QuestionGroup@7e7271d1"]},{"level3":["in.ac.coep.entity.QuestionGroup@365a9a23","in.ac.coep.entity.QuestionGroup@1b516bce","in.ac.coep.entity.QuestionGroup@1916fff2","in.ac.coep.entity.QuestionGroup@12092db5","in.ac.coep.entity.QuestionGroup@ae4123a","in.ac.coep.entity.QuestionGroup@101fbef7","in.ac.coep.entity.QuestionGroup@50957f34"]},{"level4":[]},{"level1":["in.ac.coep.entity.QuestionGroup@491cf4cc","in.ac.coep.entity.QuestionGroup@4a454689","in.ac.coep.entity.QuestionGroup@6907c390","in.ac.coep.entity.QuestionGroup@7c8bb22d","in.ac.coep.entity.QuestionGroup@66925b1","in.ac.coep.entity.QuestionGroup@2b061e03","in.ac.coep.entity.QuestionGroup@7cc46519","in.ac.coep.entity.QuestionGroup@56f5b924","in.ac.coep.entity.QuestionGroup@15aec920","in.ac.coep.entity.QuestionGroup@52c9a4f5","in.ac.coep.entity.QuestionGroup@42b68420"]},{"level2":[]},{"level3":["in.ac.coep.entity.QuestionGroup@13880d6f"]},{"level4":[]}]");
    	JSONObject dt = new JSONObject();
    	dt.put("ChapNo", 1);
    	dt.put("topicList", jsonArray);
    	arr31.put(dt);
    	
    	JSONArray jsonArray1 = new JSONArray();
    	JSONObject ab1 = new JSONObject();
    	JSONArray arr01 = new JSONArray();
    	JSONObject dt1 = new JSONObject();
    	JSONArray arr12 = new JSONArray();
    	JSONArray arr22 = new JSONArray();
    	JSONArray arr32 = new JSONArray();
    	arr01.put("in.ac.coep.entity.QuestionGroup@5b6cf022");
    	arr01.put("in.ac.coep.entity.QuestionGroup@5b6cf023");
    	arr01.put("in.ac.coep.entity.QuestionGroup@5b6cf024");
    	arr01.put("in.ac.coep.entity.QuestionGroup@5b6cf025");   	
    	
    	ab1.put("level1", arr01);
    	
    	
    	arr12.put("in.ac.coep.entity.QuestionGroup@5b6cf026");
    	arr12.put("in.ac.coep.entity.QuestionGroup@5b6cf027");
    	arr12.put("in.ac.coep.entity.QuestionGroup@5b6cf028");
    	arr12.put("in.ac.coep.entity.QuestionGroup@5b6cf029");   	
    	
    	ab1.put("level2", arr12);
    	
    	arr22.put("in.ac.coep.entity.QuestionGroup@5b6cf0210");
    	arr22.put("in.ac.coep.entity.QuestionGroup@5b6cf0213");
    	arr22.put("in.ac.coep.entity.QuestionGroup@5b6cf0214");
    	arr22.put("in.ac.coep.entity.QuestionGroup@5b6cf0215");   	
    	
    	ab1.put("level3", arr22);
    	
    	arr32.put("in.ac.coep.entity.QuestionGroup@5b6cf0221");
    	arr32.put("in.ac.coep.entity.QuestionGroup@5b6cf0231");
    	arr32.put("in.ac.coep.entity.QuestionGroup@5b6cf0241");
    	arr32.put("in.ac.coep.entity.QuestionGroup@5b6cf0251");   	
    	
    	ab1.put("level4", arr32);
    	
    	jsonArray.put(ab1);
    	
    	dt1.put("ChapNo", 2);
    	dt1.put("topicList", jsonArray);
    	arr31.put(dt1);
    	System.out.println(arr31.toString());
    	
    	for(int i=0; i < arr31.length(); i++) { 
	        JSONObject mergedResult = mergeJsonArray(arr31.getJSONObject(i).getJSONArray("topicList"));
	        System.out.println(i+ "  -- " + mergedResult.toString());  // Pretty print with 2-space indentation
    	}
    }

    public static JSONObject mergeJsonArray(JSONArray jsonArray) {
        JSONObject result = new JSONObject();
        Map<String, JSONObject> tempMap = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            for (String key : jsonObject.keySet()) {
                if (!result.has(key)) {
                    result.put(key, jsonObject.get(key));
                } else {
                    Object value = result.get(key);
                    Object newValue = jsonObject.get(key);

                    if (value instanceof JSONObject && newValue instanceof JSONObject) {
                        result.put(key, mergeJsonObjects((JSONObject) value, (JSONObject) newValue));
                    } else if (value instanceof JSONArray && newValue instanceof JSONArray) {
                        result.put(key, mergeJsonArrays((JSONArray) value, (JSONArray) newValue));
                    } else {
                        // Handle other types as needed, here we simply override with the new value
                        result.put(key, newValue);
                    }
                }
            }
        }

        return result;
    }

    private static JSONObject mergeJsonObjects(JSONObject json1, JSONObject json2) {
        JSONObject merged = new JSONObject(json1.toString()); // Make a copy of the first JSON object

        for (String key : json2.keySet()) {
            Object value1 = merged.has(key) ? merged.get(key) : null;
            Object value2 = json2.get(key);

            if (value1 instanceof JSONObject && value2 instanceof JSONObject) {
                merged.put(key, mergeJsonObjects((JSONObject) value1, (JSONObject) value2));
            } else if (value1 instanceof JSONArray && value2 instanceof JSONArray) {
                merged.put(key, mergeJsonArrays((JSONArray) value1, (JSONArray) value2));
            } else {
                merged.put(key, value2);  // Override the value
            }
        }

        return merged;
    }

    private static JSONArray mergeJsonArrays(JSONArray array1, JSONArray array2) {
        JSONArray merged = new JSONArray();

        for (int i = 0; i < array1.length(); i++) {
            merged.put(array1.get(i));
        }

        for (int i = 0; i < array2.length(); i++) {
            merged.put(array2.get(i));
        }

        return merged;
    }
}
