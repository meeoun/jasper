package jasper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JRException;

@Controller
public class ReportController {
	
	@RequestMapping("/Report")
	public String reportOne(HttpServletRequest request, Model model) {
		
		String host = request.getHeader("host");
		
		try {
			String pageNumber = "0";
			String state = "";
			if(request.getParameter("nextPushed") != null)
			{
				pageNumber = request.getParameter("next");
				
			}else if(request.getParameter("previousPushed") != null)
			{
				pageNumber = request.getParameter("previous");
			}
			
			if(request.getParameter("state")!=null)
			{
				state=request.getParameter("state");
			}
			
			if(state.length() < 1)
			{
				state = null;
			}
			
			
		    int newNumber = Integer.parseInt(pageNumber);
			Reports reports = new Reports();
			ByteArrayOutputStream report = reports.reportOneHTML(newNumber,state);
			model.addAttribute("report",report);
			int next = newNumber + 1;
			int previous = newNumber - 1;
			model.addAttribute("next",next);
			model.addAttribute("previous",previous);
			model.addAttribute("host",host);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Report";
		
	}

}
