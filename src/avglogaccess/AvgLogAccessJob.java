package avglogaccess;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import mapreduce.MapReduceJob;

public class AvgLogAccessJob
		extends
		MapReduceJob<AvgLogAccessMapper, AvgLogAccessReducer, Text, LongWritable> {

	public AvgLogAccessJob(String jobName) throws IOException {
		super(jobName, AvgLogAccessMapper.class, AvgLogAccessReducer.class,
				Text.class, LongWritable.class);
	}
}
