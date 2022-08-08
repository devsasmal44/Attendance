package com.example.myapp.excel;

import com.example.myapp.entity.Attendance;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;

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
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        font.setFontHeightInPoints((short)(10));

        row=sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Email", style);
        createCell(row, 1, "Temperature", style);
        createCell(row,2,"Office Location",style);
        createCell(row, 3, "Date", style);
        createCell(row, 4, "Time", style);

    }

    private String[] dateTimeExtractor(Attendance atten){
        long instantTime=atten.getTimestamp();
        Instant instant = Instant.ofEpochSecond(instantTime);
        String result = instant.toString();
        ZonedDateTime dateTime = ZonedDateTime.parse(result);
        String dateTimeString = dateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
        String dateTimeSplitter[] = dateTimeString.split(" ",2);
        return dateTimeSplitter;
    }

    private void writeDataLines() {
        int rowCount=2;

        CellStyle style=workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for(Attendance atten:attendanceList) {
            Row row=sheet.createRow(rowCount++);
            int columnCount=0;
            createCell(row, columnCount++, atten.getEmail(), style);
            createCell(row, columnCount++, atten.getTemperature(), style);
            createCell(row, columnCount++, atten.getLocation(), style);
            String dateTimeArray[] = dateTimeExtractor(atten);
            String dateColumn = dateTimeArray[0];
            String timeColumn  = dateTimeArray[1];
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
