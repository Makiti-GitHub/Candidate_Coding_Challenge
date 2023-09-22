import { Router } from "express";
import { User } from "../models/model";
import * as jwt from "jsonwebtoken";
import HttpError from "../utils/errorHandler";
const router = Router();

let users: User[] = [
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

type loginReqBody = {
  email: string;
  password: string;
};

router.post("/login", (req, res, next) => {
  try {
    const body = req.body as loginReqBody;
    let user = users.find((u) => u.email === body.email);
    if (!user) {
      return next(new HttpError("user_not_found", 404));
    }
    if (user.password !== body.password) {
      return next(new HttpError("wrong_password", 401));
    }

    const token = jwt.sign(
      {
        email: user.email,
        userId: user.id,
      },
      "secret123",
      { expiresIn: "1h" }
    );
    user.idToken = token;
    user.expiresIn = "3600"
    setTimeout(() => res.status(201).json(user), 3500);
  } catch (err: any) {
    return next(new HttpError(err, 500));
  }
});

router.post("/signup", (req, res, next) => {
  const body = req.body as loginReqBody;
  const id = Date.now().toString();
  let user = users.find((u) => u.email === body.email);
  if (user) {
    return next(new HttpError("email_exist", 401));
  }
  if (body.password.length < 5) {
    return next(new HttpError("weak_password", 403));
  }
  let newUser: User = {
    id: id,
    email: body.email,
    password: body.password,
  };
  users.push(newUser);
  const token = jwt.sign(
    {
      email: newUser.email,
      userId: newUser.id,
    },
    "secret123",
    { expiresIn: "1h" }
  );
  newUser.idToken = token;
  newUser.expiresIn = "3600"
  setTimeout(() => res.status(201).json(newUser), 3500);
});

export default router;
