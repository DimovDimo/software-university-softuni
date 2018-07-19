function leapYear(year) {
    if (year % 400 == 0){
        return 'yes';
    } else
        if (year % 4 == 0 && year % 100 != 0){
        return 'yes';
    } else {
        return 'no';
    }
}