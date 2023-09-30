package cz.osu.kip.eventReminder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int tagId;

  @Column(length = 128, nullable = false)
  private String text;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

}
