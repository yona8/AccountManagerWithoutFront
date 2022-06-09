package com.dingdongding.note.dao;

import com.dingdongding.note.po.Detail;
import com.dingdongding.note.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {
  private final DBUtil util = new DBUtil();

  public int update(Detail detail) {
    int result = 0;
    try {
      String sql =
          "update Account.new_table "
              + "set data = ?,itemsName = ?,quantity = ?,price = ?,balance = ?"
              + " where id = ?";
      // 创建statement 类对象，用来执行SQL语句
      PreparedStatement ps = util.createStatement(sql);
      // 将数据添加到对应数据库位置
      ps.setDate(1, detail.getData());
      ps.setString(2, detail.getItemsName());
      ps.setInt(3, detail.getQuantity());
      ps.setBigDecimal(4, detail.getPrice());
      ps.setBigDecimal(5, detail.getBalance());
      ps.setInt(6, detail.getId());
      // 执行SQL语句，返回结果，执行结果为数据库受影响的行数，如果为0则执行失败
      result = ps.executeUpdate();
      util.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
