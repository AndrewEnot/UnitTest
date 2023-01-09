package org.example.services;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.api.MyException;
import org.example.model.BasicReport;
import org.example.model.ShopReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/*
 * @author Oksiuta Andrii
 * 21.12.2022
 * 12:01
 */
class ReportPrintTest {


  @Test
  void testPrintReport() {

    //Preparing basement for testing, from test file reading an Array, which we gonna
    // checked with result of method in test
    File resultTest = new File(
        "./src/test/java/outputTest/"
            + "Сильпо_TEST.csv");
    char[] testingCharArr = new char[(int) resultTest.length()];
    try (FileReader testingReader = new FileReader(resultTest)) {
      int read = testingReader.read(testingCharArr);
      if (read == -1) {
        return;
      }
    } catch (IOException e) {
      throw new MyException("Exception in test!!! " + this.getClass().getName());
    }

    //Making mock for method
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

    String path = "./src/test/java/outputTest";
    String brand = "Сильпо";

    BasicReport basic = Mockito.mock(BasicReport.class);
    Mockito.when(basic.getReport()).thenReturn(testReport);

    ReportPrint.printReport(basic, brand, path);

    File testedResult = new File(path, brand + "_" + LocalDate.now() + ".csv");
    char[] testedCharArr = new char[(int) testedResult.length()];
    try (FileReader testedReader = new FileReader(testedResult)) {
      testedReader.read(testedCharArr);
    } catch (IOException e) {
      throw new MyException("Exception in test!!! " + this.getClass().getName());
    }

    Assertions.assertArrayEquals(testedCharArr, testingCharArr);

    //testing on NullPointerException
    ReportPrint.printReport(null, null, null);
  }
}
