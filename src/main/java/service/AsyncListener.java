package service;

import org.springframework.stereotype.Service;
import org.springframework.jms.annotation.JmsListener;

@Service
public class AsyncListener {
	  
	  @JmsListener(destination="messageDestination",containerFactory = "jmsListenerContainerFactory")
	  public void processMessage(String text) {
	    System.out.println("Received: " + text);
	  }
}
