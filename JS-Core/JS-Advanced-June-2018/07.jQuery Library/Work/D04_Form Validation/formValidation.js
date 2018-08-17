function validate() {
    let username = $('#username');
    let email = $('#email');
    let password = $('#password');
    let confirmPassword = $('#confirm-password');
    let companyCheckbox = $('#company');
    let companyNumber = $('#companyNumber');
    let companyInfo = $('#companyInfo');
    let submitBtn = $('#submit');
    let validationDiv = $('#valid');
    let allIsValid = true;
    let isMoreTime = false;

    companyCheckbox.on('change', function () {
        if (companyCheckbox.is(':checked')){
            companyInfo.css('display', '');
        } else {
            companyInfo.css('display', 'none');
        }
    });

    submitBtn.on('click', function (ev) {
        ev.preventDefault();
        validateForm();
        validationDiv.css('display', allIsValid ? 'block' : 'none');
        allIsValid = true;
    });

    function validateForm() {
        let usernamePattern = /^([A-Za-z\d]){3,20}$/g;
        let emailPattern = /(^.*?@.*?\..*$)/g;
        let passwordPattern = /^(\w){5,15}$/g;
        let conPasswordPattern = /^(\w){5,15}$/g;

        validateInputWithRegex(username, usernamePattern);
        validateInputWithRegex(email, emailPattern);
        if(password.val() === confirmPassword.val()){
            validateInputWithRegex(password, passwordPattern);
            validateInputWithRegex(confirmPassword, conPasswordPattern);
        } else {
            password.css('border', '1px solid red');
            confirmPassword.css('border', '1px solid red');
            allIsValid = false;
        }

        if (companyCheckbox.is(':checked')){
            validateCompanyNumber(companyNumber);
        } else {
            companyNumber.css('border', 'none');
        }

    }

    function validateInputWithRegex(input, pattern) {
        if(pattern.test(input.val())){
            input.css('border', 'none');
        } else {
            input.css('border', '1px solid red');
            allIsValid = false;
        }
    }

    function validateCompanyNumber(compNum) {
        let num = Number(compNum.val());
        if(1000 <= num && num <= 9999){
            compNum.css('border', 'none');
        } else {
            compNum.css('border', '1px solid red');
            allIsValid = false;
        }
    }
}
