package openapi.bldrgst.repository;

import openapi.bldrgst.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository  extends JpaRepository<Domain, Long> {

}
