import express from "express";
const router = express.Router();
import {agregarCliente, obtenerClientes,actualizarCliente,eliminarCliente,obtenerCliente } from '../controllers/clienteController.js';
import checkUser from "../middleware/authMiddleware.js"

router.route('/')
.post(checkUser,agregarCliente)
.get(checkUser, obtenerClientes);

router.route('/:id')
.get(checkUser, obtenerCliente)
.put(checkUser, actualizarCliente) //Para actualizar se puede usar el PUT o el Patch
.delete(checkUser, eliminarCliente);

export default router;