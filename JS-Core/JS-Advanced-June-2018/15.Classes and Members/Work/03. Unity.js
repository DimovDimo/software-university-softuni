class Rat {
    constructor(name){
        this.name = name;
        this.containse = [];
    }

    getRats(){
        return this.containse;
    }

    unite(otherRat){
        if (otherRat instanceof Rat){
            this.containse.push(otherRat);
        }
    }

    toString(){
        let result = [];
        result.push(this.name);
        for (const containseElement of this.containse) {
            result.push('##' + containseElement.name);
        }

        return result.join('\n');
    }
}