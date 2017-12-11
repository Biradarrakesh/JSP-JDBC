

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errors", false);
		Employee e = new Employee();
		PrintWriter out=response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		if(id==0)
		{
			System.out.println("Error");
		}else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeDB", "root", "");
					Statement stmt = con.createStatement();
					String query="select *from Employees where id="+id;
					ResultSet rs = stmt.executeQuery(query);

					if(!rs.next())
					{
						//System.out.println("A");
						response.sendRedirect("Notification4.jsp");
					}else {
						String update_query = "delete from Employees where id="+id;
						stmt.executeUpdate(update_query);
						System.out.println(update_query);
						response.sendRedirect("Notification3.jsp");
					    }
				} catch (SQLException err) {
					err.printStackTrace();
				}
			} catch (ClassNotFoundException err) {
				err.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
