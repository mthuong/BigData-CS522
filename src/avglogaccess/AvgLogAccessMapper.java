package avglogaccess;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgLogAccessMapper extends
		Mapper<LongWritable, Text, Text, LongWritable> {

	@Override
	protected void map(LongWritable key, Text record,
			Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		Record r = LogParser.parseRecord(record);
		if (r != null) {
			context.write(new Text(r.getIpAddress()), new LongWritable(Long.parseLong(r.getPackages())));
			System.out.println("=================");
		}
	}
}
