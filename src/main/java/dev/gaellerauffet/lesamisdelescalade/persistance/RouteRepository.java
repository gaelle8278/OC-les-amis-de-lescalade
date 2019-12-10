package dev.gaellerauffet.lesamisdelescalade.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.gaellerauffet.lesamisdelescalade.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
	Route findById(int id);
}

