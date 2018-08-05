package com.restaurants.acccounts;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *This is scheduler file to use the process all raestiuarnats file at given time
 *
 */
@Component
public class ScheduledFolder {
private static final Logger logger = LoggerFactory.getLogger(ScheduledFolder.class);
private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
private static final int PRETTY_PRINT_INDENT_FACTOR = 0;

	@Scheduled(cron="59 23 * * * ?")
	public void scheduledMidNightfile() {
	logger.info("Checking teh file from the match and mismatch location :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	Map map=parsePropMapping("fileLocation.txt");
	
	//read all raestaurant mainssheet at teh folder location
	String location=(String) map.get("restaurantMainSheet");	
	
	File folderLocation = new File(location);    
    File[] litsRestaurant = folderLocation.listFiles();
     
    for (File file : litsRestaurant) 
    {
    	try {
	//		parsingReceivedOrder(file.getPath());
    		parsingReceivedOrder(file.getPath(), (String)map.get("mismatch"), (String)map.get("match"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	
	
	System.out.println("going to pick up teh fiels");
	}

/**
 * This method is used to load all the properties file value of match,mismatch and original count location
 * @return
 */
		public static Map<String, String> parsePropMapping(String path){
			//String dynamicPath=path + File.separator +"./fileLocation.txt";
			//String dynamicPath="./fileLocation.txt";
			Map<String, String> mapFoldercase=new TreeMap<>();
			try {
				Stream<String> tcStream = Files.lines(Paths.get(ClassLoader.getSystemResource(path).toURI()));
			
				tcStream.filter(tcmapStream ->tcmapStream.contains("=")).map(tcmapStream ->tcmapStream.split("="))
				.distinct().forEach(strings->mapFoldercase.put(strings[0],strings[1]));
				tcStream.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	}
			
			
			 return mapFoldercase;
			 
		 }
		
		
	
	/**
	 * @throws JAXBException
	 */
	/**
	 * @param file
	 * @param Mismatch
	 * @param match
	 * @throws JAXBException
	 */
	private static void parsingReceivedOrder(String file,String Mismatch,String match) throws JAXBException
	{
		Float totalacollection =new Float(0.0);
		Float addedbillamount=new Float(0.0);
		
	    JAXBContext jaxbContext = JAXBContext.newInstance(Cmfoodchain.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	  
	    Cmfoodchain ods = (Cmfoodchain) jaxbUnmarshaller.unmarshal( new File(file) );
	     
	    for(Orderdetail order : ods.getOrders())
	    {
	    	totalacollection=totalacollection+order.getBillamount();
	        System.out.println(order.getBillamount());
	        System.out.println(order.getOrderid());
	    }
	    for(Branch brc : ods.getBranch())
	    {
	        System.out.println(brc.getLocation());
	        addedbillamount=brc.getTotalcollection();
	    }
	    
	    if(totalacollection.equals(addedbillamount)) {
	    	System.out.println("amount is matched");
	    	//do nothing
	    } else {
	    	try {
	    		System.out.println("amount is mismatched");
	    		jSonObject(file,Mismatch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    System.out.println("totalacollection=>"+totalacollection+"===addedbillamount"+addedbillamount);
	}
	
	
	
	/**
	 * convert to hso  file
	 * @param mismathFile
	 * @param mismatch
	 */
	public static void jSonObject(String mismathFile,String mismatch){
	
	    try {
	    	String xmlString = new String(Files.readAllBytes(Paths.get(mismathFile)));
	    	JSONObject xmlJSONObj = XML.toJSONObject(xmlString);	    	 
	    	
	    	 String fileCreation=mismatch+"\\Mistmatch"+System.currentTimeMillis()+".json";
	    	try (FileWriter fileWriter = new FileWriter(fileCreation)){
	    		String billContent=xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
	    		System.out.println("==>"+billContent);
	    		fileWriter.write(billContent);
	    }

	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	  }

public static void main(String[] args) {
	jSonObject("", "");
	//parseXml("match/fileLocation.txt");
	try {
		jSonObject("C:/Users/eanrsih/exercise/eclipse-workspace/RestaurantsInventory/src/main/resources/mainBalnace/a.xml", "C:\\Users\\eanrsih\\exercise\\eclipse-workspace\\RestaurantsInventory\\src\\main\\resources\\mismatch");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
