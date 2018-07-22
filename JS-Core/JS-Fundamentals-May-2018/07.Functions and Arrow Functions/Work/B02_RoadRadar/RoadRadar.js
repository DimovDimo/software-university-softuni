function roadRadar([speed, place]) {

    let limit = function (place) {
        switch (place){
            case 'motorway': return 130;
            case 'interstate': return 90;
            case 'city': return 50;
            case 'residential': return 20;
        }
    };

    let message = function (speed, limit, place) {
        let diff = speed - limit(place);
        if (diff <= 0){
            return '';
        } else if (diff <= 20){
            return 'speeding';
        } else if (diff <= 40){
            return 'excessive speeding ';
        } else {
            return 'reckless driving';
        }
    }

    console.log(message(speed, limit, place));
}

roadRadar([40, 'city']);
roadRadar([21, 'residential']);