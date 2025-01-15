package taskMaster;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
	public ArrayList<Task> tasks = new ArrayList<Task>();
	//public ArrayList<Task> activeTasks = new ArrayList<Task>();
    private Scanner sc;
	
	public TaskList(Scanner sc) 
	{
		this.sc = sc;
	}
	
	public void printTasks() {
		System.out.println("All Tasks: ");
		for(Task task : tasks) {
			System.out.println(String.format("%s (%s)", task, task.progress()));
		}
	}
	
	public void printActiveTasks() {
		System.out.println("Active Tasks: ");
		
		/*
		for(Task task : activeTasks) {
			System.out.println(task);
			for (Task subTask : task.getSubTasks()) {
				System.out.println("  - " + subTask);
			}
		}
		*/
		
		for(Task task : tasks) {
			if (task.active()) {
				System.out.println(task);
				for (Task subTask : task.getSubTasks()) {
					System.out.println("  - " + subTask);
				}
			}
			
		}
	}
	
	public void addTasks() {
		boolean stop = false;
		while (!stop) {
			System.out.println("Add a task: ");
	        String input = sc.nextLine();
	        if (input.equals("stop")) {
	        	stop = true;
	        }
	        else {
	        	tasks.add(new Task(input));
	        }
		}
	}
	
	public void addTask() {
		System.out.println("Add a task: ");
		String input = sc.nextLine();
		tasks.add(new Task(input));
		System.out.println("Task added");
	}
	
	public void removeTask() {
		System.out.println("Enter then name of the task you want to remove: ");
		String input = sc.nextLine();
		Task taskToRemove = findTask(input);

		if (taskToRemove == null) {
			System.out.println("Could not find task");
		}
		else {
			tasks.remove(taskToRemove);
			if (taskToRemove.active()) {
				//activeTasks.remove(taskToRemove);
			}
			System.out.println("Task removed");
		}
	}
	
	public void editTask() {
		System.out.println("Enter the name of the task you want to edit: ");
		String input = sc.nextLine();
		Task taskToEdit = findTask(input);
		
		if (taskToEdit == null) {
			System.out.println("Could not find task");
		}
		else {
			System.out.println("Type 'name' to change the name of the task, 'add' to add a sub task, or 'remove' to remove a sub task.");
			String command = sc.nextLine();
			if (command.equals("name")) {
				System.out.println("Enter the new name for this task: ");
				String newName = sc.nextLine();
				for (Task task : tasks) {
					if (task.equals(taskToEdit)) {
						task.setName(newName);
					}
				}
			}
			else if (command.equals("add")) {
				System.out.println("Enter the name of the sub task: ");
				String subTaskName = sc.nextLine();
				taskToEdit.addSubTask(subTaskName);
			}
			else if (command.equals("remove")) {		
				System.out.println("Enter the name of the sub task to remove: ");
				String subTaskName = sc.nextLine();
				taskToEdit.removeSubTask(subTaskName);
			}
			else {
				System.out.println("Command not found");
				return;
			}
			System.out.println("Task edited");
		}
	}
	
	
	public void changeActiveStatus() {
		System.out.println("Enter the name of a task to add to/remove from the active list");
		String input = sc.nextLine();
		Task taskToChange = findTask(input);
		
		if (taskToChange == null) {
			System.out.println("Could not find task");
		}
		else {
			if (taskToChange.active()) {
				taskToChange.setActiveStatus(false);
				//activeTasks.remove(taskToChange);
				System.out.println("Task removed from active list");
			}
			else {
				taskToChange.setActiveStatus(true);
				//activeTasks.add(taskToChange);
				System.out.println("Task added to active list");
			}
			//checkActiveStatus(); 
		}
	}
	
	public void changeProgress() {
		System.out.println("Enter the name of the task to update the progress of: ");
		String input = sc.nextLine();
		Task taskToChange = findTask(input);
		
		if (taskToChange == null) {
			System.out.println("Could not find task");
		}
		else {
			System.out.println("Current Status: " + 
								taskToChange.progress() + 
								"\nSet the new progress for this task. Type '0' for 'Not Started', '1' for 'In Progress', or '2' for 'Complete'");
			String newProgress = sc.nextLine();
			try {
				taskToChange.setProgress(Integer.parseInt(newProgress));
				System.out.println("Task progress updated");
			}
			catch (Exception e) {
				System.out.println("Invalid input");
			}
		}
	}
	
	private Task findTask(String input) {
		Task output = null;
		for (Task task : tasks) {
			if (input.equals(task.name())) {
				output = task;
			}
		}
		return output;
	}
	
	public void sort() {
		Collections.sort(tasks);
		//Collections.sort(activeTasks);
	}
	
	/*
	private void checkActiveStatus() {
		for (Task task : tasks) {
			if (task.active() && !activeTasks.contains(task)) {
				activeTasks.add(task);
			}
			else if (!task.active() && activeTasks.contains(task)) {
				activeTasks.remove(task);
			}
		}
	}
	*/
}
