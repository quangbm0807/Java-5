<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fr"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://use.fontawesome.com/4ecc3dbb0b.css" media="all">
    <link rel="stylesheet" href="/css/form.css">
</head>
<style>
    textarea{
        resize: vertical;
        height: 100px !important;
    }
</style>
<body>
    <div class="form_wrapper">
        <div class="form_container">
            <div class="title_container">
                <h2>GỬI MAIL</h2>
            </div>
            <div class="row clearfix">
                <div class="">
                    <fr:form method="POST" enctype="multipart/form-data" modelAttribute="mailModel" action="">
                        <div class="input_field">
                            <span><i aria-hidden="true" class="fa fa-users"></i></span>
                            <fr:input path="to" placeholder="Người nhận"/>
                        </div>

                        <div class="input_field">
                            <span><i aria-hidden="true" class="fa fa-lock"></i></span>
                            <fr:input path="subject" placeholder="Chủ đề"/>
                        </div>

                        <div class="input_field">
                            <span><i aria-hidden="true" class="fa fa-envelope"></i></span>
                            <fr:textarea path="body"/>
                        </div>

                        <input type="file" name="attachment" multiple="multiple">
                        <fr:button name="btnGui">Gửi</fr:button>
                    </fr:form>
                </div>
            </div>
        </div>
    </div>

    <h3>${message}</h3>
</body>
</body>
</html>