function imperialUnits(foot) {
    foot = Number(foot);
    let gradus = Math.floor(foot / 12);
    let seconds = foot - (gradus * 12);
    return `${gradus}'-${seconds}"`;
}