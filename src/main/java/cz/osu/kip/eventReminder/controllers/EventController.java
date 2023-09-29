package cz.osu.kip.eventReminder.controllers;

import cz.osu.kip.eventReminder.controllers.jtos.EventJTO;
import cz.osu.kip.eventReminder.model.Event;
import cz.osu.kip.eventReminder.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
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

  @PatchMapping
  public void update(@RequestBody EventJTO event){
    this.eventService.update(event);
  }

  @DeleteMapping
  public void delete(@RequestParam int eventId){
    this.eventService.delete(eventId);
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

@Operation(summary = "Creates a new event by its title and date-time")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The event has been succesfully created.",
                content = {
                        @Content(mediaType = "text/plain", schema = @Schema(implementation = Integer.class))
                }),
        @ApiResponse(responseCode = "404", description = "Event not created due to bad data.",
                content = {
                        @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
                })
})
@PostMapping(value = "/createDocumented", produces = "text/plain", consumes = "application/json")
public int createDocumented(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Event to add, with non-empty title and event date-time",
                required = true, content = @Content(schema = @Schema(implementation = EventDataDocumented.class)))
        EventDataDocumented data) {
  int ret = this.eventService.create(data.getTitle(), data.getDateTime());
  return ret;
}
}

@Getter
@Setter
class EventData {
  private String title;
  private LocalDateTime dateTime;
}

@Getter
@Setter
class EventDataDocumented {
  @NotBlank
  @Size(min = 1, max = 255)
  private String title;

  private LocalDateTime dateTime;
}
