<%--
  Created by IntelliJ IDEA.
  User: tangfuqiang
  Date: 2017/11/28
  Time: 下午10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <title>职业新增</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
    <script src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
</head>

<body>
<div class="head">
    <img src="<%=basePath%>images/logo.png" class="img_head">
    <span class="title">用户管理系统</span>
    <div class="div_option">
        <a href="#" class="a_option_exit">退出</a>
        <a href="#" class="a_option_help">帮助</a>
    </div>
</div>
<div class="left">
    <p><a href="<%=basePath%>user/index" class="left_a">用户查询</a></p>
    <hr>
    <p style="background-color: #C3C3C3" class="left_p2"><a href="<%=basePath%>hobby"
                                                            class="left_a">职业新增</a></p>
    <hr>
    <p class="left_p2"><a href="<%=basePath%>occupation" class="left_a">兴趣新增</a></p>
    <img src="<%=basePath%>images/logo.png" class="left_img">
    <p><a href="#" class="login">当前用户登录</a></p>
</div>
<div class="main">

    <br>
    <div class="main_title">新增兴趣</div>


    <br>
    <div class="main_input">
        &nbsp&nbsp&nbsp用户职业：<input type="text" placeholder="请输入职业" id="occupation" class="input"
                                   required="required">
        <span id="occupation_mesage"></span>
    </div>
    <hr>
    <ul class="btn">
        <li>
            <button id="save" type="button">保存</button>
        </li>
        <li>
            <button type="reset" id="reset">重置</button>
        </li>
        <li>
            <button>返回</button>
        </li>

        <span id="mesage"></span>
    </ul>
</div>
</body>
<script>
    $('#save').click(function () {
        var occupation = $('#occupation').val();
        console.log(occupation);
        if($.trim(occupation)==""){
            $('#occupation_mesage').text('新增职业不能为空');
        }else {
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>occupation/addoccupation",
                    data: {
                        occupation: occupation
                    },
                    success: function (json) {
                     if(json.mesage) {
                         alert('职业已添加');
                         $('#occupation').val('');
                         $('#occupation_mesage').text('');
                     }else {
                         $('#occupation_mesage').text('职业已存在');
                         check = false;
                         $('#occupation').focus();
                     }
                        return false;
                    },
                    error: function (json) {
                        alert('服务器内部错误');
                        return false;
                    }
                })
            }
    })

</script>

</html>
