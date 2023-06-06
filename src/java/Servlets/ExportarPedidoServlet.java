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

public class ExportarPedidoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los datos del pedido del atributo de sesión
        Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");

        if (pedido != null) {
            // Establecer el tipo de contenido de la respuesta
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=pedido.csv");

            // Crear un escritor para el cuerpo de la respuesta
            PrintWriter writer = response.getWriter();

            // Escribir los encabezados del CSV
            writer.println("ID Pedido, Fecha Pedido, Estado Pedido, ID Tienda, Nombre Producto, Precio Producto");

            // Escribir los datos del pedido en el CSV
            for (Producto producto : pedido.getProductos()) {
                writer.println(pedido.getIdPedido() + ","
                        + pedido.getFechaPedido() + ","
                        + pedido.getEstadoPedido() + ","
                        + pedido.getIdTienda() + ","
                        + producto.getNombre() + ","
                        + producto.getCantidad());
            }

            // Cerrar el escritor
            writer.close();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
