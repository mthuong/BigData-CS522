package inmapperavglogaccess;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class Pair implements Writable {
	private Long packages;
	private Integer count;
	
	public Pair() {
		super();
		this.packages = 0L;
		this.count = 0;
	}
	
	public Pair(Long packages, Integer count) {
		super();
		this.packages = packages;
		this.count = count;
	}

	public Long getPackages() {
		return packages;
	}

	public void setPackages(Long packages) {
		this.packages = packages;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		packages = in.readLong();
		count = in.readInt();
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(packages);
		out.writeInt(count);
	}
	
	public static Pair read(DataInput in) throws IOException {
		Pair w = new Pair();
        w.readFields(in);
        return w;
    }
}
