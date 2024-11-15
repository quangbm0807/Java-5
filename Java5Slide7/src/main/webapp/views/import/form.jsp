<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Import Accounts from Excel file demo</title>
</head>
<body>
<form action="/user/import/form" method="POST" enctype="multipart/form-data">
Browse to your excel file for importing the list of accounts:
<input name="attach" type="file">
<button>Import</button>
</form>
<h2>${message }</h2>

</body>
</html>