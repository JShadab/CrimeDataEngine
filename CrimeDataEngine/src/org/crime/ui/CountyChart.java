package org.crime.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import org.crime.db.CrimeDAO;
import org.crime.model.CrimeData;
import org.crime.utils.CrimeDataNotFoundException;

public class CountyChart extends JFrame {

	public CountyChart() {
		setTitle("Crime Data Chart");

		Container container = this.getContentPane();

		container.add(new ChartPanel(), BorderLayout.CENTER);
		container.add(getBottomPanel(), BorderLayout.SOUTH);

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel getBottomPanel() {
		JPanel panel = new JPanel();

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CountyChart.this.dispose();

			}
		});

		panel.add(btnClose);

		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		panel.setBorder(border);

		return panel;

	}

}
