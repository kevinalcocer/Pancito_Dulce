
import jwt from 'jsonwebtoken';
const generarJWT = (id) =>{
    return jwt.sign({id},"juan",{  //se usa una palabra clave "juan"
        expiresIn: "30d",
    }  )
};

export default generarJWT;