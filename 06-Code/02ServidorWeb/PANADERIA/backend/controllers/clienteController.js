import Panadero from "../models/Panadero.js"
import Cliente from "../models/Cliente.js";
import { json } from "express";
const agregarCliente = async(req, res) =>{
    const cliente = new Cliente(req.body);
    cliente.empleado = req.panadero._id;
    //ver contenido del objeto
    //console.log(cliente);
    try {
        const clienteAlmacenado = await cliente.save();
        res.json(clienteAlmacenado);
    } catch (error) {
        console.log(error);
    }
};
const obtenerClientes = async (req, res) =>{
    const clientes = await Cliente.find().where("empleado").equals(req.panadero);
    res.json(clientes);
};
const obtenerTodosCliente = async (req, res) =>{
    const clientes = await Cliente.find();
    res.json(clientes);
};


const obtenerCliente = async (req, res) =>{
    const {id} = req.params;
    const cliente = await Cliente.findById(id);
    if (!cliente) {
        res.status(404).json({msg: 'No encontrado'});
    }
    res.json(cliente);
};

const autorizadoObtenerCliente = async (req, res) =>{
    const {id} = req.params;
    const cliente = await Cliente.findById(id);

    if (!cliente) {
        res.status(404).json({msg: 'No encontrado'});
    }
    if (cliente.empleado._id.toString() !== req.panadero._id.toString()) {
        return res.json({ msg: "Accion no valida - no tiene permisos"});
    }
};
 const actualizarCliente = async (req, res) =>{
    const {id} = req.params;
    const cliente = await Cliente.findById(id);
    if (!cliente) {
        return res.status(404).json({msg: 'No encontrado'});
    }
    if (cliente.empleado._id.toString() !== req.panadero._id.toString()) {
        return res.json({ msg: "Accion no valida - no tiene permisos"});
    }
    //Actualizar Cliente
    cliente.nombre = req.body.nombre || cliente.nombre; //si no hay un nuevo valor se queda con el mismo valor que tenia
    cliente.email = req.body.email || cliente.email;
    cliente.fecha = req.body.fecha  || cliente.fecha;
    cliente.producto = req.body.producto || cliente.producto;
    cliente.catidad = req.body.catidad || cliente.catidad;
    cliente.costo = req.body.costo || cliente.costo;
    cliente.empleado = req.body.empleado || cliente.empleado;

    try {
        const clienteActualizado = await cliente.save();
        res.json(clienteActualizado);
    } catch (error) {
        console.log(error);
    }
 }

 const eliminarCliente = async (req, res) =>{
    const {id} = req.params;
    const cliente = await Cliente.findById(id);
    if (!cliente) {
        return res.status(404).json({msg: 'No encontrado'});
    }
    if (cliente.empleado._id.toString() !== req.panadero._id.toString()) {
        return res.json({ msg: "Accion no valida - no tiene permisos"});
    }
    try {
        await cliente.deleteOne();
        res.json({msg: "Cliente eliminado"})
    } catch (error) {
        console.log(error);
    }
 }
export{
    agregarCliente, obtenerClientes, actualizarCliente, eliminarCliente,obtenerCliente
}