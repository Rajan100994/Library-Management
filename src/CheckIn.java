

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckIn
 */
@WebServlet("/CheckIn")
public class CheckIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIn() {
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
	Connection con =null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BigDecimal value;
		
		try{
			PrintWriter out = response.getWriter();
	        
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/lcd","root","root");
				Statement st1 = con.createStatement();
				
				 				String n =  request.getParameter("cin_val");
				 			String query = "";
				 			String ch_in_loan = request.getParameter("ch_l_id");
				 			
							 String ch_in_isbn = null;
							 if(request.getParameter("ch_l_id")!= null){
								 Date date = new Date();
								 String c_in_date= new SimpleDateFormat("yyyy-MM-dd").format(date);
								 
								 query="update book_loans set date_in = '"+c_in_date+"' where book_loans.loan_id= '"+ch_in_loan+"'";
								 st1.executeUpdate(query);
								 
								 query="select isbn from book_loans where loan_id = '"+ch_in_loan+"'";
								 ResultSet r =st1.executeQuery(query);
								 while(r.next()){
									 ch_in_isbn = r.getString(1);
								 }
								 
								 query=" update book set Availability=1 where book.isbn= '"+ch_in_isbn+"'";
								 st1.executeUpdate(query);
								 
								 query = "select card_id from book_loans where book_loans.isbn= '"+ch_in_isbn+"'";
								 ResultSet rs5 = st1.executeQuery(query);
								 
								 int ch_in_c_id=0;
								 while(rs5.next()){
									 ch_in_c_id = rs5.getInt(1);
								 	}
								 
								 query="Update borrower SET act_co = act_co-1 where borrower.card_id = "+ch_in_c_id+";";
								 st1.executeUpdate(query);
								 
								 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								 String s=null;
								 java.sql.PreparedStatement ps5=con.prepareStatement("select due_date from book_loans where loan_id="+ch_in_loan+";");
								 ResultSet rs2=null;
						            rs2=ps5.executeQuery();
						            if(rs2.next())
						            {
						                s=rs2.getString(1);
						            }
						            int diff;
						            double fine=0;
						           Date d1=sdf.parse(s);
						           if(date.after(d1)){
						        	   diff=(int)((date.getTime()-d1.getTime())/(1000*60*60*24));
						        	   fine=diff*0.25;
						                java.sql.PreparedStatement ps6=con.prepareStatement("insert into fines(loan_id,fine_amt) values ("+ch_in_loan+","+fine+");");
						                ps6.executeUpdate();
						           }
						           
						           
						           
								 out.println("<html><body>");
					                out.println("<script type=\"text/javascript\">");
					            	out.println("var popwin = window.alert(\"Check-in Successful !!\")");
					            	out.println("</script>");
					                out.println("</body></html>");
					            	response.setHeader("Refresh", "3;url=options.jsp");
					            	
					            	
							 }
							 
				 				
							 else{
				 				try
				 				{
				 					value = new BigDecimal(n);
					 				query = "select title,Availability,book.isbn,authors.name,book_loans.card_id,book_loans.loan_id from book,authors,book_authors,book_loans where (title like '%"+ n +"%' or authors.name like '%" + n +"%' or book.isbn="+value+" or book_loans.card_id = "+value+") and book_loans.isbn = book.isbn and Availability=0 and authors.author_id = book_authors.author_id and book_authors.isbn = book.isbn order by book.isbn";

				 				}
				 				catch(Exception ee)
				 				{
					 				query = "select title,Availability,book.isbn,authors.name,book_loans.card_id,book_loans.loan_id from book,authors,book_authors,book_loans where (title like '%"+ n +"%' or authors.name like '%" + n +"%' ) and book_loans.isbn = book.isbn and Availability=0 and authors.author_id = book_authors.author_id and book_authors.isbn = book.isbn order by book.isbn";
				 					
				 				}
				 		
				 
								
				 if(n!=null){
				 //{
					
						 
						ResultSet rs= st1.executeQuery(query) ;
						List<ResultBean> cinlist = new ArrayList<ResultBean>();
						
						ResultBean tempResultBean; 
						
						//String s=null;
						while(rs.next())
						{
							
							tempResultBean = new ResultBean();
							tempResultBean.setTitle(rs.getString(1));
							tempResultBean.setAvailability(rs.getString(2));
							tempResultBean.setIsbn(rs.getString(3));
							tempResultBean.setAuthorname(rs.getString(4));
							tempResultBean.setCardid(rs.getInt(5));
							tempResultBean.setLoanid(rs.getInt(6));
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
							
							
							cinlist.add(tempResultBean);
							
						}
						
						request.setAttribute("cinsize",cinlist.size());
							
						
						
						con.close();
						
						//session.setAttribute("ans", rs);
						//session.setAttribute("login_name", rs.getString(1));
						request.setAttribute("cinres",cinlist);
				 //}
				 }
				 
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/check_in.jsp");
				dispatcher.forward(request, response);
				
				
				
				//response.sendRedirect("Search_book.jsp");
				//response.sendRedirect("Search_book.jsp");
				
				//response.sendRedirect("congrats.jsp");
		 
				
		
				 }
			 }
		 catch(Exception e){
			 System.out.println(e);
		 }
		
	
	}

}
