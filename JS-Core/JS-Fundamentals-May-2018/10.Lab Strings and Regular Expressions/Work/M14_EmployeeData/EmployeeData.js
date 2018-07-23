function employeeData(data) {
    let regex = /^([A-Z][A-Za-z]*) - ([1-9][0-9]*) - ([A-Za-z0-9 -]+)$/;
    for (let employee of data) {
        let matches = regex.exec(employee);
        if (matches) {
            console.log(`Name: ${matches[1]}`);
            console.log(`Position: ${matches[3]}`);
            console.log(`Salary: ${matches[2]}`);
        }
    }
}

employeeData(['Isacc - 1000 - CEO',
    'Ivan - 500 - Employee',
    'Peter - 500 - Employee']
);