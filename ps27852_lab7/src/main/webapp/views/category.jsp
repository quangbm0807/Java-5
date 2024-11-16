<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2 class="mt-5">Quản lý danh mục</h2>

<!-- Form thêm hoặc sửa danh mục -->
<form:form action="/category/${action}" method="post" modelAttribute="category" enctype="multipart/form-data">
    <div class="row">
        <div class="col-md-4">
            <div class="form-group">
                <label for="id">Mã danh mục:</label>
                <form:input path="id" class="form-control" readonly="${action == 'update'}"/> <!-- Chỉ cho phép đọc khi đang sửa -->
                <form:errors path="id" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="name">Tên danh mục:</label>
                <form:input path="name" class="form-control" />
                <form:errors path="name" cssClass="text-danger" />
            </div>
        </div>
    </div>

    <button type="submit" class="btn btn-success mt-3">
        <c:choose>
            <c:when test="${action == 'add'}">Thêm danh mục</c:when>
            <c:otherwise>Sửa danh mục</c:otherwise>
        </c:choose>
    </button>

    <a href="/category/clear" class="btn btn-secondary mt-3">Hủy</a>
</form:form>

<!-- Bảng hiển thị danh mục -->
<table class="table table-striped mt-5">
    <thead class="thead-dark">
    <tr>
        <th><a href="/category?field=id" class="text-dark text-decoration-none">Mã danh mục</a></th>
        <th><a href="/category?field=name" class="text-dark text-decoration-none">Tên danh mục</a></th>
        <th>Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="category">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>
                <a href="/category/edit/${category.id}" class="btn btn-warning">Sửa</a>
                <a href="/category/delete/${category.id}" class="btn btn-danger"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="row">
    <div class="col d-flex justify-content-center">
        <nav aria-label="Page navigation">
            <ul class="pagination d-flex justify-content-center">
                <c:if test="${page > 0}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page - 1}&size=${size}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="0" end="${totalPages - 1}" var="i">
                    <li class="page-item ${page == i ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}&size=${size}">${i + 1}</a>
                    </li>
                </c:forEach>

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
