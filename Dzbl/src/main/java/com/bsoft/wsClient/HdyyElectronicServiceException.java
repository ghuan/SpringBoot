package com.bsoft.wsClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="HdyyElectronicServiceException" type="{http://service.eastpharm.com.cn}Exception" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "hdyyElectronicServiceException" })
@XmlRootElement(name = "HdyyElectronicServiceException")
public class HdyyElectronicServiceException {

	@XmlElementRef(name = "HdyyElectronicServiceException", namespace = "http://service.eastpharm.com.cn", type = JAXBElement.class)
	protected JAXBElement<Exception> hdyyElectronicServiceException;

	/**
	 * Gets the value of the hdyyElectronicServiceException property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link Exception }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<Exception> getHdyyElectronicServiceException() {
		return hdyyElectronicServiceException;
	}

	/**
	 * Sets the value of the hdyyElectronicServiceException property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}
	 *            {@link Exception }{@code >}
	 * 
	 */
	public void setHdyyElectronicServiceException(JAXBElement<Exception> value) {
		this.hdyyElectronicServiceException = ((JAXBElement<Exception>) value);
	}

}
