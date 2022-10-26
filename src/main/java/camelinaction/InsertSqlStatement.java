package camelinaction;

import java.util.Map;
import java.time.*;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InsertSqlStatement  implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		try {
			Map body = exchange.getIn().getBody(Map.class);
		
			int id = (int)body.get("EventID");
			String shipName = (String)body.get("ShipName");

			long TimeStmp = (long)body.get("TimeStmp");
			Instant instant = Instant.ofEpochMilli(TimeStmp);		
			String strTimeStamp = instant.toString().replace("T", " ").replace("Z", "");
	        
			String qry = "INSERT INTO `pinc-analytic.cdc_enav.events` VALUES (" + id + ", '" +  shipName + "', '" + strTimeStamp + "')";
			//System.out.println(qry);		
			
			exchange.getIn().setBody(qry);
			exchange.getIn().setHeader("eventId", id);
			exchange.getIn().setHeader("shipName", shipName);
			exchange.getIn().setHeader("timeStamp", strTimeStamp);
		
		} catch (Throwable e) {
			
			//exchange.setException(e);
		
		}	
		
	}

}
