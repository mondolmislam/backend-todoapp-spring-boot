package com.proit.todoapp.services;

import java.util.List;

import com.proit.todoapp.domains.ToDoItem;

public interface ToDoItemService {
	public ToDoItem findById(Long id);
    public List<ToDoItem> getAll();
    public ToDoItem createOrUpdate(ToDoItem toDo);
    public ToDoItem delete(ToDoItem toDo);
}
