package org.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/*
 * @author Oksiuta Andrii
 * 26.12.2022
 * 22:35
 */
class ShopReportTest {

  private static ShopReport testReport;

  @BeforeAll
  public static void prepare() {
    testReport = new ShopReport("TEST", "test", 10.0, 10.0);
  }

  @Test
  void testGetShopReport() {

    ShopReport report = ShopReport.getShopReport("TEST", "test", 10.0, 10.0);
    Assertions.assertEquals(testReport, report);

    ShopReport reportNull = ShopReport.getShopReport(null, null, 10.0, 10.0);
    Assertions.assertNotNull(reportNull);
  }

  @Test
  void testToStringToPrint() {
    String expectedString = "TEST;10.0;10.0";
    String actualString = testReport.toStringToPrint();

    Assertions.assertEquals(expectedString, actualString);

  }
}
