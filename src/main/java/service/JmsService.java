package service;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class JmsService {
	
	@Inject
	JmsOperations jmsOperations;
	
	public void sendMessage(final String message){
		jmsOperations.send(new MessageCreator() {
				public Message createMessage(Session session)
				throws JMSException {
					return session.createObjectMessage(message);
				}
			}
		);
		System.out.println("Message sent:"+message);
	}
}
