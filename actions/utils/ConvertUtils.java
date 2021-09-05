package utils;

import java.io.File;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertUtils {
	
	public static HashMap<String, Object> convertJsonToHashMap(String filePath) {
		ObjectMapper mapper = new ObjectMapper();
        try {
            HashMap<String, Object> result = mapper.readValue(new File(
                    filePath), new TypeReference<HashMap<String, Object>>() {
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't read json file!");
            return null;
        }
    }

}
