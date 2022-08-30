package plane.job;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import plane.service.WhatDay;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.util.Set;

import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class JobSchedule {
	private Scheduler s;
	
	public JobSchedule () {}
	
	public void start() throws SchedulerException {
		s = StdSchedulerFactory.getDefaultScheduler();
		s.start();
	}
	
	// 강제종료
	public void shut() throws SchedulerException {
		Set<JobKey> allJobKeys = s.getJobKeys(GroupMatcher.anyGroup());
        
        // Job 강제 중단
        allJobKeys.forEach((jobKey)->{
            try {
                s.interrupt(jobKey);
            } catch (UnableToInterruptJobException e) {
                e.printStackTrace();
            }
        });
		s.shutdown(true);
	}
	
	// 특정 스케줄 일시정지
	public void pauseJob(JobKey jobKey) throws SchedulerException {
		s.pauseJob(jobKey);
	}
	// 특정 스케줄 재개
	public void resumeJob(JobKey jobKey) throws SchedulerException {
		s.resumeJob(jobKey);
	}
	
	// 항공권, 가격 조사 작업
	public void collectAll() throws SchedulerException {
		JobDetail job = newJob(CollectAll.class).withIdentity("colAll", "gr1").build();
		
		Trigger trigger = newTrigger().withIdentity("colAll", "gr1")
				.startNow()
				.withSchedule(cronSchedule("0 30 9 * * ?")) // 매일 1회, 9시 30분마다 
				.withPriority(1)
				.build();
		
		s.scheduleJob(job, trigger);
	}
	
	// 가격만 조사
	public void collectFee() throws SchedulerException {
		JobDetail job = newJob(CollectFee.class).withIdentity("colFee", "gr2").build();
		
		Trigger trigger = newTrigger().withIdentity("colFee", "gr2")
				.startNow()
				.withSchedule(cronSchedule("0 0 0/1 * * ?")) // 1시간마다
				.withPriority(2)
				.build();
		
		s.scheduleJob(job, trigger);
	}
	
	// 5분마다 만료된 항공권+가격 데이터 삭제
	public void expiredPlane() throws SchedulerException {
		JobDetail job = newJob(ExpiredPlane.class).withIdentity("expire", "gr3").build();
		
		Trigger trigger = newTrigger().withIdentity("expire", "gr3")
				.startNow()
				.withSchedule(cronSchedule("0 0/5 06-22 * * ?")) // 5분 마다 (6시부터 22시 55분까지)
				.withPriority(3)
				.build();
		
		s.scheduleJob(job, trigger);
	}
	
}
