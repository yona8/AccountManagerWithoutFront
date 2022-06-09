package com.dingdongding.note.dao;

import com.dingdongding.note.po.Detail;
import com.dingdongding.note.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class searchDao {
  private DBUtil util = new DBUtil();

  public List<Detail> search() {
    int result = 0;
    List<Detail> details = new ArrayList<>();
    try {
      String sql = "select * from Account.new_table";
      Connection con = util.getCon();
      // 创建statement 类对象，用来执行SQL语句
      PreparedStatement ps = con.prepareStatement(sql);
      // 执行SQL语句,返回集合
      ResultSet resultSet = ps.executeQuery();
      Detail detail = null;
      //  将处理结果 封装对象并装载入集合
      while (resultSet.next()) {
        //  获取数据
        int id = resultSet.getInt("id");
        Date data = resultSet.getDate("data");
        String itemsName = resultSet.getString("itemsName");
        int quantity = resultSet.getInt("quantity");
        BigDecimal price = BigDecimal.valueOf(resultSet.getInt("price"));
        BigDecimal balance = BigDecimal.valueOf(resultSet.getInt("balance"));
        String remarks = resultSet.getString("remarks");
        // 封装对象
        detail = new Detail();
        detail.setId(id);
        detail.setData(data);
        detail.setItemsName(itemsName);
        detail.setQuantity(quantity);
        detail.setPrice(price);
        detail.setBalance(balance);
        detail.setRemarks(remarks);
        // 装载入集合
        details.add(detail);
      }
      resultSet.close();
      util.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return details;
  }
}
