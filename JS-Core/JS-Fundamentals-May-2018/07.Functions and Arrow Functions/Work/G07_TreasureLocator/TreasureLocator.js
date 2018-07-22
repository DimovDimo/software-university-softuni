function treasureLocator(cordinates) {
    let isInTokelau = function (x, y) {
        let xMin = 8;
        let yMin = 0;
        let xMax = 9;
        let yMax = 1;

        if (x >= xMin && y >= yMin &&
            x <= xMax && y <= yMax ) {
            return 'Tokelau';
        } else {
            return false;
        }
    };

    let isInTuvalu = function (x, y) {
        let xMin = 1;
        let yMin = 1;
        let xMax = 3;
        let yMax = 3;

        if (x >= xMin && y >= yMin &&
            x <= xMax && y <= yMax ) {
            return 'Tuvalu';
        } else {
            return false;
        }
    };

    let isInSamoa = function (x, y) {
        let xMin = 5;
        let yMin = 3;
        let xMax = 7;
        let yMax = 6;

        if (x >= xMin && y >= yMin &&
            x <= xMax && y <= yMax ) {
            return 'Samoa';
        } else {
            return false;
        }
    };

    let isInTonga = function (x, y) {
        let xMin = 0;
        let yMin = 6;
        let xMax = 2;
        let yMax = 8;

        if (x >= xMin && y >= yMin &&
            x <= xMax && y <= yMax ) {
            return 'Tonga';
        } else {
            return false;
        }
    };

    let isInCook = function (x, y) {
        let xMin = 4;
        let yMin = 7;
        let xMax = 9;
        let yMax = 8;

        if (x >= xMin && y >= yMin &&
            x <= xMax && y <= yMax ) {
            return 'Cook';
        } else {
            return false;
        }
    };

    for (let i = 0; i < cordinates.length; i+=2) {
        let x = cordinates[i];
        let y = cordinates[i+1];

        let isInIsland = isInTokelau(x, y) ||
            isInTuvalu(x, y) ||
            isInSamoa(x, y) ||
            isInTonga(x, y) ||
            isInCook(x, y);

        if (isInIsland != false){
            console.log(isInIsland);
        } else {
            console.log('On the bottom of the ocean');
        }
    }
}

treasureLocator([4, 2, 1.5, 6.5, 1, 3]);
treasureLocator([6, 4]);