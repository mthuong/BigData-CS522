package stripefrequencies;

import java.io.IOException;

import org.apache.hadoop.io.Text;

import mapreduce.MapReduceJob;

public class StripeFrequenciesJob
		extends
		MapReduceJob<StripeFrequenciesMapper, StripeFrequenciesReducer, Text, Stripe> {

	public StripeFrequenciesJob(String jobName) throws IOException {
		super(jobName, StripeFrequenciesMapper.class, StripeFrequenciesReducer.class,
				Text.class, Stripe.class);
	}
}
