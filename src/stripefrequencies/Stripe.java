package stripefrequencies;

import java.util.Set;

import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Writable;

public class Stripe extends MapWritable {
	@Override
	public String toString() {
		String s = new String("{ ");
		Set<Writable> keys = this.keySet();
		for (Writable key : keys) {
			Writable count = this.get(key);
			s = s + key.toString() + ":" + count.toString() + ", ";
		}
		
		if(keys.size() > 0) {
			s = s.substring(0, s.length() - 2);
		}

		s = s + " }";
		return s;
	}
}
