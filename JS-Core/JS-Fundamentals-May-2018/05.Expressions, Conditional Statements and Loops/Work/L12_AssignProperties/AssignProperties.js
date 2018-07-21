function assignProperties([key1, value1, key2, value2, key3, value3, ]) {
    let keys = {
        key1:key1,
        key2:key2,
        key3:key3
    };

    return {
        [keys.key1]:value1,
        [keys.key2]:value2,
        [keys.key3]:value3};
}

assignProperties(['name', 'Pesho', 'age', '23', 'gender', 'male']);