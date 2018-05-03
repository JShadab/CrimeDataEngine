package org.shadab.parking.repository;

import org.shadab.parking.model.ParkingModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends CrudRepository<ParkingModel, Long> {


	

}
