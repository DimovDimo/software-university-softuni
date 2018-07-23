function restaurantBill(bill) {
    let products = bill.filter((product, index) => index % 2 === 0);
    let sum = 0;
    bill.filter((product, index) => index % 2 !== 0)
        .forEach(price => sum += Number(price));

    console.log(`You purchased ${products.join(', ')} for a total sum of ${sum}`);
}