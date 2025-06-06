package org.example.clinic_system.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.model.Patient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    public static ByteArrayInputStream patientsToExcel(List<PatientDTO> patients) throws IOException {
        String[] columns = { "ID", "Nombre", "Apellido", "DNI", "Email", "Teléfono" };

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Pacientes");

            // Cabecera
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
            }

            // Datos
            int rowIdx = 1;
            for (PatientDTO patient : patients) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(patient.getId_patient().toString());
                row.createCell(1).setCellValue(patient.getFirst_name());
                row.createCell(2).setCellValue(patient.getLast_name());
                row.createCell(3).setCellValue(patient.getDni());
                row.createCell(4).setCellValue(patient.getEmail());
                row.createCell(5).setCellValue(patient.getPhone());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
