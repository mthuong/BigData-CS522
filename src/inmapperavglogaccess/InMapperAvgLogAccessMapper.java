package inmapperavglogaccess;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import avglogaccess.LogParser;
import avglogaccess.Record;

public class InMapperAvgLogAccessMapper extends
		Mapper<LongWritable, Text, Text, Pair> {

	private Map<Text, Pair> map = null;

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Pair>.Context context)
			throws IOException, InterruptedException {
		map = new HashMap<>();
	}

	@Override
	protected void map(LongWritable key, Text record,
			Mapper<LongWritable, Text, Text, Pair>.Context context)
			throws IOException, InterruptedException {
		Record r = LogParser.parseRecord(record);
		if (r != null) {
			Long packageNumber = Long.parseLong(r.getPackages());
			Text ipAdress = new Text(r.getIpAddress());
			if (map.containsKey(ipAdress)) {
				Pair pair = map.get(ipAdress);

				Long value = pair.getPackages();
				value += packageNumber;

				Integer count = pair.getCount();
				count += 1;

				pair.setPackages(value);
				pair.setCount(count);

//				map.put(ipAdress, pair);
			} else {
				Pair pair = new Pair(packageNumber, 1);

				map.put(ipAdress, pair);
			}

			System.out.println("=================");
		}
	}

	@Override
	protected void cleanup(
			Mapper<LongWritable, Text, Text, Pair>.Context context)
			throws IOException, InterruptedException {
		for (Text key : map.keySet()) {
			Pair pair = map.get(key);
			context.write(key, pair);
		}
	}
}
