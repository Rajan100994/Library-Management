

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Borrower_add
 */
@WebServlet("/Borrower_add")
public class Borrower_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Borrower_add() {
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
		
		
		int count = 0;
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lcd","root","root");
	        PreparedStatement ps1=con.prepareStatement("select count(*) from borrower");
	        ResultSet rs=ps1.executeQuery();
	        if(rs.next()){
	            count = rs.getInt(1);
	            }
	        PreparedStatement ps=con.prepareStatement("insert into borrower values("+(count+1)+",?,?,?,?,?,?,?,0)");
            ps.setString(1, request.getParameter("ssn"));
            ps.setString(2, request.getParameter("first_name"));
            ps.setString(3, request.getParameter("last_name"));
            ps.setString(4, request.getParameter("address"));
            ps.setString(5, request.getParameter("city"));
            ps.setString(6, request.getParameter("state"));
            ps.setString(7, request.getParameter("phone_number"));
            
            
            int b=0;
            PrintWriter out = response.getWriter();
            b=ps.executeUpdate();
            if(b>=1){
            	
            	 out.println("<html><body>");
                 out.println("<script type=\"text/javascript\">");
                 out.println("var popwin = window.alert(\"Borrower added Successfully\")");
                 out.println("</script>");
                 out.println("</body></html>");
                 response.setHeader("Refresh", "2;url=Search_book.jsp");
            }
            
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			 out.println(e.getMessage());
             
         response.setHeader("Refresh", "5;url=addborrower.jsp");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
