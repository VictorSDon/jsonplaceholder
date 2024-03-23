package com.donato.jsonplaceholder.model.album;

import com.donato.jsonplaceholder.model.photo.PhotoDomain;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
public class AlbumDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserDomain user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "album", cascade = CascadeType.ALL)
    private List<PhotoDomain> photos;
}
