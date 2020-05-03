package com.proit.todoapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.proit.todoapp.domains.ToDoItem;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long>{

}
