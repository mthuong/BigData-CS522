package avglogaccess;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgLogAccessReducer extends
		Reducer<Text, LongWritable, Text, LongWritable> {
	private LongWritable result = new LongWritable();
	
	@Override
	protected void reduce(Text ipAddress, Iterable<LongWritable> values,
			Reducer<Text, LongWritable, Text, LongWritable>.Context ctx)
			throws IOException, InterruptedException {
		
		Long sum = 0L;
		Long count = 0L;
		for (LongWritable c : values) {
			sum += c.get();
			count += 1;
		}
		result.set(sum/count);
		ctx.write(ipAddress, result);
	}
}
