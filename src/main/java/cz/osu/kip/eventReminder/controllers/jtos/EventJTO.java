package cz.osu.kip.eventReminder.controllers.jtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventJTO {
  private int eventId;
  private String title;
  private LocalDateTime dateTime;
}
