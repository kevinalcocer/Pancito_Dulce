<%-- 
    Document   : editProduct
    Created on : Jun 24, 2023, 12:05:01 PM
    Author     : adria
--%>
<%@page import="ec.edu.espe.pancitodulce.utils.ConnectMongo"%>
<%@page import="ec.edu.espe.pancitodulce.utils.MongoDBManagement" %>
<%@page import="ec.edu.espe.pancitodulce.model.Product" %>
<%@ page import="org.bson.Document" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="3;URL=../forms/editar.html">
    </head>
    <body>
        <h1>Editado correctamente</h1>
         <%
            
            String nombre;
            String factura;
            String newname;
            
            MongoDBManagement mongoDBManagement = new MongoDBManagement();
            ConnectMongo connectMongo = new ConnectMongo();
            connectMongo.connectData();
            
            nombre = request.getParameter("nombre");
            factura = request.getParameter("factura");
            newname = request.getParameter("newname");
            
                                    //("Product","nombre", "valor", base de datos )
            mongoDBManagement.update("Product", "Nombre",nombre,"Factura", factura,newname, ConnectMongo.database);
        %>
    </body>
</html>
