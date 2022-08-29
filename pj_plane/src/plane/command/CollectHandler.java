package plane.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import plane.job.JobSchedule;
import plane.service.CollectService;

public class CollectHandler implements CommandHandler {
	private CollectService cs;
	private JobSchedule js = new JobSchedule();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		js.todayCollect();
		
		return "/WEB-INF/view/success.jsp";
	}

}
