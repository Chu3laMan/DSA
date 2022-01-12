package co.chu3la.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import co.chu3la.demo.entities.Authority;
import co.chu3la.demo.entities.User;
import co.chu3la.demo.service.JpaUserDetailsService;
import co.chu3la.demo.util.PlainTextPasswordEncoder;



@Controller
@RequestMapping("/app")
public class MainController {
	
	
	
	@Autowired
	private PlainTextPasswordEncoder bcrypt;
	
	@Autowired
	private JpaUserDetailsService userRepository;
	
	@GetMapping(value = "/index")
	public String index(Authentication auth, Model model) {
		model.addAttribute("username", auth.getName());
		return "index";
	}
	
	@RequestMapping(value = "/signup", method = { RequestMethod.GET })
	public String addUsername() {
		return "signup";
	}
	
	
	@RequestMapping(value = "/signup", method = { RequestMethod.POST })
	public RedirectView allEmployees(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession httpSession = request.getSession();
		User u;
		String email = request.getParameter("email");
		String pswd = request.getParameter("pswd");
		u = new User(email, bcrypt.encode(pswd));
		Authority role = new Authority();
		role.setName("READ");
		role.setUser(u);
		httpSession.setAttribute("user", u);
		userRepository.save(u);
		return new RedirectView("index");
	}
	
	

}
