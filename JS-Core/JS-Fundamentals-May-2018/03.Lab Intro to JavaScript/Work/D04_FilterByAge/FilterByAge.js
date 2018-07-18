function FilterByAge(minimumAge, firstPersonName, firstPersonAge, secondPersonName, secondPersonAge) {
    let firstPerson = {name:firstPersonName, age:firstPersonAge};
    let secondPerson = {name:secondPersonName, age:secondPersonAge};
    ageFilteAndPrint(minimumAge, firstPerson);
    ageFilteAndPrint(minimumAge, secondPerson);

    function ageFilteAndPrint(minimumAge, person) {
        if (person.age >= minimumAge){
            console.log(person);
        }
    }
}

FilterByAge(12, 'Ivan', 15, 'Asen', 9);