<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
    .form-group {
        margin-bottom: 1.5rem;
    }
    .product-image {
        width: 160px;
        height: 160px;
        object-fit: cover; /* Để giữ tỉ lệ hình ảnh */
    }
    .table td, .table th {
        vertical-align: middle;
    }
    .create-date {
        height: 38px; /* Chiều cao cho trường ngày tạo */
    }
</style>

<div class="container mt-5">
    <h2 class="text-center">Quản lý sản phẩm</h2>

    <form:form action="/product/add" method="post" modelAttribute="product" enctype="multipart/form-data">
        <div class="row">
            <form:input path="id" type="hidden" />

            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Tên sản phẩm:</label>
                    <form:input path="name" class="form-control" />
                    <form:errors path="name" cssClass="text-danger" />
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label for="price">Giá:</label>
                    <form:input path="price" class="form-control" type="text" />
                    <form:errors path="price" cssClass="text-danger" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="createDate">Ngày tạo:</label>
                    <form:input path="createDate" class="form-control create-date" type="date" />
                    <form:errors path="createDate" cssClass="text-danger" />
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label for="available">Có sẵn:</label>
                    <form:select path="available" class="form-control">
                        <option value="true">Có</option>
                        <option value="false">Không</option>
                    </form:select>
                    <form:errors path="available" cssClass="text-danger" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="category">Danh mục:</label>
                    <form:select path="category.id" class="form-control">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="category.id" cssClass="text-danger" />
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label for="ram">RAM:</label>
                    <form:input path="ram" class="form-control" />
                    <form:errors path="ram" cssClass="text-danger" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="storage">Bộ nhớ:</label>
                    <form:input path="storage" class="form-control" />
                    <form:errors path="storage" cssClass="text-danger" />
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label for="display">Màn hình:</label>
                    <form:input path="display" class="form-control" />
                    <form:errors path="display" cssClass="text-danger" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="os">Hệ điều hành:</label>
                    <form:input path="os" class="form-control" />
                    <form:errors path="os" cssClass="text-danger" />
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label for="battery">Pin:</label>
                    <form:input path="battery" class="form-control" />
                    <form:errors path="battery" cssClass="text-danger" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="cpu">CPU:</label>
                    <form:input path="cpu" class="form-control" />
                    <form:errors path="cpu" cssClass="text-danger" />
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label for="image">Hình ảnh:</label>
                    <form:input path="image" class="form-control" type="text" />
                    <form:errors path="image" cssClass="text-danger" />
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <img src="${pageContext.request.contextPath}/products/${product.image != null ? product.image : 'default-avatar.jpg'}"
                         alt="Hình ảnh sản phẩm"
                         class="img-thumbnail product-image">
                </div>
                <div class="form-group">
                    <label for="upload">Tải ảnh lên:</label>
                    <input type="file" class="form-control" id="upload" name="upload">
                </div>
            </div>
        </div>

        <button type="submit" class="btn btn-success mt-3">Thêm sản phẩm</button>
        <a href="/product/clear" class="btn btn-secondary mt-3">Hủy</a>
    </form:form>

    <!-- Bảng hiển thị sản phẩm -->
    <table class="table table-striped mt-5">
        <thead class="thead-dark">
        <tr>
            <th><a href="/product?field=id" class="text-dark text-decoration-none">Mã sản phẩm</a></th>
            <th><a href="/product?field=name" class="text-dark text-decoration-none">Tên sản phẩm</a></th>
            <th><a href="/product?field=price" class="text-dark text-decoration-none">Giá</a></th>
            <th><a href="/product?field=createDate" class="text-dark text-decoration-none">Ngày tạo</a></th>
            <th>Có sẵn</th>
            <th><a href="/product?field=category" class="text-dark text-decoration-none">Danh mục</a></th>
            <th>CPU</th>
            <th>RAM</th>
            <th>Bộ nhớ</th>
            <th>Màn hình</th>
            <th>Hệ điều hành</th>
            <th>Pin</th>
            <th>Hình ảnh</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty products}">
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>
                        <fmt:formatDate value="${product.createDate}" type="date" />
                    </td>
                    <td>${product.available ? 'Có' : 'Không'}</td>
                    <td>${product.category.name}</td>
                    <td>${product.cpu}</td>
                    <td>${product.ram}</td>
                    <td>${product.storage}</td>
                    <td>${product.display}</td>
                    <td>${product.os}</td>
                    <td>${product.battery}</td>
                    <td>
                        <c:choose>
                            <c:when test="${product.image != null}">
                                <img src="${pageContext.request.contextPath}/products/${product.image}" alt="${product.name}" class="img-thumbnail" style="width: 100px; height: 100px;"/>
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/products/default-avatar.jpg" alt="Hình ảnh mặc định" class="img-thumbnail" style="width: 100px; height: 100px;"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="/product/edit/${product.id}" class="btn btn-primary">Sửa</a>
                        <a href="/product/delete/${product.id}" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

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

                <!-- Kiểm tra totalPages > 0 trước khi lặp -->
                <c:if test="${totalPages > 0}">
                    <c:forEach begin="0" end="${totalPages - 1}" var="i">
                        <li class="page-item ${page == i ? 'active' : ''}">
                            <a class="page-link" href="?page=${i}&size=${size}">${i + 1}</a>
                        </li>
                    </c:forEach>
                </c:if>

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