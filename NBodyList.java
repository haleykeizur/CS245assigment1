import java.io.*;
import java.util.*;

//This is a class for reading file input and making either an arraylist or linkedlist of celestial bodies

public class NBodyList{

	private List<NBodyNode> nBodyList;
	private double scale;
	private char type;
	private int size = 0;

	public NBodyList(String f){
		ArrayList<String> nBodyArgs = readFile(f);
		this.scale = Double.parseDouble(nBodyArgs.get(1));
		if(nBodyArgs.get(0).equals("ArrayList")){
			this.nBodyList = new ArrayList<NBodyNode>();
			this.type = 'a';
			parseArgs(nBodyArgs);
		}
		else if(nBodyArgs.get(0).equals("LinkedList")){
			this.nBodyList = new LinkedList<NBodyNode>();
			this.type = 'l';
			parseArgs(nBodyArgs);
		}

		this.scale = Double.parseDouble(nBodyArgs.get(1));

	}

	public double scale(){
		return this.scale;
	}

	public NBodyNode get(int i){
		return nBodyList.get(i);
	}

	public int size(){
		return this.size;
	}

	private char getType(){
		return type;
	}

	private void parseArgs(ArrayList<String> list){
		int i = 2;
		
		while(i < list.size()){
			nBodyList.add(new NBodyNode(list.get(i), list.get(i+1), list.get(i+2), list.get(i+3)
				, list.get(i+4), list.get(i+5), list.get(i+6)));
			i += 7;
			this.size ++;
		}
	}

	//reads the input file, which has the various N Bodies and their characteristics
	private ArrayList<String> readFile(String f){
		ArrayList<String> nBodyArgs = new ArrayList<String>();
		try{
			File file = new File(f);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				Scanner scanner2 = new Scanner(scanner.nextLine());
				scanner2.useDelimiter(",");
				while(scanner2.hasNext()){
					nBodyArgs.add(scanner2.next());
				}
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("Sorry, the file was not found");
			e.printStackTrace();
		}
		return nBodyArgs;
	}

	//calls NBodyNode and prints the information from the file
	public String toString(){
		String output = "N Body List: \n";

		for(int i = 0; i < nBodyList.size(); i ++){
			output = output + "N Body: " + i + nBodyList.get(i).toString() + "\n";
		}

		return output;
	}

	public static void main(String [] args){
		NBodyList n = new NBodyList("nbody_input.txt");
		System.out.println(n.toString());
	}
}