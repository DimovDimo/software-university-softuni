const expect = require("chai").expect;

function sum(arr) {
    let sum = 0;
    for (num of arr){
        sum += Number(num);
    }

    return sum;
}

describe("Sum function tests", function () {
    it("Should return 6 for [1,2,3]", function () {
        // Arrange
        let array = [1,2,3];
        // Act
        let result = sum(array);
        // Assert
        expect(result).to.be.equal(6);
    });
    it("Should return 1 for [1]", function () {
        // Arrange
        let array = [1];
        // Act
        let result = sum(array);
        // Assert
        expect(result).to.be.equal(1);
    });
    it("Should return 1.5 for [1,0.5]", function () {
        // Arrange
        let array = [1,0.5];
        // Act
        let result = sum(array);
        // Assert
        expect(result).to.be.equal(1.5);
    });
    it("Should return -3 for [-1,-2.5]", function () {
        // Arrange
        let array = [-1,-2.5];
        // Act
        let result = sum(array);
        // Assert
        expect(result).to.be.equal(-3);
    });
    it("Should return 0 for []", function () {
        // Arrange
        let array = [];
        // Act
        let result = sum(array);
        // Assert
        expect(result).to.be.equal(0);
    });
    it("Should return NaN for ['j']", function () {
        // Arrange
        let array = ['j'];
        // Act
        let result = sum(array);
        // Assert
        expect(result).to.be.NaN;
    });
    it("Should return NaN for 'String'", function () {
        // Arrange
        let array = 'String';
        // Act
        let result = sum(array);
        // Assert
        expect(result).to.be.NaN;
    });
});

//function subSum(arr, startIndex, endIndex) {
//    if(Object.prototype.toString.call(arr) !== '[object Array]' ) {
//        return NaN;
//    }
//
//    if (startIndex < 0){
//        startIndex = 0;
//    }
//
//    if (endIndex >= arr.length){
//        endIndex = arr.length - 1;
//    }
//
//    let sum = 0;
//    for (let i = startIndex; i <= endIndex; i++) {
//        if(Object.prototype.toString.call(arr[i]) !== '[object Number]' ) {
//            return NaN;
//        }
//
//        sum += arr[i];
//    }
//
//    return sum;
//}
//
//console.log(subSum([1.1, 2.2, 3.3, 4.4, 5.5], -3, 1));