package edu.spring.redu;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import rtest.OnePersonService;

@Controller
public class OnePersonController {
	
	@Autowired
	OnePersonService p1;
	
	@RequestMapping("/map7")
	
	
	public ModelAndView get8(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(real_path); 
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);
	    File f = new File(real_path+"/resources/onemap");
		if(!f.exists()) f.mkdir();
		String gu = req.getParameter("gu");
		if(gu == null)
			gu = "강남구";
		String result = p1.returnOneperson1(real_path+"/resources/onemap", gu);
		mav.addObject("leafletChartName", "http://localhost:8000/redu/resources/onemap/"+result);
		mav.setViewName("oneView");
		return mav;
	}	

	
}
