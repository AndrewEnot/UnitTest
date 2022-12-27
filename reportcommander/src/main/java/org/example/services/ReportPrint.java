package org.example.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.example.model.BasicReport;
import org.example.model.ShopReport;


/*
 * @author Oksiuta Andrii
 * 21.12.2022
 * 12:01
 */
public class ReportPrint {

  /**This class is functional only for implementing method printReport
   * This method write to File result of processing of BasicReport.
   * At the ehd we will get file .csv
   * */

  public static void printReport(@NonNull BasicReport basic, @NonNull String brand, String path) {
    File result = new File(path, brand + "_" + LocalDate.now() + ".csv");

    List<ShopReport> reportsToPrint = new ArrayList<>();

    List<ShopReport> reports = basic.getReport().get(brand);
    List<String> products = reports.stream().map(ShopReport::getProduct).distinct()
        .collect(Collectors.toList());
    for (int i = 0; i < products.size(); i++) {
      int finalI = i;
      List<ShopReport> parties = reports.stream()
          .filter(a -> a.getProduct().equals(products.get(finalI))).collect(Collectors.toList());
      double sumSales = parties.stream().map(a -> a.getPrice() * a.getQuantity())
          .reduce(Double::sum).orElse(0.0);
      double sumQuantity = parties.stream().map(ShopReport::getQuantity).reduce(Double::sum)
          .orElse(0.1);
      double avPrice = Math.ceil((sumSales / sumQuantity) * Math.pow(10, 3)) / Math.pow(10, 3);
      reportsToPrint.add(ShopReport.getShopReport(brand, products.get(finalI), avPrice, sumQuantity));
    }

    try (FileWriter printToFile = new FileWriter(result)) {
      printToFile.write("НАИМЕНОВАНИЕ;ЦЕНА;ШТ;\n");
      for (ShopReport shopReport : reportsToPrint) {
        printToFile.write(shopReport.toStringToPrint() + ";\n");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
