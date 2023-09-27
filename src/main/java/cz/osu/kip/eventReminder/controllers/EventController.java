package cz.osu.kip.eventReminder.controllers;

import cz.osu.kip.eventReminder.model.Event;
import cz.osu.kip.eventReminder.services.EventService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
