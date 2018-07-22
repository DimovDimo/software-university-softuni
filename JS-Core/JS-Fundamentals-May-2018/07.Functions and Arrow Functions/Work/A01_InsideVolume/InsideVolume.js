function insideVolume(cordinates) {
    function inVolume(x, y, z) {
        let x1 = 10;
        let x2 = 50;
        let y1 = 20;
        let y2 = 80;
        let z1 = 15;
        let z2 = 50;

        if (x >= x1 && x <= x2 &&
            y >= y1 && y <= y2 &&
            z >= z1 && z <= z2) {
            return true;
        }

        return false;
    }

    for (let i = 0; i < cordinates.length; i += 3) {
        let x = cordinates[i];
        let y = cordinates[i+1];
        let z = cordinates[i+2];

        if (inVolume(x, y, z)){
            console.log('inside');
        } else {
            console.log('outside');
        }
    }
}