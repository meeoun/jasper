package jasper;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.swing.JRViewer;



@Controller
public class HomeController {	
	
	@RequestMapping("/")
	public String showPage(HttpServletResponse response, Model model){
        
		return "main-menu";
	}

}
