package com.web.ya_pro.service.impl;

import com.web.ya_pro.domain.entity.Note;
import com.web.ya_pro.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteService implements com.web.ya_pro.service.NoteService {
    private final NoteRepository noteRepository;

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> get(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public List<Note> getByExample(Note note) {
        return noteRepository.findAll(Example.of(note));
    }

    @Override
    public void delete(Note note) {
        noteRepository.delete(note);
    }
}
