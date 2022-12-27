package org.example.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.example.api.MyException;
import org.example.model.BasicReport;
import org.example.model.ShopReport;

/*
 * @author Oksiuta Andrii
 * 21.12.2022
 * 12:00
 */
public class ReportReader {

  /**
   * This class is functional only for implementing method getReportReader
   */

  //This method read variable quantity of outer files *.csv and combine them to BasicReport
  public static BasicReport getReportReader(@NonNull File... var) {
    Map<String, List<ShopReport>> innerMap = new HashMap<>();
    BasicReport basic = new BasicReport(innerMap);
    for (File file : var) {
      Map<String, List<ShopReport>> reportMap = load(file);
      for (String s : reportMap.keySet()) {
        if (innerMap.containsKey(s)) {
          innerMap.get(s).addAll(reportMap.get(s));
        } else {
          innerMap.put(s, reportMap.get(s));
        }
      }
    }
    return basic;
  }

  //This method read file *.csv and transforms it to map
  private static Map<String, List<ShopReport>> load(File file) {
    Map<String, List<ShopReport>> resultMap = new HashMap<>();
    if (file.length() != 0) {

      try (FileReader reader = new FileReader(file)) {

        char[] charArr = new char[(int) file.length()];
          reader.read(charArr);
        List<String> strings = new ArrayList<>(List.of(Arrays.toString(charArr).split("\r, \n")));
        strings.remove(0);

        List<ShopReport> reports = new ArrayList<>();

        for (String s : strings) {
          List<String> orders = new ArrayList<>(List.of(s.replace(", ", "")
              .replace("]", "")
              .split(";")));
          reports.add(
              new ShopReport(orders.get(0), orders.get(1), Double.parseDouble(orders.get(2)),
                  Double.parseDouble(orders.get(3))));
        }

        reports.stream()
            .map(ShopReport::getBrand)
            .distinct()
            .forEach(key -> resultMap.put(key,
                reports.stream().filter(a -> a.getBrand().equals(key)).collect(
                    Collectors.toList())));

      } catch (IOException e) {
        throw new MyException("Some problems with reading File!!!");
      }
    }
    return resultMap;
  }
}
