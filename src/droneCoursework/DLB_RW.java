package Drone_Sim;

import java.io.*;

public class ReadWrite {
	//Declaring and assigning variables
	String directory = System.getProperty("user.home");
	String fileName = "arenaInfo.txt"; // change this to change filename:: or make multiple files - (make this inputted)
	String absolutePath = directory + File.separator + fileName;

	//Method to write to a file
	public void Write(String toWrite) {
		//Opens a file writer at the given path
		try (FileWriter fileWriter = new FileWriter(absolutePath)) {
			//Write to the file with the given string
			fileWriter.write(toWrite);
		} catch (IOException e) {
			// exception handling
		}
	}

	//Method to read from a file
	public String Read() {
		String toRead = "";
		//Opens a file reader at a given path
		try (FileReader fileReader = new FileReader(absolutePath)) {
			int ch = fileReader.read();
			//While the variable is not -1(end of stream)
			while (ch != -1) {
				//Appends the character to the string
				toRead += (char) ch;
				//Reads the next character
				ch = fileReader.read();
			}
		} catch (FileNotFoundException e1) {
			// exception handling
			System.out.println("Error, file not found");
		} catch (IOException e11) {
			// exception handling
		}
		return toRead;
	}

	//Main method (For testing)
	public static void main(String[] args) {
		//Creating a new ReadWrite object
		ReadWrite rw = new ReadWrite();
		//Writing "Test" to a file
		rw.Write("Test \nTest 2");
		//Read from a file and output the result
		System.out.println(rw.Read());
	}
}


// ADD THIS TO DRONE -ARENA <
//Loads a drone with the given parameters
	public void loadDrone(int temp1,int temp2, Direction dir) {
			dList.add(new Drone(temp1, temp2, dir));
			numDrones++;
			map[temp1][temp2] = 1;
		}
    >
    
 // ADD THIS TO DRONE INTERFACE< 
 private Scanner s;
 private ReadWrite FileRW;
 //Creating a scanner and arena object
		s = new Scanner(System.in);
    fileRW = new ReadWrite();

//Method to save arena info
	void Save() {
		//Adds the dimensions of the arena
		String toSave = Integer.toString(myArena.getX()) + "," + Integer.toString(myArena.getY()) + "\n";
		//Appends the info of the drones
		toSave += myArena.getSaveInfo();
		//Calls the method to write to a file
		FileRW.Write(toSave);
	}
	
	//Method to load the arena and drones
	void Load() {
		//Declaring variables
		Direction temp = Direction.North;
		String toSplit;
		//Reads the text file
		toSplit = FileRW.Read();
		//Splits the file by lines and stores it in an array
		String ReadLines[] = toSplit.split("\n");
		
		//Splits the first line by the comma
		String Read[] = ReadLines[0].split(",");
		//Initialises a new arena
		myArena = new DroneArena(Integer.parseInt(Read[0]),Integer.parseInt(Read[1]));
		
		//For every line after the first
		for(int i = 1; i < ReadLines.length; i++) {
			//Split values by the comma
			String ReadL[] = ReadLines[i].split(",");
			//Select the direction value and assign it a direction type based on the string
			switch(ReadL[2]) {
			case "North":
				temp = Direction.North;
				break;
			
			case "East":
				temp = Direction.East;
				break;
				
			case "South":
				temp = Direction.South;
				break;
				
			case "West":
				temp = Direction.West;
				break;
			}
			
			//Load a new drone
			myArena.loadDrone(Integer.parseInt(ReadL[0]), Integer.parseInt(ReadL[1]), temp);
		}
	}
  >
