/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Beans.Pedido;
import Beans.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Joleon
 */
public class ModeloDB {

    private static final String DB_URL = "jdbc:mysql://localhost/pedidossilbon";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void insertarPedido(Pedido pedido) throws SQLException {

        // Establecer la conexión con la base de datos
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // Insertar el pedido en la tabla "pedidos"
            String sql = "INSERT INTO pedidos (id_pedido, fecha_pedido, estado_pedido, id_tienda) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, pedido.getIdPedido());
                stmt.setString(2, pedido.getFechaPedido());
                stmt.setString(3, pedido.getEstadoPedido());
                stmt.setInt(4, pedido.getIdTienda());
                stmt.executeUpdate();
            }

            // Obtener el ID del pedido que acabamos de insertar
            int idPedido = obtenerUltimoIdPedido(conn);

            // Insertar los productos en la tabla "productos_pedido"
            ArrayList<Producto> productos = pedido.getProductos();
            for (Producto producto : productos) {
                sql = "INSERT INTO productos_pedido (id_pedido, id_producto, nombre_producto, cantidad_producto) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, idPedido);
                    stmt.setInt(2, producto.getIdProducto());
                    stmt.setString(2, producto.getNombre());
                    stmt.setDouble(3, producto.getCantidad());
                    stmt.executeUpdate();
                }
            }
        }
    }

    private static int obtenerUltimoIdPedido(Connection conn) throws SQLException {
        String sql = "SELECT MAX(id_pedido) AS max_id FROM pedidos";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("max_id");
            } else {
                throw new SQLException("No se pudo obtener el ID del último pedido insertado");
            }
        }
    }
}
