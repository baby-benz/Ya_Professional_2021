package com.web.ya_pro.service.impl;

import com.web.ya_pro.domain.entity.Note;
import com.web.ya_pro.service.ControllerService;
import com.web.ya_pro.service.NoteService;
import com.web.ya_pro.web.controller.exception.NoteNotFoundException;
import com.web.ya_pro.web.dto.NewNoteDto;
import com.web.ya_pro.web.dto.NoteDto;
import com.web.ya_pro.web.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteControllerService implements ControllerService {
    private final NoteService noteService;

    @Value("${ya-pro.chars-from-title}")
    private int charsFromTitle;

    @Override
    public NoteDto addNote(NewNoteDto note) {
        return ObjectMapperUtil.map(
                noteService.save(new Note(note.getTitle(), note.getContent())),
                NoteDto.class
        );
    }

    @Override
    public List<NoteDto> getNotes() {
        return ObjectMapperUtil.mapAll(noteService.getAll(), NoteDto.class);
    }

    @Override
    public NoteDto getNote(Long id) throws NoteNotFoundException {
        Note noteToReturn =  noteService.get(id).orElseThrow(() -> new NoteNotFoundException("No note with id " + id + " was found"));

        if(noteToReturn.getTitle().isEmpty()) {
            String content = noteToReturn.getContent();
            if(content.length() < charsFromTitle) {
                noteToReturn.setTitle(content);
            } else {
                noteToReturn.setTitle(content.substring(0, charsFromTitle));
            }
        }

        return ObjectMapperUtil.map(noteToReturn, NoteDto.class);
    }

    @Override
    public List<NoteDto> getNotesByParams(NoteDto params) throws NoteNotFoundException {
        List<Note> notes = noteService.getByExample(ObjectMapperUtil.map(params, Note.class));

        if(!notes.isEmpty()) {
            for (Note noteToReturn : notes) {
                if (noteToReturn.getTitle().isEmpty()) {
                    String content = noteToReturn.getContent();
                    if (content.length() < charsFromTitle) {
                        noteToReturn.setTitle(content);
                    } else {
                        noteToReturn.setTitle(content.substring(0, charsFromTitle));
                    }
                }
            }

            return ObjectMapperUtil.mapAll(notes, NoteDto.class);
        } else {
            throw new NoteNotFoundException("Notes with the specified parameters were not found.");
        }
    }

    @Override
    public void deleteNote(Long id) throws NoteNotFoundException {
        Note noteToDelete = noteService.get(id).orElseThrow(
                () -> new NoteNotFoundException("No note with id " + id + " was found.")
        );

        noteService.delete(noteToDelete);
    }

    @Override
    public NoteDto updateNote(Long id, NewNoteDto params) throws NoteNotFoundException {
        Note noteToUpdate = noteService.get(id).orElseThrow(
                () -> new NoteNotFoundException("No note with id " + id + " was found.")
        );

        noteToUpdate.setTitle(params.getTitle());
        noteToUpdate.setContent(params.getContent());

        return ObjectMapperUtil.map(noteService.save(noteToUpdate), NoteDto.class);
    }
}
