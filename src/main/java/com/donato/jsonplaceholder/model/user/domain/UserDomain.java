package com.donato.jsonplaceholder.model.user.domain;

import com.donato.jsonplaceholder.model.album.AlbumDomain;
import com.donato.jsonplaceholder.model.comment.CommentDomain;
import com.donato.jsonplaceholder.model.photo.PhotoDomain;
import com.donato.jsonplaceholder.model.post.PostDomain;
import com.donato.jsonplaceholder.model.todo.TodoDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user1")
public class UserDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private  UserAddressDomain address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "website")
    private String website;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private UserCompanyDomain company;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoDomain> todos;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostDomain> posts;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<AlbumDomain> albums;


}
