package cz.osu.kip.eventReminder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int eventId;
  @Column(nullable = false)
  private String title;
  private LocalDateTime dateTime;

  @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
  private Set<Note> notes = new HashSet<>();
}
