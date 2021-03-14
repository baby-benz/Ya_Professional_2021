package com.web.ya_pro.service;

import com.web.ya_pro.domain.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> getAll();
    Optional<Note> get(Long id);
    List<Note> getByExample(Note note);
    Note save(Note note);
    void delete(Note note);
}
