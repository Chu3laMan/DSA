package controllers;




import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DSA.BSTImpl;
import Util.UtilPassword;
import data.UserDB;
import domain.User;




/**
 * Servlet implementation class FirstController
 */

public class FirstController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "/includes/index/jsp";
		if(action == null)
			action = "join";
		else if(action.equals("add")) {
			String keyword = request.getParameter("searching");
			System.out.println(UserDB.findLastName(keyword));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = null;
		if(action == null)
			action = "join";
		else if(action.equals("add")) {
			String email = request.getParameter("email");
			if(UserDB.checkEmail(email)) {
				UserDB.updateUserPswd("hello");
				System.out.println("Success");
			}
		}
		
		
			
		

  }
	
	public String registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		    
		    
		    
		    BSTImpl bst = null;
	        String url;	
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirm_pswd = request.getParameter("confirm_pswd");
			
		
			//Validate parameters
			String message = null;
			if(!password.equals(confirm_pswd)) {
				message = "Password doesn't match!";
				url = "/index.jsp";
			}
			
			else {
				bst = new BSTImpl();
				message = "";
				bst.insert(bst.nItems(), new User(firstName, lastName, email, password));
				url = "/thanks.jsp";
				System.out.println(bst.height());
				bst.print();
			}
			
//			request.setAttribute("user", user);
			request.setAttribute("message", message);
		
		return url;
		
	}
}
