package az.crocusoft.crocusofttask2.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDate startDate;
    LocalDate expirationDate;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teachers",referencedColumnName = "id")
    Teachers teachers;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToMany()
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(
                    name = "course_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id")
    )
    List<Students> students;

    @JsonManagedReference
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id",referencedColumnName = "id")
    Academy academy;
}


