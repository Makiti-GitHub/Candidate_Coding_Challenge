"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const jwt = __importStar(require("jsonwebtoken"));
const errorHandler_1 = __importDefault(require("../utils/errorHandler"));
const router = (0, express_1.Router)();
let users = [
    {
        id: "13231",
        email: "ivan.donfack@yahoo.fr",
        password: "12345678",
    },
    {
        id: "1323ds1",
        email: "t@gmail.com",
        password: "12345678",
    },
];
router.post("/login", (req, res, next) => {
    try {
        const body = req.body;
        let user = users.find((u) => u.email === body.email);
        if (!user) {
            return next(new errorHandler_1.default("user_not_found", 404));
        }
        if (user.password !== body.password) {
            return next(new errorHandler_1.default("wrong_password", 401));
        }
        const token = jwt.sign({
            email: user.email,
            userId: user.id,
        }, "secret123", { expiresIn: "1h" });
        user.idToken = token;
        user.expiresIn = "3600";
        setTimeout(() => res.status(201).json(user), 3500);
    }
    catch (err) {
        return next(new errorHandler_1.default(err, 500));
    }
});
router.post("/signup", (req, res, next) => {
    const body = req.body;
    const id = Date.now().toString();
    let user = users.find((u) => u.email === body.email);
    if (user) {
        return next(new errorHandler_1.default("email_exist", 401));
    }
    if (body.password.length < 5) {
        return next(new errorHandler_1.default("weak_password", 403));
    }
    let newUser = {
        id: id,
        email: body.email,
        password: body.password,
    };
    users.push(newUser);
    const token = jwt.sign({
        email: newUser.email,
        userId: newUser.id,
    }, "secret123", { expiresIn: "1h" });
    newUser.idToken = token;
    newUser.expiresIn = "3600";
    setTimeout(() => res.status(201).json(newUser), 3500);
});
exports.default = router;
