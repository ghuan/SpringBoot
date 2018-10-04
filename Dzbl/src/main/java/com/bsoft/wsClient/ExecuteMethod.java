package com.bsoft.wsClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="methodName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "methodName", "xml" })
@XmlRootElement(name = "ExecuteMethod")
public class ExecuteMethod {

	protected String methodName;
	protected String xml;

	/**
	 * Gets the value of the methodName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Sets the value of the methodName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMethodName(String value) {
		this.methodName = value;
	}

	/**
	 * Gets the value of the xml property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * Sets the value of the xml property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXml(String value) {
		this.xml = value;
	}

}
