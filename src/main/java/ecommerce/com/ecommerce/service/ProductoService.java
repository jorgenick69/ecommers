package ecommerce.com.ecommerce.service;

import ecommerce.com.ecommerce.Exceptions.ServiceException;
import ecommerce.com.ecommerce.domain.Foto;
import ecommerce.com.ecommerce.domain.Producto;
import ecommerce.com.ecommerce.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoService {

    @Autowired
    private ProductRepository pRep;

    @Autowired
    private FotoService fotoService;

    @Transactional
    public void crear(Producto producto, MultipartFile archivo1, MultipartFile archivo2) throws ServiceException {
        
        producto.setPrecio(producto.getCosto()+(producto.getCosto()*producto.getRemarque())/100);
        producto.setPrecioFinal(producto.getPrecio()-(producto.getPrecio()*producto.getDescuento())/100);//
        producto.setAlta(new Date());
        List<Foto> fotos = new ArrayList<>();
        fotos.add(fotoService.crear(archivo1));
        fotos.add(fotoService.crear(archivo2));
        producto.setFoto(fotos);
        producto.setEstado(true);
        pRep.save(producto);}

    @Transactional
    public void borrar(String id) {
        Optional<Producto> response = pRep.findById(id);
        if (response.isPresent()) {pRep.delete(response.get());}}

    public Producto listarId(String id){
        Optional<Producto> respuesta = pRep.findById(id);
        if (respuesta.isPresent()) {
            Producto producto = respuesta.get();
            return producto;
        } else {
            return null;
        }
    }

    public List<Producto> listarGenero(String genero){return pRep.findByGenero(genero);}

    public List<Producto> listarEstilo(String estilo){return pRep.findByEstilo(estilo);}

    public List<Producto> listarModelo(String modelo){return pRep.findByModelo(modelo);}

    public List<Producto> listarMarca(String marca){return pRep.findByMarca(marca);}

    public List<Producto> listarDuracion(String duracion){return pRep.findByDuracion(duracion);}

    public List<Producto> listarUso(String uso){return pRep.findByUso(uso);}

    public List<Producto> listarSuperQuery(String nombre){return pRep.findByOcurrence(nombre);}

    public List<Producto> listarStock(Integer stock){return pRep.findByStock(stock);}

}