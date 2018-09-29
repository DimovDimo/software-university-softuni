(function(){

    let Suits = {
        CLUBS: "\u2663",
        DIAMONDS: "\u2666",
        HEARTS: "\u2665",
        SPADES: "\u2660"
    };

    let Faces = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"];

    class Card{
        constructor(face, suit){
            this.face = face;
            this.suit = suit;
        }

        set face(f) {
            if (Faces.indexOf(f) < 0) {
                throw new Error('Invalid suit!');
            }

            this._face = f;
        }
        
        set suit(s) {
            if (Object.values(Suits).indexOf(s) < 0) {
                throw new Error('Invalid suit!');
            }
            
            this._suit = s;
        }

        get face(){
            return this._face;
        }

        get suit(){
            return this._suit;
        }

        toString(){
            return this._face + this._suit;
        }
    }

    return {
        Suits:Suits,
        Card:Card
    }
}())
