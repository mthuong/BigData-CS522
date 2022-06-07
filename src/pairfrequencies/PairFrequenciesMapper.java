package pairfrequencies;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PairFrequenciesMapper extends
		Mapper<LongWritable, Text, FrequencyPair, IntWritable> {

	private IntWritable ONE = new IntWritable(1);

	@Override
	protected void map(
			LongWritable key,
			Text record,
			Mapper<LongWritable, Text, FrequencyPair, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String[] tokens = record.toString().split("\\s+");
		if (tokens.length > 1) {
			for (int i = 0; i < tokens.length; i++) {
				int j = i + 1;
				while (j < tokens.length && !tokens[j].equals(tokens[i])) {
					FrequencyPair frequencyPair = new FrequencyPair(tokens[i],
							tokens[j]);
					context.write(frequencyPair, ONE);
					context.write(new FrequencyPair(tokens[i], FrequencyPair.specialToken), ONE);
					j++;
				}
			}
		}
	}
}
