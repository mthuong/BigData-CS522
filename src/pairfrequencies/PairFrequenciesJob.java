package pairfrequencies;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;

import mapreduce.MapReduceJob;

public class PairFrequenciesJob
		extends
		MapReduceJob<PairFrequenciesMapper, PairFrequenciesReducer, FrequencyPair, IntWritable> {

	public PairFrequenciesJob(String jobName) throws IOException {
		super(jobName, PairFrequenciesMapper.class, PairFrequenciesReducer.class,
				FrequencyPair.class, IntWritable.class);
	}
}
