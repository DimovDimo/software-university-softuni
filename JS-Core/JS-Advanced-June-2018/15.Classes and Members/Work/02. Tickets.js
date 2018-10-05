function ticketInfo(info, sortedParameter) {
    class ticket {
        constructor(destination, price, status) {
            this.destination = destination;
            this.price = price;
            this.status = status;
        }
    }

    let dateBase = [];
    for (let i = 0; i < info.length; i++) {
        let tokens = info[i].split("|");
        dateBase.push(
            new ticket(tokens[0], +tokens[1], tokens[2]));
    }

    return dateBase.sort((a,b) => a[sortedParameter] > b[sortedParameter]);
}

ticketInfo(['Philadelphia|94.20|available',
        'New York City|95.99|available',
        'New York City|95.99|sold',
        'Boston|126.20|departed'],
    'destination');