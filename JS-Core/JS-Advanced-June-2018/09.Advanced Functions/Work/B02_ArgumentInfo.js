function argumentInfo() {
    let objects = {};
    for (let i = 0; i < arguments.length; i++) {
        let obj = arguments[i];
        let type = typeof obj;
        console.log(type + ': ' + obj);
        if (!objects[type]){
            objects[type] = 1;
        } else {
            objects[type]++;
        }
    }

    let sortedTypes = [];
    for (let objectsKey in objects) {
        sortedTypes.push([objectsKey, objects[objectsKey]]);
    }


    sortedTypes
        .sort((a, b) => b[1] - a[1])
        .forEach(f => console.log(f[0] + ' = ' + f[1]));

}

argumentInfo('cat', 42, function () { console.log('Hello world!'); });