function addAndRemoveElements(input) {
    let rotations = Number(input.splice(input.length - 1));
    rotations %= input.length;
    for (let i = 0; i < rotations; i++) {
        input.unshift(input.pop());
    }

    console.log(input.join(' '));
}

addAndRemoveElements([
    1,
    2,
    3,
    4,
    2
]);

addAndRemoveElements([
    'Banana',
    'Orange',
    'Coconut',
    'Apple',
    15

]);