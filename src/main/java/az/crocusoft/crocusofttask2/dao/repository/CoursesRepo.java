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
    @Query("select c from Courses c join fetch c.students s join fetch c.teachers t where c.name=:name")
//    @Query("select c from Courses  c where c.name=:name")
    Courses findCoursesByName(@Param("name") String name);

//   Bir muellimin adina gore  ders kecdiyi butun kurslarin tapilmasi
    @Query("select c from Courses  c join fetch c.teachers t where t.name=:name")
    List<Courses> findCoursesByTeacherName(@Param("name") String name);


    @Query("select c from Courses c join fetch c.students s where s.name=:name")
    List<Courses> findCoursesByStudentsName(@Param("name") String name);

    @Query("select c from Courses c join fetch c.students s where s.id=:id")
    List<Courses> findCoursesOfStudentsById(@Param("id") Long id);

    @Query("select c from Courses c join fetch c.teachers t where t.id=:id")
    List<Courses> findCoursesOfTeacherById(@Param("id") Long id);

    @Query("select c from Courses c join fetch c.teachers t where t.name=:name")
    List<Courses> findCoursesOfTeachersByName(@Param("name") String name);

    @Query("select c from Courses c join fetch c.academy a where a.name like %:name%")
    List<Courses> findCoursesOfAcademyByName(@Param("name") String name);
}
