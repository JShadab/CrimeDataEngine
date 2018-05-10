package org.crime.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import org.crime.db.CrimeDAO;
import org.crime.model.CrimeData;
import org.crime.utils.CrimeDataNotFoundException;
import org.crime.utils.DataQualityCheck;

public class GUIHandler extends JFrame {

	public GUIHandler() {
		setTitle("Crime Data Engine");

		Container container = this.getContentPane();

		container.add(getTopPanel(), BorderLayout.NORTH);
		container.add(getCenterPanel(), BorderLayout.CENTER);
		container.add(getBottomPanel(), BorderLayout.SOUTH);

		setJMenuBar(getMyMenuBar());

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JMenuBar getMyMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Export");

		JMenuItem itemNoCrimeId = new JMenuItem("No Crime Id");
		JMenuItem itemDuplicateCrimeId = new JMenuItem("Duplicate Crime Id");

		itemNoCrimeId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DataQualityCheck.exportNoCrimeId();

			}
		});

		itemDuplicateCrimeId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DataQualityCheck.exportDuplicateCrimeId();
			}
		});

		menu.add(itemNoCrimeId);
		menu.add(itemDuplicateCrimeId);

		JMenu menuDisplay = new JMenu("Display");

		JMenuItem itemCountyChart = new JMenuItem("County Chart");

		itemCountyChart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new CountyChart();
			}
		});
		menuDisplay.add(itemCountyChart);

		menuBar.add(menu);
		menuBar.add(menuDisplay);

		return menuBar;
	}

	private JPanel getTopPanel() {

		JPanel panel = new JPanel();

		JPanel searchPanel = new JPanel();

		JLabel lbSeach = new JLabel("Search By: ");
		cmbSearch = new JComboBox<>(
				new String[] { "Longitude", "Latitude", "Longitude & Latitude", "LSOA name", "Crime Type" });

		cmbCrimeType = new JComboBox<>(CrimeDAO.getAllCrimeTypes());

		txtSearch = new JTextField(15);
		searchPanel.add(txtSearch);

		txtLongitude = new JTextField(15);
		txtLatitude = new JTextField(15);

		cmbSearch.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) e.getItem();

					if ("Longitude & Latitude".equals(item)) {
						searchPanel.removeAll();

						searchPanel.add(txtLongitude);
						searchPanel.add(txtLatitude);

					} else if ("Crime Type".equals(item)) {
						searchPanel.removeAll();

						searchPanel.add(cmbCrimeType);
					} else {
						searchPanel.removeAll();
						searchPanel.add(txtSearch);

					}

					panel.revalidate();
				}

			}
		});

		panel.add(lbSeach);
		panel.add(cmbSearch);
		panel.add(searchPanel);

		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		panel.setBorder(border);

		return panel;

	}

	private JPanel getCenterPanel() {

		JPanel panel = new JPanel(new BorderLayout());

		tabel = new JTable();

		tabel.setRowHeight(20);

		JScrollPane jsp = new JScrollPane(tabel);

		panel.add(jsp);

		return panel;

	}

	private JPanel getBottomPanel() {

		JPanel panel = new JPanel();

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionSearch();

			}

		});

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIHandler.this.dispose();

			}
		});

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tabel.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}

			}

		});

		panel.add(btnSearch);
		panel.add(btnPrint);
		panel.add(btnClose);

		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		panel.setBorder(border);

		return panel;

	}

	private class CrimeTableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return allCrimeData.size();

		}

		@Override
		public int getColumnCount() {

			return columns.length;
		}

		@Override
		public String getColumnName(int colIndex) {
			return columns[colIndex];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			CrimeData crimeData = allCrimeData.get(rowIndex);

			switch (columnIndex) {
			case COL_ID: {
				return crimeData.getCrimeId();
			}
			case COL_MONTH: {
				return crimeData.getMonth();
			}
			case COL_REPORTED_BY: {
				return crimeData.getReportedBy();
			}
			// case COL_CONTEXT: {
			// return crimeData.getContext();
			// }
			case COL_CRIME_TYPE: {
				return crimeData.getCrimeType();
			}
			case COL_FALLS_WITHIN: {
				return crimeData.getFallsWithin();
			}
			case COL_LAST_OUTCOME_CATEGORY: {
				return crimeData.getLastOutcomeCategory();
			}
			case COL_LATITUDE: {
				return crimeData.getLatitude();
			}
			case COL_LONGITUDE: {
				return crimeData.getLongitude();
			}
			case COL_LOCATION: {
				return crimeData.getLocation();
			}
			case COL_LOSA_CODE: {
				return crimeData.getLSOA_code();
			}
			case COL_LOSA_NAME: {
				return crimeData.getLSOA_name();
			}

			default:
				return "";
			}

		}
	}

	private void actionSearch() {

		String item = (String) cmbSearch.getSelectedItem();

		try {
			if ("Longitude & Latitude".equals(item)) {

				String longitude = txtLongitude.getText();
				String latitude = txtLatitude.getText();
				allCrimeData = CrimeDAO.getCrimeDataByLongitudeAndLatitude(longitude, latitude);

			} else if ("Crime Type".equals(item)) {
				String crimeType = (String) cmbCrimeType.getSelectedItem();
				allCrimeData = CrimeDAO.getCrimeDataByCrimeType(crimeType);
			} else if ("Longitude".equals(item)) {
				String longitude = txtSearch.getText();
				allCrimeData = CrimeDAO.getCrimeDataByLongitude(longitude);
			} else if ("Latitude".equals(item)) {
				String latitude = txtSearch.getText();
				allCrimeData = CrimeDAO.getCrimeDataByLatitude(latitude);
			} else if ("LSOA name".equals(item)) {
				String losaName = txtSearch.getText();
				allCrimeData = CrimeDAO.getCrimeDataByLSOAname(losaName);
			}
		} catch (CrimeDataNotFoundException e) {
			allCrimeData = new ArrayList<>();
		}

		if (allCrimeData.size() > 10) {

			allCrimeData = allCrimeData.subList(0, 10);

		}

		tabel.setModel(new CrimeTableModel());

	}

	static final private String[] columns = { "crimeId", "month", "reportedBy", "fallsWithin", "longitude", "latitude",
			"location", "LSOA_code", "LSOA_name", "crimeType", "lastOutcomeCategory"/* , "Context" */ };

	private List<CrimeData> allCrimeData;
	private JComboBox<String> cmbSearch;
	private JTextField txtSearch;
	private JTextField txtLongitude;
	private JTextField txtLatitude;
	private JComboBox<String> cmbCrimeType;
	private JTable tabel;

	private static final int COL_ID = 0;
	private static final int COL_MONTH = 1;
	private static final int COL_REPORTED_BY = 2;
	private static final int COL_FALLS_WITHIN = 3;
	private static final int COL_LONGITUDE = 4;
	private static final int COL_LATITUDE = 5;
	private static final int COL_LOCATION = 6;
	private static final int COL_LOSA_CODE = 7;
	private static final int COL_LOSA_NAME = 8;
	private static final int COL_CRIME_TYPE = 9;
	private static final int COL_LAST_OUTCOME_CATEGORY = 10;
	// private static final int COL_CONTEXT = 11;
}
