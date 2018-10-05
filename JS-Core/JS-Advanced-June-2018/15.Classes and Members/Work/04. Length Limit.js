class Stringer {
    constructor(innerString, innerLength){
        this.innerString = innerString;
        this.innerLength = innerLength;
    }

    get innerLength() {
        return this._innerLength;
    }

    set innerLength(value) {
        if (value > 0){
            this._innerLength = value;
        } else {
            this._innerLength = 0;
        }
    }

    increase(length){
        if (this._innerLength + length > 0){
            this._innerLength += length;
        }
    }

    decrease(length){
        if (this._innerLength - length < 0){
            this._innerLength = 0;
        } else {
            this._innerLength -= length;
        }
    }

    toString(){
        let result = this.innerString.substring(0, this._innerLength);
        if (result.length < this.innerString.length){
            result += '...';
        }

        return result;
    }
}