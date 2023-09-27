package cz.osu.kip.eventReminder.services;

import cz.osu.kip.eventReminder.model.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
  private final List<Event> events = new ArrayList<>();

  public int create(String title, LocalDateTime dateTime) {
    int id = 1 + this.events.stream()
            .mapToInt(q -> q.getEventId())
            .max()
            .orElse(0);
    Event event = new Event(id, title, dateTime);
    this.events.add(event);
    return id;
  }

  public Optional<Event> getById(int eventId) {
    Optional<Event> ret = this.events.stream()
            .filter(q -> q.getEventId() == eventId)
            .findFirst();
    return ret;
  }

  public List<Event> getAll() {
    List<Event> ret = new ArrayList<>(this.events);
    return ret;
  }
}
