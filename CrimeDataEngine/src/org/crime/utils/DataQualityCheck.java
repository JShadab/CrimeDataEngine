package org.crime.utils;

import java.io.FileWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.crime.db.CrimeDAO;
import org.crime.model.CrimeData;

public class DataQualityCheck {
	
	public static void exportNoCrimeId() {
		
		List<CrimeData> allCrimeData = CrimeDAO.getCrimeDataWithNoCrimeId();
		
		actionExport(allCrimeData);
	}

	private static void actionExport(List<CrimeData> allCrimeData) {

		JFileChooser fileChooser = new JFileChooser();

		StringBuilder sb = new StringBuilder();

		for (CrimeData crimeData : allCrimeData) {
			sb.append(crimeData.toString());
			sb.append("\n");
		}

		int retrival = fileChooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				FileWriter fw = new FileWriter(fileChooser.getSelectedFile() + ".txt");
				fw.write(sb.toString());

				fw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		JOptionPane.showMessageDialog(null, "Data Exported Successfully");
	}

	public static void exportDuplicateCrimeId() {
		List<CrimeData> allCrimeData = CrimeDAO.getCrimeDataWithDuplicateCrimeId();

		actionExport(allCrimeData);
		
	}

}
