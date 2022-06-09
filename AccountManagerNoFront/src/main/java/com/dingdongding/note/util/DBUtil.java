package com.dingdongding.note.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {
  //    1.获取数据库连接
  String url =
      "jdbc:mysql://localhost:3306/Account?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useServerPrepStmts=true";
  String username = "root";
  String password = "";
  PreparedStatement ps = null;
  Connection connection = null;
  // 封装连接通道
  public Connection getCon() {
    try {
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  // 封装交通工具对象创建细节
  public PreparedStatement createStatement(String sql) {
    try {
      ps = getCon().prepareStatement(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ps;
  }

  // ps与con销毁细节 insert，update,delete
  public void close() {
    if (ps != null) {
      try {
        ps.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (connection != null) {

      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
