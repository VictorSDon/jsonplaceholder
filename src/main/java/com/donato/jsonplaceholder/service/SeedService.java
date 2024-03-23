package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.album.AlbumDomain;
import com.donato.jsonplaceholder.model.album.AlbumPlaceholder;
import com.donato.jsonplaceholder.model.comment.CommentDomain;
import com.donato.jsonplaceholder.model.comment.CommentPlaceholder;
import com.donato.jsonplaceholder.model.photo.PhotoDomain;
import com.donato.jsonplaceholder.model.photo.PhotoPlaceholder;
import com.donato.jsonplaceholder.model.post.PostDomain;
import com.donato.jsonplaceholder.model.post.PostPlaceholder;
import com.donato.jsonplaceholder.model.todo.TodoDomain;
import com.donato.jsonplaceholder.model.todo.TodoPlaceholder;
import com.donato.jsonplaceholder.model.user.domain.UserAddressDomain;
import com.donato.jsonplaceholder.model.user.domain.UserAddressGeoDomain;
import com.donato.jsonplaceholder.model.user.domain.UserCompanyDomain;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.model.user.placeholder.UserAddressPlaceholder;
import com.donato.jsonplaceholder.model.user.placeholder.UserPlaceholder;
import com.donato.jsonplaceholder.model.user.response.UserAddressResponse;
import com.donato.jsonplaceholder.repository.client.JsonPlaceholderClient;
import com.donato.jsonplaceholder.repository.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeedService {
    private final JsonPlaceholderClient client;
//    private final TodoRepository todoRepository;
//    private final AlbumRepository albumRepository;
//    private final CommentRepository commentRepository;
//    private final PhotoRepository photoRepository;
//    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public void fullSeed(){
        List<UserPlaceholder> usersToCopy = client.getUsers();
        List<TodoPlaceholder> todosToCopy = client.getTodos();
        List<PostPlaceholder> postsToCopy = client.getPosts();
        List<PhotoPlaceholder> photosToCopy = client.getPhotos();
        List<AlbumPlaceholder> albumsToCopy = client.getAlbums();
        List<CommentPlaceholder> commentsToCopy = client.getComments();
        List<UserDomain> userToSave = usersToCopy.stream().map(currentUser -> {
            List<TodoDomain> parseTodos = todosToCopy.stream()
                    .filter(currentTodo -> currentTodo.getUserId().equals(currentUser.getId()))
                    .map(currentTodo -> TodoDomain.builder()
                            .title(currentTodo.getTitle())
                            .completed(currentTodo.getCompleted()).build()).toList();
            List<PostDomain> parsePosts = postsToCopy.stream()
                    .filter(currentPost -> currentPost.getUserId().equals(currentUser.getId()))
                    .map(currentPost ->{
                        List<CommentDomain> parseComments = commentsToCopy.stream()
                                .filter(currentComment -> currentComment.getPostId().equals(currentPost.getId()))
                                .map(currentComment -> CommentDomain.builder()
                                        .name(currentComment.getName())
                                        .email(currentComment.getEmail())
                                        .body(currentComment.getBody())
                                        .build()).toList();
                        return PostDomain.builder()
                                .title(currentPost.getTitle())
                                .body(currentPost.getBody())
                                .comments(parseComments)
                                .build();
                    }).toList();
            List<AlbumDomain> parseAlbum = albumsToCopy.stream()
                    .filter(currentAlbum -> currentAlbum.getUserId().equals(currentUser.getId())).map(currentAlbum ->{
                        List<PhotoDomain> parsePhotos = photosToCopy.stream()
                                .filter(currentPhoto -> currentPhoto.getAlbumId().equals(currentAlbum.getId()))
                                .map(currentPhoto -> PhotoDomain.builder()
                                        .title(currentPhoto.getTitle())
                                        .url(currentPhoto.getUrl())
                                        .thumbnailUrl(currentPhoto.getThumbnailUrl())
                                        .build()).toList();
                        return AlbumDomain.builder()
                                .title(currentAlbum.getTitle())
                                .photos(parsePhotos)
                                .build();
                    }).toList();
            return UserDomain.builder().name(currentUser.getName())
                    .username(currentUser.getUsername())
                    .email(currentUser.getEmail())
                    .address(UserAddressDomain.builder()
                            .street(currentUser.getAddress().getStreet())
                            .suite(currentUser.getAddress().getSuite())
                            .city(currentUser.getAddress().getCity())
                            .zipcode(currentUser.getAddress().getZipcode())
                            .geo(UserAddressGeoDomain.builder()
                                    .lat(currentUser.getAddress().getGeo().getLat())
                                    .lng(currentUser.getAddress().getGeo().getLng())
                                    .build())
                            .build())
                    .phone(currentUser.getPhone())
                    .website(currentUser.getWebsite())
                    .company(UserCompanyDomain.builder()
                            .name(currentUser.getCompany().getName())
                            .catchphrase(currentUser.getCompany().getCatchphrase())
                            .bs(currentUser.getCompany().getBs())
                            .build())
                    .todos(parseTodos)
                    .posts(parsePosts)
                    .albums(parseAlbum)
                    .build();
        }).toList();
        userRepository.deleteAll();
        for(UserDomain user: userToSave){
            for (TodoDomain todo: user.getTodos()){
                todo.setUser(user);
            }
            for (PostDomain post: user.getPosts()){
                post.setUser(user);
                for (CommentDomain comment: post.getComments()){
                    comment.setPost(post);
                }
            }
            for (AlbumDomain album: user.getAlbums()){
                album.setUser(user);
                for (PhotoDomain photo: album.getPhotos()){
                    photo.setAlbum(album);
                }
            }
        }
        userRepository.saveAll(userToSave);

//        List<TodoPlaceholder> todosToCopy = client.getTodos();
//        List<TodoDomain> todosToSave = todosToCopy.stream().map(placeholder -> TodoDomain.builder()
//                .title(placeholder.getTitle())
//                .completed(placeholder.getCompleted())
//                .build()).toList();
//        todoRepository.deleteAll();
//        todoRepository.saveAll(todosToSave);
//        List<CommentPlaceholder> commentsToCopy = client.getComments();
//        List<CommentDomain> commentsToSave = commentsToCopy.stream().map(commentPlaceholder -> CommentDomain.builder()
//                .name(commentPlaceholder.getName())
//                .email(commentPlaceholder.getEmail()).body(commentPlaceholder.getBody()).build()).toList();
//        commentRepository.deleteAll();
//        commentRepository.saveAll(commentsToSave);
//        List<AlbumPlaceholder> albumsToCopy = client.getAlbums();
//        List<AlbumDomain> albumsToSave = albumsToCopy.stream().map(albumPlaceholder -> AlbumDomain.builder()
//                .title(albumPlaceholder.getTitle()).build()).toList();
//        albumRepository.deleteAll();
//        albumRepository.saveAll(albumsToSave);
//        List<PhotoPlaceholder> photoToCopy = client.getPhotos();
//        List<PhotoDomain> photosToSave = photoToCopy.stream().map(photoPlaceholder -> PhotoDomain.builder()
//                .title(photoPlaceholder.getTitle())
//                .url(photoPlaceholder.getUrl())
//                .thumbnailUrl(photoPlaceholder.getThumbnailUrl()).build()).toList();
//        photoRepository.deleteAll();
//        photoRepository.saveAll(photosToSave);
//        List<PostPlaceholder> postsToCopy = client.getPosts();
//        List<PostDomain> postsToSave = postsToCopy.stream().map(postPlaceholder -> PostDomain.builder()
//                .title(postPlaceholder.getTitle())
//                .body(postPlaceholder.getBody()).build()).toList();
//        postRepository.deleteAll();
//        postRepository.saveAll(postsToSave);
//        List<UserPlaceholder> usersToCopy = client.getUsers();
//        List<UserDomain> usersToSave = usersToCopy.stream().map(userPlaceholder -> UserDomain.builder()
//                .name(userPlaceholder.getName())
//                .username(userPlaceholder.getUsername())
//                .email(userPlaceholder.getEmail())
//                .address(UserAddressDomain.builder()
//                        .street(userPlaceholder.getAddress().getStreet())
//                        .suite(userPlaceholder.getAddress().getSuite())
//                        .city(userPlaceholder.getAddress().getCity())
//                        .zipcode(userPlaceholder.getAddress().getZipcode())
//                        .geo(UserAddressGeoDomain.builder()
//                                .lat(userPlaceholder.getAddress().getGeo().getLat())
//                                .lng(userPlaceholder.getAddress().getGeo().getLng()).build())
//                        .build())
//                .phone(userPlaceholder.getPhone())
//                .website(userPlaceholder.getWebsite()).company(UserCompanyDomain.builder()
//                        .name(userPlaceholder.getCompany().getName())
//                        .catchphrase(userPlaceholder.getCompany().getCatchphrase())
//                        .bs(userPlaceholder.getCompany().getBs())
//                        .build())
//                .build()).toList();
//        userRepository.deleteAll();
//        userRepository.saveAll(usersToSave);
//        clone all the information from the website to my own database
//        original info
//        new prototype
//        once one becomes a clone, save the info on the database
    }
}
