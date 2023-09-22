import { Router } from "express";
import { Ingredient, Recipe } from "../models/model";
import isAuth from "../middleware/is-auth";
import HttpError from "../utils/errorHandler";
const router = Router();

let ingredients: Ingredient[] = [
  { id: "1", name: "Tomato", amount: 10 },
  { id: "sdfa1", name: "peper", amount: 20 },
];

let recipes: Recipe[] = [
  {
    id: "sdfa1fg",
    name: "Rice",
    description: "The best meal you can get",
    imagePath:
      "https://static.toiimg.com/thumb/56933159.cms?imgsize=686279&width=800&height=800",
    ingredients: [{ id: "4", name: "bread", amount: 20 }],
  },
  {
    id: "sdfadf1fg",
    name: "A est Recipe",
    description: "Just trying",
    imagePath:
      "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
    ingredients: [{ id: "4", name: "bread", amount: 20 }],
  },
];
type recipesPutRequestBody = Recipe[];

router.get("/", isAuth, (req:any, res, next) => {
  try {
    console.log(req.userId)
    res.status(200).json(recipes);
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

router.get("/ingredients", isAuth, (req, res, next) => {
  try {
    res.status(200).json(ingredients);
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

router.put("/recipes", isAuth, (req, res, next) => {
  const body = req.body as recipesPutRequestBody;
  recipes = [...body];
  res.status(200).json(recipes);
});

router.post("/", isAuth, (req, res, next) => {
  try {
    const id = Date.now().toString();
    const { name, description, imagePath } = req.body;
    const newRecipe: Recipe = {
      id: id,
      name: name,
      description: description,
      imagePath: imagePath,
      ingredients: [],
    };
    recipes.push(newRecipe);
    res.status(201).json({ name: id });
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

router.post("/ingredients", isAuth, (req, res, next) => {
  try {
    const id = Date.now().toString();
    const { name, amount } = req.body;
    const newIngredient: Ingredient = {
      id: id,
      name: name,
      amount: amount,
    };
    ingredients.push(newIngredient);
    res.status(201).json({ name: id });
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

router.delete("/ingredients/:id", isAuth, (req, res, next) => {
  try {
    ingredients = ingredients.filter((i) => i.id !== req.params.id);
    res.status(200).json({ deleted: true });
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

router.delete("/recipe/:id", isAuth, (req, res, next) => {
  try {
    recipes = recipes.filter((r) => r.id !== req.params.id);
    res.status(200).json({ deleted: true });
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

router.put("/recipe/:id", (req, res, next) => {
  try {
    const { name, description, imagePath } = req.body;
    const recipeIndex = recipes.findIndex((r) => r.id == req.params.id);
    recipes[recipeIndex].name = name;
    recipes[recipeIndex].description = description;
    recipes[recipeIndex].imagePath = imagePath;
    res.status(200).json({ updated: true });
  } catch (err) {
    return next(new HttpError(err, 500));
  }
});

export default router;
