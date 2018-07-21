function triangleOfStars(count) {
    for (let i = 0; i < count; i++) {
        console.log('*'.repeat(i));
    }

    for (let i = count; i > 0; i--) {
        console.log('*'.repeat(i));
    }
}