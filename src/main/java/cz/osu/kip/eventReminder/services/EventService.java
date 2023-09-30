package cz.osu.kip.eventReminder.services;

import cz.osu.kip.eventReminder.controllers.jtos.EventJTO;
import cz.osu.kip.eventReminder.model.Event;
import cz.osu.kip.eventReminder.model.EventRepository;
import cz.osu.kip.eventReminder.model.Note;
import cz.osu.kip.eventReminder.model.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;
  private final NoteRepository noteRepository;
  private final ModelMapper mm = new ModelMapper();

  private static final int EMPTY_ID = -1;

  public int create(String title, LocalDateTime dateTime) {
    Event event = new Event(EMPTY_ID, title, dateTime, new HashSet<>());
    eventRepository.save(event);
    return event.getEventId();
  }

  public EventJTO getById(int eventId) {
    EventJTO ret = this.eventRepository
            .findById(eventId)
            .map(q->mm.map(q, EventJTO.class))
            .orElseThrow();
    return ret;
  }

  public List<EventJTO> getAll() {
    List<EventJTO> ret = this.eventRepository
            .findAll(Sort.by("dateTime"))
            .stream()
            .map(q->mm.map(q, EventJTO.class))
            .collect(Collectors.toList());
    return ret;
  }

  public void update(EventJTO event) {
    Event original = this.eventRepository.getReferenceById(event.getEventId());
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.map(event, original);
    this.eventRepository.save(original);
  }

  public void delete(int eventId){
    Event event = this.eventRepository.getReferenceById(eventId);
    this.eventRepository.delete(event);
  }

  public void addNote(int eventId, String noteText){
    if (!this.eventRepository.existsById(eventId))
      throw new NoSuchElementException("Event id=" + eventId + " not found.");
    Event event = this.eventRepository.getReferenceById(eventId);
    Note note = new Note(EMPTY_ID, noteText, event);
    this.noteRepository.save(note);
  }

  public void deleteNote(int noteId){
    Note note = this.noteRepository.getReferenceById(noteId);
    this.noteRepository.delete(note);
  }
}
