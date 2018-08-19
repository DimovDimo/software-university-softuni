function getArticleGenerator(articles){
    return function () {
        if (articles.length > 0){
            $('#content')
                .append($('<article>')
                    .text(articles.shift()));
        }
    }
}

// function getArticleGenerator(articles) {
//     let counter = (function(){
//         let count = 0;
//         return function () {
//             return count++;
//         }
//     }());
//
//     let currentCount = counter();
//     if (currentCount < articles.length){
//         // let div = $('<div>');
//         let article = $('<article>');
//         article.text(articles[currentCount]);
//         // div.append(article);
//         $('#content').append(article);
//         console.log(currentCount);
//     }
// }
