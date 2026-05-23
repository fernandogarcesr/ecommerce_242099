package servlets;

import bos.ProductoBO;
import dtos.DisponibilidadDTO;
import dtos.ProductoDTO; 
import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part; 
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EditarProducto", urlPatterns = {"/EditarProducto"})
@MultipartConfig
public class EditarProducto extends HttpServlet {

    private ProductoBO productoBO = new ProductoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        
        if(idStr == null || idStr.isEmpty()){
            response.sendRedirect("cargarproducto?vista=adminProducto");
            return;
        }

        try {
            Long idProducto = Long.valueOf(idStr);
            ProductoDTO producto = productoBO.obtenerProductoPorId(idProducto); 
            
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("AdminEditarProducto.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(EditarProducto.class.getName()).log(Level.SEVERE, "Error al obtener producto", ex);
            response.sendRedirect("error.jsp"); 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Long id = Long.valueOf(request.getParameter("id")); 
            String nombre = request.getParameter("nombre");
            String desc = request.getParameter("desc");
            Double precio = Double.valueOf(request.getParameter("precio"));
            Integer stock = Integer.valueOf(request.getParameter("stock"));
            
            
            String rutaImagenActual = request.getParameter("rutaImagenActual"); 

            Part filePart = request.getPart("imagen");
            String rutaImagenBD = rutaImagenActual; 

            if (filePart != null && filePart.getSize() > 0) {
                String uploadPath = request.getServletContext().getRealPath("") + File.separator + "imagenes_productos";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
                String fullPath = uploadPath + File.separator + uniqueFileName;
                
                filePart.write(fullPath);
                rutaImagenBD = "imagenes_productos/" + uniqueFileName;
            }
            
            ProductoDTO producto = new ProductoDTO();
            producto.setId(id); 
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setDescripcion(desc);
            producto.setRutaImagen(rutaImagenBD);
            producto.setDisponibilidad(DisponibilidadDTO.DISPONIBLE); 

            productoBO.editarProducto(producto.getId(), producto); 

            response.sendRedirect("cargarproducto?vista=adminProducto");

        } catch (Exception ex) {
            Logger.getLogger(EditarProducto.class.getName()).log(Level.SEVERE, "Error editando producto", ex);
            request.setAttribute("error", "No se pudo actualizar el producto");
            doGet(request, response); 
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para editar productos sin categoría";
    }
}