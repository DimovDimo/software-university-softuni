function attachEventsListeners() {
    let days = document.getElementById('days');
    let hours = document.getElementById('hours');
    let minutes = document.getElementById('minutes');
    let seconds = document.getElementById('seconds');

    document
        .getElementById('daysBtn')
        .addEventListener('click', confertDays);

    document
        .getElementById('hoursBtn')
        .addEventListener('click',confertHours);

    document
        .getElementById('minutesBtn')
        .addEventListener('click', confertMinutes);

    document
        .getElementById('secondsBtn')
        .addEventListener('click', confertSeconds);

    function confertDays() {
        let currentSeconds = Number(days.value) * 60 * 60 * 24;
        fillInputs(currentSeconds);
    }

    function confertHours() {
        let currentSeconds = Number(hours.value) * 60 * 60;
        fillInputs(currentSeconds);
    }

    function confertMinutes() {
        let currentSeconds = Number(minutes.value) * 60;
        fillInputs(currentSeconds);
    }

    function confertSeconds() {
        let currentSeconds = Number(seconds.value);
        fillInputs(currentSeconds);
    }

    function fillInputs(totalSecond) {
        console.log(totalSecond);
        seconds.value = totalSecond;
        minutes.value = totalSecond / 60;
        hours.value = totalSecond / 3600;
        days.value = totalSecond / 86400;
    }
}
