function objectInheritance(input) {
    let cars = {};
    for (let item of input) {
        let tokens = item.split(' ');
        let command = tokens[0];
        let name = '';
        let parentName = '';
        let key = '';
        let value = '';
        switch (command) {
            case 'create':
                if (tokens.length === 2){
                    name = tokens[1];
                    cars[name] = {};
                } else {
                    name = tokens[1];
                    parentName = tokens[3];
                    cars[parentName] = Object.create(name);
                }
                break;
            case 'set':
                name = tokens[1];
                key = tokens[2];
                value = tokens[3];
                cars[name][key] = value;
                break;
            case 'print':
                name = tokens[1];
                let output = [];
                for (let key in cars) {
                    output.push(key + ':' + cars[key]);
                }

                console.log(output.join(','));
                break;
        }
    }
}

objectInheritance(['create c1',
    'create c2 inherit c1',
    'set c1 color red',
    'set c2 model new',
    'print c1',
    'print c2']);