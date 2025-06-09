package org.example.clinic_system.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.model.Patient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    public static ByteArrayInputStream patientsToExcel(List<PatientDTO> patients) throws IOException {
        String[] columns = {
                "ID",
                "Nombre",
                "Apellido",
                "Email",
                "Dirección",
                "Teléfono",
                "Teléfono fijo",
                "DNI",
                "Fecha de nacimiento",
                "Género",
                "Edad",
                "Antecedentes"
        };

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Pacientes");

            // Crear estilo para la cabecera
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Estilo de fuente para la cabecera
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);

            // Alinear texto al centro
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Crear fila de cabecera
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerStyle);
            }

            // Datos
            int rowIdx = 1;
            for (PatientDTO patient : patients) {
                Row row = sheet.createRow(rowIdx);

                row.createCell(0).setCellValue(rowIdx);
                row.createCell(1).setCellValue(patient.getFirst_name());
                row.createCell(2).setCellValue(patient.getLast_name());
                row.createCell(3).setCellValue(patient.getEmail());
                row.createCell(4).setCellValue(patient.getAddress());
                row.createCell(5).setCellValue(patient.getPhone());
                row.createCell(6).setCellValue(patient.getLandline_phone());
                row.createCell(7).setCellValue(patient.getDni());
                row.createCell(8).setCellValue(patient.getBirthdate() != null ? patient.getBirthdate().toString() : "");
                row.createCell(9).setCellValue(patient.getGender() != null ? patient.getGender().toString() : "");
                row.createCell(10).setCellValue(patient.getAge() != null ? patient.getAge() : 0);
                row.createCell(11).setCellValue(patient.getAntecedent() != null ? patient.getAntecedent() : "");

                rowIdx++;
            }

            // autoajustar columnas
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream doctorsToExcel(List<DoctorDTO> doctors) throws IOException {
        String[] columns = {
                "ID",
                "Nombre",
                "Apellido",
                "Especialidad",
                "Email",
                "Dirección",
                "Teléfono",
                "Teléfono fijo",
                "DNI",
        };

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Doctores");

            // Crear estilo para la cabecera
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Estilo de fuente para la cabecera
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);

            // Alinear texto al centro
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Crear fila de cabecera
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerStyle);
            }

            //Datos
            int rowIdx = 1;
            for (DoctorDTO doctor : doctors) {
                Row row = sheet.createRow(rowIdx);

                row.createCell(0).setCellValue(rowIdx);
                row.createCell(1).setCellValue(doctor.getFirst_name());
                row.createCell(2).setCellValue(doctor.getLast_name());
                row.createCell(3).setCellValue(doctor.getSpecialty().getSpecialty_name());
                row.createCell(4).setCellValue(doctor.getEmail());
                row.createCell(5).setCellValue(doctor.getAddress());
                row.createCell(6).setCellValue(doctor.getPhone());
                row.createCell(7).setCellValue(doctor.getLandline_phone());
                row.createCell(8).setCellValue(doctor.getDni());

                rowIdx++;
            }

            // autoajustar columnas
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}
