package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String serial;
	private String email;
	private String rsid;
	private String deviceId;
	private String hardwareId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRsid() {
		return rsid;
	}

	public void setRsid(String rsid) {
		this.rsid = rsid;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getHardwareId() {
		return hardwareId;
	}

	public void setHardwareId(String hardwareId) {
		this.hardwareId = hardwareId;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", serial=" + serial + ", email=" + email + ", rsid=" + rsid + ", deviceId="
				+ deviceId + ", hardwareId=" + hardwareId + "]";
	}

}
