package com.poly.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
@Service
public class TestSchedule {
private SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm: ss");

public void chayLich () {
System.out.println (sdf.format (new Date ()));
}
}