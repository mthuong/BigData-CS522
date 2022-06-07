package avglogaccess;

public class Record {
	private String ipAddress;
	
	private String packages;

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public Record(String ipAddress, String packages) {
		super();
		this.ipAddress = ipAddress;
		this.packages = packages;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getPackages() {
		return packages;
	}
	
}
