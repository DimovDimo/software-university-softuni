class CheckingAccount {
    constructor(clientId, email, firstName, lastName){
        this._clientId = clientId;
        this._email = email;
        this._firstName = firstName;
        this._lastName = lastName;
    }


    get clientId() {
        return this._clientId;
    }

    get email() {
        return this._email;
    }

    get firstName() {
        return this._firstName;
    }

    get lastName() {
        return this._lastName;
    }

    set clientId(value) {
        if (!/^\d{6}$/g.test(value)){
            throw new TypeError('Client ID must be a 6-digit number');
        }

        this._clientId = value;
    }

    set email(value) {
        if (!/^[A-Za-z0-9]+@[A-Za-z.]+$/g.test(value)){
            throw new TypeError('Invalid e-mail');
        }

        this._email = value;
    }

    set firstName(value) {
        if (!/^.{3,20}$/g.test(value)){
            throw new TypeError('First name must be between 3 and 20 characters long');
        }

        if (!/^[A-Za-z]+$/g.test(value)){
            throw new TypeError('First name must contain only Latin characters');
        }

        this._firstName = value;
    }

    set lastName(value) {
        if (!/^.{3,20}$/g.test(value)){
            throw new TypeError('Last name must be between 3 and 20 characters long');
        }

        if (!/^[A-Za-z]+$/g.test(value)){
            throw new TypeError('Last name must contain only Latin characters');
        }

        this._lastName = value;
    }
}