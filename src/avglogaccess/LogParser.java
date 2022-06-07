package avglogaccess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;

import sun.net.util.IPAddressUtil;

public class LogParser {
	public static Record parseRecord(Text record) {
		Pattern p = Pattern
				.compile("^(\\S+) (\\S+) (\\S+) \\[(.+?)\\] \"(.+?)\" (\\d{3}) (\\d+)$");

		Matcher matcher = p.matcher(record.toString().trim());
		Boolean isMatch = matcher.find();
		if (isMatch) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.printf("%d %s\n", i, matcher.group(i));
			}

			// Parse IP Address and packages number at last group
			String potentialIPV4Address = matcher.group(1);
			String packageNumbers = matcher.group(7);

			boolean isValidIPAddress = IPAddressUtil
					.isIPv4LiteralAddress(potentialIPV4Address);
			if (!isValidIPAddress) {
				// If it is not an IP address, please ignore that record.
				return null;
			}

			return new Record(potentialIPV4Address, packageNumbers);
		}

		return null;
	}
}
