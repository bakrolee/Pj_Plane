package plane.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewfee")
public class ChartService extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MakeChart mc = new MakeChart();
		
		// 요청객체에 담기
		String date = req.getParameter("date");
	
		mc.getFeeInfo(date, delo, arlo, air, detime)
		
	}
}
