<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.css" rel="stylesheet" />

<div class="container mt-5">
    <h1 class="text-center mb-4">Param Form</h1>
    <form id="paramForm" action="save" method="get">
        <div class="form-outline mb-4">
            <input type="number" id="xInput" name="x" class="form-control" value="${x != null ? x : ''}" required />
            <label class="form-label" for="xInput">Nhập số X</label>
        </div>
        <div class="form-outline mb-4">
            <input type="number" id="yInput" name="y" class="form-control" value="${y != null ? y : ''}" required />
            <label class="form-label" for="yInput">Nhập số Y</label>
        </div>
        <button type="submit" class="btn btn-primary btn-block mb-4">Save</button>
    </form>
    <ul>
        <li>x: ${x}</li>
        <li>y: ${y}</li>
    </ul>
</div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.js"></script>
