package tn.esprit.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.springproject.entity.Bloc;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {
    Bloc findByNomBloc(String nomBloc);
}
