package az.crocusoft.crocusofttask2.dao.repository;

import az.crocusoft.crocusofttask2.dao.entity.Courses;
import az.crocusoft.crocusofttask2.dao.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepo extends JpaRepository<Students,Long> {
//    Telebenin adina ve soyadina gore tapilmagi
    @Query("select s from Students s where s.name=:name and s.surname=:surname")
    Students findStudentsByNameAndSurname(@Param("name") String name,@Param("surname") String surname);

//     Kursun adina gore daxilinde olan telebelerin tapilmagi
    @Query("select s from Students s join fetch s.courses c where c.name=:name")
    List<Students> findStudentsByCourseName(@Param("name") String courseName);

    @Query("select s from Students s join fetch s.courses c where c.id=:id")
    List<Students> findStudentsOfCourseById(@Param("id") Long id);
}
