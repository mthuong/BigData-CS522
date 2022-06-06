package inmapperwordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InMapperWordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private IntWritable result = new IntWritable();
	
	@Override
	protected void reduce(Text word, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context ctx)
			throws IOException, InterruptedException {
		Integer sum = 0;
		for (IntWritable c : values) {
			sum += c.get();
		}
		result.set(sum);
		ctx.write(word, result);
	}
	
}
