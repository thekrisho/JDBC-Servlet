import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.io.*;

public class entries extends HttpServlet {
  private int mCount;
  
  
  
  // NO POST METHOD FOR THIS JAVA CLASS. THIS CLASS FILE IS PURELY FOR RETRIEVING DATA
  // MAIN GET METHOD (Runs when you open the site)
  public void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

// For Error Checking	  
String errMsg = "In Testing";  
try {
try {
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
} catch (Exception ex) { }

		// Define database location / directory
		Connection con = DriverManager.getConnection("jdbc:ucanaccess://c:/temp/Database2.accdb");
errMsg += "Con";
			// Start connection to database
			Statement stmt = con.createStatement();
errMsg += "stmt";
	
				// This is the JDBC statment / command :
				// SELECT: get
				// time -> column 1 in my database
				// event -> column 2 in my database
				// FROM -> define which table in the database
				// events -> name of my table (not database name)
				// ORDER by time DESC -> latest events based on colum "time" to the top
				String query_stmt = "SELECT time, event FROM events"+ 
					" ORDER BY time DESC";
				
				// Run the statment / command 
				ResultSet rs = stmt.executeQuery(query_stmt);
				
				// Tell java where "out" prints to 
				PrintWriter out = response.getWriter(); 
				
				// Start HTML header
				out.println("<html>\n" +
						"<head><title>" + "Android Event Database" +  "</title></head>\n" +
						"<body bgcolor=\"#f0f0f0\">\n" +
						"<h1 align=\"center\">" + "Android Event Database" + "</h1>\n" +
						"<h2 align=\"center\">" + "Kris JDBC Access" + "</h2>\n" +
						"<ul>\n");
						
				// Run through database till the end of list 
				while(rs.next()){
					// Get data
					String field1 = rs.getString("time");	
					String field2 = rs.getString("event");	
				
					// Print data to HTML
					out.println("<li><b>Time</b>: " + field1 + "\n" +
									"<b>Event</b>:" + field2 + "\n");
				}
				// Close HTML
				out.println("</ul>\n" +
						"</body></html>");
		
			// Close database connection / clean up
			rs.close();
			stmt.close();
			con.close();
			
			// For Error checking
errMsg += "End";
		} 
		catch(SQLException ex) { 
				errMsg = errMsg + "\n--- SQLException caught ---\n"; 
				while (ex != null) { 
					errMsg += "Message: " + ex.getMessage (); 
					errMsg += "SQLState: " + ex.getSQLState (); 
					errMsg += "ErrorCode: " + ex.getErrorCode (); 
					ex = ex.getNextException(); 
					errMsg += "";
				} 
		}

  }
	

// Method to handle POST from android.
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

String errMsg = "Testing";
     // Set response content type
try {
try {
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
} catch (Exception ex) { }
		Connection con = DriverManager.getConnection("jdbc:ucanaccess://c:/temp/Database2.accdb");
errMsg += "Con";
			Statement stmt = con.createStatement();
errMsg += "stmt";
			String insertStmt = "INSERT INTO Events (time, event) VALUES('" + 
				request.getParameter("time") + "','" + 
				request.getParameter("event") + "')";

			stmt.executeUpdate(insertStmt);
			stmt.close();
			con.close();
errMsg += "End";
		} 
		catch(SQLException ex) { 
				errMsg = errMsg + "\n--- SQLException caught ---\n"; 
				while (ex != null) { 
					errMsg += "Message: " + ex.getMessage (); 
					errMsg += "SQLState: " + ex.getSQLState (); 
					errMsg += "ErrorCode: " + ex.getErrorCode (); 
					ex = ex.getNextException(); 
					errMsg += "";
				} 
		} 
    PrintWriter out = response.getWriter();
      response.setContentType("text/html");

	  String title = "data submiteed";

      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
      out.println(docType +
                "<html>\n" +
                "<head><title>" + title +  "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>Time</b>: "
                + request.getParameter("time") + "\n" +
                "  <li><b>Event</b>: "
                + request.getParameter("event") + "\n" +
                "</ul>\n" +
                "</body></html>");
  }
	
}
 


	


