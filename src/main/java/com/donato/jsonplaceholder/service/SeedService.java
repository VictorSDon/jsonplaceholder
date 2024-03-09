package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.comment.CommentPlaceholder;
import com.donato.jsonplaceholder.model.todo.TodoDomain;
import com.donato.jsonplaceholder.model.todo.TodoPlaceholder;
import com.donato.jsonplaceholder.repository.client.JsonPlaceholderClient;
import com.donato.jsonplaceholder.repository.jpa.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeedService {
    private final JsonPlaceholderClient client;
    private final TodoRepository todoRepository;
    public void fullSeed(){
        List<TodoPlaceholder> todosToCopy = client.getTodos();
        List<TodoDomain> todosToSave = todosToCopy.stream().map(placeholder -> TodoDomain.builder()
                .title(placeholder.getTitle())
                .completed(placeholder.getCompleted())
                .build()).toList();
        todoRepository.deleteAll();
        todoRepository.saveAll(todosToSave);
        List<CommentPlaceholder> commentToCopy = client.getComments();
//        clone all the information from the website to my own database
//        original info
//        new prototype
//        once one becomes a clone, save the info on the database
    }
}
