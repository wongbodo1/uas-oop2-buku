package list.buku.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import list.buku.entity.Buku;

@Repository
public interface BukuRepo 
    extends JpaRepository<Buku, Integer> {
    
}