package list.buku.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import list.buku.entity.Buku;
import list.buku.repo.BukuRepo;

@RestController
public class ApiController {

    @Autowired
    private BukuRepo bukuRepo;

    @RequestMapping("/daftar-buku")
    public List<Buku> getDaftarBuku() {
        List<Buku> data = bukuRepo.findAll();
        for(int i=0; i<data.size(); i++) {
            System.out.println(((Buku) data.get(i)).getNama());
        }
        return bukuRepo.findAll();
    }

    @RequestMapping(value = "/api/tambah", method = RequestMethod.POST) 
    public void tambahData(@RequestBody Buku buku) {
        //System.out.println("id : " + buku.getId());
        //System.out.println("nopol :  "+ buku.getNama());
        //System.out.println("merk-tipe : " + buku.getJudul_buku());
        bukuRepo.save(buku);
    }

    @RequestMapping(value = "/api/hapus/{id}", method = RequestMethod.DELETE)
    public void hapusData(@PathVariable("id") int id) {
        bukuRepo.delete(id);
    }

}