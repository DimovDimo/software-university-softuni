function problem(){
    let add = (function(){
        let sum = 0;
        function result(number) {
            sum += number;
            return result;
        }

        result.toString = () => sum.toString();
        return result();
    }());
}