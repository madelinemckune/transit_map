/*
 * Name: Madeline McKune
 * Date: 4/21/2019
 * 
 */

package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class proj_4 {
	public int cities;
	public int edges; 
	//need this so that I can loop through and create my adjacencies
	public ArrayList<Node> allNodes;
	//map: node by connections
	public Map <Node, ArrayList<Node>> adjacencyList;
	//where we start DFS from 
	Node start;
	//how we check that we have found a path
	Node end;
	
	//CONSTRUCTOR
	public proj_4() {
		adjacencyList = new HashMap<>();
		allNodes = new ArrayList<>();
	}
	
	
	//create both nodes
	//I want to make a A-> B node and a B-> A node for every pair of cities
	public void createNodes(int count, String start, String end, String type, String color) {
		Node n1 = new Node (start, end, type,color);
		addNode (n1);
		Node n2 = new Node (end, start, type,color);
		addNode (n2);
		
		//if this is the first node
		if (count == 0) {
			this.start = n1;
		}
		//if this is the last node 
		if (count == edges-1) {
			this.end = n1;
		}
		
	}
	
	
	//add a node to the graph but do not connect it
	public void addNode(Node n) {
		//allNodes is used later to make my adjacencies
		allNodes.add(n);
		//make a spot for it in the graph
		adjacencyList.put(n, new ArrayList<>());
	}
	
	
	// add a edge
	public void addEdge(Node start, Node end) {
		//if they are going in the right direction
		if (start.getEndCity().equals(end.getStartCity())) {
			//if they are the right color OR the right type
			if (start.getColor().equals(end.getColor()) || start.getType().equals(end.getType())) {
				//add it to the map
				adjacencyList.get(start).add(end);
			}
		}
    }
    

	//read in from the given file
	public void readFile(String filename) {
		//line count is passed into create node so that I can find the first and last nodes
		int lineCount = 0;
		File file = new File (filename);
		Scanner sc;
		//check that the file exists
		try {
			sc = new Scanner (file);
			cities = sc.nextInt();
			edges = sc.nextInt();
			sc.nextLine();
			while (sc.hasNextLine()) {
				String readLine = sc.nextLine();
				createNodes(lineCount, Character.toString(readLine.charAt(0)), Character.toString(readLine.charAt(2)), Character.toString(readLine.charAt(4)),Character.toString(readLine.charAt(6)));
				lineCount++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	
	// print out all the nodes
	public void printNodes() {
		int sum = 0;
		for (Node n : adjacencyList.keySet()) {
			ArrayList<Node> temp = adjacencyList.get(n);
			System.out.print(n.toString() + " Adjacencies:" + temp.toString());
			sum += temp.size();
			System.out.println();
		}
	}
	
	
	//initialize the graph from the line info we read in
	public void createGraph(String fileName) {
		readFile(fileName);
		for (int i = 0; i < allNodes.size(); i++) {
			for (int j = 1; j < allNodes.size(); j++) {
				if (!allNodes.get(i).getStartCity().equals(allNodes.get(j).getEndCity())) {
					addEdge(allNodes.get(i),allNodes.get(j));
				}
			}
		}
		//printNodes();
	}
	
	//DFS but allow for a node to be revisited
	public void DFS () {
		//stack to hold the nodes we need to explore
		Stack <Node> stack = new Stack <>();
		//push are starting node to the stack bc we want to start DFS from here
		stack.push(start);
		//create a temporary node that will be updated every loop
		Node curr = new Node();
		//create a temporary arraylist to hold the current nodes connections
		ArrayList <Node> edges;
		
		//while there are still nodes to explore
		while (stack.empty() == false) {
			//pop a node from the stack
			curr = stack.peek();
			//pop the current off the stack
			stack.pop();
			//mark that it is no longer on the stack
			curr.onStack = false;
			
			//check if we are at the end
			if (curr.getEndCity().equals(end.getEndCity())) {
				//break if we have found our destination
				break;
			}
			
			//get all of its adjacencies
			edges = adjacencyList.get(curr);
		
			int index = 0;
			//add all of its adjacencies to the stack
			while (edges.size() > index) {
				//don't add edges that are already on the stack
				if (edges.get(index).onStack == false) {
					//add each connection to the stack
					stack.push(edges.get(index));
					//set that it is on the stack
					edges.get(index).onStack = true;
					//set the parent pointer to the node we are currently exploring
					edges.get(index).parent = curr;
					curr.onStack = true;
				}
				index++;
			}
		}
	}
	
	
	
	
	public static void main (String args []) {
		String fileName = "roots.txt";
		proj_4 test = new proj_4();
		test.createGraph(fileName);
		test.DFS();
		
		Node temp = test.end;
		ArrayList <Node> path = new ArrayList<>();
		while (temp.parent != null) {
			//System.out.println(temp.parent);
			path.add(temp.parent);
			temp = temp.parent;
		}
		
		System.out.println("Printing the Path:");
		
		//add the end to the front of our path
		path.add(0,test.end);
		
		/*
		for (int i = path.size()-1; i >= 0; i--) {
			System.out.println(path.get(i).getStartCity() + " -> " + path.get(i).getEndCity());
		}
		*/
	
		//print in given format
		for (int i = path.size()-1; i >= 0; i--) {
			System.out.print(path.get(i).getStartCity() + " ");
			//if we are at the last, also print the end city
			if(i == 0) {
				System.out.print(path.get(i).getEndCity() + " ");
			}
			
		}
		//print out the length of the path
		System.out.println("Path length: " + (path.size()+1));
				
	}
}
