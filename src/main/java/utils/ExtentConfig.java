package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

import static utils.ConfigReader.EXTENT_CONFIG;
import static utils.ReusableFunctions.getTimeStamp;

public class ExtentConfig {

    public static ExtentReports extentSetup(){
        ExtentReports extent = new ExtentReports();
        try {
            ExtentSparkReporter spark = new ExtentSparkReporter(EXTENT_CONFIG + File.separator + "ExtentReport_" + getTimeStamp() + ".html");
            extent.attachReporter(spark);
            // extent.createTest("MyFirstTest").log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return extent;
    }
}
