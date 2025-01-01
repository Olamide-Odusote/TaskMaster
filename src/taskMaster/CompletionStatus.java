package taskMaster;
import java.util.ArrayList;

public class CompletionStatus implements Comparable<CompletionStatus>{
	private int progress;
	private ArrayList<Integer> values = new ArrayList<Integer>();
	
	public CompletionStatus(int progress) {
		values.add(0);
		values.add(1);
		values.add(2);
		if (values.contains(progress)) {
			this.progress = progress;
		}
		else {
			throw new IllegalArgumentException("progress must be 0, 1 or 2");
		}
	}
	
	private String getProgress() {
		if (progress == 0) {
			return "Not Started";
		}
		if (progress == 1) {
			return "In Progress";
		}
		if (progress == 2) {
			return "Complete";
		}
		throw new IllegalArgumentException("progress must be 0, 1 or 2");
	}
	
	public String toString() {
		return getProgress();
	}
	
	public int compareTo(CompletionStatus other) {
		return other.progress - this.progress;
	}
}
