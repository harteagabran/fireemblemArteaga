import java.util.List;
import java.util.Scanner;

import controller.UnitHelper;
import model.Unit;

/**
 * @author Halmar Arteaga - harteagabran
 * CIS175 - Spring 2024
 * Jan 28, 2024
 */
public class Start {
	static Scanner in = new Scanner(System.in);
	static UnitHelper uh = new UnitHelper();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();
	}
	
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to my Fire Emblem army maker ---");
		while (goAgain) {
			System.out.println("* Select an item:");
			System.out.println("* 1 -- Add a Unit");
			System.out.println("* 2 -- Edit a Unit");
			System.out.println("* 3 -- Delete a Unit");
			System.out.println("* 4 -- View the Unit List");
			System.out.println("* 5 -- Exit");
			System.out.print("* Your selection: ");
			int selection = in.nextInt();
			in.nextLine();
			
			if (selection == 1) {
				addHero();
			} else if (selection == 2) {
				editHero();
			} else if (selection == 3) {
				deleteHero();
			} else if (selection == 4) {
				viewTheList();
			} else {
				uh.cleanUp();
				System.out.println(" Goodbye! ");
				goAgain = false;
			}
		}
	}

	/**
	 * 
	 */
	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<Unit> allUnits = uh.showAllUnits();
		
		for(Unit unit : allUnits) {
			System.out.println(unit.returnInfo());
		}
	}

	/**
	 * 
	 */
	private static void deleteHero() {
		// TODO Auto-generated method stub
		System.out.print("Enter the name to delete: ");
		String name = in.nextLine();
		System.out.print("Enter the class to delete: ");
		String type = in.nextLine();
		
		Unit toDelete = new Unit(name, type);
		
		uh.deleteUnit(toDelete);
	}

	/**
	 * 
	 */
	private static void editHero() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Name");
		System.out.println("2 : Search by Class");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Unit> foundUnits;
		
		//search by name
		if(searchBy == 1) {
			System.out.print("Enter Unit's Name: ");
			String unitName = in.nextLine();
			foundUnits = uh.searchForUnitByName(unitName);
		} else {
			System.out.print("Enter the class: ");
			String className = in.nextLine();
			foundUnits = uh.searchForUnitByClass(className);
		}
		
		///check if empty
		if (!foundUnits.isEmpty()) {
			System.out.println("Found Results.");
			for (Unit u : foundUnits) {
				System.out.println(u.getId() + " : " + u.getName() + " Class: " + u.getType());
			}
			
			//get an id
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();
			
			Unit toEdit = uh.searchForUnitById(idToEdit);
			
			System.out.println("Retrieved " + toEdit.getName());
			
			//update info
			System.out.println("1 : Update Name");
			System.out.println("2 : Update Class");
			int update = in.nextInt();
			in.nextLine();
			
			if (update == 1) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} else if (update == 2) {
				System.out.print("New Class: ");
				String newClass = in.nextLine();
				toEdit.setType(newClass);
			}
			uh.updateUnit(toEdit);
		} else {
			System.out.println("--- No Results Found");
		}
	}

	/**
	 * 
	 */
	private static void addHero() {
		// TODO Auto-generated method stub
		System.out.print("Enter a name: ");
		String name = in.nextLine();
		System.out.print("Enter a class: ");
		String type = in.nextLine();
		
		Unit toAdd = new Unit(name, type);
		uh.insertUnit(toAdd);
	}
}
