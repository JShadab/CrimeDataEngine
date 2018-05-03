package com.shad;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) throws Exception {

		//foo();
		 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// Hardware.getMacAddres();
		// Hardware.printComputerSystemProductInfo();
		// new AuthenticationFrame();
		 
		 AuthenticationFrame.authenticate();
	}

	private static void foo() throws Exception {
		String[][] commands = new String[][] { { "CMD", "/C", "WMIC OS GET Installdate,SerialNumber" },
				// OS Installation date & Time, OS Serial Number
				{ "CMD", "/C", "WMIC BASEBOARD GET SerialNumber" },
				// Motherboadrd Serial Number
				{ "CMD", "/C", "WMIC CPU GET ProcessorId" },
				// Processor ID
				{ "CMD", "/C", "WMIC COMPUTERSYSTEM GET Name" },
				// Computer Name
				{ "CMD", "/C", "WMIC diskdrive GET Name, Manufacturer, Model, InterfaceType, MediaLoaded, MediaType" },
				// HDD Details
				{ "CMD", "/C",
						"WMIC memphysical GET Manufacturer, Model, SerialNumber, MaxCapacity, MemoryDevices" }, };

		for (int i = 0; i < commands.length; i++) {

			String[] com = commands[i];
			Process process = Runtime.getRuntime().exec(com);
			process.getOutputStream().close();
			// Closing output stream of the process
			System.out.println();
			String s = null;
			// Reading successful output of the command
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((s = reader.readLine()) != null) {
				System.out.println(s);
			}

			// Reading error if any
			reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			while ((s = reader.readLine()) != null) {
				System.out.print(s);
			}

		}
	}

}
