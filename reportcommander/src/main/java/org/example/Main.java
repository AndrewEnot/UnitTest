package org.example;

import java.io.File;
import org.example.services.ReportPrint;
import org.example.services.ReportReader;

public class Main {

  public static void main(String[] args) {
    String fileOne = "C:/Users/7not9/IdeaProjects/UnitTest/reportcommander/src/main/resources/order_1.csv";
    String fileTwo = "C:/Users/7not9/IdeaProjects/UnitTest/reportcommander/src/main/resources/order_2.csv";
    String pathToReport = "C:/Users/7not9/IdeaProjects/UnitTest/reportcommander/src/main/output/";

    ReportPrint.printReport(ReportReader.getReportReader(new
            File(fileOne), new
            File(fileTwo)),
        "Сильпо",
        pathToReport);
  }
}