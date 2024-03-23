package com.donato.jsonplaceholder.model.user.response;

import com.donato.jsonplaceholder.model.album.AlbumResponse;
import com.donato.jsonplaceholder.model.comment.CommentResponse;
import com.donato.jsonplaceholder.model.photo.PhotoResponse;
import com.donato.jsonplaceholder.model.post.PostResponse;
import com.donato.jsonplaceholder.model.todo.TodoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private UserAddressResponse address;
    private String phone;
    private String website;
    private UserCompanyResponse company;
    private List<TodoResponse> todos;
    private List<PostResponse> posts;
    private List<AlbumResponse> albums;
}
