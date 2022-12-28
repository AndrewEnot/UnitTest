package org.example.model;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 27.12.2022
 * 16:03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicReport {

  private Map<String, List<ShopReport>> report;

}
