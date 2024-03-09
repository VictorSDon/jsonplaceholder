package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.post.PostDomain;
import com.donato.jsonplaceholder.model.todo.TodoDomain;
import com.donato.jsonplaceholder.model.todo.TodoRequest;
import com.donato.jsonplaceholder.model.todo.TodoResponse;
import com.donato.jsonplaceholder.repository.jpa.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    public List<TodoResponse> listAll(){
        List<TodoDomain> domainList = repository.findAll();
        List<TodoResponse> response = domainList.stream()
                .map(this::parseDomainToResponse)
                .toList();
        return response;
    }

    public TodoResponse getById(Long id){
        TodoDomain domain = repository.findById(id).orElseThrow();
        return parseDomainToResponse(domain);
    }

    public TodoResponse create(TodoRequest request){
        TodoDomain domain = TodoDomain.builder()
                .title(request.getTitle())
                .completed(request.getCompleted()).build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public void deleteById(Long id){repository.deleteById(id);}

    public TodoResponse update(TodoRequest request, Long id){
        TodoDomain domain = TodoDomain.builder().id(id)
                .title(request.getTitle())
                .completed(request.getCompleted()).build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public TodoResponse parseDomainToResponse(TodoDomain domain){
        return TodoResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .completed(domain.getCompleted()).build();
    }
}
