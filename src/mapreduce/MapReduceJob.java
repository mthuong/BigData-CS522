package mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

public class MapReduceJob<M extends Mapper<?, ?, ?, ?>, R extends Reducer<?, ?, ?, ?>, K, V> extends
		Configured implements Tool {
	private final Job job;

	public MapReduceJob(String jobName, Class<M> mapper, Class<R> reducer,
			Class<K> key, Class<V> value) throws IOException {
		Configuration config = new Configuration();
		job = Job.getInstance(config, jobName);
		job.setJarByClass(this.getClass());

		job.setMapperClass(mapper);
		job.setReducerClass(reducer);

		job.setOutputKeyClass(key);
		job.setOutputValueClass(value);

//		job.setInputFormatClass(TextInputFormat.class);
//		job.setOutputFormatClass(TextOutputFormat.class);

		// Set custom partitioner
		// job.setPartitionerClass(WordCountPartition.class);
	}

	@Override
	public int run(String[] args) throws Exception {
		String inputPath = args[1];
		String outputPath = args[2];
		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		Integer numberOfReducer = 1;
		if (args.length > 4) {
			numberOfReducer = Integer.parseInt(args[3]);
		}
		job.setNumReduceTasks(numberOfReducer);

		Configuration conf = getConf();

		Path output = new Path(outputPath);
		FileSystem hdfs = FileSystem.get(conf);

		// delete existing directory
		if (hdfs.exists(output)) {
			hdfs.delete(output, true);
		}

		return job.waitForCompletion(true) ? 0 : 1;
	}

}
