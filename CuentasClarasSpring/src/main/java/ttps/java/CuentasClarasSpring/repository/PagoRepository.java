package ttps.java.CuentasClarasSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttps.java.CuentasClarasSpring.model.Pago;

public interface PagoRepository  extends JpaRepository<Pago, Long> {

}
