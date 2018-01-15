package list.buku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "buku")
public class Buku {

    @Id @Getter @Setter
    private int id;
    @Getter @Setter
    private String nama;
    @Getter @Setter
    private String judul_buku;

}