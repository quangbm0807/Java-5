<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Form Demo</title>
</head>
<body>
<form action="save" method="POST" enctype="multipart/form-data">
Browse to your file for uploading:
<input name="attach" type="file">
<button>Upload</button>
</form>
</body>
</html>