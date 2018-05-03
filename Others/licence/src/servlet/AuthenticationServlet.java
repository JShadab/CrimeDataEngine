package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/test"})
public class AuthenticationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String serial = req.getParameter("serial");
		String email = req.getParameter("email");
		String rsid = req.getParameter("rsid");
//		String deviceId = req.getParameter("deviceId");
//		String hardwareId = req.getParameter("hardwareId");
		
		
		resp.getWriter().println("haan bbai");
		

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("haan bbai");
	}

}
