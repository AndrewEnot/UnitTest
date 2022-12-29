package org.example.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
public abstract class ReportReader {

  /**
   * This class is functional only for implementing method getReportReader
   */

  //This method read variable quantity of outer files *.csv and combine them to BasicReport
  public static BasicReport getReportReader(@NonNull File... files) {

    Map<String, List<ShopReport>> innerMap = new HashMap<>();
    BasicReport basic = new BasicReport(innerMap);

    for (File file : files) {
      if (!file.exists()) {
        break;
      }
      Map<String, List<ShopReport>> reportMap = logic(fileReader(file));
      combiner(innerMap, reportMap);
    }
    return basic;
  }

  //this method joint two maps
  private static void combiner(Map<String, List<ShopReport>> innerMap,
      Map<String, List<ShopReport>> reportMap) {
    for (Entry<String, List<ShopReport>> stringListEntry : reportMap.entrySet()) {
      String key = stringListEntry.getKey();
      List<ShopReport> value = stringListEntry.getValue();
      if (innerMap.containsKey(key)) {
        innerMap.get(key).addAll(value);
      } else {
        innerMap.put(key, value);
      }
    }
  }

  //this method read char[] and transforms it to map
  private static Map<String, List<ShopReport>> logic(char[] chars) {
    Map<String, List<ShopReport>> resultMap = new HashMap<>();
    List<ShopReport> reports = new ArrayList<>();

    List<String> strings = new ArrayList<>(List.of(Arrays.toString(chars)
        .split("\r, \n")));
    strings.remove(0);
    for (String s : strings) {
      List<String> orders = new ArrayList<>(List.of(s
          .replace(", ", "")
          .replace("]", "")
          .replace("[", "")
          .split(";")));
      reports.add(
          new ShopReport(orders.get(0), orders.get(1), Double.parseDouble(orders.get(2)),
              Double.parseDouble(orders.get(3))));
    }
    reports.stream()
        .map(ShopReport::getBrand)
        .distinct()
        .forEach(key -> resultMap.put(key, reports.stream()
            .filter(a -> a.getBrand().equals(key))
            .collect(Collectors.toList())));

    return resultMap;
  }

  //this method read file to char array
  private static char[] fileReader(File file) {
    char[] chars = new char[0];
    if (file != null) {
      chars = new char[(int) file.length()];
      try (FileReader reader = new FileReader(file)) {
        int read = reader.read(chars);
        if (read == -1) {
          return chars;
        }
      } catch (IOException e) {
        throw new MyException("Some problems with reading File!!!");
      }
    }
    return chars;
  }
}
