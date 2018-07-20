function distanceOverTime(args) {
    let kmInHToMInSecond = 1000 / 3600;
    let firstSpeed = Number(args[0]) * kmInHToMInSecond;
    let secondSpeed = Number(args[1]) * kmInHToMInSecond;
    let time = Number(args[2]);
    let firstDistance = firstSpeed * time;
    let secondDistance = secondSpeed * time;
    console.log(Math.abs(firstDistance - secondDistance));
}

distanceOverTime([0, 60, 3600]);
distanceOverTime([11, 10, 120]);
distanceOverTime([5, -5, 40]);