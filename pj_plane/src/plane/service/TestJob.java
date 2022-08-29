package plane.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import plane.model.SearchKeyWord;

@WebServlet("/feetest")
public class TestJob extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CollectService cs = new CollectService();
		
		// 오늘 항공편, 가격 조사 -> DB입력
		List<SearchKeyWord> list = null;
		try {
			list = cs.allRoute(WhatDay.isToday());
		} catch(Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println("출발지: " + list.get(i).getDepLoc() + "도착지: " + list.get(i).getArrLoc() + 
					"날짜: " + list.get(i).getDate());
//			cs.oneRoutePlane(list.get(i).getDepLoc(), list.get(i).getArrLoc(), list.get(i).getDate());
			cs.oneRouteFee(list.get(i).getDepLoc(), list.get(i).getArrLoc(), list.get(i).getDate());
		}
		
		cs.getCi().getDriver().quit();
	}
	
}
