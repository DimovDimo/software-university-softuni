const expect = require("chai").expect;

function createCalculator() {
    let value = 0;
    return {
        add: function(num) { value += Number(num); },
        subtract: function(num) { value -= Number(num); },
        get: function() { return value; }
    }
}

describe("createCalculator function tests", function () {
    let calc;
    beforeEach(function () {
        // Arrange
        calc = createCalculator();
    });

    describe('Add', function () {
        it('Should return 0 after initalization', function () {
            // Act
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(0);
        });
        it('Should return 5 after add 5', function () {
            // Act
            calc.add(5);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(5);
        });
    });

    describe('Subtract', function () {
        it('Should return -3 after subtract 3', function () {
            // Act
            calc.subtract(3);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(-3);
        });
    });

    describe('Add and Subtract', function () {
        it('Should return 7 after add 10 and subtract 3', function () {
            // Act
            calc.add(10);
            calc.subtract(3);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(7);
        });
        it('Should return 8 after add 3.5 and add 6.5 and subtract 2', function () {
            // Act
            calc.add(3.5);
            calc.add(6.5);
            calc.subtract(2);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(8);
        });
        it('Should return 0.75 after subtract 0.25 and add 1', function () {
            // Act
            calc.subtract(0.25);
            calc.add(1);
            let result = calc.get();
            // Assert
            expect(result).to.be.equal(0.75);
        });
    });

    describe('NaN', function () {
        it('Should return 5 after add {}', function () {
            // Act
            calc.add({});
            let result = calc.get();
            // Assert
            expect(result).to.be.NaN;
        });
    });
});