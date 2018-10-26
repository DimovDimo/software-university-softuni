function attachEvents() {
    const URL = 'https://baas.kinvey.com/appdata/kid_BJ5mf9k4m/'
    const USERNAME = 'Peter'
    const PASSWORD = 'p'
    const BASE_64 = btoa(USERNAME + ':' + PASSWORD)
    const AUTH = {"Authorization": 'Basic ' + BASE_64}
    const SELECT = $('#posts')
    const TITLE = $("#post-title")
    const BODY = $('#post-body')
    const COMMENTS = $("#post-comments")

    $('#btnLoadPosts').on('click', loadPosts)
    $('#btnViewPost').on('click', viewPosts)

    function loadPosts() {
        $.ajax({
            method: "GET",
            url: URL + 'posts',
            headers: AUTH
        }).then(function (res) {
            for (const post of res) {
                SELECT.append(
                    $(`<option id="${post._id}" body="${post.body}">${post.title}</option>`))
            }
        }).catch(function (err) {
            console.log(err)
        })
    }
    
    function viewPosts() {
        let selectedElement = SELECT.find(":selected")
        let id = selectedElement.attr("id")
        $.ajax({
            method: "GET",
            url: URL + `comments/?query={"post_id":"${id}"}`,
            headers: AUTH
        }).then(function (res) {
            BODY.empty()
            COMMENTS.empty()
            let value = selectedElement.text()
            let body = selectedElement.attr('body')
            TITLE.text(value)
            BODY.append($(`<li>${body}</li>`))
            let id = selectedElement.attr("id")
            for (const comment of res) {
                COMMENTS.append($(`<li>${comment.text}</li>`))
            }
        }).catch(function (err) {
            console.log(err)
        })
    }
}