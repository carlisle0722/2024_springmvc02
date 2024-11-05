package com.ict.edu05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailController {
	@GetMapping("/email")
	public ModelAndView emailForm() {
		return new ModelAndView("day05/email_form");
	}
	@GetMapping("/email_send")
	public ModelAndView sendMail() {
		return new ModelAndView("day05/email_chk_form");
	}
	@GetMapping("/email_send_ok")
	public ModelAndView sendMailOK() {
		return new ModelAndView("day05/email_chk_form");
	}
}
