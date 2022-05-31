package com.Osori.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String vid;

    @Column
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "Playlist_id")
    private Playlist playlist;
}
