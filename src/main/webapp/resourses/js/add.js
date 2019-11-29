current_id = 2;
row_id = "row_";
name_name = "recipe_ingredient_name_";
amount_name = "recipe_ingredient_amount_";
amount_type_name = "recipe_ingredient_amount_type_";
create_button = document.getElementById("add");
create_button.onclick = function(){
    let last_row = document.getElementById(row_id + (current_id - 1));

    let new_row = document.createElement("div");
    new_row.className = "row my-1";
    new_row.id = row_id + current_id;

    let new_name_col = document.createElement("div");
    new_name_col.className = "col";
    let new_name = document.createElement("input");
    new_name.name = name_name + current_id;
    new_name.className = "form-control";
    new_name.required = true;
    new_name.placeholder = "Enter ingredient name";


    new_row.appendChild(new_name_col);
    new_name_col.appendChild(new_name);

    let new_amount_col = document.createElement("div");
    new_amount_col.className = "col";

    let new_amount = document.createElement("input");
    new_amount.name = amount_name + current_id;
    new_amount.className = "form-control";
    new_amount.required = true;
    new_amount.placeholder = "Enter ingredient amount(1.5)";


    new_row.appendChild(new_amount_col);
    new_amount_col.appendChild(new_amount);


    let new_amount_type_col = document.createElement("div");
    new_amount_type_col.className = "col";

    let new_amount_type = document.createElement("input");
    new_amount_type.name = amount_type_name + current_id;
    new_amount_type.className = "form-control";
    new_amount_type.required = true;
    new_amount_type.placeholder = "Enter ingredient amount type(ml)";


    new_row.appendChild(new_amount_type_col);
    new_amount_type_col.appendChild(new_amount_type);

    last_row.insertAdjacentElement("afterend", new_row);

    let ingredient_amount = document.getElementById("ingredient_amount");
    ingredient_amount.value = current_id;
    current_id++;
};
