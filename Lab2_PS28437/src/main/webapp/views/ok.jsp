<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- MDBootstrap CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.css" rel="stylesheet" />

<div class="container mt-5">
<jsp:include page="menu.jsp" flush="true"></jsp:include>
  <form action="/ok" method="post">
    <h1 class="text-center" id="result">${result}</h1>
    
    <!-- OK 1: POST method to /ok -->
    <button type="submit" formmethod="get" formaction="/ok/1" class="btn btn-primary btn-block mb-4">OK 1</button>

    <!-- OK 2: GET method to /ok/2 -->
    <button type="submit" formmethod="get" formaction="/ok/2" class="btn btn-secondary btn-block mb-4">OK 2</button>

    <!-- OK 3: GET method to /ok/3 -->
    <button type="submit" formmethod="get" formaction="/ok/3" class="btn btn-success btn-block mb-4">OK 3</button>
  </form>
</div>

<!-- MDBootstrap JavaScript -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.js"></script>
