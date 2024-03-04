package mini;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



	public class utils {

		public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static CellStyle style;   
		

		public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
		{
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			if(row==null)
			{
				row =ws.createRow(rownum);
			}
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			fo=new FileOutputStream(xlfile);
			wb.write(fo);		
			wb.close();
			fi.close();
			fo.close();
					
		}
		
		public static void fillGreenColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
		{
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cell=row.getCell(colnum);
			
			style=wb.createCellStyle();
			
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
					
			cell.setCellStyle(style);
			fo=new FileOutputStream(xlfile);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
		}
		
		
		public static void fillRedColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
		{
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cell=row.getCell(colnum);
			
			style=wb.createCellStyle();
			
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
			
			cell.setCellStyle(style);		
			fo=new FileOutputStream(xlfile);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
		}
		
	}

