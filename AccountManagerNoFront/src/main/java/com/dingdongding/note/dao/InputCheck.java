package com.dingdongding.note.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputCheck {

  // 验证用户是否输入的日期格式正确
  public static java.sql.Date inputData() {
    Date data = null;
    java.sql.Date date = null;

    while (true) {
      // 获取用户输入的日期
      Scanner si = new Scanner(System.in);
      String ss = si.nextLine();
      // 利用java中的SimpleDateFormat类，指定日期格式，注意yyyy,MM，dd大小写
      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      try {
        // 用format.parse转化用户输入的日期
        // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，例如38/23/24528 会被接受
        format.setLenient(false);
        data = format.parse(ss);
        // 转化成功，返回 date
        date = new java.sql.Date(data.getTime());
        break;

      } catch (ParseException e) {
        // 转换失败，输出下面语句
        System.out.println("Wrong data format, Input again please");
      }
    }
    return date;
  }
  // 校验用户输入是否为金额
  public static BigDecimal inputBigDecimal() {

    BigDecimal i = BigDecimal.valueOf(0);
    while (true) {
      Scanner si = new Scanner(System.in);
      if (si.hasNextBigDecimal()) {
        i = si.nextBigDecimal();
        if (i.intValue() > 0) {
          return i;
        } else {
          System.out.println("Input must be above 0!! Input again please");
        }
      } else {
        System.out.println("input price format ONLY! Input again please");
      }
    }
    //    return i;
  }
  // 校验用户输入的数字是否为int
  public static int inputInt() {
    while (true) {
      Scanner si = new Scanner(System.in);
      if (si.hasNextInt()) {
        int result = si.nextInt();
        if (result > 0) {
          return result;
        } else {
          System.out.println("Input must be above 0!! Input again please");
        }
      } else {
        System.out.println("input integer ONLY! Input again please");
      }
    }
  }
  // 校验用户输入的字符串是否为空
  public static String inputString() {
    while (true) {
      Scanner si = new Scanner(System.in);
      String ss = si.nextLine();
      if (ss.trim().isEmpty()) { // 需注意这里ss 不能替换成si.nextLine.trim().isEmpty
        System.out.println("The input is empty");
      } else if (isNumeric(ss)) {
        System.out.println("Cannot just be a number");
      } else {
        return ss;
      }
    }
  }
  // 校验用户输入的货品名称是否全都是数字
  public static boolean isNumeric(String s) {
    for (int i = s.length(); --i >= 0; ) {
      if (!Character.isDigit(s.charAt(i))) {
        return false;
      }
    }
    return true;
  }
}
