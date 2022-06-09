package com.dingdongding.note.po;

import java.math.BigDecimal;
import java.sql.Date;

public class Detail {

  private Date data;
  private String itemsName;
  private Integer quantity;
  private BigDecimal price;
  private BigDecimal balance;
  private Integer id;
  private String remarks;

  public Detail() {}

  public Detail(
      Date data,
      String itemsName,
      Integer quantity,
      BigDecimal price,
      BigDecimal balance,
      Integer id,
      String remarks) {
    this.data = data;
    this.itemsName = itemsName;
    this.quantity = quantity;
    this.price = price;
    this.balance = balance;
    this.id = id;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public String getItemsName() {
    return itemsName;
  }

  public void setItemsName(String itemsName) {
    this.itemsName = itemsName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public String toString() {
    return "Detail{"
        + "data="
        + data
        + ", itemsName='"
        + itemsName
        + '\''
        + ", quantity="
        + quantity
        + ", price="
        + price
        + ", balance="
        + balance
        + ", id="
        + id
        + ", remarks='"
        + remarks
        + '\''
        + '}';
  }
}
