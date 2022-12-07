package com.example.dev.excel;

import com.example.dev.entity.Attendance;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class AttendanceExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Attendance> attendanceList;

    public AttendanceExcelExporter(List<Attendance> attendanceList) {
        this.attendanceList=attendanceList;
        workbook = new XSSFWorkbook();

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
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
    private void writeHeaderLine() {
        sheet=workbook.createSheet("Attendance");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row,0,"Attendance Information",style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
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

    private String[] dateTimeExtractor(Attendance atten){
        long instantTime=atten.getTimestamp();
        Instant instant = Instant.ofEpochSecond(instantTime);
        String result = instant.toString();
        ZonedDateTime dateTime = ZonedDateTime.parse(result);
        String dateTimeString = dateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).format(DateTimeFormatter.ofPattern("E dd-MMM-yyyy hh:mm:ss a"));
        String dateTimeSplitter[] = dateTimeString.split(" ",3);
        return dateTimeSplitter;
    }

    private void writeDataLines() {
        int rowCount=2;

        CellStyle style=workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00"));

        for(Attendance atten:attendanceList) {
            Row row=sheet.createRow(rowCount++);
            int columnCount=0;
            createCell(row, columnCount++, atten.getName(), style);
            createCell(row, columnCount++, atten.getEmail(), style);
            createCell(row, columnCount++, atten.getTemperature(), style);
            createCell(row, columnCount++, atten.getLocation(), style);
            String dateTimeArray[] = dateTimeExtractor(atten);
            String dateColumn = dateTimeArray[0] + " " + dateTimeArray[1];
            String timeColumn  = dateTimeArray[2];
            createCell(row, columnCount++, dateColumn, style);
            createCell(row, columnCount++, timeColumn, style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}