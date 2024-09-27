<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Student Form</title>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css" rel="stylesheet"/>
</head>
<body>
    <div class="container mt-5">
        <h2>${student.id == null ? 'Add' : 'Edit'} Student</h2>
        <form:form action="${pageContext.request.contextPath}/students/${student.id == null ? 'add' : 'edit'}" method="post" modelAttribute="student" enctype="multipart/form-data">
            <form:hidden path="id" />
            
            <div class="form-outline mb-4">
                <form:input path="fullName" id="fullName" class="form-control" />
                <label class="form-label" for="fullName">Full Name</label>
                <form:errors path="fullName" cssClass="text-danger" />
            </div>

            <div class="form-outline mb-4">
                <form:input path="email" id="email" class="form-control" />
                <label class="form-label" for="email">Email</label>
                <form:errors path="email" cssClass="text-danger" />
            </div>

            <div class="mb-4">
                <label class="form-label d-block">Gender</label>
                <c:forEach items="${genders}" var="gender">
                    <div class="form-check form-check-inline">
                        <form:radiobutton path="gender" id="gender${gender.value}" value="${gender.value}" class="form-check-input" />
                        <label class="form-check-label" for="gender${gender.value}">${gender.label}</label>
                    </div>
                </c:forEach>
                <form:errors path="gender" cssClass="text-danger d-block" />
            </div>

            <div class="form-outline mb-4">
                <form:input path="GPA" id="GPA" class="form-control" type="number" step="0.01" />
                <label class="form-label" for="GPA">GPA</label>
                <form:errors path="GPA" cssClass="text-danger" />
            </div>

            <div class="mb-4">
                <label class="form-label">Hobbies</label>
                <c:forEach items="${hobbies}" var="hobby">
                    <div class="form-check">
                        <form:checkbox path="hobbie" id="hobby${hobby.id}" value="${hobby.id}" class="form-check-input" />
                        <label class="form-check-label" for="hobby${hobby.id}">${hobby.name}</label>
                    </div>
                </c:forEach>
                <form:errors path="hobbie" cssClass="text-danger" />
            </div>

            <div class="form-outline mb-4">
                <form:select path="address" id="address" class="form-select">
                    <form:option value="" label="Select a province" />
                    <form:options items="${provinces}" itemValue="code" itemLabel="nameEn" />
                </form:select>
                <label class="form-label" for="address">Province</label>
                <form:errors path="address" cssClass="text-danger" />
            </div>

            <div class="mb-4">
                <label class="form-label" for="file">Profile Image</label>
                <input type="file" name="file" id="file" class="form-control" />
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">Cancel</a>
        </form:form>
    </div>
    <!-- MDB -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
</body>
</html>