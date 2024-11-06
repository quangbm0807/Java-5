package com.java5.slide6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Report1 {
    private String username;
    private String fullname;
    private Double totalMoney;
    private Long totalQuantity;
    private Date newestDate;
    private Date lastestDate;
    public Report1(String username, String fullname, Double totalMoney, 
            Long totalQuantity, Date newestDate, Date lastestDate) {
   this.username = username;
   this.fullname = fullname;
   this.totalMoney = totalMoney;
   this.totalQuantity = totalQuantity;
   this.newestDate = newestDate;
   this.lastestDate = lastestDate;
}
}