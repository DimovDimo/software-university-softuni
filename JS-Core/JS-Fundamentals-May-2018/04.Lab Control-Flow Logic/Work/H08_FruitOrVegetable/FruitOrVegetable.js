function FruitOrVegetable(input) {
// •	Fruits are: banana, apple, kiwi, cherry, lemon, grapes, peach
// •	Vegetable are: tomato, cucumber, pepper, onion, garlic, parsley

    switch (input){
        case 'banana':
        case 'apple':
        case 'kiwi':
        case 'cherry':
        case 'lemon':
        case 'grapes':
        case 'peach':
            return 'fruit';
            break;
        case 'tomato':
        case 'cucumber':
        case 'pepper':
        case 'onion':
        case 'garlic':
        case 'parsley':
            return 'vegetable';
            break;
        default:
            return 'unknown';
            break
    }
}