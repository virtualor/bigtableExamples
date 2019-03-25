package cn.rojao.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Date:2019/3/24
 * Author:moriton
 * Desc:
 */
public class POITest {

    @Test
    public void write2Excel()throws Exception{

        HSSFWorkbook sheets = new HSSFWorkbook();
        HSSFSheet sheet = sheets.createSheet("test");
        HSSFRow row = sheet.createRow(3);
        HSSFCell cell = row.createCell(3);
        cell.setCellValue("hello world");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\test.xls");
        sheets.write(fileOutputStream);
        fileOutputStream.close();
        sheets.close();
    }


    @Test
    public void readFromExcel()throws Exception{
        FileInputStream fileInputStream = new FileInputStream("D:\\test\\test.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.getRow(3);
        HSSFCell cell = row.getCell(3);
        String stringCellValue = cell.getStringCellValue();
        System.out.println(stringCellValue);
        workbook.close();
        fileInputStream.close();
    }


    @Test
    public void readFrom03And07() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("D:\\test\\test.xlsx");
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(2);
        Cell cell = row.getCell(2);
        String value = cell.getStringCellValue();
        System.out.println(value);
        workbook.close();
        fileInputStream.close();
    }

}
