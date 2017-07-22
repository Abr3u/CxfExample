package org.apache.jsp.WEB_002dINF.jsp.error;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class uncaughtException_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    Throwable exception = org.apache.jasper.runtime.JspRuntimeLibrary.getThrowable(request);
    if (exception != null) {
      response.setStatus((Integer)request.getAttribute("javax.servlet.error.status_code"));
    }
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<h2>Internal error</h2>\n");
      out.write("<h3>Class : ");
      out.print(exception.getClass().getName() );
      out.write("</h3>\n");
      out.write("<h3>Message : ");
      out.print(exception.getMessage() );
      out.write("</h3>\n");
      out.write("<h3>Stack trace</h3>\n");
      out.write("\n");
 
try {

	Throwable ex = exception;
	if (exception instanceof ServletException) {
		// It's a ServletException: we should extract the root cause
		ServletException sex = (ServletException) exception;
		Throwable rootCause = sex.getRootCause();
		if (rootCause == null) {
			ex = exception;
		}
		out.println("<h3>Root cause is: "+ rootCause.getMessage() + "</h3>");
	}

    StringWriter sw = new StringWriter();
    exception.printStackTrace(new PrintWriter(sw)); 

    String stackTrace = sw.toString();
    stackTrace = stackTrace.replaceAll("\n", "<br>");
    out.write(stackTrace);
	
	
	
	// Display cookies
	out.println("<h3>Cookies:</h3>");
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
    	for (int i = 0; i < cookies.length; i++) {
      		out.println(cookies[i].getName() + "=[" + cookies[i].getValue() + "]");
		}
	}
	    
} catch (Exception ex) { 
	ex.printStackTrace(new java.io.PrintWriter(out));
}

      out.write("\n");
      out.write("\n");
      out.write("<p/>\n");
      out.write("<br/>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
