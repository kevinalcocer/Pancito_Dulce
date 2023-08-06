import mongoose from "mongoose"; //se importa para acceder a las capas de mongoose

const clienteSchema = mongoose.Schema(
 {
    nombre:{
        type: String,
        required: true,
    },
    email:{
        type: String,
        required: true,
    },
    fecha:{
        type: Date,
        required: true,
        default: Date.now(),
    },
    producto:{
        type: String,
        required: true,
    },
    catidad:{
        type: Number,
        required: true,
    },
    costo:{
        type: Number,
        required: true,
    },
    empleado:{
        type: mongoose.Schema.Types.ObjectId, //este campo lo relaciono con el id del empleado PANDERO
        ref: "Panadero" //Modelo al cual se quiere relacionar este campo
    }

},{
    timestamps: true,  //crea las columnas de editado y creado
});

const Cliente = mongoose.model("Cliente", clienteSchema);
export default Cliente;