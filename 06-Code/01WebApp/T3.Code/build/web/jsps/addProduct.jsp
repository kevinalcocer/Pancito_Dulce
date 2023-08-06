<%-- 
    Document   : addProduct
    Created on : 8 jun. 2023, 22:22:09
    Author     : pc
--%>
<%@page import="org.bson.Document" %>
<%@page import="ec.edu.espe.pancitodulce.utils.ConnectMongo"%>
<%@page import="ec.edu.espe.pancitodulce.utils.MongoDBManagement" %>
<%@page import="ec.edu.espe.pancitodulce.model.Product" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Confirmación</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="3;URL=../forms/Product.html"
    </head>
    <body>
        <h1>Datos añadidos correctamente</h1>
        
        <%
            
            String nombre;
            int cantidad;
            String medida;
            float valor;
            String factura;
            String fechaCaducidad;
            
            Product product;
            
            MongoDBManagement mongoDBManagement = new MongoDBManagement();
            ConnectMongo connectMongo = new ConnectMongo();
            connectMongo.connectData();
            Document document = new Document();

            nombre = request.getParameter("nombre");
            cantidad = Integer.parseInt(request.getParameter("cantidad"));
            medida = request.getParameter("medida");
            valor = Float.parseFloat(request.getParameter("valor"));
            factura = request.getParameter("factura");
            fechaCaducidad = request.getParameter("fechaCaducidad");
            product = new Product(nombre, cantidad, medida, valor, fechaCaducidad, factura);
            document.put("Nombre",product.getNombre());
            document.put("Cantidad",product.getCantidad());
            document.put("Medida",product.getMedida());
            document.put("Valor",product.getValor());
            document.put("Factura",product.getFactura());
            document.put("Caduca",product.getFechaCaducidad());

            mongoDBManagement.save(document, "Product", ConnectMongo.database);
        %>
    </body>
</html>
