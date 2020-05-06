package com.proit.todoapp.domains;

import java.util.List;

public class ToDoItemResponse {
	
	private List<ToDoItem> ToDoItems;

	public ToDoItemResponse() {
		super();
	}

	public ToDoItemResponse(List<ToDoItem> toDoItems) {
		super();
		ToDoItems = toDoItems;
	}

	public List<ToDoItem> getToDoItems() {
		return ToDoItems;
	}

	public void setToDoItems(List<ToDoItem> toDoItems) {
		ToDoItems = toDoItems;
	}
}
