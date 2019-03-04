package com.bhaiti.beans;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class CreatePdf {



		public static PdfPCell getHeaderCell(String content) {
			PdfPCell cell = getCell(content);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			return cell;
		}

		public static PdfPCell getSmallHeaderCell(String content) {
			PdfPCell cell = getSmallCell(content);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			return cell;
		}

		public static PdfPCell getSmallCell(String content) {
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
			Chunk chunk = new Chunk(content, font);

			PdfPCell cell = new PdfPCell();
			cell.setBorderWidth(0.2f);
			cell.setPaddingLeft(5);
			cell.setPaddingTop(3f);
			cell.setPaddingBottom(3f);
			cell.setPhrase(new Phrase(chunk));
			return cell;
		}
		
		public static PdfPCell getemptyBorderCell(String content) {
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
			Chunk chunk = new Chunk(content, font);

			PdfPCell cell = new PdfPCell();
			cell.setBorderWidth(0f);
			cell.setPaddingLeft(5);
			cell.setPaddingTop(3f);
			cell.setPaddingBottom(3f);
			cell.setPhrase(new Phrase(chunk));
			return cell;
		}

		public static PdfPCell getVerySmallCell(String content) {
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
			Chunk chunk = new Chunk(content, font);

			PdfPCell cell = new PdfPCell();
			cell.setBorderWidth(0.2f);
			cell.setPaddingLeft(2);
			cell.setPaddingTop(2f);
			cell.setPaddingBottom(5f);
			cell.setPhrase(new Phrase(chunk));
			return cell;
		}

		public static PdfPCell getCell(String content) {
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
			Chunk chunk = new Chunk(content, font);

			PdfPCell cell = new PdfPCell();
			cell.setBorderWidth(0.2f);
			cell.setPaddingLeft(5);
			cell.setPaddingTop(10f);
			cell.setPaddingBottom(10f);
			cell.setPhrase(new Phrase(chunk));
			return cell;
		}

		public static PdfPTable drawInfoTable() throws DocumentException {
			PdfPTable infoMainTable = new PdfPTable(1);
			infoMainTable.setWidthPercentage(100f);

			PdfPTable infoTable = new PdfPTable(6);
			infoTable.setWidthPercentage(100f);


			infoTable.addCell(getHeaderCell("FO Number/It No"));
			infoTable.addCell(getCell(""));

			infoTable.addCell(getHeaderCell("Customer"));
			infoTable.addCell(getCell("")); 

			infoTable.addCell(getHeaderCell("Size/Type"));
			infoTable.addCell(getCell(""));

			PdfPCell cell = new PdfPCell(infoTable);
			cell.setBorderWidth(0);
			cell.setBorderWidthTop(1);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			PdfPTable descTable = new PdfPTable(6);
			descTable.setWidthPercentage(100f);
			descTable.addCell(getCell("Painting Colour : "));
			descTable.addCell(getCell("Body Assy. "));
			descTable.addCell(getCell(" "));
			descTable.addCell(getCell("Actuator Assy. "));
			descTable.addCell(getCell(" "));
			descTable.addCell(getCell("IA"));

			cell = new PdfPCell(descTable);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			//document.add(infoMainTable);
			return infoMainTable;
		}

		public static PdfPTable drawHeatingreadingTable(UserInput userInput) throws DocumentException {
			PdfPTable infoMainTable = new PdfPTable(1);
			infoMainTable.setWidthPercentage(100f);

			PdfPTable heatDetails = new PdfPTable(1);
			heatDetails.setWidthPercentage(100f);
			heatDetails.addCell(getSmallHeaderCell("Heat Number details: "));

			PdfPCell cell = new PdfPCell(heatDetails);
			cell.setBorderWidth(0);
			cell.setBorderWidthTop(1);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			PdfPTable bodyBonnet = new PdfPTable(4);
			bodyBonnet.setWidthPercentage(100f);
			bodyBonnet.addCell(getCell("Body: "+userInput.getBody()));
			bodyBonnet.addCell(getCell("Bonnet: "+userInput.getBonnet()));
			bodyBonnet.addCell(getCell("Plug /Disk(or)Ball: "+userInput.getPlug()));
			bodyBonnet.addCell(getCell("Cage/Retainer : "+userInput.getCage()));

			cell = new PdfPCell(bodyBonnet);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			PdfPTable source = new PdfPTable(4);
			source.setWidthPercentage(100f);
			source.addCell(getCell("Source:"+userInput.getSource()));
			source.addCell(getCell("Source:"+userInput.getSource2()));
			source.addCell(getCell("Seat:"+userInput.getSeat()));
			source.addCell(getCell("Stem/Shaft: "+userInput.getStem()));

			cell = new PdfPCell(source);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			PdfPTable others = new PdfPTable(3);
			others.setWidthPercentage(100f);
			others.addCell(getCell("others:"));
			others.addCell(getCell("others:"));
			others.addCell(getCell("others:"));

			cell = new PdfPCell(others);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			return infoMainTable;
		}

		public static PdfPTable drawRadiographyTable() throws DocumentException {
			PdfPTable infoMainTable = new PdfPTable(1);
			infoMainTable.setWidthPercentage(100f);

			PdfPTable radiography = new PdfPTable(1);
			radiography.setWidthPercentage(100f);
			radiography.addCell(getSmallHeaderCell("Radiography Number details:"));

			PdfPCell cell = new PdfPCell(radiography);
			cell.setBorderWidth(0);
			cell.setBorderWidthTop(1);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			PdfPTable bodyBonnet = new PdfPTable(7);
			bodyBonnet.setWidthPercentage(100f);
			bodyBonnet.addCell(getCell("Body: "));
			bodyBonnet.addCell(getCell(""));
			bodyBonnet.addCell(getCell("Bonnet: "));
			bodyBonnet.addCell(getCell(""));
			bodyBonnet.addCell(getCell("others: "));
			bodyBonnet.addCell(getCell(""));
			bodyBonnet.addCell(getCell(""));

			cell = new PdfPCell(bodyBonnet);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			return infoMainTable;
		}

		public static PdfPTable drawSerialNumberTable() throws DocumentException {
			PdfPTable infoMainTable = new PdfPTable(1);
			infoMainTable.setWidthPercentage(100f);

			PdfPTable serialNumber = new PdfPTable(1);
			serialNumber.setWidthPercentage(100f);
			serialNumber.addCell(getSmallHeaderCell("Serial Number details:"));

			PdfPCell cell = new PdfPCell(serialNumber);
			cell.setBorderWidth(0);
			cell.setBorderWidthTop(1);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			PdfPTable product = new PdfPTable(7);
			product.setWidthPercentage(100f);
			product.addCell(getSmallHeaderCell("Product: "));
			product.addCell(getCell("          SOV"));
			product.addCell(getCell("          DVC "));
			product.addCell(getCell("          AFR"));
			product.addCell(getCell("          POS. T "));
			product.addCell(getCell("    Others"));
			product.addCell(getCell(" Imported Valve Sl. NO."));

			cell = new PdfPCell(product);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			PdfPTable bodyBonnet = new PdfPTable(7);
			bodyBonnet.setWidthPercentage(100f);
			bodyBonnet.addCell(getSmallHeaderCell("SI. No.        "));
			bodyBonnet.addCell(getCell(""));
			bodyBonnet.addCell(getCell(""));
			bodyBonnet.addCell(getCell(""));
			bodyBonnet.addCell(getCell(""));
			bodyBonnet.addCell(getCell(""));
			bodyBonnet.addCell(getCell(""));

			cell = new PdfPCell(bodyBonnet);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);

			return infoMainTable;
		}

		public static PdfPTable drawTestingTable() throws DocumentException {
			PdfPTable infoMainTable = new PdfPTable(2);
			infoMainTable.setWidthPercentage(100f);
			float[] widths ={50,50};
			infoMainTable.setWidths(widths);



			PdfPTable testing = new PdfPTable(1);
			testing.setWidthPercentage(100f);
			
			
			PdfPTable heading = new PdfPTable(1);
			heading.setWidthPercentage(100f);
			heading.addCell(getSmallHeaderCell("Testing"));

			PdfPCell cell = new PdfPCell(heading);
			cell.setBorderWidth(0);
			cell.setBorderWidthTop(1);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);

			PdfPTable product = new PdfPTable(2);
			product.setWidthPercentage(100f);
			product.addCell(getCell("Bench Set: "));
			product.addCell(getCell(""));

			cell = new PdfPCell(product);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable actuator = new PdfPTable(1);
			actuator.setWidthPercentage(100f);
			actuator.addCell(getHeaderCell("A) Actuator assembly air test as per FMP 2C1.1 (Spring actuator) / FMP 10A8 (Piston actuator)"));

			cell = new PdfPCell(actuator);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable testPressure = new PdfPTable(2);
			testPressure.setWidthPercentage(100f);
			testPressure.addCell(getCell("Test pressure in psig :"));
			testPressure.addCell(getCell(""));

			cell = new PdfPCell(testPressure);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			
			PdfPTable acceptanceStandard = new PdfPTable(2);
			acceptanceStandard.setWidthPercentage(100f);
			acceptanceStandard.addCell(getCell("Acceptance standard (No leak allowed) :"));
			acceptanceStandard.addCell(getCell(""));

			cell = new PdfPCell(acceptanceStandard);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			
			PdfPTable emptyRows = new PdfPTable(2);
			emptyRows.setWidthPercentage(100f);
			emptyRows.addCell(getCell(""));
			emptyRows.addCell(getCell(""));

			cell = new PdfPCell(emptyRows);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable bodyBonnetTorque = new PdfPTable(2);
			bodyBonnetTorque.setWidthPercentage(100f);
			bodyBonnetTorque.addCell(getHeaderCell("B) Body & Bonnet tightening torque"));
			bodyBonnetTorque.addCell(getCell(""));

			cell = new PdfPCell(bodyBonnetTorque);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable valveAssemby = new PdfPTable(1);
			valveAssemby.setWidthPercentage(100f);
			valveAssemby.addCell(getHeaderCell("C) Valve assembly air test Refer FGS 4L1"));

			cell = new PdfPCell(valveAssemby);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable testPressurePsig = new PdfPTable(2);
			testPressurePsig.setWidthPercentage(100f);
			testPressurePsig.addCell(getCell("Test pressure in Psig"));
			testPressurePsig.addCell(getCell("80"));

			cell = new PdfPCell(testPressurePsig);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable acceptancePsig = new PdfPTable(2);
			acceptancePsig.setWidthPercentage(100f);
			acceptancePsig.addCell(getCell("Acceptance standard (No leak allowed)"));
			acceptancePsig.addCell(getCell("OK"));

			cell = new PdfPCell(acceptancePsig);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable emptypsig = new PdfPTable(2);
			emptypsig.setWidthPercentage(100f);
			emptypsig.addCell(getCell(""));
			emptypsig.addCell(getCell(""));

			cell = new PdfPCell(emptypsig);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable seatleakage = new PdfPTable(1);
			seatleakage.setWidthPercentage(100f);
			seatleakage.addCell(getCell("D) Seat leakage test Refer FGS 4L5"));

			cell = new PdfPCell(seatleakage);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable leakageClass = new PdfPTable(5);
			leakageClass.setWidthPercentage(100f);
			float[] leakageClasswidths ={20,25,15,25,15};
			leakageClass.setWidths(leakageClasswidths);
			leakageClass.addCell(getCell("Leakage Class"));
			leakageClass.addCell(getCell("Test medium"));
			leakageClass.addCell(getCell("Pressure in"));
			leakageClass.addCell(getCell("Allowable leakage"));
			leakageClass.addCell(getCell("Observed leakage"));

			cell = new PdfPCell(leakageClass);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable airleakage = new PdfPTable(5);
			airleakage.setWidthPercentage(100f);
			float[] airleakagewidths ={20,25,15,25,15};
			airleakage.setWidths(airleakagewidths);
			airleakage.addCell(getCell(""));
			airleakage.addCell(getCell("Air"));
			airleakage.addCell(getCell("50 psig"));
			airleakage.addCell(getCell(""));
			airleakage.addCell(getCell(""));

			cell = new PdfPCell(airleakage);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable emptyAirleakage = new PdfPTable(5);
			emptyAirleakage.setWidthPercentage(100f);
			float[] emptyAirleakagewidths ={20,25,15,25,15};
			emptyAirleakage.setWidths(emptyAirleakagewidths);
			emptyAirleakage.addCell(getCell(""));
			emptyAirleakage.addCell(getCell(""));
			emptyAirleakage.addCell(getCell(""));
			emptyAirleakage.addCell(getCell(""));
			emptyAirleakage.addCell(getCell(""));

			cell = new PdfPCell(emptyAirleakage);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable flowArrow = new PdfPTable(2);
			flowArrow.setWidthPercentage(100f);
			float[] flowArrowwidths ={60,40};
			flowArrow.setWidths(flowArrowwidths);
			flowArrow.addCell(getCell("FLOW Arrow fixing"));
			flowArrow.addCell(getCell(""));

			cell = new PdfPCell(flowArrow);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			PdfPTable accessories = new PdfPTable(2);
			accessories.setWidthPercentage(100f);
			float[] accessorieswidths ={60,40};
			accessories.setWidths(accessorieswidths);
			accessories.addCell(getHeaderCell("E) Accessories functional test: "));
			accessories.addCell(getCell("OK"));

			cell = new PdfPCell(accessories);
			cell.setBorderWidth(0);
			cell.setBorderWidthRight(1);
			testing.addCell(cell);
			
			
			//adding testing to maintable
			
			cell = new PdfPCell(testing);
			cell.setBorderWidth(0);
			infoMainTable.addCell(cell);
			
			

			PdfPTable bodyBonnet = new PdfPTable(1);
			bodyBonnet.setWidthPercentage(100f);
			
			
			PdfPTable finalCheck = new PdfPTable(2);
			finalCheck.setWidthPercentage(100f);
			float[]  finalCheckwidths ={85,15};
			finalCheck.setWidths(finalCheckwidths);
			finalCheck.addCell(getSmallHeaderCell("Final Checks -- Please put Tick (or) N/A wherever applicable"));
			finalCheck.addCell(getSmallHeaderCell("Status"));

			cell = new PdfPCell(finalCheck);
			cell.setBorderWidth(0);
			cell.setBorderWidthTop(1);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable visualCheck = new PdfPTable(3);
			visualCheck.setWidthPercentage(100f);
			float[]  visualCheckwidths ={10,75,15};
			visualCheck.setWidths(visualCheckwidths);
			visualCheck.addCell(getCell("1"));
			visualCheck.addCell(getCell("Visual check- damage and dent damage "));
			visualCheck.addCell("");

			cell = new PdfPCell(visualCheck);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable correctBody = new PdfPTable(3);
			correctBody.setWidthPercentage(100f);
			float[]  correctBodywidths ={10,75,15};
			correctBody.setWidths(correctBodywidths);
			correctBody.addCell(getCell("2"));
			correctBody.addCell(getCell("Correct body size, configuration, flange serration & BWE Schedule."));
			correctBody.addCell("");

			cell = new PdfPCell(correctBody);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable correctASME = new PdfPTable(3);
			correctASME.setWidthPercentage(100f);
			float[]  correctASMEwidths ={10,75,15};
			correctASME.setWidths(correctASMEwidths);
			correctASME.addCell(getCell("3"));
			correctASME.addCell(getCell("Correct ASME rating marked on body."));
			correctASME.addCell("");

			cell = new PdfPCell(correctASME);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			
			PdfPTable heatNumbersIdentity = new PdfPTable(3);
			heatNumbersIdentity.setWidthPercentage(100f);
			float[]  heatNumbersIdentitywidths ={10,75,15};
			heatNumbersIdentity.setWidths(heatNumbersIdentitywidths);
			heatNumbersIdentity.addCell(getCell("4"));
			heatNumbersIdentity.addCell(getCell("Heat numbers identified and recorded"));
			heatNumbersIdentity.addCell("");

			cell = new PdfPCell(heatNumbersIdentity);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable hydro = new PdfPTable(3);
			hydro.setWidthPercentage(100f);
			float[]  hydrowidths ={10,75,15};
			hydro.setWidths(hydrowidths);
			hydro.addCell(getCell("5"));
			hydro.addCell(getCell("Hydro 'H' stamped on body and bonnet."));
			hydro.addCell("");

			cell = new PdfPCell(hydro);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable naceStamped = new PdfPTable(3);
			naceStamped.setWidthPercentage(100f);
			naceStamped.setWidths(hydrowidths);
			naceStamped.addCell(getCell("6"));
			naceStamped.addCell(getCell("NACE 'S' stamped on body and bonnet."));
			naceStamped.addCell("");

			cell = new PdfPCell(hydro);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable checkstampings = new PdfPTable(3);
			checkstampings.setWidthPercentage(100f);
			checkstampings.setWidths(hydrowidths);
			checkstampings.addCell(getCell("7"));
			checkstampings.addCell(getCell("Check IBR / RGT / LPI / PMI / MPI stampings."));
			checkstampings.addCell("");

			cell = new PdfPCell(checkstampings);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable vacuumTest = new PdfPTable(3);
			vacuumTest.setWidthPercentage(100f);
			vacuumTest.setWidths(hydrowidths);
			vacuumTest.addCell(getCell("8"));
			vacuumTest.addCell(getCell("Vacuum test / Helium leak test"));
			vacuumTest.addCell("");

			cell = new PdfPCell(vacuumTest);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable flowDirection = new PdfPTable(3);
			flowDirection.setWidthPercentage(100f);
			flowDirection.setWidths(hydrowidths);
			flowDirection.addCell(getCell("9"));
			flowDirection.addCell(getCell("Flow direction identified in the correct orientation."));
			flowDirection.addCell("");

			cell = new PdfPCell(flowDirection);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable valveSerialNumber = new PdfPTable(3);
			valveSerialNumber.setWidthPercentage(100f);
			valveSerialNumber.setWidths(hydrowidths);
			valveSerialNumber.addCell(getCell("10"));
			valveSerialNumber.addCell(getCell("Valve serial number clearly punched in body assembly."));
			valveSerialNumber.addCell("");

			cell = new PdfPCell(valveSerialNumber);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable namePlate = new PdfPTable(3);
			namePlate.setWidthPercentage(100f);
			namePlate.setWidths(hydrowidths);
			namePlate.addCell(getCell("11"));
			namePlate.addCell(getCell("Correct name plate details as per spec.sheet."));
			namePlate.addCell("");

			cell = new PdfPCell(namePlate);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable flangeCovers = new PdfPTable(3);
			flangeCovers.setWidthPercentage(100f);
			flangeCovers.setWidths(hydrowidths);
			flangeCovers.addCell(getCell("12"));
			flangeCovers.addCell(getCell("Flange covers attached & open ports capped."));
			flangeCovers.addCell("");

			cell = new PdfPCell(flangeCovers);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable looseComponents = new PdfPTable(3);
			looseComponents.setWidthPercentage(100f);
			looseComponents.setWidths(hydrowidths);
			looseComponents.addCell(getCell("13"));
			looseComponents.addCell(getCell("Loose components attached as per requisition."));
			looseComponents.addCell("");

			cell = new PdfPCell(looseComponents);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable pneumatic = new PdfPTable(3);
			pneumatic.setWidthPercentage(100f);
			pneumatic.setWidths(hydrowidths);
			pneumatic.addCell(getCell("14"));
			pneumatic.addCell(getCell("Pneumatic fittings and tubing leak check"));
			pneumatic.addCell("");

			cell = new PdfPCell(pneumatic);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable dvcName = new PdfPTable(3);
			dvcName.setWidthPercentage(100f);
			dvcName.setWidths(hydrowidths);
			dvcName.addCell(getCell("15"));
			dvcName.addCell(getCell("DVC Name plate fixed and Version identification."));
			dvcName.addCell("");

			cell = new PdfPCell(dvcName);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			PdfPTable emptyFinalChecks= new PdfPTable(3);
			emptyFinalChecks.setWidthPercentage(100f);
			emptyFinalChecks.setWidths(hydrowidths);
			emptyFinalChecks.addCell(getCell(""));
			emptyFinalChecks.addCell(getCell(""));
			emptyFinalChecks.addCell(getCell(""));

			cell = new PdfPCell(emptyFinalChecks);
			cell.setBorderWidth(0);
			cell.setBorderWidthLeft(1);
			bodyBonnet.addCell(cell);
			
			
			
			//adding bodyBonnet to maintable
			
			cell = new PdfPCell(bodyBonnet);
			cell.setBorderWidth(0);
			cell.setPaddingLeft(10);
			infoMainTable.addCell(cell);

			return infoMainTable;
		}
		
		public static PdfPTable drawEmptyTable() throws DocumentException {
			
			PdfPTable infoMainTable = new PdfPTable(1);
			infoMainTable.setWidthPercentage(100f);
			
			PdfPTable emptyTable = new PdfPTable(1);
			emptyTable.setWidthPercentage(100f);
			emptyTable.addCell(getCell(""));

			PdfPCell cell = new PdfPCell(emptyTable);
			cell.setBorderWidth(0);
			cell.setBorderWidthTop(1);
			cell.setBorderWidthLeft(1);
			cell.setBorderWidthRight(1);
			infoMainTable.addCell(cell);
			return infoMainTable;
		}
		
		
		public static PdfPTable drawCalibrationTable() throws DocumentException {
			PdfPTable infoMainTable = new PdfPTable(2);
			infoMainTable.setWidthPercentage(100f);
			float[] widths ={50,50};
			infoMainTable.setWidths(widths);



			PdfPTable primaryTable = new PdfPTable(1);
			primaryTable.setWidthPercentage(100f);
			
			
			PdfPTable heading = new PdfPTable(1);
			heading.setWidthPercentage(100f);
			heading.addCell(getHeaderCell("F) Calibration Detail of Positional Transmitter"));

			PdfPCell cell = new PdfPCell(heading);
			cell.setBorderWidth(0);
			primaryTable.addCell(cell);

			
			
			
			//adding testing to maintable
			
			cell = new PdfPCell(primaryTable);
			cell.setBorderWidth(0);
			infoMainTable.addCell(cell);
			
			

			PdfPTable secondaryTable = new PdfPTable(1);
			secondaryTable.setWidthPercentage(100f);
			
			
			PdfPTable percentage = new PdfPTable(6);
			percentage.setWidthPercentage(100f);
			percentage.addCell(getCell("% Opening"));
			percentage.addCell(getCell("0%"));
			percentage.addCell(getCell("25%"));
			percentage.addCell(getCell("50%"));
			percentage.addCell(getCell("75%"));
			percentage.addCell(getCell("100%"));

			cell = new PdfPCell(percentage);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);
			
			PdfPTable signal = new PdfPTable(6);
			signal.setWidthPercentage(100f);
			signal.addCell(getCell("Signal"));
			signal.addCell(getCell(""));
			signal.addCell(getCell(""));
			signal.addCell(getCell(""));
			signal.addCell(getCell(""));
			signal.addCell(getCell(""));

			cell = new PdfPCell(signal);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);
			
			PdfPTable increase = new PdfPTable(6);
			increase.setWidthPercentage(100f);
			increase.addCell(getCell("Increase"));
			increase.addCell(getCell(""));
			increase.addCell(getCell(""));
			increase.addCell(getCell(""));
			increase.addCell(getCell(""));
			increase.addCell(getCell(""));

			cell = new PdfPCell(increase);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);
			
			PdfPTable decrease = new PdfPTable(6);
			decrease.setWidthPercentage(100f);
			decrease.addCell(getCell("Decrease"));
			decrease.addCell(getCell(""));
			decrease.addCell(getCell(""));
			decrease.addCell(getCell(""));
			decrease.addCell(getCell(""));
			decrease.addCell(getCell(""));

			cell = new PdfPCell(decrease);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);

			
			
			
			//adding bodyBonnet to maintable
			
			cell = new PdfPCell(secondaryTable);
			cell.setBorderWidth(0);
			infoMainTable.addCell(cell);

			return infoMainTable;
		}
		
		public static PdfPTable drawAssemblyRemarksTable() throws DocumentException {
			PdfPTable infoMainTable = new PdfPTable(2);
			infoMainTable.setWidthPercentage(100f);
			float[] widths ={50,50};
			infoMainTable.setWidths(widths);



			PdfPTable primaryTable = new PdfPTable(1);
			primaryTable.setWidthPercentage(100f);
			
			
			PdfPTable heading = new PdfPTable(1);
			heading.setWidthPercentage(100f);
			heading.addCell(getSmallHeaderCell("ASSEMBLY REMARKS"));

			PdfPCell cell = new PdfPCell(heading);
			cell.setBorderWidth(0);
			primaryTable.addCell(cell);
			
			PdfPTable open = new PdfPTable(2);
			open.setWidthPercentage(100f);
			open.addCell(getCell(""));
			open.addCell(getCell("Open-                  sec"));

			cell = new PdfPCell(open);
			cell.setBorderWidth(0);
			primaryTable.addCell(cell);
			
			
			PdfPTable close = new PdfPTable(2);
			close.setWidthPercentage(100f);
			close.addCell(getCell(""));
			close.addCell(getCell("close-                  sec"));

			cell = new PdfPCell(close);
			cell.setBorderWidth(0);
			primaryTable.addCell(cell);
			
			PdfPTable emptyAssembly = new PdfPTable(2);
			emptyAssembly.setWidthPercentage(100f);
			emptyAssembly.addCell(getCell(""));

			cell = new PdfPCell(close);
			cell.setBorderWidth(0);
			primaryTable.addCell(cell);
			
			cell = new PdfPCell(close);
			cell.setBorderWidth(0);
			primaryTable.addCell(cell);
			
			cell = new PdfPCell(close);
			cell.setBorderWidth(0);
			primaryTable.addCell(cell);
			
			//adding testing to maintable
			
			cell = new PdfPCell(primaryTable);
			cell.setBorderWidth(0);
			infoMainTable.addCell(cell);

			PdfPTable secondaryTable = new PdfPTable(1);
			secondaryTable.setWidthPercentage(100f);
			
			PdfPTable percentage = new PdfPTable(1);
			percentage.setWidthPercentage(100f);
			percentage.addCell(getSmallHeaderCell("Quality Remarks"));

			cell = new PdfPCell(percentage);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);
			
			PdfPTable emptyQuality = new PdfPTable(1);
			emptyQuality.setWidthPercentage(100f);
			emptyQuality.addCell(getCell(""));

			cell = new PdfPCell(emptyQuality);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);
		

			cell = new PdfPCell(emptyQuality);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);
			
			cell = new PdfPCell(emptyQuality);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);
			
			cell = new PdfPCell(emptyQuality);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);
			
			cell = new PdfPCell(emptyQuality);
			cell.setBorderWidth(0);
			secondaryTable.addCell(cell);

			
			
			
			//adding bodyBonnet to maintable
			
			cell = new PdfPCell(secondaryTable);
			cell.setBorderWidth(0);
			cell.setPaddingLeft(10f);
			infoMainTable.addCell(cell);

			return infoMainTable;
		}
}


