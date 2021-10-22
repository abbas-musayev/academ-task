package az.crocusoft.crocusofttask2.dao.repository;

import az.crocusoft.crocusofttask2.dao.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepo extends JpaRepository<Courses,Long> {

//    Kursun adina gore axtaris
    @Query("select c from Courses c join fetch c.students join fetch c.teachers where c.name=:name")
    Courses findCoursesByName(@Param("name") String CourseName);


//   Bir muellimin adina gore  ders kecdiyi butun kurslarin tapilmasi
    @Query("select c from Courses  c join fetch c.teachers t where t.name=:name")
    List<Courses> findCoursesByTeacherName(@Param("name") String teacherName);


    @Query("select c from Courses c join fetch c.students s where s.name=:name")
    List<Courses> findCoursesByStudentsName(@Param("name") String studentsName);

}
