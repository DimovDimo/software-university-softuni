function distanceIn3D([x1, y1, z1, x2, y2, z2]) {
    let pointA = {x:x1, y:y1, z:z1};
    let pointB = {x:x2, y:y2, z:z2};

    let diffX = Math.abs(pointA.x - pointB.x);
    let diffY = Math.abs(pointA.y - pointB.y);
    let diffZ = Math.abs(pointA.z - pointB.z);

    let perpendicularТоAxisZ = Math.sqrt(diffX ** 2 + diffY ** 2);
    return Math.sqrt(perpendicularТоAxisZ ** 2 + diffZ ** 2);
}