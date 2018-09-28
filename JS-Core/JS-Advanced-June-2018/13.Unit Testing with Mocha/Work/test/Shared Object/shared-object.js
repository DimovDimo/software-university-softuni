let chai = require('chai');
let jsdom = require('jsdom-global')();
let $ = require('jquery');
let expect = chai.expect;

document.body.innerHTML = `<div id="wrapper">
        <input type="text" id="name">
        <input type="text" id="income">
    </div>`;


let sharedObject = {
    name: null,
    income: null,
    changeName: function (name) {
        if (name.length === 0) {
            return;
        }
        this.name = name;
        let newName = $('#name');
        newName.val(this.name);
    },
    changeIncome: function (income) {
        if (!Number.isInteger(income) || income <= 0) {
            return;
        }
        this.income = income;
        let newIncome = $('#income');
        newIncome.val(this.income);
    },
    updateName: function () {
        let newName = $('#name').val();
        if (newName.length === 0) {
            return;
        }
        this.name = newName;
    },
    updateIncome: function () {
        let newIncome = $('#income').val();
        if (isNaN(newIncome) || !Number.isInteger(Number(newIncome)) || Number(newIncome) <= 0) {
            return;
        }
        this.income = Number(newIncome);
    }
};

describe('sharedObject Unit Tests', function () {
    describe('Initial value tests', function () {
        it('should be null', function () {
            expect(sharedObject.name).to.be.null;
        });

        it('should be null', function () {
            expect(sharedObject.income).to.be.null;
        });
    });

    describe('changeName', function () {
        it('Pass empty string, should not change', function () {
            sharedObject.changeName('');
            expect(sharedObject.name).to.be.equal(null);
        });

        it('Pass empty string with preexisting value', function () {
            sharedObject.name = 'A';
            sharedObject.changeName('');
            expect(sharedObject.name).to.be.equal('A');
        });

        it('change name', function () {
            sharedObject.changeName('B');
            expect(sharedObject.name).to.be.equal('B');
        });
    });

    describe('changeName textBox tests', function () {
        it('Pass empty string', function () {
            let nameTxt = $('#name');
            sharedObject.changeName('');
            expect(nameTxt.val()).to.be.equal('B');
        });
    });
});