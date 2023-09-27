package cz.osu.kip.eventReminder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Event {
  private int eventId;
  private String title;
  private LocalDateTime dateTime;
}
