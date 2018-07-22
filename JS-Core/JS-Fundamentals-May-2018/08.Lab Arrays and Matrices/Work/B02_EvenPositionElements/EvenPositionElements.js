function evenPositionElements(arrInput) {
    let output = [];
    for (let i = 0; i < arrInput.length; i++) {
        if (i % 2 === 0){
            output.push(arrInput[i]);
        }
    }

    console.log(output.join(' '));
}