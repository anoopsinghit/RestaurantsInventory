package com.restaurants.acccounts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Orderdetail {

    @XmlElement(name="orderid")
    private String orderid;
    public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	@XmlElement(name="billamount")
    private Float billamount;
	public Float getBillamount() {
		return billamount;
	}
	public void setBillamount(Float billamount) {
		this.billamount = billamount;
	}
   

}
