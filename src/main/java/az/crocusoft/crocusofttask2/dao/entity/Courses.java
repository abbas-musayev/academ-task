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
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    LocalDate startDate;
    LocalDate expirationDate;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "teachers",referencedColumnName = "id")
    Teachers teachers;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(
                    name = "student_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id")
    )
    List<Students> students;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id",referencedColumnName = "id")
    Academy academy;
}


