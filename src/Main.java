import inmapperwordcount.InMapperWordCountJob;
import mapreduce.MapReduceJob;

import org.apache.hadoop.util.ToolRunner;

import wordcount.WordCountJob;

public class Main {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length < 3) {
			System.out
					.println("Please enter application arguments: <part1a,part1b,part1c,part1d> <input> <output> and optional reducers");
			return;
		}
		
		MapReduceJob<?, ?, ?, ?> job = null;
		
		switch (args[0]) {
		case "part1a":
			job = new WordCountJob("Word Count Job");
			break;
			
		case "part1b":
			job = new InMapperWordCountJob("In Mapper Word Count Job");
			break;

		default:
			break;
		}

		int res = ToolRunner.run(job, args);

		System.exit(res);
	}
}
