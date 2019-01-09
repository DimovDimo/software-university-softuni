const fs = require('fs');
const path = './storage.json';

let storage = {};

module.exports = {
    put: (key, value) => {
        if (typeof (key) !== 'string') {
            throw new Error('Key must be string!');
        }

        if (storage.hasOwnProperty(key)) {
            throw new Error('Key already exists!');
        }

        storage[key] = value;
    },
    get: (key) => {
        if (typeof (key) !== 'string') {
            throw new Error('Key must be string!');
        }

        if (!storage.hasOwnProperty(key)) {
            throw new Error('Key does not exists!');
        }

        return storage[key];
    },
    getAll: () => {
        if (Object.keys(storage).length === 0) {
            return "Storage is empty!";
        } else {
            return storage;
        }
    },
    update: (key, newValue) => {
        if (typeof (key) !== 'string') {
            throw new Error('Key must be string!');
        }

        if (!storage.hasOwnProperty(key)) {
            throw new Error('Key does not exists!');
        }

        storage[key] = newValue;
    },
    delete: (key) => {
        if (typeof (key) !== 'string') {
            throw new Error('Key must be string!');
        }

        if (!storage.hasOwnProperty(key)) {
            throw new Error('Key does not exists!');
        }

        delete storage[key]
    },
    clear: () => {
        storage = {};
    },
    save: () => {
        fs.writeFileSync(path, JSON.stringify(storage));
        console.log('Saved successfully!');
    },
    load: () => {
        let exists = fs.exists(path);

        if (exists) {
            storage = JSON.parse(fs.readFileSync(path));
            console.log('Loaded successfully!');
        }
    },
    loadAsync: () => {
        return new Promise((resolve, reject) => {
            let data = fs.readFile(path, (err, data) => {
                if (err) {
                    reject(err);
                    return;
                }

                storage = JSON.parse(data);
                resolve();
            })
        });
    }
}