package cz.osu.kip.eventReminder.controllers.jtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteJTO {
  private int tagId;
  private String text;
}
