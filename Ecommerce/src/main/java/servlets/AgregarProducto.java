package servlets;

import bos.ProductoBO;
import dtos.DisponibilidadDTO;
import dtos.ProductoDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig    
@WebServlet(name = "AgregarProducto", urlPatterns = {"/AgregarProducto"})
public class AgregarProducto extends HttpServlet {

    private ProductoBO productoBO = new ProductoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("AdminCrearProducto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            String nombre = request.getParameter("nombre");
            String desc = request.getParameter("desc");
            Double precio = Double.valueOf(request.getParameter("precio"));
            Integer stock = Integer.valueOf(request.getParameter("stock"));
                        
            Part filePart = request.getPart("imagen");
            String rutaImagenBD = ""; 

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
            } else {
                rutaImagenBD = "imgs/default-product.png";
            }

            ProductoDTO producto = new ProductoDTO(
                null,                   
                nombre,                 
                precio,                  
                stock,                   
                desc,                    
                    DisponibilidadDTO.NO_DISPONIBLE,          
                desc,                   
                rutaImagenBD             
            );
            
            productoBO.agregarProducto(producto);

            response.sendRedirect("AgregarProducto");
            
        } catch (Exception ex) {
            Logger.getLogger(AgregarProducto.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "No se pudo guardar el producto");
            request.getRequestDispatcher("AdminCrearProducto.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para agregar productos sin categorías";
    }
}