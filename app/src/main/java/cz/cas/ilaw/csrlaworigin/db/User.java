/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cz.cas.ilaw.csrlaworigin.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Relation;
import java.util.List;

@Entity
public class User {
    public @PrimaryKey()
    String id;
    public String name;
    public double lat;
    public double lng;
}



//// Case: ONE cook book has MANY Recipes
//
//@Entity
//public class CookBook {
//    @PrimaryKey
//    public int id; // Cook book id
//    public String title;
//}
//
//@Entity
//public class Recipe {
//    @PrimaryKey
//    public int id;     //
//    public int cookBookId ; // reference to cook book
//    public String title;
//}
//
//// one to many relationship
//public class CookBookWithRecipes {
//    @Embedded
//    public CookBook cookBook;
//
//    @Relation(parentColumn = "id", entityColumn = "cookBookId", entity = Recipe.class)
//    public List<Recipe> recipes;
//}
//
//@Dao
//public interface CookBookDao {
//    @Query("SELECT * FROM CookBook")
//    public List<CookBookWithRecipes> loadCookBooksWithRecipes();
//}
//
//// Case: ONE cook book can have MANY Recipes, and ONE Recipe can be part of MANY Cook books
//// MANY to MANY relationship
//
//@Entity
//public class CookBook {
//    @PrimaryKey
//    public int id; // Cook book id
//    public String title;
//}
//
//@Entity
//public class Recipe {
//    @PrimaryKey
//    public int id;     // Recipe id
//    public String title;
//}
//
//// Extra entity (CookBookRecipe) which keeps the relation between CookBook and Recipe
//@Entity
//public class CookBookRecipe {
//    @PrimaryKey(autoGenerate = true)
//    public int id;
//
//    public int cookBookId;
//    public int recipeId;
//}
//
//public class CookBookWithRecipes {
//    @Embedded
//    public CookBook cookBook;
//
//    @Relation(parentColumn = "id", entityColumn = "cookBookId", entity = Recipe.class, projection = "recipeId")
//    public List<Integer> recipeIdList;
//}
//
//public class RecipeWithCookBooks {
//    @Embedded
//    public Recipe recipe;
//
//    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = CookBook.class, projection = "cookBookId")
//    public List<Integer> cookBookIdList;
//}
//
//
////DAOs
//@Dao
//public interface CookBookDao {
//    @Query("SELECT * FROM CookBook")
//    public List<CookBookWithRecipes> loadCookBooksWithRecipes();
//}
//
//@Dao
//public interface RecipeDao {
//    @Query("SELECT * FROM Recipe")
//    public List<RecipeWithCookBooks> loadRecipeWithCookBooks();
//}