class Point {
    constructor(x, y){
        this.x = x;
        this.y = y;
    }

    static distance(a, b){
        let diffX = a.x - b.x;
        let diffY = a.y - b.y;
        return Math.sqrt(diffX*diffX + diffY*diffY);
    }
}