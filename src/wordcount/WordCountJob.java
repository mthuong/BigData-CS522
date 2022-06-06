package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import mapreduce.MapReduceJob;

public class WordCountJob extends
		MapReduceJob<WordCountMapper, WordCountReducer, Text, IntWritable> {

	public WordCountJob(String jobName) throws IOException {
		super(jobName, WordCountMapper.class, WordCountReducer.class, Text.class,
				IntWritable.class);
	}

}
