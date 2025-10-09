# Melting Recipe Data Format

- `type`: The type of recipe. Must be `create_melting:salvaging`.
- `ingredient`: The Ingredient for the recipe. Can be an ID or a tag.
- `heat_requirement`: The heat required for the attached Blaze Burner. Can be `heated` or `superheated`.
- `result_material`: An object with information about the material to be produced, used to calculate partial salvaging results.
    - `ingot`: The ingot of the material to be produced.
    - `nugget`: The material representing a partial ingot of the material to be produced.
    - `ratio`: The number of nuggets to produce one ingot.
- `result_amount`: The number of ingots to produce.
    - This value is multiplied by the durability of the input item, then split into the corresponding amount of ingots and nuggets, rounded down.

```json
{
  "type": "create_melting:salvaging",
  
  "ingredient": {
    "id": "minecraft:iron_pickaxe"
  },
    
  "heat_requirement": "heated",

  "result_material": {
    "ingot": "minecraft:iron_ingot",
    "nugget": "minecraft:iron_nugget",
    "ratio": 9
  },
  
  "result_amount": 1.5
}
```

This Iron Pickaxe recipe will turn into 1 Iron Ingot and 4 Iron Nuggets (due to rounding down).

```json
{
  "type": "create_melting:salvaging",
  
  "input": {
    "id": "minecraft:netherite_chestplate"
  },
    
  "heat_requirement": "superheated",

  "result_material": {
    "ingot": "minecraft:netherite_ingot",
    "nugget": "minecraft:ancient_debris",
    "ratio": 4
  },
  
  "result_amount": 0.5
}
```

This Netherite Chestplate recipe will turn into 2 Ancient Debris.