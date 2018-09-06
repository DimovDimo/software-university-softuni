function personalBMI(name, age, weight, height) {
    let person = {};
    person['name'] = name;
    let personalInfo = {
        'age': +age,
        'weight': +weight,
        'height': +height
    };

    person['personalInfo'] = personalInfo;
    let heichtMeters = +height / 100;
    let bmi = Math.round(+weight / (heichtMeters * heichtMeters));
    person['BMI'] = bmi;
    // underweight, for BMI less than 18.5;
    // normal, for BMI less than 25;
    // overweight, for BMI less than 30;
    // obese, for BMI 30 or more;

    if (bmi < 18.5){
        person['status'] = 'underweight';
    } else if (bmi < 25){
        person['status'] = 'normal';
    } else if (bmi < 30){
        person['status'] = 'overweight';
    } else {
        person['status'] = 'obese';
        person['recommendation'] = 'admission required';
    }

    return person;
}