function deckOfCards(desk) {
    function makeCard(card, suit) {
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


    let output = [];
    for (let deskElement of desk) {
        try {
            let card = deskElement.substr(0, deskElement.length - 1);
            let suit = deskElement[deskElement.length - 1];
            // debugger;
            output.push('' + makeCard(card, suit));
        } catch (e) {
            console.log('Invalid card: ' + deskElement);
            return;
        }
    }

    console.log(output.join(' '));
}

deckOfCards(['AS', '10D', 'KH', '2C']);
deckOfCards(['5S', '3D', 'QD', '1C']);