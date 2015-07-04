<%--
  Created by IntelliJ IDEA.
  User: lihongxun
  Date: 15/6/30
  Time: 下午4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <form>
    <label>输入用户名查询：<input type="text" name="q"></label>
    <input type="submit" name="查询" />
  </form>

  <s:iterator value="list">
    <s:property value="id"/>
    <s:property value="username"/>
    <s:property value="password"/>
  </s:iterator>
  <p><s:property value="message"/></p>
  </body>
</html>