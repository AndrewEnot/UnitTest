package org.example.services;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.model.BasicReport;
import org.example.model.ShopReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * @author Oksiuta Andrii
 * 21.12.2022
 * 12:00
 */
class ReportReaderTest {

  @Test
  void testGetReportReader() {
    File fileTestOne = new File(
        "C:/Users/7not9/IdeaProjects/UnitTest/reportcommander/src/test/java/inputTest/order_1.csv");
    File fileTestTwo = new File(
        "C:/Users/7not9/IdeaProjects/UnitTest/reportcommander/src/test/java/inputTest/order_2.csv");
    File fileTestNotExist = new File(
        "C:/Users/7not9/IdeaProjects/UnitTest/reportcommander/src/test/java/inputTest/order_3.csv");

    Map<String, List<ShopReport>> testReport = new HashMap<>();
    testReport.put("АТБ", Lists.newArrayList(
        new ShopReport("АТБ", "Сахар", 21.25, 170),
        new ShopReport("АТБ", "Мука", 26.5, 80),
        new ShopReport("АТБ", "Гречка", 31.25, 120),
        new ShopReport("АТБ", "Сахар", 20.25, 170),
        new ShopReport("АТБ", "Мука", 23.5, 80),
        new ShopReport("АТБ", "Молоко", 21.4, 110)));
    testReport.put("Сильпо", Lists.newArrayList(
        new ShopReport("Сильпо", "Гречка", 31.25, 24),
        new ShopReport("Сильпо", "Сахар", 22.20, 107),
        new ShopReport("Сильпо", "Мука", 24.2, 99),
        new ShopReport("Сильпо", "Гречка", 31.85, 24),
        new ShopReport("Сильпо", "Сахар", 21.40, 107),
        new ShopReport("Сильпо", "Мука", 22.2, 99)));
    BasicReport reportReaderExpected = new BasicReport(testReport);

    BasicReport reportReaderActual = ReportReader.getReportReader(fileTestOne, fileTestTwo);

    Assertions.assertEquals(reportReaderExpected, reportReaderActual);

    //testing if file doesn't exist
    Assertions.assertNotNull(ReportReader.getReportReader(fileTestNotExist));
  }
}
