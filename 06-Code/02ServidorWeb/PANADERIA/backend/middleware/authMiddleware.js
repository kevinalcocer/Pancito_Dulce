import jwt from "jsonwebtoken";
import Panadero from "../models/Panadero.js";
const checkUser = async(req, res, next)=>{
    let token;
    if (req.headers.authorization && req.headers.authorization.startsWith('Bearer')) {
        try {
            token=req.headers.authorization.split(' ')[1]
            const decoded = jwt.verify(token,"juan");
            req.panadero = await Panadero.findById(decoded.id).select("-password -token -confirmado");
            return next();
        } catch (error) {
            const error2 = new Error("Token no valido o inexistente");
            return res.status(403).json({msg: error2.message});
        }
    }
    if (!token) {
        const error = new Error("Token no valido o inexistente");
        res.status(403).json({msg: error.message});
    }
    next();
}

export default checkUser;