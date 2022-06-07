package inmapperavglogaccess;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InMapperAvgLogAccessReducer extends
		Reducer<Text, Pair, Text, LongWritable> {
	private LongWritable result = new LongWritable();
	
	@Override
	protected void reduce(
			Text ipAddress,
			Iterable<Pair> pairs,
			Reducer<Text, Pair, Text, LongWritable>.Context ctx)
			throws IOException, InterruptedException {
		Long sum = 0L;
		Integer count = 0;
		for (Pair pair : pairs) {
			Long packageNumbers = pair.getPackages();
			Integer countValue = pair.getCount();
			
			sum += packageNumbers;
			count += countValue;
		}
		result.set(sum/count);
		ctx.write(ipAddress, result);
	}


}
