package pairfrequencies;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class FrequencyPair implements Writable, WritableComparable<FrequencyPair> {
	private String left;
	private String right;

	public FrequencyPair() {
		super();
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public FrequencyPair(String left, String right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		left = in.readUTF();
		right = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(left);
		out.writeUTF(right);
	}

	public static FrequencyPair read(DataInput in) throws IOException {
		FrequencyPair w = new FrequencyPair();
		w.readFields(in);
		return w;
	}

	@Override
	public int compareTo(FrequencyPair o) {
		int k = this.left.compareTo(o.left);
		if (k != 0) {
			return k;
		}
		
		return this.right.compareTo(o.right);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FrequencyPair other = (FrequencyPair) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + left + ", " + right + "]";
	}
	
	public static String specialToken = "*";
}
