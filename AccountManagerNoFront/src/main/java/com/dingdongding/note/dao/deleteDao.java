package com.dingdongding.note.dao;

import com.dingdongding.note.util.DBUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteDao {
  private final DBUtil util = new DBUtil();

  public int delete(int id) {
    int result = 0;
    try {
      String sql1 = "SELECT price FROM Account.new_table where id = ?";
      String sql2 = "UPDATE Account.new_table SET remarks = 'canceled' WHERE ID = ?";
      String sql3 = "SELECT MAX(id) from Account.new_table";
      String sql4 = "update Account.new_table set balance = ? where id = ?";
      // 创建statement 类对象，用来执行SQL语句
      PreparedStatement ps = util.createStatement(sql1);
      ps.setInt(1, id);
      // 执行SQL1语句，返回结果集合
      ResultSet DeletePrice = ps.executeQuery();
      BigDecimal dp = BigDecimal.valueOf(0);
      if (DeletePrice.next()) {
        //        获取集合中的价格
        dp = DeletePrice.getBigDecimal("price");
      }
      //      执行sql2 给要删除的数据行的remarks 添加canceled
      PreparedStatement prs = util.createStatement(sql2);
      prs.setInt(1, id);
      //      返回结果，成功为1，不成功为0
      result = prs.executeUpdate();
      //      获取最后一个余额数并加上删除行数的价格
      exBalanceCheck exBalanceCheck = new exBalanceCheck();
      BigDecimal ebc = exBalanceCheck.ebc();
      ebc = ebc.add(dp);
      // 执行SQL3，添加最后余额进入数据库最后一行
      PreparedStatement ps3 = util.createStatement(sql3);
      ResultSet idNub = ps3.executeQuery();
      int idnub = 0;
      while (idNub.next()) {
        idnub = idNub.getInt(1);
        System.out.println(idnub);
      }
      PreparedStatement ps4 = util.createStatement(sql4);
      ps4.setBigDecimal(1, ebc);
      ps4.setInt(2, idnub);
      ps4.executeUpdate();

      util.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
