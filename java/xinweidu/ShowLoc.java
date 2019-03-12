package com.xinweidu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowLoc
 */
@WebServlet("/ShowLoc")
public class ShowLoc extends HttpServlet {
	private Connection conn = null;
	private PrintWriter out = null;
	private static final long serialVersionUID = 1L;
	private static final String pass = "98ddce6fc91f0e569f190a5981d198b7";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLoc() {
        super();
     // TODO Auto-generated method stub
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/bs";
			conn = DriverManager.getConnection(url,"root","xiaofeng");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		if(pass.equals(key)) {
			if(conn!=null) {
				String sql  = "SELECT * FROM loc ORDER BY id DESC LIMIT 1";
				try {
					Statement sm = conn.createStatement();
					ResultSet rs = sm.executeQuery(sql);
					while(rs.next()) {
						String jing = rs.getString("jing");
						String wei = rs.getString("wei");
						out = response.getWriter();
						out.print(jing+"-"+wei);
						System.out.println("已获取APP的数据");
					}
					out.close();
					rs.close();
					sm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("Error PassWord!");
		}
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
