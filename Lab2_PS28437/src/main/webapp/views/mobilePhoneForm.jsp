<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mobile Phone Management</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Mobile Phone Form</h2>
        <form:form action="/mobilephone/save" method="post" modelAttribute="mobilePhone" class="needs-validation">
            <div class="mb-4">
                <form:label path="name" class="form-label">Name:</form:label>
                <form:input path="name" class="form-control" required="required" />
                <div class="invalid-feedback">Please enter the name.</div>
            </div>
            <div class="mb-4">
                <form:label path="price" class="form-label">Price:</form:label>
                <form:input path="price" type="number" step="0.01" class="form-control" required="required" />
                <div class="invalid-feedback">Please enter the price.</div>
            </div>
            <div class="mb-4">
                <form:label path="maker" class="form-label">Maker:</form:label>
                <form:input path="maker" class="form-control" required="required" />
                <div class="invalid-feedback">Please enter the maker.</div>
            </div>
            <div class="mb-4">
                <form:label path="img" class="form-label">Image URL:</form:label>
                <form:input path="img" class="form-control" />
            </div>
            <div class="mb-4">
                <form:label path="country" class="form-label">Country:</form:label>
                <form:input path="country" class="form-control" required="required" />
                <div class="invalid-feedback">Please enter the country.</div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form:form>

        <h2 class="text-center mt-5">Mobile Phone List</h2>
        <table class="table table-hover mt-3">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Maker</th>
                    <th>Image</th>
                    <th>Country</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${mobilePhones}" var="phone" varStatus="status">
                    <tr>
                        <td>${phone.name}</td>
                        <td>${phone.price}</td>
                        <td>${phone.maker}</td>
                        <td><img src="${phone.img}" alt="${phone.name}" width="50" class="img-fluid"></td>
                        <td>${phone.country}</td>
                        <td>
                            <a href="/mobilephone/edit/${status.index}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="/mobilephone/delete/${status.index}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.js"></script>
    <script>
        (function () {
            'use strict'
            const forms = document.querySelectorAll('.needs-validation')

            Array.prototype.slice.call(forms).forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
        })()
    </script>
</body>
</html>