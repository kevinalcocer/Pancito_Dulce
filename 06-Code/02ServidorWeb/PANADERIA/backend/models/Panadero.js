import mongoose from "mongoose";
import bcrypt from 'bcrypt';
import generarId from "../helpers/generaris.js";


const panaderoSchema = mongoose.Schema({
    nombre:{
        type: String,
        required: true,
        trim: true  //elmina los espacon en blanco
    },
    password:{
        type: String,
        required: true 
    },
    email: {
        type: String,
        required: true,
        unique: true, //si el mismo correo existe en la base no permite el ingreso en el put o post, se queda tratando de enviar y da error en consola
        trim: true
    },
    telefono:{
        type: String,
        default: null,
        trim: true
    },

    token:{
        type: String,
        default: generarId() //si no ingresa el valor se genera una automaticamente por medio de la funcion
    },

    confirmado:{
        type: Boolean,
        default: false
    },


});

panaderoSchema.pre("save", async function(next){
    if(!this.isModified('password')){
        next(); //pasa al siguiente middleware o siguiente paso del index.js
    }
    const salt = await bcrypt.genSalt(10);
    this.password = await bcrypt.hash(this.password, salt);
}) //metodo pre es de mongoose que se una para ejecutar algo antes de

panaderoSchema.methods.comprobarPassword = async function(passwordFormulario){
    return await bcrypt.compare(passwordFormulario, this.password)
};

const Panadero = mongoose.model("Panadero", panaderoSchema);
export default Panadero;
