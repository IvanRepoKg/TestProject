package ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFactory {
	
	public static ExtentReports get_ExtentReportInstance() {
		
		ExtentReports extent;
		//String path="C:\Users\Ivan\eclipse-workspace\TransPerfect\ExtentReports\Task1";
		//gornja linija je nepotrebna jer su promenili nacin...sada se path prosledjuje objektu klase ExtentSparkReporter
		extent=new ExtentReports();
		return extent;
		}
		
		public static ExtentSparkReporter get_ExtentSparkReporterInstance(String path)
		{
			ExtentSparkReporter spark;
			//String path="D:\\Udemy\\Mastering Software Testing & Beyond_ A Complete BootCamp\\Extent Reports\\GreenkartReport_TC002";
			spark= new ExtentSparkReporter(path);
			return spark;
		}

}
