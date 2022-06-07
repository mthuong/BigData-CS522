package inmapperavglogaccess;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import mapreduce.MapReduceJob;

public class InMapperAvgLogAccessJob
		extends
		MapReduceJob<InMapperAvgLogAccessMapper, InMapperAvgLogAccessReducer, Text, Pair> {

	public InMapperAvgLogAccessJob(String jobName) throws IOException {
		super(jobName, InMapperAvgLogAccessMapper.class, InMapperAvgLogAccessReducer.class,
				Text.class, Pair.class);
	}
}
