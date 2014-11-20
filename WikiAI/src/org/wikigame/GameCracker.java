package org.wikigame;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import org.wikipedia.Wiki;

public class GameCracker {
	static String startPage;
	static String endPage;
	static Wiki englishWiki;
	static LinkedList<Node> queue;
	static HashSet<String> seen;
	
	private static Node breadthFirstSearch(Node startNode) throws IOException {
		seen = new HashSet<String>();
		queue = new LinkedList<Node>();
		
		queue.add(startNode);
		Node best = new Node("","",Integer.MAX_VALUE);
		Node current;
		
		while(!queue.isEmpty()) {
			current = queue.poll();
			if(seen.contains(current.title)) continue;
			seen.add(current.title);
			if(current.title.equals(endPage)) {
				if(current.length < best.length) {
					best = current;
				}
			} else if(current.length+1 > best.length) {
				continue;
			} else {
				for(String s : englishWiki.getLinksOnPage(current.title)) {
					queue.add(new Node(s,current.path + "->" + s, current.length+1));
					
				}
			}
		}
		
		return best;
	}
	
	
				
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		englishWiki = new Wiki();
		
		System.out.print("Start:");
		startPage = in.nextLine();
		System.out.print("End:");
		endPage = in.nextLine();
		
		Node answer = breadthFirstSearch(new Node(startPage,startPage,0));
		System.out.println("Found shortest path");
		System.out.println("Length:" + answer.length);
		System.out.println("Path:" + answer.path);
	}

}
