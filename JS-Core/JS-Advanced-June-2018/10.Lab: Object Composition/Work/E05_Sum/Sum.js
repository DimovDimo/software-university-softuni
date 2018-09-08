// function solution() {
//
//     let item1;
//     let item2;
//     let itemResult;
//
//     function init(sel1, sel2, result) {
//         item1 = $(sel1);
//         item2 = $(sel2);
//         itemResult = $(result);
//     }
//
//     function add() {
//         itemResult.val(Number(item1.val()) + Number(item2.val()));
//     }
//
//     function subtract() {
//         itemResult.val(Number(item1.val()) - Number(item2.val()));
//     }
//
//     return {init, add, subtract};
// }
//
// let obj = solution();
// function sum() {
//     obj.init('#num1', '#num2', '#result',);
//     obj.add();
// }
//
// function sub() {
//     obj.init('#num1', '#num2', '#result',);
//     obj.subtract();
// }