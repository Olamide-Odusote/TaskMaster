package taskMaster;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	static TaskList tasks;
	static Scanner sc;
	static String commands;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		tasks = new TaskList(sc);
		System.out.println("Start adding some tasks to create a list. Type 'stop' to stop adding tasks.");
		
		tasks.addTasks();
		tasks.printTasks();
		System.out.println("\nType 'help' to view all commands");
		
		try {
			File source = new File("commands.txt");
			Scanner reader = new Scanner(source);
			commands = "";
			while (reader.hasNextLine()) {
				commands += reader.nextLine() + "\n";
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("commands.txt not found");
		}
		
		
		while(true) {
			checkInput();
			tasks.sort();
		}
	}
	
	static void checkInput() {
		String input = sc.nextLine();
		if (input.equals("help")){

			System.out.println(commands);
		}
		else if (input.equals("add")){
			tasks.addTask();
		}
		else if (input.equals("addm")){
			tasks.addTasks();
		}
		else if (input.equals("remove")){
			tasks.removeTask();
		}
		else if (input.equals("edit")) {
			tasks.editTask();
		}
		else if (input.equals("active")) {
			tasks.changeActiveStatus();
		}
		else if (input.equals("update")) {
			tasks.changeProgress();
		}
		else if (input.equals("view")){
			tasks.printTasks();
		}
		else if (input.equals("viewa")) {
			tasks.printActiveTasks();
		}
		else {
		System.out.println("Command not found");
		}
	}
}
