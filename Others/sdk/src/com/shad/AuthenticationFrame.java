package com.shad;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AuthenticationFrame {

	private JFrame frame;
	private JTextField txtEmail;
	private JTextField txtRSID;
	private JTextField txtSerial;

	public AuthenticationFrame() {
		iniGUi();
	}

	private void iniGUi() {
		frame = new JFrame("Product Name");
		Container con = frame.getContentPane();

		con.add(getCenterPanel(), BorderLayout.CENTER);
		con.add(getButtonPanel(), BorderLayout.SOUTH);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

	private JPanel getCenterPanel() {

		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel lbSerial = new JLabel("*Serial Number");
		txtSerial = new JTextField(30);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lbSerial, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(txtSerial, gbc);

		JLabel lbEmail = new JLabel("Email");
		txtEmail = new JTextField(30);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(lbEmail, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(txtEmail, gbc);

		JLabel lbRSID = new JLabel("RSID");
		txtRSID = new JTextField(30);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(lbRSID, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(txtRSID, gbc);

		return panel;
	}

	private JPanel getButtonPanel() {

		JPanel panel = new JPanel();

		JButton btnAuthenticate = new JButton("Authenticate");
		JButton btnExit = new JButton("Exit");

		panel.add(btnAuthenticate);
		panel.add(btnExit);

		btnAuthenticate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				authenticate();

			}

		});

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});

		return panel;
	}

	public static void authenticate() {

		String serial = "1234"/* txtSerial.getText() */;
		String email = "abc@gmail.com" /* txtEmail.getText() */;
		String rsid = "hdhdhdhdh"/* txtRSID.getText() */;

		/***********************************************/
		try {
			String urlParameters = "serial=" + serial + "&email=" + email + "&rsid=" + rsid;
			byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;

			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", CHAR_SET);
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			conn.setUseCaches(false);
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();

				int responseCode = conn.getResponseCode();
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Post parameters : " + urlParameters);
				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/***********************************************/

		// URLConnection connection;
		//
		// connection = new URL(URL).openConnection();
		//
		// connection.setRequestProperty("Accept-Charset", CHAR_SET);
		// InputStream response = connection.getInputStream();
		//
		// try (Scanner scanner = new Scanner(response)) {
		// String responseBody = scanner.useDelimiter("\\A").next();
		// System.out.println(responseBody);
		// }

	}

	private static final String URL = "http://localhost:8080/licence/test";
	private static final String CHAR_SET = java.nio.charset.StandardCharsets.UTF_8.name();
}
