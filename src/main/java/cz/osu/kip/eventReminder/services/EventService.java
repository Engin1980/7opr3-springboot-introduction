package cz.osu.kip.eventReminder.services;

import cz.osu.kip.eventReminder.controllers.jtos.EventJTO;
import cz.osu.kip.eventReminder.model.Event;
import cz.osu.kip.eventReminder.model.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;

  private static final int EMPTY_ID = -1;

  public int create(String title, LocalDateTime dateTime) {
    Event event = new Event(EMPTY_ID, title, dateTime);
    eventRepository.save(event);
    return event.getEventId();
  }

  public Optional<Event> getById(int eventId) {
    Optional<Event> ret = this.eventRepository.findById(eventId);
    return ret;
  }

  public List<Event> getAll() {
    List<Event> ret = this.eventRepository.findAll(Sort.by("dateTime"));
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
}
