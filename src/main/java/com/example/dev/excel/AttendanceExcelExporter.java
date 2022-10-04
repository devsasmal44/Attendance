package com.example.dev.excel;

import com.example.dev.entity.Attendance;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AttendanceExcelExporter {
    //public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static XSSFWorkbook workbook = new XSSFWorkbook();

    public static Sheet sheet;

    private static void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell=row.createCell(columnCount);
        if(value instanceof String) {
            cell.setCellValue((String) value);
        }else if(value instanceof String) {
            cell.setCellValue((String) value);
        }else if(value instanceof Float) {
            cell.setCellValue((Float) value);
        }else if(value instanceof String) {
            cell.setCellValue((String) value);
        }else if(value instanceof String){
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    private static void writeHeaderLine() {
        sheet=workbook.createSheet("Attendance");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font= (XSSFFont) workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row,0,"Attendance Information",style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        font.setFontHeightInPoints((short)(10));

        row=sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Name", style);
        createCell(row, 1, "Email", style);
        createCell(row, 2, "Temperature", style);
        createCell(row,3,"Office Location",style);
        createCell(row, 4, "Date", style);
        createCell(row, 5, "Time", style);

    }

    private static String[] dateTimeExtractor(Attendance atten){
        long instantTime=atten.getTimestamp();
        Instant instant = Instant.ofEpochSecond(instantTime);
        String result = instant.toString();
        ZonedDateTime dateTime = ZonedDateTime.parse(result);
        String dateTimeString = dateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).format(DateTimeFormatter.ofPattern("E dd-MMM-yyyy hh:mm:ss a"));
        String dateTimeSplitter[] = dateTimeString.split(" ",3);
        return dateTimeSplitter;
    }
    public static ByteArrayInputStream writeDataLines(List<Attendance> attendanceList) {
        writeHeaderLine();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream())
        {
            int rowCount=2;

            CellStyle style=workbook.createCellStyle();
            XSSFFont font= (XSSFFont) workbook.createFont();
            font.setFontHeight(14);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);

            for (Attendance atten : attendanceList) {
                Row row = sheet.createRow(rowCount++);

                row.createCell(0).setCellValue(atten.getName());
                row.createCell(1).setCellValue(atten.getEmail());
                row.createCell(2).setCellValue(atten.getTemperature());
                row.createCell(3).setCellValue(atten.getLocation());
                String dateTimeArray[] = dateTimeExtractor(atten);
                String dateColumn = dateTimeArray[0] + " " + dateTimeArray[1];
                String timeColumn  = dateTimeArray[2];
                row.createCell(4).setCellValue(dateColumn);
                row.createCell(5).setCellValue(timeColumn);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }

}
