package org.crime.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.crime.model.CrimeData;

public class CrimeDAO {

	public static List<CrimeData> getCrimeDataByLongitude(String longitude) {

		Connection connection = DatabaseHandler.handleDbConnection();

		List<CrimeData> allCrimeData = null;

		try {

			String select = "SELECT * FROM crimedata WHERE Longitude=?";

			PreparedStatement ps = connection.prepareStatement(select);

			ps.setString(1, longitude);

			ResultSet rs = ps.executeQuery();

			allCrimeData = extractData(rs);

			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCrimeData;
	}

	public static List<CrimeData> getCrimeDataByLatitude(String latitude) {

		Connection connection = DatabaseHandler.handleDbConnection();

		List<CrimeData> allCrimeData = null;

		try {

			String select = "SELECT * FROM crimedata WHERE Latitude=?";

			PreparedStatement ps = connection.prepareStatement(select);

			ps.setString(1, latitude);

			ResultSet rs = ps.executeQuery();

			allCrimeData = extractData(rs);

			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCrimeData;
	}

	public static List<CrimeData> getCrimeDataByLSOAname(String LSOAname) {

		Connection connection = DatabaseHandler.handleDbConnection();

		List<CrimeData> allCrimeData = null;

		try {

			String select = "SELECT * FROM crimedata WHERE LSOA_name=?";

			PreparedStatement ps = connection.prepareStatement(select);

			ps.setString(1, LSOAname);

			ResultSet rs = ps.executeQuery();

			allCrimeData = extractData(rs);

			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCrimeData;
	}

	public static List<CrimeData> getCrimeDataByCrimeType(String crimeType) {

		Connection connection = DatabaseHandler.handleDbConnection();

		List<CrimeData> allCrimeData = null;

		try {

			String select = "SELECT * FROM crimedata WHERE Crime_type=?";

			PreparedStatement ps = connection.prepareStatement(select);

			ps.setString(1, crimeType);

			ResultSet rs = ps.executeQuery();

			allCrimeData = extractData(rs);

			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCrimeData;
	}

	public static List<CrimeData> getCrimeDataByLongitudeAndLatitude(String longitude, String latitude) {

		Connection connection = DatabaseHandler.handleDbConnection();

		List<CrimeData> allCrimeData = null;

		try {

			String select = "SELECT * FROM crimedata WHERE Latitude=? AND Longitude=?";

			PreparedStatement ps = connection.prepareStatement(select);

			ps.setString(1, latitude);
			ps.setString(2, longitude);

			ResultSet rs = ps.executeQuery();

			allCrimeData = extractData(rs);

			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCrimeData;
	}

	public static String[] getAllCrimeTypes() {

		Connection connection = DatabaseHandler.handleDbConnection();

		Set<String> crimeTypes = new HashSet<>();

		try {

			String select = "SELECT Crime_type FROM crimedata";

			PreparedStatement ps = connection.prepareStatement(select);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				crimeTypes.add(rs.getString("Crime_type"));
			}

			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return crimeTypes.toArray(new String[] {});
	}

	public static List<CrimeData> getCrimeDataWithNoCrimeId() {

		Connection connection = DatabaseHandler.handleDbConnection();

		List<CrimeData> allCrimeData = null;

		try {

			String select = "SELECT * FROM crimedata WHERE Crime_ID IS NULL OR Crime_ID = ''";

			PreparedStatement ps = connection.prepareStatement(select);

			ResultSet rs = ps.executeQuery();

			allCrimeData = extractData(rs);

			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCrimeData;
	}

	public static List<CrimeData> getCrimeDataWithDuplicateCrimeId() {

		Connection connection = DatabaseHandler.handleDbConnection();

		Map<String, CrimeData> idVsCrimeData = getAllCrimeData();

		List<CrimeData> allDuplicateCrimeData = new ArrayList<>();

		try {

			String select = "SELECT Crime_ID, COUNT(*) FROM crimedata  GROUP BY Crime_ID HAVING COUNT(*) > 1";

			PreparedStatement ps = connection.prepareStatement(select);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String crimeId = rs.getString("Crime_ID");

				if (crimeId.isEmpty()) {
					continue;
				}

				allDuplicateCrimeData.add(idVsCrimeData.get(crimeId));

			}

			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allDuplicateCrimeData;

	}

	private static Map<String, CrimeData> getAllCrimeData() {

		Connection connection = DatabaseHandler.handleDbConnection();

		Map<String, CrimeData> idVsCrimeData = new HashMap<>();

		try {

			String select = "SELECT * FROM crimedata";

			PreparedStatement ps = connection.prepareStatement(select);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				CrimeData crimeData = new CrimeData();

				crimeData.setContext(rs.getString("Context"));
				crimeData.setCrimeId(rs.getString("Crime_ID"));
				crimeData.setCrimeType(rs.getString("Crime_type"));
				crimeData.setFallsWithin(rs.getString("Falls_within"));
				crimeData.setLastOutcomeCategory(rs.getString("Last_outcome_category"));
				crimeData.setLatitude(rs.getString("Latitude"));
				crimeData.setLocation(rs.getString("Location"));
				crimeData.setLongitude(rs.getString("Longitude"));
				crimeData.setLSOA_code(rs.getString("LSOA_code"));
				crimeData.setMonth(rs.getString("Month"));
				crimeData.setLSOA_name(rs.getString("LSOA_name"));
				crimeData.setReportedBy(rs.getString("Reported_by"));

				idVsCrimeData.put(crimeData.getCrimeId(), crimeData);

			}

			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idVsCrimeData;
	}

	private static List<CrimeData> extractData(ResultSet rs) throws SQLException {

		List<CrimeData> allCrimeData = new ArrayList<>();

		while (rs.next()) {

			CrimeData crimeData = new CrimeData();

			crimeData.setContext(rs.getString("Context"));
			crimeData.setCrimeId(rs.getString("Crime_ID"));
			crimeData.setCrimeType(rs.getString("Crime_type"));
			crimeData.setFallsWithin(rs.getString("Falls_within"));
			crimeData.setLastOutcomeCategory(rs.getString("Last_outcome_category"));
			crimeData.setLatitude(rs.getString("Latitude"));
			crimeData.setLocation(rs.getString("Location"));
			crimeData.setLongitude(rs.getString("Longitude"));
			crimeData.setLSOA_code(rs.getString("LSOA_code"));
			crimeData.setMonth(rs.getString("Month"));
			crimeData.setLSOA_name(rs.getString("LSOA_name"));
			crimeData.setReportedBy(rs.getString("Reported_by"));

			allCrimeData.add(crimeData);

		}

		return allCrimeData;
	}

}
