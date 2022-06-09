package com.dingdongding.note.dao;

import com.dingdongding.note.util.DBUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class exBalanceCheck {
  private final DBUtil util = new DBUtil();

  public BigDecimal ebc() {
    BigDecimal balance = BigDecimal.valueOf(0);
    try {
      String sql = "select balance from Account.new_table order by id desc limit 1";
      // 创建statement 类对象，用来执行SQL语句
      PreparedStatement ps = util.createStatement(sql);
      // 执行SQL语句，返回结果集
      ResultSet resultSet = ps.executeQuery();
      // 如果结果集里由余额，获取余额，如果没有赋值余额为1000
      if (!resultSet.next()) {
        balance = BigDecimal.valueOf(1000);
      } else {
        balance = resultSet.getBigDecimal("balance");
      }
      resultSet.close();
      util.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return balance;
  }
}
