function palindrome(input) {
    return input === input.split('').reverse().join('');
}