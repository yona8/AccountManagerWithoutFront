package com.dingdongding.note.dao;

import com.dingdongding.note.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteOneDao {
  private final DBUtil util = new DBUtil();

  public int delete(int id) {
    int result = 0;
    try {
      String sql1 = "SELECT * FROM Account.new_table where id = ' ？' ";
      String sql2 =
          "insert into Account.new_table(id,data,itemsName,quantity,price,balance) values(?,?,?,?,?)";

      // 创建statement 类对象，用来执行SQL语句
      PreparedStatement ps = util.createStatement(sql1);
      //      ps.setInt(1, id);
      //        执行SQL语句，返回结果
      ResultSet resultSet = ps.executeQuery();
      // 创建集合
      // 将处理结果 封装对象并装载入集合
      while (resultSet.next()) {
        //                获取数据
        int Id = resultSet.getInt("id");
        int data = resultSet.getInt("data");
        System.out.println(data);
        String itemName = resultSet.getString("itemsName");
        int quantity = resultSet.getInt("quantity");
        int price = resultSet.getInt("price");
        int Exbalance = resultSet.getInt("balance");
        ps.setInt(1, Id);
        ps.setInt(2, data);
        ps.setString(3, itemName);
        ps.setInt(4, -quantity);
        ps.setInt(5, -price);
        int balance = Exbalance + price;
        ps.setInt(6, balance);

        PreparedStatement ps2 = util.createStatement(sql2);
        result = ps2.executeUpdate();
      }
      resultSet.close();
      util.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
