package com.example.project.repository;

import com.example.project.model.Album;
import com.example.project.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
    @Query("SELECT s FROM Song s WHERE s.album = :album")
    List<Song> findSongsByAlbum(@Param("album") Album album);
}