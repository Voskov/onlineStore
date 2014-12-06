package servlets;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.store.core.StoreItems;

@WebServlet(
		urlPatterns = { "/Controller" }, 
		initParams = { 
				@WebInitParam(name = "email", value = "support@online.store")
		})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String supportMail;
	private int hitCounter;
	private StoreItems store;
	
	public void init(ServletConfig config){
		System.out.println("Controller.init()");
		this.store = new StoreItems();
		this.supportMail = config.getInitParameter("email");
		System.out.println("Controller.init()");
		System.out.println(supportMail);
	}
       
	@PostConstruct
	public void post(){
		System.out.println("Controller.post()");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
