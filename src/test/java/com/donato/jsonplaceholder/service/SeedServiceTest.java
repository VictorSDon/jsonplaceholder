package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.album.AlbumObjectMother;
import com.donato.jsonplaceholder.model.album.AlbumPlaceholder;
import com.donato.jsonplaceholder.model.comment.CommentObjectMother;
import com.donato.jsonplaceholder.model.comment.CommentPlaceholder;
import com.donato.jsonplaceholder.model.photo.PhotoObjectMother;
import com.donato.jsonplaceholder.model.photo.PhotoPlaceholder;
import com.donato.jsonplaceholder.model.post.PostObjectMother;
import com.donato.jsonplaceholder.model.post.PostPlaceholder;
import com.donato.jsonplaceholder.model.todo.TodoObjectMother;
import com.donato.jsonplaceholder.model.todo.TodoPlaceholder;
import com.donato.jsonplaceholder.model.user.UserObjectMother;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.model.user.placeholder.UserPlaceholder;
import com.donato.jsonplaceholder.repository.client.JsonPlaceholderClient;
import com.donato.jsonplaceholder.repository.jpa.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SeedServiceTest {
    @InjectMocks
    private SeedService target;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JsonPlaceholderClient client;


    private UserPlaceholder userPlaceholder;
    private TodoPlaceholder todoPlaceholder;
    private PostPlaceholder postPlaceholder;
    private PhotoPlaceholder photoPlaceholder;
    private CommentPlaceholder commentPlaceholder;
    private AlbumPlaceholder albumPlaceholder;
    private UserDomain userToSave;

    @BeforeEach
    public void setup(){
        userPlaceholder = UserObjectMother.userPlaceholder();
        todoPlaceholder = TodoObjectMother.todoPlaceholder();
        photoPlaceholder = PhotoObjectMother.photoPlaceholder();
        postPlaceholder = PostObjectMother.postPlaceholder();
        commentPlaceholder = CommentObjectMother.commentPlaceholder();
        albumPlaceholder = AlbumObjectMother.albumPlaceholder();
        userToSave = UserObjectMother.CurrentUser();

    }


    @Test
    public void shouldFullSeed(){
        when(client.getUsers()).thenReturn(List.of(userPlaceholder));
        when(client.getComments()).thenReturn(List.of(commentPlaceholder));
        when(client.getAlbums()).thenReturn(List.of(albumPlaceholder));
        when(client.getPosts()).thenReturn(List.of(postPlaceholder));
        when(client.getTodos()).thenReturn(List.of(todoPlaceholder));
        when(client.getPhotos()).thenReturn(List.of(photoPlaceholder));

        target.fullSeed();
        verify(client).getUsers();
        verify(client).getComments();
        verify(client).getAlbums();
        verify(client).getPosts();
        verify(client).getTodos();
        verify(client).getPhotos();
        verify(userRepository).deleteAll();
        verify(userRepository).saveAll(List.of(userToSave));
    }
}
