package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 26.12.2022
 * 22:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopReport {

  private String brand;
  private String product;
  private double price;
  private double quantity;

  public static ShopReport getShopReport(String brand, String product, double price, double quantity) {
    return new ShopReport(brand, product, price, quantity);
  }

  public String toStringToPrint() {
    return product.toUpperCase() + ';' +
        price + ';' + quantity;
  }
}
