<%-- 
    Document   : consulta
    Created on : 9 jun. 2023, 01:29:22
    Author     : pc
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="ec.edu.espe.pancitodulce.controller.dataController"%>
<%@page import="ec.edu.espe.pancitodulce.model.Product"%>
<%@page import="ec.edu.espe.pancitodulce.modelDAO.ProductDao"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/consultastyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventario</title>
        
    </head>
    <body>
            <div class="container header_content">
            <img src="../img/pan_2.png" alt="pan_img" class="img-header">
            <h1>Inventario</h1>
            <img src="../img/pan_1.png" alt="pan_img" class="img-header">
        </div>
        
        <%
             dataController dataController = new dataController();
            ArrayList<Product> products;
            products = dataController.getProduct();
            ProductDao proDao = new ProductDao();
            DecimalFormat formato = new DecimalFormat("#.00");
        %>
        
        <div class="container">
            <h3 align="center">Listado de productos</h3>
            <br>
        </div>
        
        <div class="container grid_content">
            <table class="table table-light">
                <thead class="">
                    <tr class="text-center">
                        <th class="table-secondary text-dark" scope="col">Factura</th>
                        <th class="table-secondary text-dark" scope="col">Nombre</th>
                        <th class="table-secondary text-dark" scope="col">Cantidad</th>
                        <th class="table-secondary text-dark" scope="col">Medida</th>
                        <th class="table-secondary text-dark" scope="col">Valor</th>
                        <th class="table-secondary text-dark" scope="col">Subtotal</th>
                        <th class="table-secondary text-dark" scope="col">Iva</th>
                        <th class="table-secondary text-dark" scope="col">Total</th>
                        <th class="table-secondary text-dark" scope="col">Caducidad</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                      double perdida =0;
                      double totalInventario =0;
                      for(Product product : products){
                    %>
                        <tr class="text-center">
                            <td><% out.print(product.getFactura()); %></td>
                            <td><% out.print(product.getNombre()); %></td>
                            <td><% out.print(product.getCantidad()); %></td>
                            <td><% out.print(product.getMedida()); %></td>
                            <td><% out.print(product.getValor()); %></td>
                            <td><% out.print(formato.format(proDao.calcularSubTotal(product.getCantidad(), product.getValor()))); %></td>
                            <td><% out.print(formato.format(proDao.calculaIva(product.getCantidad(), product.getValor()))); %></td>
                            <td><% out.print(formato.format(proDao.calculaTotal(product.getCantidad(), product.getValor()))); %></td>
                            <td>
                                <font color="<% if (proDao.caducidad(product.getFechaCaducidad()).equals("Caducado")) { out.print("red"); } else { out.print("white"); } %>">
                                <% out.print(proDao.caducidad(product.getFechaCaducidad())); %>
                                </font>
                            </td>
                        </tr>
                    <%
                      if(proDao.caducidad(product.getFechaCaducidad()) == "Caducado"){
                            perdida += proDao.calculaTotal(product.getCantidad(), product.getValor());
                        }else{
                            totalInventario += proDao.calculaTotal(product.getCantidad(), product.getValor());
                        }
                      }
                    %>
                </tbody>
            </table>
                <div class="analytics">
                 <h3 style="text-align: left;font-weight: normal;">Costo total por productos caducados:    <font color="red"><% out.print(formato.format(perdida)); %></font></h3>
                 <h3 style="text-align: left;font-weight: normal;">Costo total de productos Vigentes:      <% out.print(formato.format(totalInventario)); %></h3>
                 <h3 style="text-align: left;font-weight: normal;">Valor invertido en bodega:      <% out.print(formato.format(totalInventario+perdida)); %></h3>
                </div>
                 <div class="regresar">
                    <a href="../index.html">Regresar al menú inicial</a>
                </div>
        </div>
        
        
    </body>
</html>