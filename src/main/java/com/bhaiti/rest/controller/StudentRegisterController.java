package com.bhaiti.rest.controller;


/**
 * @author prate
 *
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.bhaiti.beans.StudentRegistration;
import com.bhaiti.beans.StudentRegistrationReply;

@Controller
public class StudentRegisterController {
	
	@RequestMapping(method = RequestMethod.POST, value="/register/student")
	@ResponseBody
	StudentRegistrationReply registerStudent(@RequestBody StudentRegistration studentregd) {
	StudentRegistrationReply stdregreply = new StudentRegistrationReply();
	stdregreply.setName(studentregd.getName());
	stdregreply.setAge(studentregd.getAge());
	stdregreply.setRegistrationNumber("12345678");
	stdregreply.setRegistrationStatus("Successful");
	return stdregreply;
	}

}
