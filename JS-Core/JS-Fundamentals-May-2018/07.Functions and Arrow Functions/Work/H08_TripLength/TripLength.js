function tripLength(cordinates) {
    let points = [];
    for (let i = 0; i < cordinates.length; i+=2) {
        let point = {x:cordinates[i], y:cordinates[i+1]};
        points.push(point);
    }

    let getDistance = function ([x1, y1, x2, y2]) {
        let x = x1 - x2;
        let y = y1 - y2;
        return Math.sqrt(x ** 2 + y ** 2);
    };

    let distances = [];
    for (let i = 0; i < points.length; i++) {
        let thisPoint = points[i];
        let nextPoint = i === points.length - 1 ? points[1]: points[i+1];
        distances.push(
            getDistance([thisPoint.x,thisPoint.y, nextPoint.x,nextPoint.y]));
    }

    let threePointsDistances = [];
    for (let i = 0; i < distances.length; i++) {
        let thisDistance = distances[i];
        let nextDistance = i === distances.length - 1 ? distances[0] : distances[i+1];
        threePointsDistances.push(thisDistance + nextDistance);
    }

    let bestDistnceLenght = 0;
    let bestDistnceIndex = 0;
    for (let i = 0; i < threePointsDistances.length; i++) {
        let thisDistance = threePointsDistances[i];
        if (thisDistance > bestDistnceLenght){
            bestDistnceLenght = thisDistance;
            bestDistnceIndex = i;
        }
    }

    let firstPoint = bestDistnceIndex + 1;
    let secondPoint;
    let thirdPoint;
    
}

tripLength([0, 0, 2, 0, 4, 0]);
tripLength([5, 1, 1, 1, 5, 4]);
tripLength([-1, -2, 3.5, 0, 0, 2]);