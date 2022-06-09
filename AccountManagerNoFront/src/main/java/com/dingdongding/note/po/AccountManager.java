package com.dingdongding.note.po;

import com.dingdongding.note.dao.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AccountManager {
  public static void main(String[] args) throws IOException {
    while (true) {
      int num1;
      System.out.println("-----Welcome to account management");
      System.out.println("1.Add new shopping record");
      System.out.println("2.Query all shopping history");
      System.out.println("3.Cancel shopping record");
      System.out.println("4.Update a shopping history by ID");
      System.out.println("5.Exit");
      System.out.println("Please enter a number between 1 and 5：");
      // 检查用户输入是否为数字
      int n = 0;
      n = InputCheck.inputInt();
      Detail detail = new Detail();
      exBalanceCheck exBalanceCheck = new exBalanceCheck();
      switch (n) {
        case 1:
          System.out.println("1.Add new shopping record");
          System.out.println("Please enter data:Example:dd/mm/yyyy");
          Date data = InputCheck.inputData();
          System.out.println("Please enter itemName：");
          String itemName = InputCheck.inputString();
          System.out.println("Please enter quantity：");
          Integer quantity = InputCheck.inputInt();
          System.out.println("Please enter price：");
          BigDecimal price = InputCheck.inputBigDecimal();
          BigDecimal balance = exBalanceCheck.ebc().subtract(price);
          // 把用户输入的数据封装到detail
          detail.setData(data);
          detail.setItemsName(itemName);
          detail.setQuantity(quantity);
          detail.setPrice(price);
          detail.setBalance(balance);
          addDao addDao = new addDao();
          int result = addDao.add(detail);
          if (result == 1) {
            System.out.println("Add success");

          } else {
            System.out.println("Add failed");
          }
          break;
        case 2:
          System.out.println("2.Query all shopping history");
          searchDao searchDao = new searchDao();
          List<Detail> details = searchDao.search();
          //          打印表格
          System.out.println(
              "---------------------------------------------------------------------------------------------------");
          System.out.printf(
              "%2s %5s %5s %5s %5s %10s %5s %5s %5s %5s %5s %5s %5s %5s %5s",
              "|",
              "id",
              "|",
              "data",
              "|",
              "itemsName",
              "|",
              "quantity",
              "|",
              "price",
              "|",
              "balance",
              "|",
              "remarks",
              "|");
          System.out.println();
          System.out.println(
              "----------------------------------------------------------------------------------------------------");
          for (Detail detail1 : details) {
            System.out.format(
                "%2s %5s %5s %5s %5s %10s %5s %5s %5s %5s %5s %5s %5s %5s %5s ",
                "|",
                detail1.getId(),
                "|",
                detail1.getData(),
                "|",
                detail1.getItemsName(),
                "|",
                detail1.getQuantity(),
                "|",
                detail1.getPrice(),
                "|",
                detail1.getBalance(),
                "|",
                detail1.getRemarks(),
                "|");
            System.out.println();

            System.out.println(
                "--------------------------------------------------------------------------------------------------");
          }

          break;
        case 3:
          System.out.println("3.Cancel shopping record");
          System.out.println("Please enter the id you wanna delete:");
          // 检查用户输入是否为数字
          int id = 0;
          id = InputCheck.inputInt();
          deleteDao deleteDao = new deleteDao();
          int rs = deleteDao.delete(id);
          if (rs == 1) {
            System.out.println(" Delete success");

          } else {
            System.out.println("Delete failed");
          }
          break;
        case 4:
          System.out.println("4.Update a shopping history by ID");
          System.out.println("Please enter data:Example:dd/mm/yyyy：");
          Date newData = InputCheck.inputData();
          System.out.println("Please enter itemName：");
          String newItemName = InputCheck.inputString();
          System.out.println("Please enter quantity：");
          Integer newQuantity = InputCheck.inputInt();
          System.out.println("Please enter price：");
          BigDecimal newPrice = InputCheck.inputBigDecimal();
          System.out.println("Please enter ID：");
          Integer newId = InputCheck.inputInt();
          BigDecimal newBalance = exBalanceCheck.ebc().subtract(newPrice);
          // 把用户输入的数据封装到detail
          detail.setData(newData);
          detail.setItemsName(newItemName);
          detail.setQuantity(newQuantity);
          detail.setPrice(newPrice);
          detail.setBalance(newBalance);
          detail.setId(newId);
          UpdateDao updateDao = new UpdateDao();
          int rt = updateDao.update(detail);
          if (rt == 1) {
            System.out.println("Update success");

          } else {
            System.out.println("Update failed");
          }
          break;
        case 5:
          System.out.println("Are you sure you want to quit? YES enter 1 or No enter 2");
          while (true) {
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            if (i == 1) {
              System.exit(0); // JVM 退出
            } else if (i != 2) {
              System.out.println("Please ONLY enter 1 or 2 ");
            } else {
              break;
            }
          }
          break;
        default:
          System.out.println("Error: The " + n + " is not between 1 and 5, try again");
      }
    }
  }
}
