package gl.app.fishCare.model.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import gl.app.fishCare.model.entity.Hatchery;
import gl.app.fishCare.model.entity.User;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.exception.HatcheryAlreadyAssignedException;
import gl.app.fishCare.model.repository.HatcheryRepository;
import gl.app.fishCare.model.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HatcheryService {

	private final HatcheryRepository hatcheryRepository;
	private final UserRepository userRepository;

	// Comprobar: duplicate key value violates unique constraint
	public Hatchery createHatchery(Long userId, String name) throws EntityNotFoundException, HatcheryAlreadyAssignedException {
		Hatchery newHatchery = new Hatchery();
		Optional<User> optionalUser = userRepository.findById(userId);

		if (!optionalUser.isPresent()) {
			throw new EntityNotFoundException("User not found");
		}

		User user = optionalUser.get();

		if (user.getHatchery() != null) {
			throw new HatcheryAlreadyAssignedException("This user has already assigned a Hatchery");
		}

		newHatchery.setName(name);
		newHatchery.setUser(user);
		user.setHatchery(newHatchery);
		return hatcheryRepository.save(newHatchery);
	}

	public Hatchery getUserHatchery(Long userId) throws EntityNotFoundException {
		Optional<Hatchery> optionalHatchery = hatcheryRepository.findByUserId(userId);

		if (!optionalHatchery.isPresent()) {
			throw new EntityNotFoundException("Hatchery not found");
		}
		return optionalHatchery.get();
	}

}