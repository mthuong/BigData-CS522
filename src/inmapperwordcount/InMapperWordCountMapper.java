package inmapperwordcount;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class InMapperWordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Map<Text, IntWritable> mapWords = null;
	
	@Override
	protected void setup(
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		super.setup(context);
		// Initialize
		mapWords = new HashMap<>();
	}

	@Override
	protected void map(LongWritable key, Text record,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		String[] words = record.toString().split("\\s+");
		for (String token : words)
		{
			token = token.trim();
			if (!"".equals(token)) {
				Text word = new Text(token);
					
				if (mapWords.containsKey(word)) {
					// Increase value
					IntWritable value = mapWords.get(word);
					Integer intValue = value.get();
					intValue += 1;
					value.set(intValue);
					mapWords.put(word, value);
				} else {
					// Set word into hash map
					mapWords.put(word, new IntWritable(1));
				}
			}
		}
	}
	
	@Override
	protected void cleanup(
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		super.cleanup(context);
		
		// Close method
		for (Text key : mapWords.keySet()) {
			context.write(key, mapWords.get(key));
		}
	}
	
}
