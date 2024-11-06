package com.java5.slide6.controller;

import com.java5.slide6.dao.Report1DAO;
import com.java5.slide6.entity.Report1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Report1Controller {
    
    @Autowired
    Report1DAO report1;
    
    @ResponseBody
    @GetMapping("/api/sales-report")
    public List<Report1> getSalesReport() {
        return report1.getSalesReport();
    }
}
