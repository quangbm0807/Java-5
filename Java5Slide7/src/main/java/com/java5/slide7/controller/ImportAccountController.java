package com.java5.slide7.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.java5.slide7.dao.AccountDAO;
import com.java5.slide7.entity.Account;

import jakarta.servlet.ServletContext;
@Controller
public class ImportAccountController {
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	ServletContext app;
	@GetMapping("/user/import/form")
	public String form() {
		return "import/form";
	}
	@PostMapping("/user/import/form")
	public String send(@RequestParam("attach") MultipartFile attach, Model model) 
			throws IllegalStateException, IOException {
		if(!attach.isEmpty()) {
			String fileName = attach.getOriginalFilename();
			
			File file = new File(app.getRealPath("/accountdatafile/") + fileName);
			System.out.println(app.getRealPath("/accountdatafile/"));
			attach.transferTo(file);
			//Import the list of accounts
			List<Account> listAccounts = new ArrayList<Account>();
			List<Account> listAccountsFail = new ArrayList<Account>();
			try
			{
				FileInputStream fileImport = new FileInputStream(file);

				//Create Workbook instance holding reference to .xlsx file
				XSSFWorkbook workbook = new XSSFWorkbook(fileImport);

				//Get first/desired sheet from the workbook
				XSSFSheet sheet = workbook.getSheetAt(0);

				//Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				int rowNum = 0;
				Account account;
				//Skip the header row
				rowIterator.next();
				while (rowIterator.hasNext()) 
				{
					Row row = rowIterator.next();
					account = new Account();
					account.setUsername(row.getCell(0).getStringCellValue());
					account.setEmail(row.getCell(1).getStringCellValue());
					account.setFullname(row.getCell(2).getStringCellValue());
					if(row.getCell(3).getStringCellValue() != null && 
							row.getCell(3).getStringCellValue().equals("Admin"))
						account.setAdmin(true);
					else
						account.setAdmin(false);
					
					if(row.getCell(4).getStringCellValue() != null && 
							row.getCell(4).getStringCellValue().equals("Active"))
						account.setActivated(true);
					else
						account.setActivated(false);
					
					//set default password 123abc
					account.setPassword("123abc");
					//username đã tồn tại không thể import account
					if(accountDAO.findByUsername(account.getUsername()) != null)
						listAccountsFail.add(account);
					else {
						accountDAO.save(account);
						listAccounts.add(account);
					}
					rowNum++;
				}
				fileImport.close();
				
				for(Account account1:listAccounts)
					System.out.println(account1);
				model.addAttribute("listAccounts", listAccounts);
				model.addAttribute("listAccountsFail", listAccountsFail);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}else
			System.out.println("The file is empty!");
		model.addAttribute("message", "Import the list of users successfully!");
		return "import/form";
	}
}