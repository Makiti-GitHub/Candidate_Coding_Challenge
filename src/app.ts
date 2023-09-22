import express from "express";
import bodyParser from "body-parser";
import postRoutes from "./routes/posts";
import recipeRoutes from "./routes/recipes";
import authRoutes from "./routes/auth";
import cors from "cors";


const app = express();
app.use(cors());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.json());

app.use("/post", postRoutes);
app.use("/recipe", recipeRoutes);
app.use("/auth", authRoutes);
app.use((error: any, req: any, res: any, next: any) => {
  if (res.headerSent) {
    return next(error);
  }
  res.status(error.code || 500);
  res.json({ message: error.message || "An error occured! Please try again" });
});

app.listen(5000);
