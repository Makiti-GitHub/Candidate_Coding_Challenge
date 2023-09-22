import { Router } from "express";
import { Post } from "../models/model";
import HttpError from "../utils/errorHandler";
import isAuth from "../middleware/is-auth";

const router = Router();
let posts: Post[] = [
  { id: "1234", title: "the best post", content: "where ever you are" },
  { id: "2234", title: "trying post", content: "are you ready??" },
];
type RequestBody = { title: string; content: string };
type RequestParams = { todoId: string };

router.get("/", (req, res, next) => {
  try {
    res.status(200).json(posts);
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

router.post("/", isAuth, (req, res, next) => {
  try {
    const body = req.body as RequestBody;
    const id = Date.now().toString();
    const newPost: Post = {
      id: id,
      title: body.title,
      content: body.content,
    };
    posts.push(newPost);
    res.status(201).json({ name: id });
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

router.delete("/", (req, res, next) => {
  try {
    posts = [];
    res.status(200).json({ deleted: true });
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});
// router.delete('/todo/:todoId', (req, res, next) => {
//     const params = req.params as RequestParams;
//     posts = posts.filter(todoItem => todoItem.id !== params.todoId);
//     res.status(200).json({message: "successfully", todos: posts})
// })
export default router;
