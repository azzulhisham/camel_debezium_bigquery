package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MarshelProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		try {
			String body = exchange.getIn().getBody(String.class);
		
			//System.out.println(body);			
		
		} catch (Throwable e) {
			
			//exchange.setException(e);
		
		}		
	}
}
