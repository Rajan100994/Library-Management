

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

/**
 * Servlet implementation class Search
 */
@WebServlet("/SearchAbcd.jsp")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    Connection con=null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BigDecimal value;
		try{
			PrintWriter out = response.getWriter();
	        
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/lcd","root","root");
				Statement st1 = con.createStatement();
				//String n= " Rajan " ;
				 				String n =  request.getParameter("search_val");
				 			String query = "";
				 				try
				 				{
				 					value = new BigDecimal(n);
					 				query = "select title,Availability,book.isbn,authors.name from book,authors,book_authors where (title like '%"+ n +"%' or authors.name like '%" + n +"%' or book.isbn="+value+") and authors.author_id = book_authors.author_id and book_authors.isbn = book.isbn order by book.isbn";

				 				}
				 				catch(Exception ee)
				 				{
					 				query = "select title,Availability,book.isbn,authors.name from book,authors,book_authors where (title like '%"+ n +"%' or authors.name like '%" + n +"%' ) and authors.author_id = book_authors.author_id and book_authors.isbn = book.isbn order by book.isbn";
				 					
				 				}
				 				
				 				
				 if(n!=null){
				 //{
					
						 //HttpSession session = request.getSession();
						ResultSet rs= st1.executeQuery(query) ;
						//List<ResultBean> finaLlist = new ArrayList<ResultBean>();
						List<ResultBean> list = new ArrayList<ResultBean>();
						ResultBean tempResultBean; 
						//List<ResultBean1> list = new ArrayList<ResultBean1>();
						//session.setAttribute("list", rs);
						String s=null;
						while(rs.next())
						{
							
							tempResultBean = new ResultBean();
							tempResultBean.setTitle(rs.getString(1));
							tempResultBean.setAvailability(rs.getString(2));
							tempResultBean.setIsbn(rs.getString(3));
							tempResultBean.setAuthorname(rs.getString(4));
							//int cnt=0;
							while(rs.next()){
									
								if(tempResultBean.getIsbn().equals(rs.getString(3))){
									
									tempResultBean.setAuthorname(tempResultBean.getAuthorname() + ", " + rs.getString(4));
									}
								else{
									rs.previous();
									break;
									}
								
							}
							
							
							list.add(tempResultBean);
							
						}
						
						
						request.setAttribute("size1",list.size());
						con.close();
						
						//session.setAttribute("ans", rs);
						//session.setAttribute("login_name", rs.getString(1));
						request.setAttribute("res",list);
				 //}
				 }
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Search_book.jsp");
				dispatcher.forward(request, response);
				
				
				
				//response.sendRedirect("Search_book.jsp");
				//response.sendRedirect("Search_book.jsp");
				
				//response.sendRedirect("congrats.jsp");
		 
				
		
		}
		 catch(Exception e){
			 System.out.println(e);
		 }
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	
	}
	

}
