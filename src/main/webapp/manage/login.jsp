<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<style type="text/css"> 
.align-center{ 
position:fixed;left:50%;top:50%;margin-left:width/2;margin-top:height/2;
width:500px; /* 给个宽度 顶到浏览器的两边就看不出居中效果了 */ 
text-align:center; /* 文字等内容居中 */ 
}
.loginPanel{
	background-image: url(resources/images/login_background.png);
	background-size:100%;
}
</style>
<body class="loginPanel">
<div class="align-center">
<form action="login.do" method="POST">
    <table>
        <tbody>
         <tr>
            <td colspan=2><font color="red" size="4">
            <%
			if (request.getParameter("error")!=null)//用request得到
				 out.println("错误提示："+request.getParameter("error"));
			%>
            </font></td>
        </tr>
        <tr>
            <td><font color="white" size="4">用户名:</font></td>
            <td><input type="text" name="userName" value=""></td>
        </tr>
        <tr>
            <td><font color="white" size="4">密码:</font></td>
            <td><input type="password" name="passWord"></td>
        </tr>
        <tr>
            <td></td>
            <td><input name="submit" type="submit" value="Login"></td>
        </tr>
        </tbody>
    </table>
</form>
</div> 
</body>