package plane.job;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import plane.service.WhatDay;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobDataMap;

import static org.quartz.SimpleScheduleBuilder.*;

public class JobSchedule {
	private Scheduler s;
	
	public void todayCollect() throws SchedulerException {
		s = StdSchedulerFactory.getDefaultScheduler();
		
		s.start();
		
		JobDetail jobAll = newJob(CollectAll.class).withIdentity("collectAll", "group1").build();
		
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(simpleSchedule().withIntervalInSeconds(60).withRepeatCount(3))
				.build();
		
		s.scheduleJob(jobAll, trigger);
		
	}
	
	public void shutCollect() throws SchedulerException {
		s.shutdown();
	}
}
