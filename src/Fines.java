

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Fines
 */
@WebServlet("/Fines")
public class Fines extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fines() {
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
		int q=0;
		
		System.out.println(q);
		try {
			try{
				q = Integer.parseInt(request.getParameter("payf"));
				}
			catch (Exception e){
				
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lcd","root","root");
	        Statement st1 = con.createStatement();
	        String query=null;
	        int over = 5;
	        
	        String fines_borr =  request.getParameter("fines_val");
	        String rvalue=request.getParameter("r1");
	        System.out.println(rvalue);
	        List<ResultBean> list = new ArrayList<ResultBean>();
			ResultBean tempResultBean; 
	        if(rvalue.equals("UnPaid"))
            {
	        	query="select fines.loan_id,fines.fine_amt,book_loans.card_id,book_loans.isbn,book.title,fines.paid from fines,book_loans,book where fines.loan_id=book_loans.loan_id and book.isbn=book_loans.isbn and fines.paid=0 and book_loans.card_id='"+fines_borr+"'"; 
	        	ResultSet rs=null;
	        	String ps="Unpaid";
	        	rs = st1.executeQuery(query);
	        	System.out.println("Hello");
	        	while(rs.next()){
	        		
	        		tempResultBean = new ResultBean();
	        		tempResultBean.setLoanid(rs.getInt(1));
	        		tempResultBean.setAmt(rs.getFloat(2));
	        		tempResultBean.setCardid(rs.getInt(3));
	        		tempResultBean.setIsbn(rs.getString(4));
	        		tempResultBean.setTitle(rs.getString(5));
	        		if(rs.getInt(6)==1){
	        			ps="Paid";
	        		}
	        		tempResultBean.setPaid_unpaid(ps);
	        		
	        		
	        		list.add(tempResultBean);
	        	}
	        	
	        	
	        	request.setAttribute("flistsize", list.size());
	        	request.setAttribute("flist", list);
	        	
            }
	        
	        if(rvalue.equals("Paid"))
            {
	        	query="select fines.loan_id,fines.fine_amt,book_loans.card_id,book_loans.isbn,book.title,fines.paid from fines,book_loans,book where fines.loan_id=book_loans.loan_id and book.isbn=book_loans.isbn and fines.paid=1 and book_loans.card_id='"+fines_borr+"'"; 
	        	ResultSet rs=null;
	        	String ps="Unpaid";
	        	rs = st1.executeQuery(query);
	        	System.out.println("Hello");
	        	while(rs.next()){
	        		
	        		tempResultBean = new ResultBean();
	        		tempResultBean.setLoanid(rs.getInt(1));
	        		tempResultBean.setAmt(rs.getFloat(2));
	        		tempResultBean.setCardid(rs.getInt(3));
	        		tempResultBean.setIsbn(rs.getString(4));
	        		tempResultBean.setTitle(rs.getString(5));
	        		if(rs.getInt(6)==1){
	        			ps="Paid";
	        		}
	        		tempResultBean.setPaid_unpaid(ps);
	        		over = 4;
	        		
	        		list.add(tempResultBean);
	        	}
	        	
	        	
	        	request.setAttribute("flistsize", list.size());
	        	request.setAttribute("flist", list);
	        	
            }
	        
	        if(rvalue.equals("Both"))
            {
	        	query="select fines.loan_id,fines.fine_amt,book_loans.card_id,book_loans.isbn,book.title,fines.paid from fines,book_loans,book where fines.loan_id=book_loans.loan_id and book.isbn=book_loans.isbn and book_loans.card_id='"+fines_borr+"'"; 
	        	ResultSet rs=null;
	        	String ps="Unpaid";
	        	rs = st1.executeQuery(query);
	        	System.out.println("Hello");
	        	while(rs.next()){
	        		
	        		tempResultBean = new ResultBean();
	        		tempResultBean.setLoanid(rs.getInt(1));
	        		tempResultBean.setAmt(rs.getFloat(2));
	        		tempResultBean.setCardid(rs.getInt(3));
	        		tempResultBean.setIsbn(rs.getString(4));
	        		tempResultBean.setTitle(rs.getString(5));
	        		if(rs.getInt(6)==1){
	        			ps="Paid";
	        		}
	        		tempResultBean.setPaid_unpaid(ps);
	        		
	        		
	        		list.add(tempResultBean);
	        	}
	        	
	        	
	        	request.setAttribute("flistsize", list.size());
	        	request.setAttribute("flist", list);
	        	
            }
	        
	        
	        if(rvalue.equals("OverDue"))
            {
	        	
	        	{
	        	query="select due_date,book_loans.card_id,book_loans.loan_id,book.isbn,book.title from book,book_loans where book.isbn=book_loans.isbn and card_id='"+fines_borr+"' and date_in is null "; 
	        	ResultSet rs = st1.executeQuery(query);
	        	while(rs.next()){
	        		String due_dt=rs.getString(1);
	        		 Date d1=new Date();
	        		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     Date d2=null;
                     d2=sdf.parse(due_dt);
                     int diff=(int)((d2.getTime()-d1.getTime())/(1000*60*60*24));
                     if(diff<0){
                    	tempResultBean = new ResultBean();
     	        		tempResultBean.setLoanid(rs.getInt(3));
     	        		tempResultBean.setAuthorname(rs.getString(1));
     	        		tempResultBean.setCardid(rs.getInt(2));
     	        		tempResultBean.setIsbn(rs.getString(4));
     	        		tempResultBean.setTitle(rs.getString(5));
     	        		
     	        		list.add(tempResultBean);
                     }
                     over =1;
                     
	        	}
	        	//request.setAttribute("flistsize", list.size());
	        	request.setAttribute("flist", list);
	        	
	        	
	        	
	        	}	
            }
	        
	        if(rvalue.equals("Settle"))
            {
	        	query="select fines.loan_id,fines.fine_amt,book_loans.card_id,book_loans.isbn,book.title,fines.paid from fines,book_loans,book where fines.loan_id=book_loans.loan_id and book.isbn=book_loans.isbn and fines.paid=0 and book_loans.card_id="+fines_borr+"";
	        	ResultSet rs = st1.executeQuery(query);
	        	while(rs.next()){
	        		tempResultBean = new ResultBean();
	        		tempResultBean.setLoanid(rs.getInt(1));
	        		tempResultBean.setAmt(rs.getFloat(2));
	        		tempResultBean.setCardid(rs.getInt(3));
	        		tempResultBean.setIsbn(rs.getString(4));
	        		tempResultBean.setTitle(rs.getString(5));
	        		tempResultBean.setPaid_unpaid(rs.getString(6));
	        		
	        		list.add(tempResultBean);
	        		request.setAttribute("pay",1);
	        		
	        	}
	        		
	        		request.setAttribute("flist", list);
            }
	        
	        
	        
	          
	        
	        request.setAttribute("od", over);
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fines.jsp");
			dispatcher.forward(request, response);
	        
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
			response.setHeader("Refresh", "3;url=fines.jsp");
		}
	}

}
