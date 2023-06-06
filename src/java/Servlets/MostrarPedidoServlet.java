/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Beans.Pedido;
import Beans.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MostrarPedidoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los parámetros del pedido
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        String fechaPedido = request.getParameter("fechaPedido");
        String estadoPedido = request.getParameter("estadoPedido");
        int idTienda = Integer.parseInt(request.getParameter("idTienda"));

        // Crear el objeto Pedido
        Pedido pedido = new Pedido(idPedido, fechaPedido, estadoPedido, idTienda);

        // Obtener los productos del pedido
        String[] nombresProductos = request.getParameterValues("nombreProducto");
        String[] cantidadsProductos = request.getParameterValues("cantidadProducto");

        for (int i = 0; i < nombresProductos.length; i++) {
            String nombreProducto = nombresProductos[i];
            int cantidadProducto = Integer.parseInt(cantidadsProductos[i]);

            Producto producto = new Producto(nombreProducto, cantidadProducto);
            pedido.agregarProducto(producto);
        }
        
         // Almacenar el pedido en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("pedido", pedido);
        
        // Mostrar los datos del pedido
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Datos del Pedido</title></head>");
        out.println("<body>");
        out.println("<h1>Datos del Pedido:</h1>");
        out.println("<p>ID Pedido: " + pedido.getIdPedido() + "</p>");
        out.println("<p>Fecha Pedido: " + pedido.getFechaPedido() + "</p>");
        out.println("<p>Estado Pedido: " + pedido.getEstadoPedido() + "</p>");
        out.println("<p>ID Tienda: " + pedido.getIdTienda() + "</p>");
        out.println("<h2>Productos:</h2>");
        for (Producto producto : pedido.getProductos()) {
            out.println("<p>Nombre: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad() + "</p>");
        }
        out.println("<form action=\"exportarpedido\" method=\"POST\">");
        out.println("<input type=\"hidden\" name=\"idPedido\" value=\"" + pedido.getIdPedido() + "\">");
        out.println("<input type=\"submit\" value=\"Exportar a CSV\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
