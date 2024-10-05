package com.java5.slide6.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java5.slide6.dao.EmployeeDAO;
import com.java5.slide6.dao.ProductDAO;
import com.java5.slide6.entity.Account;
import com.java5.slide6.entity.Order;
import com.java5.slide6.entity.OrderDetail;
import com.java5.slide6.entity.Product;
import com.java5.slide6.entity.Report;

@Controller
public class Slide6Controller {
	 @Autowired
	    EmployeeDAO employeeDao;
	    @Autowired
	    ProductDAO productDao;

    // 1. Tìm sản phẩm theo minPrice và maxPrice
    @ResponseBody
    @GetMapping("findProductBetweenPrice")
    List<Product> findProductBetweenPrice(double min, double max) {
        return productDao.findProductBetweenPrice(min, max);
    }

    // 2. Tìm sản phẩm theo name (Chứa keyword)
    @ResponseBody
    @GetMapping("findAllByNameContaining")
    List<Product> findAllByNameContaining(String name) {
        return productDao.findAllByNameContaining(name);
    }

    // 3. Ứng với từng loại sản phẩm cho biết số lượng bán, tổng tiền bán được
    @ResponseBody
    @GetMapping("getProductSalesReport")
    List<Report> getProductSalesReport() {
        return productDao.getInventoryByCategory();
    }

    // 4. Tìm hóa đơn có giá trị cao nhất
    @ResponseBody
    @GetMapping("findHighestValueOrder")
    List<OrderDetail> findHighestValueOrder() {
        return productDao.findOrderDetailsForHighestValueOrder();
    }

    // 5. Tìm hóa đơn có giá trị thấp nhất
    @ResponseBody
    @GetMapping("findLowestValueOrder")
    List<Order> findLowestValueOrder() {
        return productDao.findLowestValueOrder();
    }

    // 6. Tìm khách hàng có tổng tiền mua nhiều nhất
    @ResponseBody
    @GetMapping("findTopCustomer")
    List<Account> findTopCustomer() {
        return productDao.findTopCustomer();
    }

    // 7. Tìm top n sản phẩm bán được nhiều nhất theo khoảng thời gian beginDate và endDate
    @ResponseBody
    @GetMapping("findTopSellingProducts")
    List<Product> findTopSellingProducts(
            @RequestParam int n,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Product> products = productDao.findTopSellingProducts(beginDate, endDate);
        return products.stream().limit(n).collect(Collectors.toList());
    }
}