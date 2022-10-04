package com.example.dev.excel;

import com.example.dev.entity.Attendance;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class AttendanceExcelExporter {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Name", "Email", "Temperature", "Office Location", "Date", "Time" };
    static String SHEET = "Attendance Information";

    public static String[] dateTimeExtractor(Attendance atten){
        long instantTime=atten.getTimestamp();
        Instant instant = Instant.ofEpochSecond(instantTime);
        String result = instant.toString();
        ZonedDateTime dateTime = ZonedDateTime.parse(result);
        String dateTimeString = dateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).format(DateTimeFormatter.ofPattern("E dd-MMM-yyyy hh:mm:ss a"));
        String dateTimeSplitter[] = dateTimeString.split(" ",3);
        return dateTimeSplitter;
    }

    public static ByteArrayInputStream writeDataLines(List<Attendance> attendanceList) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Attendance atten : attendanceList) {
                Row row = sheet.createRow(rowIdx++);

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
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

}
