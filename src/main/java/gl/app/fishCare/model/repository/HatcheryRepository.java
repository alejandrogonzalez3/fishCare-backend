package gl.app.fishCare.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import gl.app.fishCare.model.entity.Hatchery;

public interface HatcheryRepository extends CrudRepository<Hatchery, Long> {

	Optional<Hatchery> findByName(String name);

	@Query("SELECT h FROM Hatchery h WHERE h.user.id = :userId")
	Optional<Hatchery> findByUserId(@Param("userId") Long userId);

}