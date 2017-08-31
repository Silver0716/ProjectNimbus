package processor;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.mod_ExcelOutput;

public class proc_ExcelOutput extends mod_ExcelOutput
{
	
	
	public void printOutputValues()
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet;
		int initRowCount = ExcelOutputTable.size();
		
		int ctr_rowConfig = 1;
		int ctr_rowCU = 1;
		
		for(int row=0; row<initRowCount; row++)
		{
			int initColCount = ExcelOutputTable.get(row).size();
			int rowExcel = row+1;
			
			for(int col=0; col<initColCount; col++)
			{
				String keyValue = ExcelOutputTable.get(row).get(col).toString();
				int colExcel = col;
				
				
				switch(keyValue)
				{
					case CollaborateUser :
						
						 sheet = workbook.getSheet(CollaborateUser);
						sheet = isSheetExisting(sheet,workbook,CollaborateUser);
						
						Row rowCU = sheet.createRow(ctr_rowCU);
						
							while(col<initColCount)
							{
								
								Cell cellCU = rowCU.createCell(col);
								String value = (String)ExcelOutputTable.get(row).get(col).toString();
								if(value!=CollaborateUser&&col==0)
								{
									col = col+4;
								}
								else
								{
									cellCU.setCellValue(value);
									col++;
								}
								
							}
							ctr_rowCU++;
						break;

						
					case CommunicateUser :
						
						break;
						
					case PureCloudUser :
						
						break;
					
					case AdministratorUser :
						
						break;
					
					case PureCloudCCManager :
						
						break;
						
					case PureCloudDeveloper :
					
						break;
						
					case Config :
						
						sheet = workbook.getSheet(Config);
						sheet = isSheetExisting(sheet,workbook,Config);
						
						Row rowConfig = sheet.createRow(ctr_rowConfig);
						
						
							while(col<initColCount)
							{
								
								Cell cellCU = rowConfig.createCell(col);
								cellCU.setCellValue((String)ExcelOutputTable.get(row).get(col).toString());
								col++;
							}
						ctr_rowConfig++;
						break;
				}
			}
		}
		
		
		 try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) 
		 {
	            workbook.write(outputStream);
	     }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	
	public void createHeader(XSSFSheet sheet)
	{
		int rowCount = 0;
		int columnCount = 0;
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		
		cell.setCellValue((String)"Category");
		
		cell = row.createCell(1);
		cell.setCellValue((String)"Test Case Objecive");
		
		cell = row.createCell(2);
		cell.setCellValue((String)"Status");
		
		cell = row.createCell(3);
		cell.setCellValue((String)"Remarks");
	}
	
	public XSSFSheet isSheetExisting(XSSFSheet sheet, XSSFWorkbook workbook, String sheetName)
	{
		sheet = workbook.getSheet(sheetName);
		int s = workbook.getNumberOfSheets();
		if(workbook.getNumberOfSheets()!=0)
		{
			for(int i=0; i<workbook.getNumberOfSheets();i++)
			{
				if(workbook.getSheetName(i).equals(sheetName))
				{
					sheet = workbook.getSheet(sheetName);
					return sheet;
				}
			}
			sheet = workbook.createSheet(sheetName);
			createHeader(sheet);
			return sheet;
		}
		else
		{
			sheet = workbook.createSheet(sheetName);
			createHeader(sheet);
			return sheet;
		}
		
	}
	
	
}
