function robotBreakfast() {
    let robot = {
        protein: 0,
        carbohydrates: 0,
        fat: 0,
        flavours: 0
    };

    return function (line) {
        let tokens = line.split(" ");
        let command = tokens[0];
        switch (command) {
            case "restock":
                if (robot[tokens[1]]){
                    robot[tokens[1]] += +tokens[2];
                    return "Success";
                }
                break;
            case "prepare":
                //TODO
                break;
            case "report":
                console.log(`protein=${robot["protein"]} carbohydrate=${robot["carbohydrate"]} fat=${robot["fat"]} flavour=${robot["flavour"]}`)
                break;
        }
    }
}