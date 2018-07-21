function lastMonth([day, mounth, year]) {
    let date = new Date(year, mounth-1, 0);
    return date.getDate();
}