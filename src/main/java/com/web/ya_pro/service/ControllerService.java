package com.web.ya_pro.service;

import com.web.ya_pro.web.controller.exception.NoteNotFoundException;
import com.web.ya_pro.web.dto.NewNoteDto;
import com.web.ya_pro.web.dto.NoteDto;

import java.util.List;

public interface ControllerService {
    NoteDto addNote(NewNoteDto note);
    List<NoteDto> getNotes();
    NoteDto getNote(Long id) throws NoteNotFoundException;
    List<NoteDto> getNotesByParams(NoteDto params) throws NoteNotFoundException;
    void deleteNote(Long id) throws NoteNotFoundException;
    NoteDto updateNote(Long id, NewNoteDto params) throws NoteNotFoundException;
}
