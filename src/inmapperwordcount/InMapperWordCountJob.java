package inmapperwordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import mapreduce.MapReduceJob;

public class InMapperWordCountJob extends
		MapReduceJob<InMapperWordCountMapper, InMapperWordCountReducer, Text, IntWritable> {

	public InMapperWordCountJob(String jobName) throws IOException {
		super(jobName, InMapperWordCountMapper.class, InMapperWordCountReducer.class, Text.class,
				IntWritable.class);
	}

}
