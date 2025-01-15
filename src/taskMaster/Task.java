
package taskMaster;
import java.util.ArrayList;

public class Task implements Comparable<Task> {
	protected String name;
	protected CompletionStatus progress;
	protected boolean activeStatus;
	private ArrayList<SubTask> subTasks;
	
	public Task(String name) {
		this.name = name;
		progress = new CompletionStatus(0);
		activeStatus = false;
		subTasks = new ArrayList<SubTask>();
	}
	
	public String name() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public CompletionStatus progress() {
		return progress;
	}
	
	public void setProgress(int newProg) {
		progress = new CompletionStatus(newProg);
	}
	
	public boolean active() {
		return activeStatus;
	}
	
	public void setActiveStatus(boolean newActive) {
		activeStatus = newActive;
	}
	
	public ArrayList<SubTask> getSubTasks(){
		return subTasks;
	}
	
	public void addSubTask(String name) {
		SubTask subTask = new SubTask(name);
		subTask.setActiveStatus(activeStatus);
		subTasks.add(subTask);
	}
	
	public void removeSubTask(String name) {
		Task taskToRemove = null;
		for (Task subTask : subTasks) {
			if (subTask.name().equals(name)) {
				taskToRemove = subTask;
			}
		}
		if (taskToRemove != null) {
			subTasks.remove(taskToRemove);
		}
	}
	
	public String toString() {
		return name;
	}
	
	public int compareTo(Task other) {
		return progress.compareTo(other.progress());
	}
}