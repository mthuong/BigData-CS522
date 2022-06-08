package stripefrequencies;

import java.io.IOException;
import java.util.Set;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

public class StripeFrequenciesReducer extends
		Reducer<Text, Stripe, Text, Stripe> {

	@Override
	protected void reduce(Text u, Iterable<Stripe> values,
			Reducer<Text, Stripe, Text, Stripe>.Context ctx)
			throws IOException, InterruptedException {
		Stripe HF = new Stripe();
		
		Integer sum = 0;
		for (Stripe H : values) {
			Set<Writable> keys = H.keySet();
			for (Writable key : keys) {
				IntWritable fromCount = (IntWritable) H.get(key);
				if (HF.containsKey(key)) {
					IntWritable count = (IntWritable) HF.get(key);
					count.set(count.get() + fromCount.get());
					HF.put(key, count);
				} else {
					HF.put(key, fromCount);
				}
				
				sum += fromCount.get();
			}
		}

		Set<Writable> keys = HF.keySet();
		for (Writable key : keys) {
			IntWritable count = (IntWritable) HF.get(key);
			HF.put(key,	new DoubleWritable(count.get() * 1.0 / sum));
		}
		
		ctx.write(u, HF);
	}
}
