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
 * Servlet implementation class GPRSServlet
 */
@WebServlet("/GPRSServlet")
public class GPRSServlet extends HttpServlet {
	
	private Connection conn;
	private static final long serialVersionUID = 1L;
	private static final String key = "98ddce6fc91f0e569f190a5981d198b7";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GPRSServlet() {
        super();
        
    }
    @Override
    public void init() throws ServletException {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String jingStr = request.getParameter("jing");
		String weiStr = request.getParameter("wei");
		String mi = request.getParameter("key");
		PrintWriter out = null;
		if(!key.equals(mi)) {
			System.out.println("Error PassWord");
			return;
		}
			
		if(conn!=null) {
			String sql = "update loc set jing=?,wei=?";
			String sq  = "SELECT * FROM status ORDER BY id DESC LIMIT 1";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,jingStr);
				ps.setString(2,weiStr);
				ps.executeUpdate();
				
				Statement sm = conn.createStatement();
				ResultSet rs = sm.executeQuery(sq);
				while(rs.next()) {
					String flag = rs.getString("flag");
					out = response.getWriter();
					out.print(flag);
					System.out.println(flag);
				}
				out.close();
				rs.close();
				sm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
