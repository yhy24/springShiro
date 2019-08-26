package yhy.util;

import org.apache.poi.hssf.usermodel.*;

/**
 * @Author: yhy
 * @Date: 2018/8/6 14:46
 * @Version 1.0
 */
public class ExcelUtil {

    /**
     * @param sheetName sheetName的文件的名字
     * @param title 标题
     * @param values 对应的值
     * @param hwb 一个excel的对象
     * @return 返回一个 HSSFWorkbook对象
     */
    public static HSSFWorkbook getExcel(String sheetName, String[] title, String[][] values, HSSFWorkbook hwb) {
        System.out.println("-----"+sheetName+"---------"+title.toString()+"-----------"+values.length+"---------");
        //创建一个HSSFWorkbook,对应一个Excel文件
        if (hwb == null) {
            hwb = new HSSFWorkbook();
        }
//        创建一个sheet对象
        HSSFSheet hssfSheet = hwb.createSheet(sheetName);
//        在sheet中添加第零行
        HSSFRow hssfRow = hssfSheet.createRow(0);
//        创建单元格，并设置表格的，并要求居中
        HSSFCellStyle style = hwb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//       声明列单元格对象
        HSSFCell hssfCell = null;
//        创建每列的标题
        for (int i =0;i <title.length;i++) {
            hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(title[i]);
            hssfCell.setCellStyle(style);
        }
//        创建每个cell内容
        for (int i = 0; i < values.length; i++) {
            hssfRow = hssfSheet.createRow(i + 1); //第一行标题已经占用
            for (int j = 0; j < values[i].length; j++) {
                HSSFCell cell = hssfRow.createCell(j);
//                将内容放入cell中
                cell.setCellValue(values[i][j]);
                cell.setCellStyle(style);
            }
        }
        return hwb;
    }

}
