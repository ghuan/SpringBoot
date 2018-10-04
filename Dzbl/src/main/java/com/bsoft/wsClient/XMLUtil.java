package com.bsoft.wsClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class XMLUtil {
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
		for (Map<String, Object> map : list) {
			Element sub = el.addElement(QName);
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
