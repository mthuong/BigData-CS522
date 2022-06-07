package pairfrequencies;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class PairFrequenciesReducer extends
		Reducer<FrequencyPair, IntWritable, FrequencyPair, DoubleWritable> {
	Integer sum;
	
	@Override
	protected void setup(
			Reducer<FrequencyPair, IntWritable, FrequencyPair, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		sum = 0;
	}
	
	@Override
	protected void reduce(FrequencyPair pair, Iterable<IntWritable> values,
			Reducer<FrequencyPair, IntWritable, FrequencyPair, DoubleWritable>.Context ctx)
			throws IOException, InterruptedException {
		Integer s = 0;
		for (IntWritable c : values) {
			s += c.get();
		}
		if (pair.getRight().equals(FrequencyPair.specialToken)) {
			sum = s;
		} else {
			ctx.write(pair, new DoubleWritable(s * 1.0 / sum ));
		}
	}
}
