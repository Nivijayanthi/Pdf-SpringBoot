package com.bhaiti.rest.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhaiti.beans.CVDetails;
import com.bhaiti.beans.CreatePdf;
import com.bhaiti.beans.UserInput;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class WelcomeController {
	
	public String scannedId = null;
	
	public static final String ROOT_SHARE_PATH = "D:/pdfOutput/";
	
	@RequestMapping(value = "/getpdf/{folderName}/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getpdf(@PathVariable("id") String id, @PathVariable("folderName") String folderName) throws JSONException {
		System.out.println("inside the class........." + id);
		String oaNumber = id;
		ResponseEntity<byte[]> response = null ;

		byte[] fileContent = new byte[40];
		try {
			java.nio.file.Path path = Paths.get(ROOT_SHARE_PATH + folderName + "/"+id );
			File f = new File(ROOT_SHARE_PATH + folderName );
			
			if(f.isDirectory()) {
				File[] dirContent = f.listFiles();
				for(File file : dirContent) {
					if(file.getName().endsWith(".pdf")) {
						f = file;
						break;
					}
				}
			}
			if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
				System.out.println("Success");
			} else {
				System.out.println("Not Found");
				response = new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
			}
			fileContent = Files.readAllBytes(f.toPath());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			// Here you have to set the actual filename of your pdf
			String filename = "report1.pdf";
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			response = new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return response;
	}

	@RequestMapping(value = "/getById/{id}")
	@ResponseBody
	public CVDetails getById(@PathVariable("id") String id) {
		 
		CVDetails cvDetails = new CVDetails();
		JSONObject jObject = new JSONObject();
		HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
		
		scannedId = id;

		jObject.put("Fo Number", id);
		jObject.put("Customer", "Emerson");
		jObject.put("Size", "100mm");
		jObject.put("IA", "12345");

		map.put(id, jObject);
//		File f = new File(ROOT_SHARE_PATH + scannedId);
		
		System.out.println("id......."+ id);
//		if (f.exists()) {
		if(map.containsKey(id)) {
			JSONObject obj = map.get(id);
			cvDetails.setFoNumber(obj.getString("Fo Number"));
			cvDetails.setCustomer(obj.getString("Customer"));
			cvDetails.setIaNumber(obj.getString("IA"));
			cvDetails.setSize(obj.getString("Size"));
			cvDetails.setStatusCode("200");
			cvDetails.setStatusMessage("SUCCESS");
			System.out.println("inside");
		}else {
			System.out.println("Given OA Number is Not Found");
			cvDetails.setStatusCode("404");
			cvDetails.setStatusMessage("NOT_FOUND");
		}
		System.out.println(cvDetails);

		return cvDetails;
	}

	@RequestMapping(value = "/savePdf", method = RequestMethod.POST)
	public String savePDF(@RequestBody UserInput userInput) throws DocumentException, FileNotFoundException {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, new FileOutputStream(ROOT_SHARE_PATH + "result/" + userInput.getId() + ".pdf"));

		document.open();

		//	Image img = Image.getInstance("D:/thumbnailimage.img.jpg");
		CreatePdf createPdf = new CreatePdf();

		PdfPTable resultTable = new PdfPTable(1);
		resultTable.setWidthPercentage(100f);

		PdfPCell resultCell = new PdfPCell(createPdf.drawInfoTable());
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultTable.addCell(resultCell);

		resultCell = new PdfPCell(createPdf.drawHeatingreadingTable(userInput));
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultCell.setPaddingTop(5f);
		resultTable.addCell(resultCell);

		resultCell = new PdfPCell(createPdf.drawRadiographyTable());
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultCell.setPaddingTop(5f);
		resultTable.addCell(resultCell);

		resultCell = new PdfPCell(createPdf.drawSerialNumberTable());
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultCell.setPaddingTop(5f);
		resultTable.addCell(resultCell);

		resultCell = new PdfPCell(createPdf.drawTestingTable());
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultCell.setPaddingTop(5f);
		resultTable.addCell(resultCell);
		

		resultCell = new PdfPCell(createPdf.drawEmptyTable());
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultCell.setPaddingTop(5f);
		resultTable.addCell(resultCell);
		
		resultCell = new PdfPCell(createPdf.drawCalibrationTable());
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultTable.addCell(resultCell);
		
		resultCell = new PdfPCell(createPdf.drawEmptyTable());
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultTable.addCell(resultCell);
		
		resultCell = new PdfPCell(createPdf.drawAssemblyRemarksTable());
		resultCell.setBorder(0);
		resultCell.setBorderWidthLeft(1f);
		resultCell.setBorderWidthRight(1f);
		resultTable.addCell(resultCell);





		document.add(resultTable);

		document.close();
		return null;
		
	}
}
