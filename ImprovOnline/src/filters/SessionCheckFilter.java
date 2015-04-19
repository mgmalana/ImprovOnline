package filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class SessionCheckFilter implements Filter {

	  private String contextPath;

	  @Override
	  public void init(FilterConfig fc) throws ServletException {
	    contextPath = fc.getServletContext().getContextPath();
	  }

	  @Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
	    System.out.println("enter sessioncheckfilter");
		  
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;  
	    System.out.println("User: " + req.getSession().getAttribute("user"));
	    if (req.getSession().getAttribute("user") == null) { //checks if there's a user set in session...
	        res.sendRedirect(contextPath + "/login.jsp"); //or page where you want to redirect
	    } else{
	    	fc.doFilter(request, response);
	    }
	    
	  }

	  @Override
	  public void destroy() {
	  }
	}