package cn.rojao.poi;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Date:2019/3/25
 * Author:jianxian mao
 * Describe:
 */
public class ExcelStyleTest {

    @Test
    public void mergeCell() throws Exception{

        HSSFWorkbook workbook = new HSSFWorkbook();

        CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 2, 4);

        HSSFSheet sheet = workbook.createSheet("test");
        sheet.addMergedRegion(cellRangeAddress);

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        font.setFontHeightInPoints((short)16);

        Row row = sheet.createRow(2);

        Cell cell = row.createCell(2);


        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        //cellStyle.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());

        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);


        cell.setCellValue("你好，我爱你！");

        FileOutputStream outputStream = new FileOutputStream("D:\\test\\test1.xls");
        workbook.write(outputStream);

        outputStream.close();
        workbook.close();



    }

}
