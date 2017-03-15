

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add_b
 */
@WebServlet("/Add_b")
public class Add_b extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_b() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
        doPost(request, response);
}
    
    Connection con= null; 
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	
 		 try{
 				
 				Class.forName("com.mysql.jdbc.Driver");
 				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/lcd","root","root");
 				Statement st1 = con.createStatement();
 				//String n= " Rajan " ;
 				 				String n =  request.getParameter("user_id");
 				//String u =  request.getParameter("username");
 				//String p =  request.getParameter("password");
 				 				String u =  request.getParameter("admin_login");
 								String p = request.getParameter("admin_password");
 				ResultSet rs = st1.executeQuery("select login_id from login_details where Username='"+u+"' and Password='"+p+"'");
 				
 				if(rs.next()){
 				response.sendRedirect("options.jsp");
 				}
 				else{
 					response.sendRedirect("options.jsp");
 				}
 				con.close();
 		 }
 		 catch(Exception e){
 			 System.out.println(e);
 		 }
 	
 	}

 }


