package plane.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import plane.model.SearchKeyWord;
import plane.service.CollectService;
import plane.service.WhatDay;

public class CollectAll implements Job {
	private CollectService cs;

	public CollectAll() {}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		cs = new CollectService();
		
		// 오늘 항공편, 가격 조사 -> DB입력
		dayOfCollect(WhatDay.isToday());
		// 내일
		dayOfCollect(WhatDay.addDay(1));
	}
	
	private void dayOfCollect (String day) {
		List<SearchKeyWord> list = cs.allRoute(day);
		
		for (int i = 0; i < list.size(); i++) {
			cs.oneRoutePlane(list.get(i).getDepLoc(), list.get(i).getArrLoc(), list.get(i).getDate());
			cs.oneRouteFee(list.get(i).getDepLoc(), list.get(i).getArrLoc(), list.get(i).getDate());
		}
	}
}
