drop database lab5;

create database lab5;

use lab5;

INSERT INTO accounts (username, password, fullname, email, phone, photo, activated, admin) VALUES 
('admin', '123', 'Admin User', 'admin@example.com', '0942211324', 'admin_photo.jpg', true, true),
('tung', '123', 'Võ Thanh Tùng', 'user1@example.com', '0942211425', 'user1_photo.jpg', true, false);



-- Insert dữ liệu vào bảng categories
INSERT INTO categories (id, name) VALUES 
('C01', 'Apple'),
('C02', 'Samsung'),
('C03', 'Dell'),
('C04', 'HP'),
('C05', 'Lenovo');

-- Insert dữ liệu vào bảng products
INSERT INTO products (name, image, price, create_Date, available, category_Id, cpu, ram, storage, display, os, battery) VALUES 
('MacBook Air M1', '01.jpg', 999.99, '2024-10-01', true, 'C01', 'Apple M1', '8GB', '256GB SSD', '13.3" Retina', 'macOS', '49.9Wh'),
('MacBook Pro M1', '02.jpg', 1299.99, '2024-10-02', true, 'C01', 'Apple M1', '16GB', '512GB SSD', '13.3" Retina', 'macOS', '58.2Wh'),

('Galaxy Book S', '03.jpg', 999.99, '2024-10-03', true, 'C02', 'Intel i5', '8GB', '256GB SSD', '13.3" FHD', 'Windows 10', '42Wh'),
('Galaxy Book Flex', '04.jpg', 1399.99, '2024-10-04', true, 'C02', 'Intel i7', '16GB', '512GB SSD', '13.3" QLED', 'Windows 10', '69.7Wh'),

('Dell XPS 13', '05.jpg', 1199.99, '2024-10-05', true, 'C03', 'Intel i7', '16GB', '512GB SSD', '13.4" FHD+', 'Windows 11', '52Wh'),
('Dell Inspiron 15', '06.jpg', 799.99, '2024-10-06', true, 'C03', 'Intel i5', '8GB', '256GB SSD', '15.6" FHD', 'Windows 11', '42Wh'),

('HP Spectre x360', '07.jpg', 1299.99, '2024-10-07', true, 'C04', 'Intel i7', '16GB', '512GB SSD', '13.3" 4K', 'Windows 11', '60Wh'),
('HP Pavilion 15', '08.jpg', 749.99, '2024-10-08', true, 'C04', 'Intel i5', '8GB', '512GB SSD', '15.6" FHD', 'Windows 11', '41Wh'),

('Lenovo ThinkPad X1', '09.jpg', 1399.99, '2024-10-09', true, 'C05', 'Intel i7', '16GB', '1TB SSD', '14" 2K', 'Windows 11', '57Wh'),
('Lenovo IdeaPad 3', '10.jpg', 599.99, '2024-10-10', true, 'C05', 'AMD Ryzen 5', '8GB', '256GB SSD', '15.6" FHD', 'Windows 10', '45Wh');

select * from orders;	
select * from order_details;

