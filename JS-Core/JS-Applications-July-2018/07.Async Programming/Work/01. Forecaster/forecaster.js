function attachEvents() {
    let forecastDiv = $('#forecast');
    let symbol;

    $('#submit').click(function () {
        $.get('https://judgetests.firebaseio.com/locations.json')
            .then(loadData)
            .catch(handleError)
    });

    function loadData(res) {
        let input = $('#location').val();
        $('#location').val('');

        let urlForecast = `https://judgetests.firebaseio.com/forecast/today/`;
        let urlUpcoming = `https://judgetests.firebaseio.com/forecast/upcoming/`;

        for (let obj of res) {
            if (input === obj.name) {
                urlForecast += `${obj.code}.json `;
                urlUpcoming += `${obj.code}.json `;
                break;
            }
        }

        $.get(urlForecast).then(createElement).catch(handleError);
        $.get(urlUpcoming).then(getUpcoming).catch(handleError);
    }

    function createElement(res) {
        symbol = res.forecast.condition === "Sunny" ? '&#x2600;' : res.forecast.condition === "Partly sunny" ? '&#x26C5;' : res.forecast.condition === "Overcast" ? '&#x2601;' : '&#x2614;';

        $('#current').empty().append($('<div class="label">Current conditions</div>'));

        let innerSpan = $('<span class="condition"></span>')
            .append($(`<span class="forecast-data">${res.name}</span>`))
            .append($(`<span class="forecast-data">${res.forecast.low}&#176;/${res.forecast.high}&#176;</span>`))
            .append($(`<span class="forecast-data">${res.forecast.condition}</span>`));

        $('#current').append($(`<span class="condition symbol">${symbol}</span>`))
            .append(innerSpan);

        forecastDiv.attr('style', 'display:block');
    }

    function getUpcoming(res) {
        let upcoming = $('#upcoming').empty().append($('<div class="label">Three-day forecast</div>'));
        for (let obj of res.forecast) {
            symbol = obj.condition === "Sunny" ? '&#x2600;' : obj.condition === "Partly sunny" ? '&#x26C5;' : obj.condition === "Overcast" ? '&#x2601;' : '&#x2614;';

            upcoming.append($('<span class="upcoming">')
                .append($(`<span class="symbol">${symbol}</span>`))
                .append($(`<span class="forecast-data">${obj.low}&#176;/${obj.high}&#176;</span>`))
                .append($(`<span class="forecast-data">${obj.condition}</span>`)))
        }
    }

    function handleError(err) {
        forecastDiv.empty();
        forecastDiv.attr('style', 'display:block').text(err.statusText);
    }
}