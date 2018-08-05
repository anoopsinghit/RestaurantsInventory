package com.restaurants.acccounts;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "branch")
@XmlAccessorType (XmlAccessType.FIELD)
public class Branch
{
    private Float totalcollection;
    public Float getTotalcollection() {
		return totalcollection;
	}
	public void setTotalcollection(Float totalcollection) {
		this.totalcollection = totalcollection;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	private String locationid;
    private String location;

     
    //Getters and Setters
}




