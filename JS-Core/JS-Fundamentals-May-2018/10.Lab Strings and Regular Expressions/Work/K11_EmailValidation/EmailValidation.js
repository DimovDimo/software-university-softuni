function emailValidation(email) {
    let emailPattern = /^([A-Za-z0-9]+@[a-z]+.[a-z]+)$/g;
    if (emailPattern.test(email)){
        return 'Valid';
    } else {
        return 'Invalid';
    }
}