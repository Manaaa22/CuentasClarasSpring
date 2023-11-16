package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T> extends JpaRepository<T, Long> {

	T save();

	boolean exists(long id);

	T deleteById(long id);

	T delete();

	T findOne(long id);

	List<T> findAll(); // o uso Iterable?
}
