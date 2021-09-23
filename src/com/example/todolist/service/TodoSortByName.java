package com.example.todolist.service;

import java.util.Comparator;
import com.example.todolist.dao.TodoItem;

public class TodoSortByName implements Comparator<TodoItem> {
	@Override
	public int compare(TodoItem o1, TodoItem o2) {
		return o1.getTitle().compareTo(o2.getTitle());
	}
}
