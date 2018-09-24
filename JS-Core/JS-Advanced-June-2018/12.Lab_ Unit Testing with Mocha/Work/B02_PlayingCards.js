function playingCards(card, suit) {
    const VALID_CARDS = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"];
    const VALID_SUITS = {
        'S': '\u2660',
        'H': '\u2665',
        'D': '\u2666',
        'C': '\u2663'
    };

    if (VALID_CARDS.includes(card) &&
    VALID_SUITS.hasOwnProperty(suit)) {
        return {
            card: card,
            suit: suit,
            toString: () => `${card}${VALID_SUITS[suit]}`
        }
    } else {
        throw new Error("Error");
    }
}