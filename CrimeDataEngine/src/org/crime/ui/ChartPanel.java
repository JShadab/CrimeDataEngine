package org.crime.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import org.crime.db.CrimeDAO;

public class ChartPanel extends JPanel {

	private Map<String, Integer> map;
	private int total;

	public ChartPanel() {
		map = CrimeDAO.mapCountiVsCount();

		total = 0;

		Collection<Integer> values = map.values();
		for (Integer count : values) {

			total += count;

		}

		setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		double x = 50;
		double y = 50;

		double width = getWidth() - 2 * x;
		double height = getHeight() - 2 * y;

		double perPix = height / total;

		Rectangle2D.Double chart = new Rectangle2D.Double(x, y, width, height);

		// y-AXIS
		int value = 0;

		float tempY = (float) height+50;
		for (int i = 0; i < 11; i++) {

			g2d.drawString(String.valueOf(value), (float) x - 30, tempY);

			value = value + total / 10;

			tempY = (float) (tempY - (height / 10));

		}
		// x-AXIS

		Set<String> values = map.keySet();

		int blocks = values.size();

		double range = width / blocks;

		for (String county : values) {

			x = x + range - 100;

			int val = map.get(county);

			g2d.drawString(county, (float) (x), (float) getHeight() - 25);

			double barH = val * perPix;

			g2d.drawString(String.valueOf(val), (float) x, (float) (height - barH + 40));

			Rectangle2D.Double bar = new Rectangle2D.Double(x, height - barH + 50, 50, barH);

			g2d.setColor(Color.BLUE);
			g2d.fill(bar);
			g2d.setColor(Color.BLACK);
		}

		g2d.draw(chart);

	}

}
