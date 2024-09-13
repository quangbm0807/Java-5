<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng ký thông tin</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">Đăng ký thông tin</h2>
		<form action="/register" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="lastName" class="form-label">Họ</label> <input
					type="text" class="form-control" id="lastName" name="lastName"
					required>
			</div>
			<div class="mb-3">
				<label for="firstName" class="form-label">Tên</label> <input
					type="text" class="form-control" id="firstName" name="firstName"
					required>
			</div>
			<div class="mb-3">
				<label for="province" class="form-label">Tỉnh/Thành phố</label> <select
					class="form-select" id="province" name="province" required>
					<option value="">Chọn Tỉnh/Thành phố</option>
					<c:forEach var="province" items="${provinces}">
						<option value="${province.code}">${province.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-3">
				<label for="district" class="form-label">Quận/Huyện</label> <select
					class="form-select" id="district" name="district" required>
					<option value="">Chọn Quận/Huyện</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="ward" class="form-label">Phường/Xã</label> <select
					class="form-select" id="ward" name="ward" required>
					<option value="">Chọn Phường/Xã</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="file" class="form-label">Tải lên tệp</label> <input
					type="file" class="form-control" id="file" name="file">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
    document.addEventListener('DOMContentLoaded', function() {
        const provinceSelect = document.getElementById('province');
        const districtSelect = document.getElementById('district');
        const wardSelect = document.getElementById('ward');

        provinceSelect.addEventListener('change', function() {
            const provinceCode = this.value;
            if (provinceCode) {
                fillDistricts(provinceCode);
            } else {
                clearSelect(districtSelect);
                clearSelect(wardSelect);
            }
        });

        districtSelect.addEventListener('change', function() {
            const districtCode = this.value;
            if (districtCode) {
                fillWards(districtCode);
            } else {
                clearSelect(wardSelect);
            }
        });

        function fillDistricts(provinceCode) {
        	fetch('https://provinces.open-api.vn/api/p/'+provinceCode+'?depth=2')
                .then(response => response.json())
                .then(data => {
                    clearSelect(districtSelect);
                    if (data && data.districts) {
                        data.districts.forEach(district => {
                            const option = createOption(district.code, district.name);
                            districtSelect.appendChild(option);
                        });
                    } else {
                        console.error("No districts data found in API response");
                    }
                })
                .catch(error => console.error("API request failed:", error));
        }

        function fillWards(districtCode) {
            fetch('https://provinces.open-api.vn/api/d/'+districtCode+'?depth=2')
                .then(response => response.json())
                .then(data => {
                    clearSelect(wardSelect);
                    if (data && data.wards) {
                        data.wards.forEach(ward => {
                            const option = createOption(ward.code, ward.name);
                            wardSelect.appendChild(option);
                        });
                    } else {
                        console.error("No wards data found in API response");
                    }
                })
                .catch(error => console.error("API request failed:", error));
        }

        function clearSelect(selectElement) {
            selectElement.innerHTML = '<option value="">Chọn</option>';
        }

        function createOption(value, text) {
            const option = document.createElement('option');
            option.value = value;
            option.textContent = text;
            return option;
        }
    });
    </script>
</body>
</html>