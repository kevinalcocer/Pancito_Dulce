import express from 'express';
import {registrar, perfil, confirmar, autenticar} from "../controllers/panaderoController.js";
import checkUser from '../middleware/authMiddleware.js';
const router = express.Router();

router.post("/", registrar);
router.get("/confirmar/:token", confirmar);
router.post("/login", autenticar);
router.get('/perfil', checkUser, perfil);

export default router;