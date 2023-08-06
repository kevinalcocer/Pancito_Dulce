<%-- 
    Document   : deleteProduct
    Created on : 9 jun. 2023, 16:24:24
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="3;URL=../forms/deleteProduct.html"
    </head>
    <body>
        <h1>Borrado Correctamente</h1>
        <%
            
            String nombre;
            String factura;
            
            MongoDBManagement mongoDBManagement = new MongoDBManagement();
            ConnectMongo connectMongo = new ConnectMongo();
            connectMongo.connectData();
            nombre = request.getParameter("nombre");
            factura = request.getParameter("factura");
            
            mongoDBManagement.delete("Product","Nombre",nombre,"Factura",factura,ConnectMongo.database);
                                    //("Product","nombre", "valor", base de datos )
            //mongoDBManagement.delete("Product", "Nombre",nombre,ConnectMongo.database);
        %>
    </body>
</html>
