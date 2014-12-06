package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.Cookies;

import quicktime.std.movies.media.LevelMeterInfo;

@WebServlet({ "/LoginServlet", "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COOKIE_DATE_NAME = "dateCookie";

	private static final String USERNAME_PARAMETER = "userName";
	private Logger logger = Logger.getLogger(this.getClass().toString());
	
	private static final String USERNAME_ATTR = "userName";
	private static final String LAST_VISIT = "lastVisit";
       
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LoginServlet.init()");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet.service()");
		String userName = request.getParameter(USERNAME_PARAMETER);
		String lastVisit = null;
		String currentVisit;
		if (userName == null || userName.trim().isEmpty()){
			userName = "Guest";
		}
		logger.log(Level.INFO, "userName - " + userName);
		request.setAttribute(USERNAME_ATTR, userName);
		String nextPage = "/welcome.jsp";
//		long now = System.currentTimeMillis() / 1000L;
		String now = new Date().toString();
		Cookie[] cookies = request.getCookies();
		Cookie lastVisitCookie = null;
		if (cookies != null && cookies.length > 0){
			boolean foundCookie = false;
			for (Cookie cookie : cookies){
				if (COOKIE_DATE_NAME.equals(cookie.getName())){
					lastVisitCookie = cookie;
					foundCookie = true;
					break;
				}
			}
			if (!foundCookie){
				logger.log(Level.INFO, "Cookie not found");
				logger.log(Level.INFO, "Creating a new cookie - " + now);
				
				Cookie nowCookie = new Cookie(COOKIE_DATE_NAME, now);
				nowCookie.setMaxAge(7*24*60*60); //One week
				response.addCookie(nowCookie);
				lastVisit = "It's your first time here!";
				
			} else {
				logger.log(Level.INFO, "Cookie found");
				lastVisit = lastVisitCookie.getValue();
				logger.log(Level.INFO, "Cookie value - " + lastVisit);
				
				lastVisitCookie.setValue(now);
				response.addCookie(lastVisitCookie);
			}
			logger.log(Level.INFO, "Setting attribute: " + LAST_VISIT + " - " + lastVisit);
			request.setAttribute(LAST_VISIT, lastVisit);
		}
		response.setContentType("html");
		logger.log(Level.INFO, "userName attribute - " + request.getAttribute(USERNAME_ATTR));
		request.getRequestDispatcher(nextPage).forward(request, response);
		
		
		
	}
}
