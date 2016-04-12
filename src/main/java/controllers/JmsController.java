package controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.Message;
import service.JmsService;

@Controller
@RequestMapping("/")
public class JmsController {
	
	@Inject JmsService jmsService;
	
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("greeting", "Apache Active MQ with Spring");
       
        return "index";
    }
    
    @RequestMapping(value="/index", method = RequestMethod.POST)
    public String indexPost(ModelMap model,Message message) {
        model.addAttribute("greeting", message.getMessage());
        jmsService.sendMessage(message.getMessage());
        return "index";
    }
}