package az.crocusoft.crocusofttask2.dao.repository;

import az.crocusoft.crocusofttask2.dao.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachersRepo extends JpaRepository<Teachers,Long> {

//    Kursun adina gore bu kursu kecen muellimin tapilmagi
    @Query("select t from Teachers t join fetch t.courses c where c.name=: name")
    List<Teachers> findAllTeachersByCoursesName(@Param("name") String courseName);

}
