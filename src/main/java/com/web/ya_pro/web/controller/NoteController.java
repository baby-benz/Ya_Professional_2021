package com.web.ya_pro.web.controller;

import com.web.ya_pro.service.ControllerService;
import com.web.ya_pro.web.controller.exception.NoteNotFoundException;
import com.web.ya_pro.web.dto.NewNoteDto;
import com.web.ya_pro.web.dto.NoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class NoteController {
    private final ControllerService controllerService;

    @PostMapping("/notes")
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

    @GetMapping("/notes/{id}")
    public NoteDto getNote(@PathVariable Long id) throws NoteNotFoundException {
        return controllerService.getNote(id);
    }

    @GetMapping("/notes")
    public List<NoteDto> getNoteByParams(@RequestParam NoteDto params) throws NoteNotFoundException {
        return controllerService.getNotesByParams(params);
    }

    @PutMapping("/notes/{id}")
    public NoteDto putNote(@PathVariable Long id, @RequestBody NewNoteDto params) throws NoteNotFoundException {
        return controllerService.updateNote(id, params);
    }
}
