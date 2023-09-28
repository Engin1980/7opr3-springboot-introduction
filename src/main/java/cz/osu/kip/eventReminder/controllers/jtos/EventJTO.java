package cz.osu.kip.eventReminder.controllers.jtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventJTO {
  private int eventId;
  private String title;
  private LocalDateTime dateTime;
  private Set<NoteJTO> notes;
}
