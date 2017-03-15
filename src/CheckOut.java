

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOut() {
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
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lcd","root","root");
	        Statement st1 = con.createStatement();
	        
	        String query;
	        String c_out_isbn =  request.getParameter("co_isbn");
			String c_out_card_id = request.getParameter("card_id_co");
			int available=0,active_check_outs=0,l_id=0;
			query=("Select book.availability from book where book.isbn= '"+c_out_isbn+"'");
			
			ResultSet rs= st1.executeQuery(query) ;
			if(rs.next()){
				available=rs.getInt(1);
				}
			
			query=("Select borrower.act_co from borrower where borrower.card_id= '"+c_out_card_id+"'");
			ResultSet rs1 = st1.executeQuery(query);
			if(rs1.next()){
				active_check_outs = rs1.getInt(1);
				}
			
			ResultSet rs2 = st1.executeQuery("select count(*) from book_loans");
			if(rs2.next()){
			l_id=rs2.getInt(1);
			}
			
				
			Date date = new Date();
			String dt= new SimpleDateFormat("yyyy-MM-dd").format(date);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(dt));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.add(Calendar.DATE, 14);  // number of days to add
			String dt1 = sdf.format(c.getTime());  
			int flag=0;
			
			query=("Select due_date from book_loans where card_id= '"+c_out_card_id+"' and date_in is null");
			ResultSet rs3= st1.executeQuery(query);
			while(rs3.next()){
				
				Date due= (Date) sdf.parse(rs3.getString(1));
				if(date.after(due)){
				flag=1;
				}
			}
			
			if(available!=1){
				flag=2;
			}
			
			if(active_check_outs >= 3){
				flag=3;
			}
			if(flag==0){
				query=("insert into book_loans(loan_id,isbn,card_id,date_out,due_date) values('"+(l_id + 1)+"','"+c_out_isbn+"','"+c_out_card_id+"','"+dt+"','"+dt1+"')");
				st1.executeUpdate(query);
				query=("Update book SET Availability=0 where book.isbn = '"+c_out_isbn+"'");
				st1.executeUpdate(query);
				query=("Update borrower SET act_co = act_co+1 where borrower.card_id = '"+c_out_card_id+"'");
				st1.executeUpdate(query);
			}
			
			
           
            if(flag==1){
            	out.println("<html><body>");
                out.println("<script type=\"text/javascript\">");
            	out.println("var popwin = window.alert(\"A book is overdue !!\")");
            	out.println("</script>");
                out.println("</body></html>");
            	response.setHeader("Refresh", "3;url=options.jsp");
            }
            else if(flag==2){
            	out.println("<html><body>");
                out.println("<script type=\"text/javascript\">");
            	out.println("var popwin = window.alert(\"The book is not available ! Please try later...\")");
            	out.println("</script>");
                out.println("</body></html>");
            	response.setHeader("Refresh", "3;url=options.jsp");
            }
            else if(flag==3){
            	out.println("<html><body>");
                out.println("<script type=\"text/javascript\">");
            	out.println("var popwin = window.alert(\"You have 3 active check outs... Please return a book to get one\")");
            	out.println("</script>");
                out.println("</body></html>");
            	response.setHeader("Refresh", "3;url=options.jsp");
            }
            
            else{
            	 out.println("<html><body>");
                 out.println("<script type=\"text/javascript\">");
                 out.println("var popwin = window.alert(\"Check out Successful...\")");
                 out.println("</script>");
                 out.println("</body></html>");
                 response.setHeader("Refresh", "2;url=options.jsp");
            }
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
            
	         response.setHeader("Refresh", "5;url=options.jsp");
		}
		
	}

}
