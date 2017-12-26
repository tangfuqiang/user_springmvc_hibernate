<%--
  Created by IntelliJ IDEA.
  User: tangfuqiang
  Date: 2017/12/9
  Time: 下午10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <title>用户查询</title>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
    <script src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
    <script src="<%=basePath%>js/jquery.twbsPagination.min.js"></script>
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

    <p style="background-color: #C3C3C3"><a href="<%=basePath%>user/index" class="left_a">用户查询</a></p>
    <hr>
    <p class="left_p2"><a href="<%=basePath%>hobby" class="left_a">兴趣新增</a></p>
    <hr>
    <p class="left_p2"><a href="<%=basePath%>occupation" class="left_a">职业新增</a></p>
    <img src="<%=basePath%>images/logo.png" class="left_img">
    <p><a href="#" class="login">当前用户登录</a></p>
</div>

<div class="main">
    <div>

        <br>
        &nbsp&nbsp&nbsp用户名称：
        <input type="text" name="username" id="username" required="required"> &nbsp&nbsp
        <button id="query">查询</button>
        <input type="button" id="getexcel" value="导出文件">
        <input type="button" value="批量删除" id="checkremove">
        <br>
        <br>
        <table border="1" cellspacing="
    0" id="table">
            <tr class="one">
                <td><input type="checkbox" id="allcheck"/>ID</td>
                <td class="td_username">用户名称</td>
                <td class="ß">职业</td>
                <td class="td_email">邮箱</td>
                <td class="td_sex">性别</td>
                <td class="td_speciality">爱好</td>
                <td>生日</td>
                <td>创建时间</td>
                <td class="td_handle">操作</td>
            </tr>
            <c:forEach items="${userlist}" var="user">
                <tr class="tr_user">
                    <td class="td_id"><input type="checkbox" value="${user.id}" name="idcheck"/>${user.id}</td>
                    <td class="td_username">${user.username}</td>
                    <td class="td_profession">${user.occupation}</td>
                    <td class="td_email">${user.email}</td>
                    <td class="td_sex">${user.sex}</td>
                    <td class="td_speciality">${user.hobby}</td>
                    <td class="td_birthday" style='width: 100px'>${user.birthday}</td>
                    <td class="td_createtime"><fmt:formatDate value="${user.createtime}"
                                                              pattern="YYYY-MM-dd HH:mm:ss"/></td>
                    <td class="td_handle" style='width:100px '>
                        <a class="update"><span href="#">编辑</span></a>

                        <a class="save" style="display: none;">
                            <span href="#" style='width: 20px'>保存</span></a>

                        &nbsp&nbsp<a class="remove"><span>删除</span></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="page1">
    <div class="am-cf">
        <div class="am-fr">
            <ul id="pagination-demo" class="pagination-sm"></ul>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var paginator;
    var total;
    var start;
    $(document).ready(function () {

        //分页插件
        paginator = $('#pagination-demo').twbsPagination({
            totalPages: ${querytotalPage},
            visiblePages: 3,
            startPage:${startPage},
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                $('#page-content').text('Page ' + ${startPage});
                window.location.href = "<%=basePath%>user/nextquerypage?pageNo=" + page + "&username=${username}";
            }
        });

        //删除
        $('#table').on('click', '.remove', function () {
            var id = $(this).parent().siblings('.td_id').text();
            var check = confirm("确定删除？");
            if (check) {
                var $pagination = $('#pagination-demo');
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>user/removequryuser",
                    data: {
                        id: id,
                        pageNo:${startPage},
                        username: '${username}',
                    },
                    dataType: "json",
                    success: function (json) {
                        $('.tr_user').remove();
                        var counts = eval(json.newuserlist).length;
                        for (var i = 0; i < counts; i++) {

                            var tr = " <tr class=\"tr_user\">\n" +
                                "                    <td class=\"td_id\"><input type=\"checkbox\" name='idcheck' value=\"" + json.newuserlist[i].id + "\"/>" + json.newuserlist[i].id + "</td>\n" +
                                "                    <td class=\"td_username\">" + json.newuserlist[i].username + "</td>\n" +
                                "                    <td class=\"td_profession\">" + json.newuserlist[i].occupation + "</td>\n" +
                                "                    <td class=\"td_email\">" + json.newuserlist[i].email + "</td>\n" +
                                "                    <td class=\"td_sex\">" + json.newuserlist[i].sex + "</td>\n" +
                                "                    <td class=\"td_speciality\">" + json.newuserlist[i].hobby + "</td>\n" +
                                "                    <td class=\"td_birthday\" style='width: 180px'>" + json.newuserlist[i].birthday + "</td>\n" +
                                "                    <td class=\"td_createtime\">" + json.newuserlist[i].createtime + "</td>\n" +
                                "                    <td class=\"td_handle\" style='width:100px '><a class=\"update\"><span href=\"#\">编辑</span></a>" +
                                "                    <a class=\"save\" style=\"display: none;\">" +
                                "                    <span href=\"#\">保存</span></a>" +
                                "                    &nbsp&nbsp<a class=\"remove\" ><span >删除</span></a></td>\n" +
                                "                </tr>"
                            $('#table').append(tr);
                        }
                        start = json.startPage;
                        total = ${totalPage};
                        $pagination.twbsPagination('destroy');
                        $pagination.twbsPagination($.extend({}, {
                            startPage: (start),
                            totalPages: (total),
                            initiateStartPageClick: false,
                            onPageClick: function (event, page) {
                                $('#page-content').text('Page ' + ${startPage});
                                window.location.href = "<%=basePath%>user/nextpage?startPage=" + page;
                            }
                        }));
                    },
                    error: function (json) {
                        alert('服务器内部错误');
                        return false;
                    }
                })
                return false;
            }


        })

        //查询
        $('#query').click(function () {
            var username = $('#username').val();
            if (username == "") {
                alert("请输入用户名");
            }
            else {
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>user/queryuser",
                    data: {
                        username: username,
                    },
                    success: function (json) {
                        if (json.check) {
                            window.location.href = '<%=basePath%>index/toquerpage?username=' + username;
                        } else {
                            alert('error:未查询到任何相关信息！');
                        }

                    },
                    error: function (json) {
                        alert("服务器内部错误")
                        return false;
                    }
                })
            }
            return false;
        })

        //编辑
        var username;
        var occupation;
        var sex;
        var email;
        var hobby;
        var birthday;

        var oldusername;
        var oldoccupation;
        var oldsex;
        var oldemail;
        var oldhobby;
        var oldbirthday;
        $('#table').on('click', '.update', function () {
            $(this).css('display', 'none');

            $(this).siblings('.save').css('display', 'inline-block');

            username = $(this).parent().siblings('.td_username');
            occupation = $(this).parent().siblings('.td_profession');
            sex = $(this).parent().siblings('.td_sex');
            email = $(this).parent().siblings('.td_email');
            hobby = $(this).parent().siblings('.td_speciality');
            birthday = $(this).parent().siblings('.td_birthday');

            oldusername = username.text();
            oldoccupation = occupation.text();
            oldsex = sex.text();
            oldemail = email.text();
            oldhobby = hobby.text();
            oldbirthday = birthday.text();


            $.ajax({
                type: "POST",
                url: "<%=basePath%>user/getnews",
                success: function (json) {
                    var ocTotal = eval(json.oclist).length;
                    var ocselect = "<select><option>" + oldoccupation + "</option>";
                    for (var i = 0; i < ocTotal; i++) {
                        if (json.oclist[i].occupation != oldoccupation) {
                            ocselect = ocselect + "<option>" + json.oclist[i].occupation + "</option>"
                        }
                    }
                    ocselect = ocselect + "</select>"

                    username.text("")
                    username.append("<input type='text' class='updateusername' value=" + oldusername + " style='width: 100px'>");

                    occupation.text("")
                    occupation.append(ocselect);

                    sex.text("");
                    if (oldsex == "男") {
                        sex.append("<select>" +
                            "<option>男</option>" +
                            "<option>女</option>" +
                            "</select>");
                    } else {
                        sex.append("<select>" +
                            "<option>女</option>" +
                            "<option>男</option>" +
                            "</select>");
                    }
                    email.text("");
                    email.append("<input type='text' value=" + oldemail + " style='width: 180px'>");

                    hobby.text("");
                    hobby.append("<input type='text' value=" + oldhobby + " style='width: 100px'>");

                    birthday.text("");
                    birthday.append("<input type='date' value=" + oldbirthday + " >");
                },
                error: function (json) {
                    alert("请求失败");
                    return false;
                }
            })
        })


        $('#table').on('click', '.save', function () {
            var now = $(this);
            var verify = confirm("确定修改？");
            if (verify) {
                var newusernaem = username.children('input').val();
                var newsex = sex.children('select ').val();
                var newemail = email.children('input').val();
                var newhobby = hobby.children('input').val();
                var newbirthday = birthday.children('input').val();
                var newoccupation = occupation.children('select').val();
                var id = now.parent().siblings('.td_id').text();

                var usernamecheck = true;
                if (oldusername == newusernaem) {
                    usernamecheck = false;
                }
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>user/updateuser",
                    data: {
                        id: id,
                        username: newusernaem,
                        sex: newsex,
                        email: newemail,
                        hobby: newhobby,
                        birthday: newbirthday,
                        occupation: newoccupation,
                        usernamecheck: usernamecheck,
                    },

                    success: function (json) {
                        if (json.check) {
                            alert('修改成功');
                            username.text(newusernaem);
                            sex.text(newsex);
                            email.text(newemail);
                            hobby.text(newhobby);
                            birthday.text(newbirthday);
                            occupation.text(newoccupation);
                            now.css('display', 'none');
                            now.siblings('.update').css('display', 'inline-block');
                        } else {
                            alert('用户名已存在');
                        }
                    },
                    error: function (json) {
                        alert('服务器内部错误,修改失败');
                        username.text(oldusername);
                        sex.text(oldsex);
                        email.text(oldemail);
                        hobby.text(oldhobby);
                        birthday.text(oldbirthday);
                        occupation.text(oldoccupation);
                        now.css('display', 'none');
                        now.siblings('.update').css('display', 'inline-block');
                    }

                })

                return false;

            } else {

                now.css('display', 'none');
                now.siblings('.update').css('display', 'inline-block');
                username.text(oldusername);
                sex.text(oldsex);
                email.text(oldemail);
                hobby.text(oldhobby);
                birthday.text(oldbirthday);
                occupation.text(oldoccupation);
            }
        })

        //导出文件
        $('#getexcel').click(function () {
            var pageNo = ${startPage};
            var username = '${username}';

            window.location.href = "<%=basePath%>user/getqueryexcel?pageNo=" + pageNo + "&username=" + username;

        })

        var allcheck = true;
        $('#allcheck').click(function () {
            if (allcheck) {
                $("[name='idcheck']").prop("checked", true);
            } else {
                $("[name='idcheck']").prop("checked", false);
            }
            allcheck = !allcheck;
        })

        //批量删除
        $('#checkremove').click(function () {
            var idarrays = new Array();
            var index = 0;
            $("[name='idcheck']:checked").each(function () {
                console.log($(this).val());
                idarrays[index] = $(this).val();
                index++;
            });
            if (idarrays == 0) {
                alert('请选择要删除的用户');
            } else {
                if (confirm("确定删除所选用户？")) {
                    $.ajax({
                        type: "POST",
                        url: "<%=basePath%>user/removequerysome",
                        data: {
                            ids: idarrays,
                            pageNo:${startPage},
                            username: '${username}'
                        },

                        success: function (json) {
                            $('.tr_user').remove();
                            var counts = eval(json.newuserlist).length;
                            for (var i = 0; i < counts; i++) {

                                var tr = " <tr class=\"tr_user\">\n" +
                                    "                    <td class=\"td_id\"><input type=\"checkbox\" name='idcheck' value=\"" + json.newuserlist[i].id + "\"/>" + json.newuserlist[i].id + "</td>\n" +
                                    "                    <td class=\"td_username\">" + json.newuserlist[i].username + "</td>\n" +
                                    "                    <td class=\"td_profession\">" + json.newuserlist[i].occupation + "</td>\n" +
                                    "                    <td class=\"td_email\">" + json.newuserlist[i].email + "</td>\n" +
                                    "                    <td class=\"td_sex\">" + json.newuserlist[i].sex + "</td>\n" +
                                    "                    <td class=\"td_speciality\">" + json.newuserlist[i].hobby + "</td>\n" +
                                    "                    <td class=\"td_birthday\" style='width: 180px'>" + json.newuserlist[i].birthday + "</td>\n" +
                                    "                    <td class=\"td_createtime\">" + json.newuserlist[i].createtime + "</td>\n" +
                                    "                    <td class=\"td_handle\" style='width:100px '><a class=\"update\"><span href=\"#\">编辑</span></a>" +
                                    "                    <a class=\"save\" style=\"display: none;\">" +
                                    "                    <span href=\"#\">保存</span></a>" +
                                    "                    &nbsp&nbsp<a class=\"remove\" ><span >删除</span></a></td>\n" +
                                    "                </tr>"
                                $('#table').append(tr);
                            }
                            start = json.startPage;
                            total = json.jsontotal;
                            $pagination.twbsPagination('destroy');
                            $pagination.twbsPagination($.extend({}, {
                                startPage: (start),
                                totalPages: (total),
                                initiateStartPageClick: false,
                                onPageClick: function (event, page) {
                                    $('#page-content').text('Page ' + ${startPage});
                                    window.location.href = "<%=basePath%>user/nextpage?startPage=" + page;
                                }
                            }));
                        },
                        error: function (json) {
                            alert('服务器内部错误');
                        }
                    })
                }
            }
        })
    })

</script>
</html>

