export interface Ingredient {
  id: string;
  name: string;
  amount: number;
}

export interface Recipe {
  id: string;
  name: string;
  description: string;
  imagePath: string;
  ingredients: Ingredient[];
}

export interface Post {
  id: string;
  title: string;
  content: string;
}

export interface User {
  id: string;
  email: string;
  password: string;
  idToken?: string;
  expiresIn?: string;
}

