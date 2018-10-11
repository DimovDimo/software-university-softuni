function mapSort(map, sortFunc) {
    let keys = Array.from(map.keys());
    let resultMap = new Map();
    if (sortFunc){
        keys = keys.sort(sortFunc);
    } else {
        keys = keys.sort(function (a, b) {
            if(typeof keys[0] === 'number'){
                return a - b;
            } else {
                return a.localeCompare(b);
            }
        });
    }

    for (let key of keys){
        resultMap.set(key, map.get(key));
    }

    return resultMap;
}

module.exports = mapSort