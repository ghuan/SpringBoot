package com.bsoft.wsClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="ExecuteMethodResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "executeMethodResult" })
@XmlRootElement(name = "ExecuteMethodResponse")
public class ExecuteMethodResponse {

	@XmlElement(name = "ExecuteMethodResult")
	protected String executeMethodResult;

	/**
	 * Gets the value of the executeMethodResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getExecuteMethodResult() {
		return executeMethodResult;
	}

	/**
	 * Sets the value of the executeMethodResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setExecuteMethodResult(String value) {
		this.executeMethodResult = value;
	}

}
