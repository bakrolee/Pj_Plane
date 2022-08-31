package plane.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import plane.job.JobSchedule;
import plane.service.CollectService;

public class StartScheduleHandler implements CommandHandler {
	private CollectService cs;
	private JobSchedule js; 
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		js= new JobSchedule();
		js.start();
		js.collectAll();
		js.collectFee();
//		js.expiredPlane();
		
		return "/WEB-INF/view/success.jsp";
	}
}
