package com.sinodata.evaluate.net;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

public class JsonFactory {
	private static JsonFactory factory = null;

	public static JsonFactory getJsonFactory() {
		if (factory == null) {
			factory = new JsonFactory();
		}
		return factory;
	}


	/**
	 * 新用户注册
	 * 
	 * @param content
	 * @return
	 */
	public HashMap<String, Object> paserRegist(String content) {

//		HashMap<String, Object> map = new HashMap<String, Object>();
//		JSONObject jsonObject = JSONObject.parseObject(content);
//		boolean success = jsonObject.getBoolean("success");
//
//		map.put("success", success);
//		map.put("msg", jsonObject.getString("msg"));
//		if (success) {
//
//		}
//		return map;
		return null;
	}

	/**
	 * 解析用户信息
	 * 
	 * @param content
	 * @return
	 */
	public HashMap<String, Object> pasergetUserInfo(String content) {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		JSONObject obj = JSONObject.parseObject(content);
//		map.put("success", obj.getBoolean("success"));
//		map.put("msg", obj.getString("msg"));
//		if (!((boolean) obj.getBoolean("success"))) {
//			return map;
//		}
//		ArrayList<ProductBean> beanList = new ArrayList<ProductBean>();
//		JSONArray array = obj.getJSONArray("rows");
//		for (int k = 0; k < array.size(); k++) {
//			JSONObject jsobj = array.getJSONObject(k);
//			beanList.add(new ProductBean(jsobj.getString("pro_code"), jsobj.getString("pro_name"), jsobj.getString("pro_ea_s"), jsobj.getString("pro_ea_m"),
//					jsobj.getString("pro_days"), jsobj.getString("pro_buy_s"), jsobj.getString("pro_buy_m"), jsobj.getString("pro_buy_count"), jsobj
//							.getString("pro_ea_t"), jsobj.getString("pro_amt_avg"), jsobj.getString("pro_amt_ins"), jsobj.getString("pro_amt_quit"), jsobj
//							.getString("pro_type_quit"), jsobj.getString("pro_buy_amt")));
//		}
//		map.put("list", beanList);
//		return map;
		return null;
	}

	/**
	 * 用户注销
	 * 
	 * @param content
	 * @return
	 */
	public HashMap<String, Object> paserQuitOut(String content) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(content);
		map.put("success", obj.getBoolean("success"));
		map.put("msg", obj.getString("msg"));
		if (!((boolean) obj.getBoolean("success"))) {
			return map;
		}
		return map;
	}
	/**
	 * 激活验证
	 * @param content
	 * @return
	 */
	public HashMap<String, Object> paserLicense(String content) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(content);
		map.put("success", obj.getBoolean("success"));
		if (!((boolean) obj.getBoolean("success"))) {
			return map;
		}
		return map;
	}
	
	/**
	 * 检测版本更新
	 * @param content
	 * @return
	 */
	public HashMap<String, Object> paserVersionInfo(String content) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(content);
		map.put("result", obj.getString("result"));
		if (!(obj.getString("result")).equals("000000")) {
			return map;
		}else{
			map.put("version", obj.getString("version"));
		}
		return map;
	}
	
}