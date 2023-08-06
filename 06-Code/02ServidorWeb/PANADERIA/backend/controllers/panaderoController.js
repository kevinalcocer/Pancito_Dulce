import Panadero from "../models/Panadero.js";
import generarJWT from "../helpers/generarJWT.js";
const registrar = async (req, res)=>{
    //Se almacena lo enviado en json
   // const {nombre, email, password} = req.body (se extrae por cada uno y si se le deja solo body da como resultado todo el json)
   //Prevenir usuarios duplicados
   const {email} = req.body;
   const existeUsuario = await Panadero.findOne({ email });//si son iguales se puede enviar email
   if (existeUsuario) {
        const error = new Error("Usuario ya registrado");
        return res.status(400).json({msg: error.message});
   }

    try {
        const panadero = new Panadero(req.body);
        const panaderoGuardado = await panadero.save(); //bloquea o espera hasta que se guarde el registro
        //res.send({url: "desde api registro"}); (envia al navegador)
        res.json({msg: "Registrando Usuario..."});
        
    } catch (error) {
        console.log(error);
    }


};

const perfil = ( req, res) => {
    const {panadero} = req;
    res.json({panadero});
};

const confirmar = async(req, res) =>{
    const {token} = req.params;

    const usuarioConfirmar = await Panadero.findOne({token});
    if(!usuarioConfirmar){
        const error = new Error('Token no válido');
        return res.status(404).json({msg: error.message});
    }
    try {
        usuarioConfirmar.token = null;
        usuarioConfirmar.confirmado = true;
        await usuarioConfirmar.save();
        res.json({msg: 'Usuario confirmado correctamente'})
    } catch (error) {
        console.log(error);
    }
}

const autenticar = async(req, res) =>{
    //verificar que llega en el req
    //console.log(req.body);
    const {email, password} = req.body;
    const usuario = await Panadero.findOne({email});
    if(!usuario){
        const error = new Error('Usuario no existe');
        return res.status(404).json({msg: error.message});
    }
    //comprobar si el usuario esta confirmado
    if(!usuario.confirmado){
        const error = new Error('Tu cuenta no ha sido confirmada');
        return res.status(403).json({msg: error.message});
    }
    
    //revisar password
    if (await usuario.comprobarPassword(password)) {
        console.log(usuario);
        res.json({token: generarJWT(usuario._id)});
    }else{
        const error = new Error('Password incorrecto');
        return res.status(403).json({msg: error.message});
    }
}
export {
    registrar,
    perfil,
    confirmar,
    autenticar
}