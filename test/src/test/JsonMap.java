package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class JsonMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map productMap = new HashMap<Object, Object>();
		productMap.clear();
		Mapss m = new Mapss("name", "1");
		productMap.put("1", new Mapss("hu","12"));
		productMap.put("2", m);
		List list = new ArrayList();
		list.add(productMap);
		//list.add(new Mapss("hu","12"));
		JSONObject js = new JSONObject(productMap);
		JSONObject js1 = new JSONObject(list);
		System.out.println(js1);
	}
	
}
