import createView from "../createView.js";
// import addLoginEvent from "../auth";

const POST_URI = "http://localhost:8081/api/posts";

export default function PostIndex(props) {
    return `
    <div class="container-fluid">
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => {
                return `<h3 id="title-${post.id}">${post.title}</h3>
                 <p id="content-${post.id}">${post.content}</p>
                  <button type="button" class="btn btn-success mb-3 edit-button" data-id="${post.id}">Edit</button>
                 <button type="button" class="btn btn-danger mb-3 delete-button" data-id="${post.id}">Delete</button>`
                
    }).join('')} 
            </div>
            
            <div class="container" id="add-posts">
               <form id="add-posts-form">
                <div class="mb-3">
                    <label for="add-posts-title" class="form-label">Title</label>
                    <input type="text" class="form-control" id="add-posts-title" placeholder="Enter Title">
                </div>
                <div class="mb-3">
                    <label for="add-posts-content" class="form-label">Content</label>
                    <input type="text" class="form-control" id="add-posts-content" placeholder="Enter Content">
                </div>
                <button id="add-posts-button" type="button" class="btn btn-primary mb-3">Add Post</button>
                <button id="update-posts-button" type="button" class="btn btn-secondary mb-3 update-btn">Save Post</button>
               </form>
           </div>
        </main>
    </div>
    `;
}

export function PostsEvent() {
    createAddPostListener();
    //TODO: add edit post listener function
    editListener();
    //TODO: add delete post listener function
    deleteListener();
}

function createAddPostListener() {
    console.log("adding add post listener")
    $("#add-posts-button").click(function () {
        const newPost = {
            title: $("#add-post-title").val(),
            content: $("#add-post-content").val()
        }

        const postId = $("#add-post-id").val();
        const request = {
            method: "Post",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newPost)
        }
        fetch(POST_URI, request)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
        }).finally(() => {
            createView("/posts");
        });
    });
}

function editListener(){
    $(".edit-button").click(function (){
        const postId = $(this).data("id");
        const postTitle = $(`title-${postId}`).val();
        const postContent = $(`content-${postId}`).val();
        $("#add-post-id").val(postId);
        $("#add-post-title").val(postTitle);
        $("#add-post-content").val(postContent);
    });

    $("#update-post-button").click(function (){
        const newTitle = $("#add-post-title").val();
        const newContent = $("#add-post-content").val();
        const postId = $(this).data("id");

        const updatedPost = {
            id: postId,
            title: newTitle,
            content: newContent
        }

        console.log("Ready to update:")
        console.log(updatedPost)

        const request = {
            method: "PUT",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(updatedPost)
        }
        fetch(`${POST_URI}/${postId}`, request)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
        }).finally(() => {
            createView("/posts");
        });
    });
}

function deleteListener(){
    $(".delete-button").click(function (){
        const postId = $(this).data("id");
        console.log("deleting Post: " + postId);

        const request = {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            }
        };
        fetch(`${POST_URI}/${postId}`)
            .then(res => {
                console.log("DELETE SUCCESS: " + res.status);
            }).catch(error => {
            console.log("DELETE ERROR: " + error)
        }).finally(() => {
            createView("/posts")
        });
    });
}