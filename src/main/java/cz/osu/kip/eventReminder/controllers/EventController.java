package cz.osu.kip.eventReminder.controllers;

import cz.osu.kip.eventReminder.model.Event;
import cz.osu.kip.eventReminder.services.EventService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

  @Autowired
  private EventService eventService;

  @PostMapping
  public int create(@RequestBody EventData data) {
    int ret = this.eventService.create(data.getTitle(), data.getDateTime());
    return ret;
  }

  @GetMapping
  public Event getById(@RequestParam int eventId) {
    Event ret = this.eventService.getById(eventId).orElse(null);
    return ret;
  }

// ***
// Zpracování chyby při nenalezení přes ResponseEntity
// ***
//  @GetMapping
//  public ResponseEntity<?> getById(@RequestParam int eventId) {
//    Optional<Event> event = this.eventService.getById(eventId);
//    ResponseEntity<?> ret;
//
//    if (event.isPresent())
//      ret = ResponseEntity.ok(event);
//    else
//      ret = new ResponseEntity<>("Event with eventId=" + eventId + " not found.", HttpStatus.NOT_FOUND);
//
//    return ret;
//  }


// ***
// Zpracování chyby při nenalezení přes ResponseStatusException
// ***
//  @GetMapping
//  public Event getById(@RequestParam int eventId) {
//    Optional<Event> event = this.eventService.getById(eventId);
//
//    if (event.isEmpty())
//      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with eventId=" + eventId + " not found.");
//
//    return event.get();
//  }


// ***
// Zpracování chyby při nenalezení přes ControllerExceptionHandler
// ***
//  @GetMapping
//  public Event getById(@RequestParam int eventId) {
//    return this.eventService
//            .getById(eventId)
//            .orElseThrow(() -> new NoSuchElementException(
//                    "Event with eventId=" + eventId + " not found."));
//  }

  @GetMapping("/list")
  public List<Event> list() {
    List<Event> ret = this.eventService.getAll();
    return ret;
  }
}

@Getter
@Setter
class EventData {
  private String title;
  private LocalDateTime dateTime;
}
