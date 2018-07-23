function findVariableNamesInSentences(text) {
    let variableNamePattern = /_([A-Za-z0-9]+)/g;
    let variable = variableNamePattern.exec(text);
    let variables = [];
    while (variable){
        variables.push(variable[1]);
        variable = variableNamePattern.exec(text);
    }

    console.log(variables.join(','));
}