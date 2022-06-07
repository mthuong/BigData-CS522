package stripefrequencies;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StripeFrequenciesMapper extends
		Mapper<LongWritable, Text, Text, Stripe> {
	
	private IntWritable ONE = new IntWritable(1);

	@Override
	protected void map(
			LongWritable key,
			Text record,
			Mapper<LongWritable, Text, Text, Stripe>.Context context)
			throws IOException, InterruptedException {
		String[] tokens = record.toString().split("\\s+");
		if (tokens.length > 1) {
			for (int i = 0; i < tokens.length; i++) {
				Stripe H = new Stripe();
				int j = i + 1;
				while (j < tokens.length && !tokens[j].equals(tokens[i])) {
					Text xKey = new Text();
					xKey.set(tokens[j]);
					if (H.containsKey(xKey)) {
						IntWritable value = (IntWritable) H.get(xKey);
						Integer currentValue = value.get() + 1;
						value.set(currentValue);
						H.put(xKey, value);
					} else {
						H.put(xKey, ONE);
					}
					
					Text uKey = new Text();
					uKey.set(tokens[i]);
					context.write(uKey, H);
					
					j++;
				}
			}
		}
	}
}
