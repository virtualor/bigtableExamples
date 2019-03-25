package cn.itcast.utils;

import cn.itcast.pojo.TAvgpvNum;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Date:2019/3/26
 * Author:jianxian mao
 * Describe:
 */
public class ExportExcelUtil {
    public static void exportAvgPvNum(List<TAvgpvNum> list, ServletOutputStream outputStream) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("近7日平均访问量");

        HSSFCellStyle titleCellStyle = createCellStyle(workbook,16);
        HSSFCellStyle rowCellStyle = createCellStyle(workbook,12);


        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 1);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFRow rowTitle = sheet.createRow(0);
        HSSFCell rowTitleCell = rowTitle.createCell(1);
        rowTitleCell.setCellStyle(titleCellStyle);
        rowTitleCell.setCellValue("近7日平均访问量");

        String[] columns = {"日期","浏览次数（PV）"};

        HSSFRow row = sheet.createRow(1);
        for (int i = 0; i < columns.length; i++) {
            String column = columns[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(rowCellStyle);
            cell.setCellValue(column);
        }


        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 2);
            HSSFCell cell1 = row1.createCell(0);
            cell1.setCellValue(list.get(i).getDatestr());
            HSSFCell cell2 = row1.createCell(1);
            cell1.setCellValue(list.get(i).getAvgpvnum().doubleValue());
        }

        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, int size) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)size);
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }
}
