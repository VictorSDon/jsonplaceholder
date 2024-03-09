package com.donato.jsonplaceholder.repository.client;

import com.donato.jsonplaceholder.model.album.AlbumPlaceholder;
import com.donato.jsonplaceholder.model.comment.CommentPlaceholder;
import com.donato.jsonplaceholder.model.photo.PhotoPlaceholder;
import com.donato.jsonplaceholder.model.post.PostPlaceholder;
import com.donato.jsonplaceholder.model.todo.TodoPlaceholder;
import com.donato.jsonplaceholder.model.user.placeholder.UserPlaceholder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://jsonplaceholder.typicode.com/", name = "Jsonplaceholder")
public interface JsonPlaceholderClient {
    @GetMapping("/posts")
    List<PostPlaceholder> getPosts();
    @GetMapping("/comments")
    List<CommentPlaceholder> getComments();
    @GetMapping("/albums")
    List<AlbumPlaceholder> getAlbums();
    @GetMapping("/photos")
    List<PhotoPlaceholder> getPhotos();
    @GetMapping("/todos")
    List<TodoPlaceholder> getTodos();
    @GetMapping("/users")
    List<UserPlaceholder> getUsers();
}
