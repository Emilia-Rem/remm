<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>xxxx</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script> 
  </head>
  <body>

  <form action="${pageContext.request.contextPath}/goods?method=getGoodById">

      <div>
          <input type="text">
      </div>
      <button type="submit"><span></span>&nbsp;&nbsp;搜索</button>
  </form>

  </body>
</html>