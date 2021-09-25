package com.example.todolist.service;

import java.util.*;
import com.example.todolist.dao.TodoItem;
import com.example.todolist.dao.TodoList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String category, title, desc, due_date;
		Scanner s= new Scanner(System.in);
		
		System.out.print("[Add Item]\n" + "Enter category > ");
		category = s.next();
		s.nextLine();
		
		System.out.print("Enter title > ");
		title = s.next();
		if(list.isDuplicate(title)) {
			System.out.println("This item already exists!");
			return;
		}
		s.nextLine();
		
		System.out.print("Enter description > ");
		desc = s.nextLine().trim();
		
		System.out.print("Enter due date (yyyy/mm/dd) > ");
		due_date = s.next();
		s.nextLine();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("New item has been added!");
	}
	
	public static void deleteItem(TodoList list) { 
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("[Delete Item]\n" + "Enter number of item to be removed > ");
		int item_num = s.nextInt();
		
		int counter=0;
		String answer;
		for(TodoItem item : list.getList()) {
			if(++counter == item_num) {
				System.out.println(counter + ". " + item.toString());
				System.out.print("Do you wish to delete the item shown above? (y/n) > ");
				answer = s.next().trim();
				
				if(answer.equals("y")) {
					list.deleteItem(item);
					System.out.println("The item has been deleted!");	
					return;
				}
				else return;
			}
		}
		System.out.println("There is no item number " + item_num + "!");
	}
	
	public static void updateItem(TodoList list) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("[Update Item]\n" + "Enter number of item to be updated > ");
		int item_num = s.nextInt();
		
		int counter=0;
		for(TodoItem item : list.getList()) {
			if(++counter == item_num) {
				System.out.println(counter + ". " + item.toString());
				
				System.out.print("Enter new category > ");
				String new_category = s.next().trim();
				s.nextLine();
				item.setCategory(new_category);
				
				System.out.print("Enter new title > ");
				String new_title = s.next().trim();
				if(list.isDuplicate(new_title)) {
					System.out.println("This item already exists!");
					return;
				}
				s.nextLine();
				item.setTitle(new_title);
				
				System.out.print("Enter new description > ");
				String new_description = s.nextLine().trim();
				item.setDesc(new_description);
				
				System.out.print("Enter new due_date (yyyy/mm/dd) > ");
				String new_due_date = s.next().trim();
				item.setDue_date(new_due_date);
				
				SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
				item.setCurrent_date(f.format(new Date()));
				
				return;
			}
		}
		System.out.println("There is no item number " + item_num + "!");
	}
	
	public static void listAll(TodoList list) {
		int counter=0;
		for (TodoItem item : list.getList()) {
			counter++;
		}
		System.out.println("[Total of " + counter + " items in list]");
		counter=0;
		for (TodoItem item : list.getList()) {
			System.out.print(++counter + ". ");
			System.out.println(item.toString());
		}
	}


	public static void findKeyword(TodoList list) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("[Find Item]\n" + "Enter the keyword to be searched > ");
		String keyword = s.next().trim();
		
		int match_counter=0;
		int item_counter=0;
		for(TodoItem item : list.getList()) {
			item_counter++;
			if(item.toString().contains(keyword)) {
				match_counter++;
				System.out.println(item_counter + ". " + item.toString());
			}
		}
		System.out.println("Number of matching items: " + match_counter);
	}
	


	public static void findKeywordCate(TodoList list) {

		Scanner s = new Scanner(System.in);
		
		System.out.print("[Find Category]\n" + "Enter the category to be searched > ");
		String keyword_cate = s.next().trim();
		
		int match_counter=0;
		int item_counter=0;
		for(TodoItem item : list.getList()) {
			item_counter++;
			if(item.getCategory().equals(keyword_cate)) {
				match_counter++;
				System.out.println(item_counter + ". " + item.toString());
			}
		}
		System.out.println("Number of matching items: " + match_counter);
	}
	
	
	// File I/O >> save and load in "todolist.txt"
	public static void loadList(TodoList list, String filename) {	// use BufferedReader, FileReader, StringTokenizer
		try {
			BufferedReader r = new BufferedReader(new FileReader(filename));
			String oneline;
			int counter=0;
			while((oneline = r.readLine()) != null) {
				counter++;
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String date = st.nextToken();
				TodoItem t = new TodoItem(category, title, desc, due_date);
				t.setCurrent_date(date);
				list.addItem(t);
								
			}
			System.out.println("Number of loaded items: " + counter);
			r.close();
		} catch (FileNotFoundException e) {
			System.out.println("There is no file >> " + filename);
		} catch (IOException e) {
			System.out.println("There is no file >> " + filename);
		}
	}
	
	public static void saveList(TodoList list, String filename) {	// use FileWriter 
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : list.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("All items have been saved!");
		} catch(FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}
