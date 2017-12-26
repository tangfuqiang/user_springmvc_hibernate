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
    <title>用户新增</title>
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
    <p style="background-color: #C3C3C3" class="left_p2"><a href="<%=basePath%>add/toaddpage"
                                                            class="left_a">用户新增</a></p>
    <hr>
    <p class="left_p2"><a href="<%=basePath%>index/tochartpage" class="left_a">用户分析</a></p>
    <img src="<%=basePath%>images/logo.png" class="left_img">
    <p><a href="#" class="login">当前用户登录</a></p>
</div>
<div class="main">

    <br>
    <div class="main_title">新增&编辑用户信息</div>

    <div class="file">
        <div class="uploadimage">
            <input type="file" accept="image/*" id="file" class="upfile">
            <label for="file" id="imageLable">
                <img src="<%=basePath%>images/avator.png" class="main_img">
            </label>
        </div>
        <br>
        <input type="button" value="上传头像" id="uploadbutton">
    </div>

    <br>
    <div class="main_input">
        &nbsp&nbsp&nbsp用户名称：<input type="text" placeholder="请输入您的用户名" id="username" class="input"
                                   required="required">
        <span id="user_mesage"></span>
        <br><br>
        &nbsp&nbsp&nbsp账 &nbsp&nbsp &nbsp&nbsp号：<input type="text" placeholder="请输入您的用户名" id="account" class="input"
                                                       required="required"> <span id="account_mesage"></span>
        <br><br>
        &nbsp&nbsp&nbsp登录密码：<input type="password" placeholder="请输入您的密码" id="paw" class="input" required="required">
        <span id="paw_mesage"> </span>
        <br>
        <br>
        &nbsp&nbsp&nbsp重复密码：<input type="password" placeholder="请重复您的密码" id="okpaw" class="input"
                                   required="required">
        <span id="okpaw_mesage"></span>
        <br>
        <br>
        &nbsp&nbsp&nbsp电子邮箱：<input type="email" placeholder="请输入您的邮箱" id="em" class="input" required="required">
        <span id="em_mesage"></span>
        <br>
        <br>
        &nbsp&nbsp&nbsp出生日期：<input type="date" id="birthday" class="input" required="required">
        <br>
        <br>
        &nbsp&nbsp&nbsp性&nbsp&nbsp&nbsp&nbsp别： <input type="radio" name="sex" value="男">男
        <input type="radio" name="sex" value="女">女
        <br>
        <br>
        &nbsp&nbsp&nbsp职&nbsp&nbsp&nbsp&nbsp业：<select class="input" id="occupation">
        <c:forEach items="${occupatiolist}" var="o">
            <option>${o.occupation}</option>
        </c:forEach>
    </select>
        <br>
        <br>
        &nbsp&nbsp&nbsp爱&nbsp&nbsp&nbsp&nbsp好： <select multiple="multiple" class="input" id="hobby">
        <c:forEach items="${hobbyList}" var="h">
            <option value="${h.hobby}">${h.hobby}</option>
        </c:forEach>
    </select>
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
    $(document).ready(function () {
        var imgcheck = false;
        var imgurl;
        var url = "<%=basePath%>";
        $('#save').click(function () {
            if (!imgcheck) {
                alert('请先上传头像');
            } else {
                var myreg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
                var password = $('#paw').val();
                var okpassword = $('#okpaw').val();
                var occupation = $('#occupation option:selected').text();
                var hobby = $('#hobby').val();
                var sex = $('input[type=\'radio\']:radio:checked').val();
                var username = $('#username').val();
                var email = $('#em').val();
                var birthday = $('#birthday').val();
                var account = $('#account').val();
                if (username == "" || password == "" || okpassword == "" || email == "") {
                    alert('请在输入框输入相应的值');
                } else if (!myreg.test(email)) {
                    alert('邮箱格式错误');
                }
                else if (okpassword != password) {
                    alert('确认密码与密码不相同');
                } else {
                    var check = confirm("确定新增这个用户？");
                    if (check) {

                        $.ajax({
                            type: "POST",
                            url: "<%=basePath%>add/adduser",
                            data: {
                                "account": account,
                                "username": username,
                                "password": password,
                                "occupation": occupation,
                                "hobby": hobby.join(),
                                "sex": sex,
                                "email": email,
                                "imageurl":imgurl,
                                "birth": birthday
                            },

                            success: function (json) {
                                if (!json.check) {
                                    alert('用户名已存在，请重新输入');
                                } else {
                                    alert('添加成功');

                                    $('#paw').val("");
                                    $('#okpaw').val("");
                                    $('#username').val("");
                                    $('#em').val("");
                                    $('#birthday').val("");
                                    $('#hobby').val("");
                                    var radios = $('input[type=\'radio\']');
                                    for (var i = 0; i < radios.length; i++) {
                                        if (radios[i].checked) {
                                            radios[i].checked = false;
                                        }
                                    }

                                }
                                imgcheck = false;
                                return false;
                            },
                            error: function (json) {
                                imgcheck = false;
                                alert('内部错误')
                                return false;
                            }
                        })

                        return false;
                    }

                }
            }
        })

        $('#uploadbutton').click(function () {
            var fromData = new FormData();
            fromData.append("file", $("#file")[0].files[0]);
            $.ajax({
                url: "<%=basePath%>add/uploadimg",
                type: "post",
                data: fromData,//文件
                dataType: "json",
                processData: false,
                contentType: false,
                success: function (json) {
                    $('.main_img').attr('src',url+"userimg/"+json.url);
                    imgurl = json.url;
                    imgcheck = true;
                    return false;
                },
                error: function () {
                    alert('error');
                    return false;
                }

            })
        })

    })
</script>

</html>
