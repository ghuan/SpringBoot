package com.bsoft.wsClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QysjwsUtil {
		protected static Logger logger = LoggerFactory.getLogger(QysjwsUtil.class);
//		private static String uid = "HZSJGQPBJDSQWSFWZX003";
//		private static String pwd = "27e9fe3d530c28be79f30f1e7ff84efc";
//		/**
//		 * 报文头
//		 */
//		public static Map<String, Object>   getCsHeaderInfo() {
//			Map<String,Object> result = new LinkedHashMap<String, Object>();
//			Map<String, Object> paramsMap = new LinkedHashMap<String, Object>();
//			paramsMap.put("PlatformID","ca36ba7989ec3875fc5069443b45abcb");
//			paramsMap.put("MedicalOrgCode", "医院收货信息");
//			paramsMap.put("RegisteredDoctorCode", "1.0.0");
//			paramsMap.put("RegisteredDoctorCardID", "0140d1d29671a343395b6472c02a0bb2");
//			paramsMap.put("RegisteredYear", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//			paramsMap.put("StartDate", "1.0.0");
//			paramsMap.put("EndDate", "1.0.0");
//			paramsMap.put("RegisteredState", "1.0.0");
//			paramsMap.put("PageIndex", "1.0.0");
//			paramsMap.put("PageSize", "1.0.0");
//			result.put("Root", paramsMap);
//			return result;
//		}
		
		/**
		 * 	医院下载收货信息
		 * @throws HdyyElectronicServiceException_Exception 
		 */
//		public static Map<String, Object>  doRequest() throws HdyyElectronicServiceException_Exception {
//			HdyyElectronicServicePortType ws = new HdyyElectronicService().getHdyyElectronicServiceHttpSoap11Endpoint();
//			String xml = ws.doRequest(uid, pwd, "YSFW.HIS.DOWNLOAD.RECEIVE.INFOR");
//			if("null".equals(xml+"")){
//				return null;
//			}else {
//				return XMLUtil.xmlParseMap(xml);
//			}
//		}
		
		/**
		 * 	請求
		 * @throws HdyyElectronicServiceException_Exception 
		 */
		public static Map<String, Object>  doRequestWithXml(Map<String,Object> xmlParams) {
			CommonInterface cif = new CommonInterface();
			String requestXml = "";
			if(xmlParams != null && !xmlParams.isEmpty()){
				Map<String,Object> requestMap = null ;
//				= QysjwsUtil.getCsHeaderInfo();
//				requestMap.put("Root", xmlParams);
				requestXml = mapParseXML(xmlParams, "Root");
			}
			String xml = cif.getCommonInterfaceSoap().executeMethod("CXQY", requestXml);
			if((xml+"").equals("")){
				return null;
			}else {
				int qysjl = 0;
				Map<String, Object> qysj = new HashMap<String, Object>();
				try {
					qysjl = Integer.parseInt(xml);
				} catch (Exception e) {
					return XMLUtil.xmlParseMap(xml);
				}
				qysj.put("qysj", qysjl);
				return qysj;
			}
		}
		
		/**
		 * map解析成xml格式字符串
		 */
		public static String mapParseXML(Map<String, Object> data, String head) {
			Document doc = DocumentHelper.createDocument();
			Element e = doc.addElement(head);
			mapToElement(data, e);
			return doc.asXML();
		}

		private static void listToElement(String QName,
				List<Map<String, Object>> list, Element el) {
			Element sub = el.addElement(QName);
			for (Map<String, Object> map : list) {
				mapToElement(map, sub);
			}
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static void mapToElement(Map<String, Object> data, Element el) {
			for (String key : data.keySet()) {
				Object value = data.get(key);
				if (value == null) {
					continue;
				}
				if (value instanceof List) {
					listToElement(key, (List) value, el);
				} else if (value instanceof Map) {
					Element sub = el.addElement(key);
					mapToElement((Map) value, sub);
				} else {
					Element sub = el.addElement(key);
					sub.setText(String.valueOf(data.get(key)));
				}
			}
		}

		/**
		 * xml字符串解析成map集合
		 * 
		 * @param s
		 * @return
		 */
		public static Map<String, Object> xmlParseMap(String s) {
			Element root = null;
			try {
				Document doc = DocumentHelper.parseText(s);
				root = doc.getRootElement();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			return getText(root, new HashMap<String, Object>());

		}

		private static Map<String, Object> getText(Element e,
				Map<String, Object> map) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			map.put(e.getName(), list);
			Iterator<Element> it = e.elementIterator();
			while (it.hasNext()) {
				Map<String, Object> m = new HashMap<String, Object>();
				Element el = it.next();
				if (el.elements().size() != 0) {
					m = getText(el, m);
				} else {
					m.put(el.getName(), el.getText());
				}
				list.add(m);
			}
			return map;
		}
		
		public static Map<String,Object> xml2map(String xml) throws DocumentException{
			Document doc = DocumentHelper.parseText(xml);
			Element element = doc.getRootElement();
			return (Map<String, Object>) element2map(element);
		}

		public static Object element2map(Element element) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<Element> elements = element.elements();
			if (elements.size() == 0) {
				map.put(element.getName(), element.getText());
				if (!element.isRootElement()) {
					return element.getText();
				}
			} else if (elements.size() == 1) {
				map.put(elements.get(0).getName(), element2map(elements.get(0)));
			} else if (elements.size() > 1) {
				// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
				// 构造一个map用来去重
				Map<String, Element> tempMap = new HashMap<String, Element>();
				for (Element ele : elements) {
					tempMap.put(ele.getName(), ele);
				}
				Set<String> keySet = tempMap.keySet();
				for (String string : keySet) {
					Namespace namespace = tempMap.get(string).getNamespace();
					List<Element> elements2 = element.elements(new QName(string, namespace));
					// 如果同名的数目大于1则表示要构建list
					if (elements2.size() > 1) {
						List<Object> list = new ArrayList<Object>();
						for (Element ele : elements2) {
							list.add(element2map(ele));
						}
						map.put(string, list);
					} else {
						// 同名的数量不大于1则直接递归去
						map.put(string, element2map(elements2.get(0)));
					}
				}
			}

			return map;
		}


}
