package ecommerce.com.ecommerce.repository;

import ecommerce.com.ecommerce.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
}