import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class WordPlay {
	
	/*
	 * This is an incomplete solution. Please allow for a re-submission in order for us to solve this assignment. 
	 */
	
	public static void main(String[] args) {
		
		String wordList = "words";
		int permLength = 4;
		
		createPermutations(wordList, permLength);
		HashMap<String, LinkedList<String>> graph = graph(wordList);
		
		while (StdIn.hasNextLine()) {
			String start = StdIn.readString();
			String end = StdIn.readString();
			BFS(start, end, graph);
			StdIn.readLine();
		}
		
	}
	
	public static void BFS(String start, String end, HashMap<String, LinkedList<String>> graph) {
		
		Queue<String> q = new LinkedList<String>();
		HashMap<String, Boolean> visited = new HashMap<>();
		HashMap<String, String> path = new HashMap<>();
		
		q.add(start);
		String current = start;
		
		while (!q.isEmpty()) {
			current = q.poll();
			LinkedList<String> toBeExpanded = graph.get(current);
			LinkedList<String> afterDeletion = new LinkedList<>();
			for (String s : toBeExpanded) {
				if (!q.contains(s) && visited.get(s) == null) {
					afterDeletion.add(s); // prepare to add to queue
//					q.add(s); // add a node to be expanded if its not already in the queue OR its not been visited
				}
			}
			for (String s : afterDeletion) {
				path.put(s, current);
			}
			q.addAll(afterDeletion); // add to queue
			
			// check if current is equal to end string:
			if (current.equals(end)) {
//				System.out.println("Found a path between " + start + " and " + end);
				
				// time to back-trace the path
				String currentNode = end;
				Stack<String> stack = new Stack<>();
				while (!currentNode.equals(start)) {
					stack.push(currentNode);
					currentNode = path.get(currentNode);
				}
//				stack.add(start);
//				System.out.println(stack.toString());
				System.out.println(stack.size());
				return;
			}
			visited.put(current, true); 
		}
//		System.out.println("The queue is now empty. All resources exhausted. No match!");
		System.out.println("-1");
	}
	
	public static HashMap<String, LinkedList<String>> graph(String filename) {
		
		HashMap<String, LinkedList<String>> graph = new HashMap<String, LinkedList<String>>();
		File file = new File(filename);
		try {
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String word = sc.nextLine();
				LinkedList<String>  words = PermutationsK.map.get(word.substring(1));
				LinkedList<String> clone = new LinkedList<>();
				for (String s : words) {
					clone.add(s);
				}
				
				if (clone.contains(word)) { // make sure the word does not point to itself
					clone.remove(word);
				}
				graph.put(word, clone);
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return graph;
	}
	
	public static void createPermutations(String filename, int k) {

		File file = new File(filename);
		try {
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				PermutationsK.choose(line, k);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
	}
}
