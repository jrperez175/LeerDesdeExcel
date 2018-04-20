package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel {

	// Dependencies: POI | HSSF Workbook/Sheet/Row/Cell
	// This method will read and return Excel data into a double array
	public static String[][] get(String filename) { //obtiene los datos del excel y recibe la url donde esta el archivo
		String[][] dataTable = null; //se crea matriz
		File file = new File(filename);
		try {
			// Create a file input stream to read Excel workbook and worksheet
			FileInputStream xlfile = new FileInputStream(file); //instacia un libro
			HSSFWorkbook xlwb = new HSSFWorkbook(xlfile); //instacia una hoja
			HSSFSheet xlSheet = xlwb.getSheetAt(0); //lee nombre de la hoja

			// Get the number of rows and columns
			int numRows = xlSheet.getLastRowNum() + 1; //limites de columna y fila
			int numCols = xlSheet.getRow(0).getLastCellNum(); //limites de columna y fila

			// Create double array data table - rows x cols
			// We will return this data table
			dataTable = new String[numRows][numCols]; 

			// For each row, create a HSSFRow, then iterate through the "columns"
			// For each "column" create an HSSFCell to grab the value at the specified cell
			// (i,j)
			for (int i = 0; i < numRows; i++) {
				HSSFRow xlRow = xlSheet.getRow(i);
				for (int j = 0; j < numCols; j++) {
					HSSFCell xlCell = xlRow.getCell(j);
					dataTable[i][j] = xlCell.toString();
				}
			}
		} catch (IOException e) {
			System.out.println("ERROR FILE HANDLING " + e.toString());
		}
		
		return dataTable;
	}

}
