<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<h2 class="mt-5">Quản lý tài khoản</h2>
<form:form action="/admin/add" method="post" modelAttribute="account" enctype="multipart/form-data">
    <div class="row">
        <div class="col-md-3">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <form:input path="username" class="form-control" />
                <form:errors path="username" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <form:input type="password" path="password" class="form-control" />
                <form:errors path="password" cssClass="text-danger" />
            </div>
            <!-- Checkbox cho Kích hoạt và Quản trị viên -->
            <div class="form-group">
                <label>
                    <form:checkbox path="activated" /> Kích hoạt
                </label>
            </div>
        </div>

        <div class="col-md-3">
            <div class="form-group">
                <label for="fullname">Họ và tên:</label>
                <form:input path="fullname" class="form-control" />
                <form:errors path="fullname" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <form:input type="email" path="email" class="form-control"/>
                <form:errors path="email" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="phone">Số điện thoại:</label>
                <form:input type="phone" path="phone" class="form-control"/>
                <form:errors path="phone" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label>
                    <form:checkbox path="admin" /> Quản trị viên
                </label>
            </div>
        </div>

        <!-- Cột thứ ba (col-md-3) chứa hình ảnh -->
        <div class="col-md-3">
            <div class="form-group">
                <img src="${pageContext.request.contextPath}/img/${account.photo != null ? account.photo : 'default-avatar.png'}"|
                     alt="Ảnh đại diện"
                     class="img-thumbnail"
                     style="max-width: 100%; height: auto; width:160px; height: 160px;">
            </div>
            <div class="form-group">
                <label for="upload">Tải ảnh lên:</label>
                <input type="file" class="form-control" id="upload" name="file"> <!-- Đổi name thành "file" -->
                <span class="text-danger">${message}</span>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary mt-3">Thêm tài khoản</button>
    <a href="/admin/clear" class="btn btn-secondary mt-3">Hủy</a>
</form:form>

<!-- Bảng hiển thị tài khoản -->
<table class="table table-striped mt-5">
    <thead>
    <tr>
        <th><a href="/admin?field=username"  class="text-dark text-decoration-none">Tên đăng nhập</a></th>
        <th><a href="/admin?field=fullname"  class="text-dark text-decoration-none">Họ và tên</a></th>
        <th><a href="/admin?field=email"  class="text-dark text-decoration-none">Email</a></th>
        <th><a href="/admin?field=phone"  class="text-dark text-decoration-none">Số điện thoại</a></th>
        <th>Kích hoạt</th>
        <th>Quản trị viên</th>
        <th>Hình ảnh</th>
        <th>Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
        <!-- Kiểm tra nếu tài khoản không phải là quản trị viên -->
        <c:if test="${!account.username.equals('admin')}">
            <tr>
                <td>${account.username}</td>
                <td>${account.fullname}</td>
                <td>${account.email}</td>
                <td>${account.phone}</td>
                <td>${account.activated ? 'Có' : 'Không'}</td>
                <td>${account.admin ? 'Có' : 'Không'}</td>
                <td>
                    <c:if test="${account.photo != null}">
                        <img src="${pageContext.request.contextPath}/img/${account.photo}" alt="Photo" width="50" height="50"/>
                    </c:if>
                    <c:if test="${account.photo == null}">
                        Không có ảnh
                    </c:if>
                </td>
                <td>
                    <a href="/admin/edit/${account.username}" class="btn btn-warning">Sửa</a>
                    <a href="/admin/delete/${account.username}"
                       class="btn btn-danger"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản này?');">
                        Xóa
                    </a>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>

<div class="row">
    <div class="col d-flex justify-content-center">
        <!-- Phân trang -->
        <nav aria-label="Page navigation">
            <ul class="pagination d-flex justify-content-center">
                <!-- Nút trang trước -->
                <c:if test="${page > 0}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page - 1}&size=${size}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <!-- Hiển thị số trang -->
                <c:forEach begin="0" end="${totalPages - 1}" var="i">
                    <li class="page-item ${page == i ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}&size=${size}">${i + 1}</a>
                    </li>
                </c:forEach>

                <!-- Nút trang sau -->
                <c:if test="${page < totalPages - 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page + 1}&size=${size}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
