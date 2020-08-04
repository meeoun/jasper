package jasper;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.engine.JasperExportManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;





public class Reports {

	private Connection conn;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	public HtmlExporter report;
	private HashMap params;
	
	public Reports() throws ClassNotFoundException {
		/*try {
			this.initialization();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.reportOne(response);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	
	
	
	private void mysqlInitialization() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = DriverManager.getConnection("jdbc:mysql://mysql.conciseit.net:3306/classicmodels","ironclad","Ironclad2020!");

	}
	
	
	private void reportOne(HttpServletResponse response) throws JRException, IOException {
		this.params = new HashMap();
		this.params.put("stateParameter", null);
		this.jasperReport = JasperCompileManager.compileReport("C:\\Users\\kpark\\JaspersoftWorkspace\\MyReports\\mysql.jrxml");
		this.jasperPrint = JasperFillManager.fillReport(this.jasperReport,this.params, this.conn);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(this.jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
	
		
	}
	
	
	
	public ByteArrayOutputStream reportOneHTML(int page, String state) throws JRException, IOException {
		try {
			this.mysqlInitialization();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.params = new HashMap();
		this.params.put("stateParameter", state);
		final InputStream stream = this.getClass().getResourceAsStream("mysqlParameter.jrxml");
		this.jasperReport = JasperCompileManager.compileReport(stream);
		this.jasperPrint = JasperFillManager.fillReport(this.jasperReport,this.params, this.conn);
		int size =this.jasperPrint.getPages().size()-1;
		if (page > size){
			page = size;
		}
		if(page < 0)
		{
			page = 0;
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(this.jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
		SimpleHtmlReportConfiguration reportConfig = new SimpleHtmlReportConfiguration();
		reportConfig.setStartPageIndex(page);
		reportConfig.setEndPageIndex(page);
		exporter.setConfiguration(reportConfig);
		exporter.exportReport();
		//JasperExportManager.exportReportToPdfFile(this.jasperPrint,"C:\\Users\\kpark\\Downloads\\reportone.pdf");
		return out;
		//this.report = new JRViewer(jasperPrint);
		
	}
	
	
	
	
	
	
}
