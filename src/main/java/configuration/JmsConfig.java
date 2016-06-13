package configuration;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() throws NamingException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	    connectionFactory.setBrokerURL("failover:(tcp://localhost:61616)?maxReconnectAttempts=1&timeout=10");
	    return connectionFactory;
	}
	
	@Bean
	public CachingConnectionFactory cachingConnectionFactory() throws NamingException{
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setTargetConnectionFactory(connectionFactory());
		cachingConnectionFactory.setSessionCacheSize(10);
		return cachingConnectionFactory;
	}
	
	@Bean 
	public ActiveMQQueue messageDestination(){
		ActiveMQQueue queue = new ActiveMQQueue("messageQueue1");
		return queue;
	}
	
	@Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDefaultDestinationName("messageDestination");
        jmsTemplate.setConnectionFactory(cachingConnectionFactory());
        jmsTemplate.setReceiveTimeout(10000);
        return jmsTemplate;
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws NamingException {
      
		DefaultJmsListenerContainerFactory factory =
              new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        //factory.setDestinationResolver(cachingDestinationResolver());
       factory.setConcurrency("3-10");
       return factory;
    }
	
	
}
