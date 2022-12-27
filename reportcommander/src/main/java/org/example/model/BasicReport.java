package org.example.model;

import java.util.List;
import java.util.Map;
import lombok.Data;

/*
 * @author Oksiuta Andrii
 * 27.12.2022
 * 16:03
 */

@Data
public class BasicReport {

  private final Map<String, List<ShopReport>> report;

}
