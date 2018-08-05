package com.restaurants.acccounts;



import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="cmfoodchain")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cmfoodchain {

	@XmlElement(name = "branch")
    private List<Branch> branch = null;

   /* @XmlElement(name="Location")
    private String location;*/

  /*  @XmlElementWrapper(name="branch")
    @XmlElement(name="location")
    @XmlElement(name="totalcollection")*/
    
	 /*@XmlElementWrapper(name="branch")
	 @XmlElement(name="location")
	   // private String location;
	    @XmlElement(name="totalcollection")
	    private String totalcollection;
	    @XmlElement(name="locationid")
	    private String locationid;
	    private List<String> remainingChildren;*/
	
	
   /* @XmlElementWrapper(name="branch")
    private List<Branch> branch; */
    /*@XmlElement(name="location")
    private String location;
    @XmlElement(name="totalcollection")
    private String totalcollection;
    @XmlElement(name="locationid")
    private String locationid;
    */
    
    @XmlElementWrapper(name="orders")
    @XmlElement(name="orderdetail")
    private List<Orderdetail> orders;

	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}

	public List<Orderdetail> getOrders() {
		return orders;
	}

	public void setOrders(List<Orderdetail> orders) {
		this.orders = orders;
	}    
    
    

}

