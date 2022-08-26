package plane.service;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import plane.model.SearchKeyWord;

public class JobCollect implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		CollectService cs = new CollectService();
		
		List<SearchKeyWord> list = cs.allRoute(WhatDay.isToday());
		for (int i = 0; i < list.size(); i++) {
			cs.oneRoutePlane(list.get(i).getDepLoc(), list.get(i).getArrLoc(), list.get(i).getDate());
		}
	}
}
