package com.bhaiti.rest.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.HashMap;

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
			java.nio.file.Path path = Paths.get(ROOT_SHARE_PATH + folderName );
			File f = new File(ROOT_SHARE_PATH + folderName  + "/"+ id + ".pdf" );
			
			if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
				f = new File(ROOT_SHARE_PATH + "default.pdf" );
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
		scannedId = id;
		JSONObject obj = getIAInfo(id);


		
//		File f = new File(ROOT_SHARE_PATH + scannedId);
		
		System.out.println("id......."+ id);
//		if (f.exists()) {
		if(obj != null) {
			
			cvDetails.setFoNumber(obj.getString("FoNumber"));
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

	@RequestMapping(value = "/savepdf", method = RequestMethod.POST)
	@ResponseBody
	public String savepdf(@RequestBody UserInput userInput) throws DocumentException, FileNotFoundException {
		
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
	
	private JSONObject getIAInfo(String id) {
		StringBuilder sb = new StringBuilder();
		HttpURLConnection conn = null;
		
		JSONObject jObject = new JSONObject();
		jObject.put("FoNumber", id);
		jObject.put("Customer", "Ferabi");
		jObject.put("Size", "4 EZ");
		jObject.put("IA", "IA153428");
		
		try {
			  
				URL url = new URL("http://localhost:8080/emerson_sms/fetchFGInformation.html?iaNumber=IA"+id);
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				String output;
				
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				System.err.println("Server output is "+sb.toString());
				
				String response = sb.toString();
				
				String[] respMessages = response.split("::");
				
				for(String keyValue : respMessages) {
					String[] keyValuePair = keyValue.split("=");
					if("ianumber".equals(keyValuePair[0])) {
						jObject.put("IA", "IA"+id);
					} else if("OANum".equals(keyValuePair[0])) {
						jObject.put("FoNumber", keyValuePair[1]);
					} else if("matCode".equals(keyValuePair[0])) {
						jObject.put("Size", keyValuePair[1]);
					} 
				
				}
				
				jObject.put("Customer", "");
				
				
			  } catch (MalformedURLException e) {

				System.err.println(e);

			  } catch (IOException e) {

				System.err.println(e);

			  } finally {
				  if(conn != null) { try { conn.disconnect(); } catch(Exception e) {}}
			  }
			  
		return jObject;
		
	}
}
