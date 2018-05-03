package com.shad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Hardware {

	public static final void getMacAddres() {

		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());

			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			while (networks.hasMoreElements()) {
				NetworkInterface network = networks.nextElement();
				byte[] mac = network.getHardwareAddress();

				if (mac != null) {
					System.out.print("Current MAC address : ");

					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
					}
					System.out.println(sb.toString());
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public static void printComputerSystemProductInfo() {

		String vbClassName = "Win32_ComputerSystemProduct";
		String[] propNames = new String[] { "Caption", "Description", "IdentifyingNumber", "SKUNumber", "Name", "UUID",
				"Vendor", "Version" };

		String vbScript = makeVbScript(vbClassName, propNames);

		// System.out.println("----------------------------------------");
		// System.out.println(vbScript);
		// System.out.println("----------------------------------------");

		try {
			// Create temporary file.
			File file = File.createTempFile("vbsfile", ".vbs");
			System.out.println("Create File: " + file.getAbsolutePath());
			System.out.println("------");

			// Write script content to file.
			FileWriter fw = new FileWriter(file);
			fw.write(vbScript);
			fw.close();

			// Execute the file.
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());

			// Create Input stream to read data returned after execute vb script file.
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

			Map<String, String> map = new HashMap<String, String>();
			String line;
			int i = 0;
			while ((line = input.readLine()) != null) {
				if (i >= propNames.length) {
					break;
				}
				String key = propNames[i];
				map.put(key, line);
				i++;
			}
			input.close();
			//
			for (String propName : propNames) {
				System.out.println(propName + " : " + map.get(propName));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String makeVbScript(String vbClassName, String[] propNames) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < propNames.length; i++) {
			if (i < propNames.length - 1) {
				sb.append(propNames[i]).append(",");
			} else {
				sb.append(propNames[i]);
			}
		}
		String colNameString = sb.toString();
		sb.setLength(0);

		sb.append("Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")").append("\n");
		sb.append("Set colItems = objWMIService.ExecQuery _ ").append("\n");
		sb.append("(\"Select ").append(colNameString).append(" from ").append(vbClassName).append("\") ").append("\n");
		sb.append("For Each objItem in colItems ").append("\n");
		for (String propName : propNames) {
			sb.append("    Wscript.Echo objItem.").append(propName).append("\n");
		}
		sb.append("Next ").append("\n");
		return sb.toString();
	}
}
