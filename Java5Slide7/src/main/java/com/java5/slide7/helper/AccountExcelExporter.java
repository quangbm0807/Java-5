package com.java5.slide7.helper;

import java.io.IOException;
import java.util.List;
 

 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.java5.slide7.entity.Account;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
 
public class AccountExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Account> listAccounts;
     
    public AccountExcelExporter(List<Account> listAccounts) {
        this.listAccounts = listAccounts;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Accounts");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Username", style);      
        createCell(row, 1, "Email", style);       
        createCell(row, 2, "Full Name", style);    
        createCell(row, 3, "Roles", style);
        createCell(row, 4, "Enabled", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Account user : listAccounts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, user.getUsername(), style);
            createCell(row, columnCount++, user.getEmail(), style);
            createCell(row, columnCount++, user.getFullname(), style);
            createCell(row, columnCount++, user.getRole(), style);
            createCell(row, columnCount++, user.isActive(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
    }
}
