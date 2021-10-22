package az.crocusoft.crocusofttask2.dao.repository;

import az.crocusoft.crocusofttask2.dao.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepo extends JpaRepository<Academy,Long> {
}
