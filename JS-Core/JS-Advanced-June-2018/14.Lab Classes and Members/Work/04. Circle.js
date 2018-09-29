class Circle {
    constructor(radius){
        this.radius = radius;
    }

    get diameter(){
        return this.radius * 2;
    }

    get area(){
        return Math.PI * this.radius * this.radius;
    }

    set diameter(d){
        if (d > 0){
            this.radius = d / 2;
        }
    }
}