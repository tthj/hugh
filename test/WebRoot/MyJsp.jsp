<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function f1(){
			date = new Date();
			var y = "";
			for(var j=10;j>0;j-- ){
				y += ""+parseInt(Math.random()*10);
			}
		document.getElementById("button").value = y;
		}
		
		var sh;
		function f2(){
		sh=window.clearInterval(sh);
		sh=setInterval(f1,100);
		} 
		
	
	</script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <input type="button" onclick="f2()" value="本期中奖号码"> 
    <input type="button"  id="button" onclick="sh=window.clearInterval(sh)"> 
  </body>
</html>
