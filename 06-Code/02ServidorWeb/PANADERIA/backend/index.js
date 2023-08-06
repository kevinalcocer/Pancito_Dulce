import express from "express";
import dotenv from 'dotenv';
import conectarDB from './config/db.js';
import panaderoEndPoints from './api/panaderoEndpoints.js';
import clienteEndpoints from './api/clienteEndpoints.js';

const app = express();
app.use(express.json()); //habilito para que se resiva informa tipo json
dotenv.config();
conectarDB();
const PORT = process.env.PORT || 4000;
//req es lo que se envia, res la respuesta del servidor
/*app.use('/',(req, res)=>{
    res.send('Hola mundo')
});*/
app.use('/api/panaderos', panaderoEndPoints);
app.use('/api/clientes', clienteEndpoints);


app.listen(PORT, () =>{
    console.log("Servidor corriendo en el puerto 4000");
});