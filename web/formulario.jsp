<%-- 
    Document   : formulario
    Created on : 5 abr 2023, 8:42:15
    Author     : jleon
--%>

<html>
<head>
    <title>Formulario de Pedido</title>
</head>
<body>
    <h1>Formulario de Pedido</h1>
    <form action="mostrarpedido" method="POST">
        <label for="idPedido">ID Pedido:</label>
        <input type="text" id="idPedido" name="idPedido"><br><br>
        
        <label for="fechaPedido">Fecha Pedido:</label>
        <input type="date" id="fechaPedido" name="fechaPedido"><br><br>
        
        <label for="estadoPedido">Estado Pedido:</label>
        <input type="text" id="estadoPedido" name="estadoPedido"><br><br>
        
        <label for="idTienda">ID Tienda:</label>
        <input type="text" id="idTienda" name="idTienda"><br><br>
        
        <h2>Productos:</h2>
        <div id="productos">
            <div class="producto">
                <label for="nombreProducto">Nombre:</label>
                <input type="text" id="nombreProducto" name="nombreProducto">
                
                <label for="cantidadProducto">Cantidad:</label>
                <input type="text" id="cantidadProducto" name="cantidadProducto"><br>
            </div>
        </div>
        
        <button type="button" onclick="agregarProducto()">Agregar Producto</button><br><br>
        <input type="submit" value="Enviar Pedido">
    </form>
    
    <script>
        function agregarProducto() {
            var productosDiv = document.getElementById("productos");
            
            var productoDiv = document.createElement("div");
            productoDiv.classList.add("producto");
            
            var nombreLabel = document.createElement("label");
            nombreLabel.textContent = "Nombre:";
            var nombreInput = document.createElement("input");
            nombreInput.type = "text";
            nombreInput.name = "nombreProducto";
            productoDiv.appendChild(nombreLabel);
            productoDiv.appendChild(nombreInput);
            
            var cantidadLabel = document.createElement("label");
            cantidadLabel.textContent = "cantidad:";
            var cantidadInput = document.createElement("input");
            cantidadInput.type = "text";
            cantidadInput.name = "cantidadProducto";
            productoDiv.appendChild(cantidadLabel);
            productoDiv.appendChild(cantidadInput);
            
            productosDiv.appendChild(productoDiv);
        }
    </script>
</body>
</html>
