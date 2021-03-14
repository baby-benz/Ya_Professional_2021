package com.web.ya_pro.web.controller;

import com.web.ya_pro.service.ControllerService;
import com.web.ya_pro.web.controller.exception.NoteNotFoundException;
import com.web.ya_pro.web.dto.NewNoteDto;
import com.web.ya_pro.web.dto.NoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class NoteController {
    private final ControllerService controllerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public NoteDto postNote(@RequestBody NewNoteDto note) {
        return controllerService.addNote(note);
    }

    @DeleteMapping("/notes{id}")
    public void deleteNote(@PathVariable Long id) throws NoteNotFoundException {
        controllerService.deleteNote(id);
    }

    @GetMapping("/notes/")
    public List<NoteDto> getNotes() {
        return controllerService.getNotes();
    }

    @GetMapping(value = "/notes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDto getNote(@PathVariable Long id) throws NoteNotFoundException {
        return controllerService.getNote(id);
    }

    @GetMapping("/notes")
    public List<NoteDto> getNoteByParams(NoteDto params) throws NoteNotFoundException {
        return controllerService.getNotesByParams(params);
    }

    @PutMapping(value = "/notes/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NoteDto putNote(@PathVariable Long id, @RequestBody NewNoteDto params) throws NoteNotFoundException {
        return controllerService.updateNote(id, params);
    }
}
