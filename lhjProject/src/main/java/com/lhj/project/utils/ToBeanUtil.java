package com.lhj.project.utils;

import org.apache.commons.collections.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import org.dom4j.*;

public class ToBeanUtil {
	//xml转map
	public static Map<String, Object> parseXML(String xmlStr) throws DocumentException{
		Map<String, Object> map = new HashMap<String,Object>();
		Document document = DocumentHelper.parseText(xmlStr);
		//获取根节点
		Element root = document.getRootElement();
		List<Element> elements = root.elements();
		if(!elements.isEmpty()){
			if(CollectionUtils.isNotEmpty(elements) && elements.size() >= 2 
					&& "error".equals(elements.get(1).getName())){
				for (Element element : elements) {
					map.put(element.getName(), element.getStringValue());
				}
				return map;
			}
			
			//获得指定的子节点
			Element is_success = root.element("is_success");
			map.put(is_success.getName(), is_success.getStringValue());
			Element request = root.element("request");
			if (null != request && null != request.elements()) {
				List<Element> elements1 = request.elements();
				for (Element element : elements1) {
					List<Attribute> attributes = element.attributes();
					for (Attribute attribute : attributes) {
						map.put(attribute.getValue(), element.getStringValue());
					}
				}
			}
			
			Element response = root.element("response");
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if (null != response) {
				recursiveNode(response,list);
				for (Map<String, Object> map1 : list) {
					map.putAll(map1);
				}
			}
			
			return map;
		}
		return null;
	}
	
	/**
	 * 递归遍历所有的节点获得对应的值
	 * 
	 * @param
	 */
	private static void recursiveNode(Element root,
			List<Map<String, Object>> list) {
		// 遍历根结点的所有孩子节点
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			HashMap<String, Object> map = new HashMap<String, Object>();

			Element element = (Element) iter.next();
			if (element == null)
				continue;
			// 获取属性和它的值
			for (Iterator attrs = element.attributeIterator(); attrs.hasNext();) {
				Attribute attr = (Attribute) attrs.next();
				if (attr == null)
					continue;
				String attrName = attr.getName();
				String attrValue = attr.getValue();

				map.put(attrName, attrValue);
			}
			// 如果有PCDATA，则直接提出
			if (element.isTextOnly()) {
				String innerName = element.getName();
				String innerValue = element.getText();
				map.put(innerName, innerValue);
				list.add(map);
			} else {
				list.add(map);
				// 递归调用
				recursiveNode(element, list);
			}

		}
	}
	
	
	/**  
     * 将一个 Map 对象转化为一个 JavaBean  
     * @param type 要转化的类型  
     * @param map 包含属性值的 map  
     * @return 转化出来的 JavaBean 对象  
     * @throws IntrospectionException 如果分析类属性失败  
     * @throws IllegalAccessException 如果实例化 JavaBean 失败  
     * @throws InstantiationException 如果实例化 JavaBean 失败  
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败  
     */    
    @SuppressWarnings("rawtypes")    
    public static Object convertMap(Class type, Map map)    
            throws IntrospectionException, IllegalAccessException,    
            InstantiationException, InvocationTargetException {    
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性    
        Object obj = type.newInstance(); // 创建 JavaBean 对象    
    
        // 给 JavaBean 对象的属性赋值    
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();    
        for (int i = 0; i< propertyDescriptors.length; i++) {    
            PropertyDescriptor descriptor = propertyDescriptors[i];    
            String propertyName = descriptor.getName();    
            if (map.containsKey(propertyName)) {    
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。    
                Object value = map.get(propertyName);    
                Object[] args = new Object[1];    
                args[0] = value;    
    
                descriptor.getWriteMethod().invoke(obj, args);    
            }    
        }    
        return obj;    
    }

	/**
	 * Map转成实体对象
	 * @param map map实体对象包含属性
	 * @param clazz 实体对象类型
	 * @return
	 */
	public static Object map2Object(Map<String, String> map, Class<?> clazz) {
		if (map == null) {
			return null;
		}
		Object obj = null;
		try {
			obj = clazz.newInstance();

			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
