

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Payfine
 */
@WebServlet("/Payfine")
public class Payfine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payfine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 try{
			 Connection con=null;
             PrintWriter out = response.getWriter();
			//ResultSet rs;
                        String n =  request.getParameter("check");
                        //int n1 = Integer.parseInt(n);
                        //out.println(n);
//                        String n1 =  request.getParameter("card_id");
                        int temp1=0,cout = 0, count = 0, b = 0, b1 = 0, b2 = 0, b3 = 0;
                        Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/lcd","root","root");
			java.sql.Statement st1 = con.createStatement();
                        String query = "UPDATE fines SET paid = 1 WHERE loan_id = '"+n+"';";
                        b=st1.executeUpdate(query);
                        if(b>=1)
    {
        //check = 1;
        //out.println("Success");
        out.println("<html><body>");
        out.println("<script type=\"text/javascript\">");
        out.println("var popwin = window.alert(\"Fine Paid Successfully\")");
        out.println("setTimeout(function(){ popwin.close(); window.location.href='fines.jsp';},3000)");
        out.println("</script>");
        out.println("</body></html>");
        //response.sendRedirect("webpages/index.html");
        response.setHeader("Refresh", "2;url=options.jsp");
        //response.sendRedirect("webpages/index.html");
    }
         }
	 catch(Exception e){
		 System.out.println(e);
	 }
}

	}


